package com.jifan.pssmis.foundation.excel.style;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.message.SysMessage;
import com.jifan.pssmis.foundation.util.ExpressionEvaluatorUtil;




/**
 * 
 * @author jifan
 *
 */
public class Row {
	private static Log log = LogFactory.getLog(Row.class);
	private int index;			//行索引
	private int height;			//行高
	
	private String value;		//支持混合表达式
	private String var;			//循环变量名称
	private String indexVar;	//循环索引
	private List fields = new ArrayList();
	
	/********* start********************/
	private int rowStep;		//行的步长
	private int colStep;		//列的步长
	/********* end**********************/
	
	/*********** start**************/
	//控制行是否有效，如果无效则不显示。
	private String disable;		//支持表达式，可动态计算
	
	private boolean loop;	//标志是否采用循环输出，主要用于循环时记住当前行数。
	/*********** end**************/
	/**
	 * 
	 *功能描述：	判断行是否有效
	 * @return	如果表达式返回 true 或 "true" 时则有效，否则无效。
	 */
	public boolean isDisabled() {
		Object rtnVal = ExpressionEvaluatorUtil.getExprValue(disable);
		String val = rtnVal == null ? "false" : rtnVal.toString();
		return Boolean.valueOf(val.trim()).booleanValue();
	}
	
	/*********** end**************/
	
