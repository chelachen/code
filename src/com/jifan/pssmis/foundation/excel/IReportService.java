
package com.jifan.pssmis.foundation.excel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import jxl.write.WriteException;

import org.dom4j.DocumentException;

import com.jifan.pssmis.foundation.excel.style.BodyContent;
import com.jifan.pssmis.foundation.excel.style.Column;
import com.jifan.pssmis.foundation.excel.style.Field;
import com.jifan.pssmis.foundation.excel.style.StyleSheet;
import com.jifan.pssmis.foundation.exception.BizException;





/**
 * 
 * @author jifan
 *
 */
public interface IReportService {
	/**
	 * 暂时未实现
	 *功能描述：
	 *参数说明：
	 * @param title
	 * @param columnTiles
	 * @param columns
	 * @param mapList
	 * @return
	 * @throws BizException
	 */
	public ByteArrayOutputStream createExcelReport (String title,String [] columnTiles,String [] columns,List mapList) throws BizException ;
	/**
	 * 
	 *功能描述：
	 *参数说明：
	 * @param title
	 * @param columnTiles
	 * @param properties
	 * @param beanList
	 * @return
	 * @throws BizException
	 */
	public ByteArrayOutputStream createExcelReport2 (String title,String [] columnTiles,String [] properties,List beanList) throws BizException ;
	/**
	 * 
	 *功能描述：
	 *参数说明：
	 * @param title
	 * @param columnTiles
	 * @param properties
	 * @param beanList
	 * @param showTime
	 * @return
	 * @throws BizException
	 */
	public ByteArrayOutputStream createExcelReport2 (String title,String [] columnTiles,String [] properties,List beanList,boolean showTime) throws BizException ;
	
	/**
	 * 
	 * 功能描述：返回EXCEL报表输出流，目前没有将样式配成模板，有待升级。。。 参数说明：
	 * 
	 * @param title
	 *            报表标题
	 * @param columnTiles
	 *            显示列标题
	 * @param properties
	 *            显示的属性
	 * @param dataList
	 *            bean列表
	 * @param showTime
	 *            是否显示时间
	 * @param units
	 *            显示的单位
	 * @return
	 * @throws BizException
	 */
	public ByteArrayOutputStream createExcelReport2(String title,
			String[] columnTiles, String[] properties, List dataList,
			boolean showTime, String units) throws BizException;
	/**
	 * 
	 *功能描述：
	 *参数说明：
	 * @param dataList
	 * @param template
	 * @return
	 * @throws BizException
	 */
	//public ByteArrayOutputStream createExcelReport2 (List dataList,ReportTemplate template) throws BizException ;
	/**
	 * 
	 *功能描述：模板核心方法
	 *参数说明：
	 * @param template
	 * @return
	 * @throws BizException
	 * @throws IOException
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public ByteArrayOutputStream createExcelReport2 (ReportTemplate template) throws BizException ,IOException ,WriteException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException,DocumentException;
	
	/**
	 * 
	 *功能描述：模板核心方法
	 *参数说明：
	 * @param template
	 * @return
	 * @throws BizException
	 * @throws IOException
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public ByteArrayOutputStream createExcelReportMulSheet (List templates) throws BizException ,IOException ,WriteException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException,DocumentException;
	
	/**
	 * 导入Excel(暂不支持带String以外类型的bo)
	 * @param fileName 上传后的Excel文件名（带路径）
	 * @param bean　导入数据的bo名
	 * @param properties　要插入数据的bo属性名集合
	 * @param columnTiles　要读取的Excel列的序号集合
	 * @return 返回从Excel读取数据的bo集合
	 * @throws BizException
	 */
	public Set readExcelReport(String fileName,Class bean,String [] properties)throws BizException;
	
	/**
	 * 导入Excel
	 * 
	 * @param fileName
	 *            上传后的Excel文件名（带路径）
	 * @param bean
	 *            导入数据的bo名
	 * @param properties
	 *            要插入数据的bo属性名集合
	 * @param startRow
	 *            起始行
	 * @return 返回从Excel读取数据的bo集合
	 * @throws BizException
	 * chelachen 2010-05-06
	 */
	public Set readExcelReport2(String fileName, Class bean, String[] properties, int startRow) throws BizException;
	
	/**
	 * 
	 *功能描述：跑批数据查询
	 * @return
	 * @throws BizException
	 */
	public String queryRunData() throws BizException ;
	/**
	 * 
	 *功能描述：模板核心方法
	 *参数说明：
	 * @param template
	 * @param coloums
	 * @return
	 * @throws BizException
	 * @throws IOException
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	public ByteArrayOutputStream createExcelReport3(List<Column> coloums,List<Field> headerFields,List<Field> footerFields,
			ReportTemplate template) throws BizException, IOException,
			WriteException, IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, DocumentException;
	public ByteArrayOutputStream createExcelReport4(String titleName,String sheetName,List<Column> coloums,List<Field> fields,ReportTemplate template)
	throws BizException, IOException, WriteException,
	IllegalAccessException, NoSuchMethodException,
	InvocationTargetException, DocumentException ;
}