	/**
	 * 
	 *功能描述：获取真实域
	 *				即如果<row>中设置了行索引：index，则以其为主；否则，以<field>中设置的rowIndex为主
	 *				value中存在表达式，则计算表达式的值；当存在循环时，计算出所有的Field进行缓存
	 * @return	返回真实域列表
	 * @throws BizException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public List getRealFields() throws BizException,
										NoSuchMethodException,
										IllegalAccessException,
										InvocationTargetException {
		 
		List realFields = this.getFields();
		//设置索引
		if(index > 0) {
			for(Iterator ite = realFields.iterator();ite.hasNext();) {
				Field field = (Field)ite.next();
				/********* start *************/
				if(!this.isLoop()) {
					field.setRowIndex(String.valueOf(index));
				}
				/********* end *************/
			}
		}
		
		/*************************** start ************************************/
		/*if(!this.isLoop()) {
			//如果没有循环标志，则不循环处理
			return realFields;
		}*/
		/*************************** end ************************************/
		
		//当存在循环时，计算出所有的Field进行缓存
		Object val = this.getRealValue();
		if(val instanceof List) {
			List valList = (List)val;
			
			//缓存大小
			int size = this.getFields().size() * valList.size();
			//缓存域。
			List cacheFields = new ArrayList(size);
			
			for(int i = 0;i < valList.size();i ++) {
				//循环每个对象，设置Field
				Object obj = valList.get(i);
				for(int k = 0;k < realFields.size();k ++) {
					Field newField = new Field();
					Field field = (Field)realFields.get(k);
					//域拷贝
					PropertyUtils.copyProperties(newField,field);
					
					//忽略空格
					String valstr = field.getValue() == null ? "" : field.getValue().trim();
					
					if(log.isDebugEnabled()) {
						log.debug("=======Row.java 中 valstr=" + valstr);
					}
					
					/***************************替换索引变量开始********************************/
					if(indexVar == null) {
						indexVar = "";
					}
					if(indexVar.length() > 0 && valstr.indexOf(indexVar) > 0) {
						//替换索引变量，例如：${index + 1} --> ${ i(具体值) + 1}
						valstr = valstr.replaceAll(indexVar,String.valueOf(i));
						
						if(log.isDebugEnabled()) {
							log.debug("=======Row :替换后的表达式2=" + valstr);
						}
					}
					/***************************替换索引变量结束********************************/
					
					//匹配混合表达式，分两种情况：
					//	1、对含有循环变量var的表达式进行（1、计算循环变量值）；
					//	2、对含有索引变量indexVar的表达式进行（2、计算索引变量值）；
					
					Pattern p = Pattern.compile(ExpressionEvaluatorUtil.REG);
					Matcher m = p.matcher(valstr);
					StringBuffer realVal = new StringBuffer();
					
					while (m.find()) {
						String valexpr = m.group();
						
						/***************************1、计算循环变量值*********************************************/
						if(var == null) {
							var = "";
						}
						if(var.length() > 0 && valexpr.indexOf(var) > 0) {
							//取出对象的属性值，例如：${file.fileName} ---> fileName
							if(valexpr.indexOf(".") > 0 && valexpr.indexOf("}") == valexpr.length() - 1) {
								String prop = valexpr.substring(valexpr.indexOf(var) + var.length() + 1,valexpr.length() - 1);
								
								if(log.isDebugEnabled()) {
									log.debug("=======Row :表达式=" + valexpr);
									log.debug("=======Row :变量=" + var);
									log.debug("=======Row :属性=" + prop);
								}
								
								Object propVal = PropertyUtils.getProperty(obj,prop);
								
								m.appendReplacement(realVal,String.valueOf(propVal));
							}else {
								throw new BizException(new SysMessage("表达式错误：" + valexpr));
							}
						}else {
							/********************************2、计算索引变量值****************************************/
							String indexVal = ExpressionEvaluatorUtil.getValue(valexpr);
							
							m.appendReplacement(realVal,String.valueOf(indexVal));
						}
					}
					m.appendTail(realVal);
					
					//设置域的计算后的真实值
					newField.setValue(realVal.toString());
					//newField.setColIndex(i + k + 1);
					
					/*****************循环情况 计算索引值***********************/
					
					//设置域的真实列索引值
					String colIndex = newField.getColIndex();
					if(colIndex != null && colIndex.indexOf(indexVar) > 0) {
						if(log.isDebugEnabled()) {
							log.debug("=====设置域的真实列索引值之前的值：" + colIndex);
						}
						if(colStep == 0) {
							//列步长默认为1
							this.setColStep(1);
						}
						colIndex = colIndex.replaceAll(indexVar,String.valueOf(i * colStep));
						if(log.isDebugEnabled()) {
							log.debug("=====设置域的真实列索引值之后的值：" + colIndex);
						}
						String colIndexVal = ExpressionEvaluatorUtil.getValue(colIndex);
						if(log.isDebugEnabled()) {
							log.debug("=====设置域的真实列索引值之后的计算值：" + colIndexVal);
						}
						newField.setColIndex(colIndexVal);
					}
					
					//设置域的真实行索引值
					String rowIndex = newField.getRowIndex();
					
					if(rowIndex != null && rowIndex.indexOf(indexVar) > 0) {
						if(log.isDebugEnabled()) {
							log.debug("=====设置域的真实行索引值之前的值：" + rowIndex);
						}
						if(rowStep == 0) {
							//行步长默认为1
							this.setRowStep(1);
						}
						rowIndex = rowIndex.replaceAll(indexVar,String.valueOf(i * rowStep));
						if(log.isDebugEnabled()) {
							log.debug("=====设置域的真实行索引值之后的值：" + rowIndex);
						}
						String rowIndexVal = ExpressionEvaluatorUtil.getValue(rowIndex);
						if(log.isDebugEnabled()) {
							log.debug("=====设置域的真实行索引值之后的计算值：" + rowIndexVal);
						}
						
						/*******************start *********************/
						//循环时，记住当前行数
						if(this.isLoop()) {
							rowIndexVal = String.valueOf(this.getIndex() + Integer.valueOf(rowIndexVal).intValue());
						}
						
						if(log.isDebugEnabled()) {
							log.debug("==========当前行数：" + this.getIndex());
							log.debug("==========第" + (i + 1) + "次循环之后的行数：" + rowIndexVal);
						}
						/******************* end ***********************/
						
						newField.setRowIndex(rowIndexVal);
					}
					/***************** end*******************************************/
					
					//进行缓存
					cacheFields.add(newField);
				}
			}
			return cacheFields;
		}else {
			return realFields;
		}
	}
	/*************** start ******************/
	/**
	 * 
	 *功能描述：获取循环的次数
	 * @return
	 */
	public int getLoopCount() {
		if(!this.isLoop()) {
			return 0;
		}
		Object val = this.getRealValue();
		if(val instanceof List) {
			List valList = (List)val;
			return valList.size();
		}
		return 0;
	}
	/*************** start ******************/
	/**
	 * 
	 *功能描述：	获取真实表达式的值
	 * @return	真实表达式的值
	 */
	public Object getRealValue() {
		return ExpressionEvaluatorUtil.getExprValue(value);
	}
	/**
	 * @return Returns the fields.
	 */
	public List getFields() {
		return fields;
	}
	/**
	 * @param fields The fields to set.
	 */
	public void setFields(List fields) {
		this.fields = fields;
	}
	/**
	 * @return Returns the height.
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height The height to set.
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return Returns the index.
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index The index to set.
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return Returns the indexVar.
	 */
	public String getIndexVar() {
		return indexVar;
	}
	/**
	 * @param indexVar The indexVar to set.
	 */
	public void setIndexVar(String indexVar) {
		this.indexVar = indexVar;
	}
	/**
	 * @return Returns the var.
	 */
	public String getVar() {
		return var;
	}
	/**
	 * @param var The var to set.
	 */
	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * @return Returns the rowStep.
	 */
	public int getRowStep() {
		return rowStep;
	}

	/**
	 * @param rowStep The rowStep to set.
	 */
	public void setRowStep(int rowStep) {
		this.rowStep = rowStep;
	}

	/**
	 * @return Returns the colStep.
	 */
	public int getColStep() {
		return colStep;
	}

	/**
	 * @param colStep The colStep to set.
	 */
	public void setColStep(int colStep) {
		this.colStep = colStep;
	}

	/**
	 * @return Returns the disable.
	 */
	public String getDisable() {
		return disable;
	}

	/**
	 * @param disable The disable to set.
	 */
	public void setDisable(String disable) {
		this.disable = disable;
	}

	/**
	 * @return Returns the loop.
	 */
	public boolean isLoop() {
		return loop;
	}

	/**
	 * @param loop The loop to set.
	 */
	public void setLoop(boolean loop) {
		this.loop = loop;
	}
	
}
