package com.jifan.pssmis.foundation.excel;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.jifan.pssmis.foundation.excel.style.BodyContent;
import com.jifan.pssmis.foundation.excel.style.Column;
import com.jifan.pssmis.foundation.excel.style.ColumnHeader;
import com.jifan.pssmis.foundation.excel.style.DateTime;
import com.jifan.pssmis.foundation.excel.style.Field;
import com.jifan.pssmis.foundation.excel.style.PageFooter;
import com.jifan.pssmis.foundation.excel.style.PageHeader;
import com.jifan.pssmis.foundation.excel.style.Row;
import com.jifan.pssmis.foundation.excel.style.Rownum;
import com.jifan.pssmis.foundation.excel.style.StyleSheet;
import com.jifan.pssmis.foundation.excel.style.Title;
import com.jifan.pssmis.foundation.excel.style.Virtualcol;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.message.SysMessage;
import com.jifan.pssmis.foundation.util.BeanUtil;





/**
 * 报表引擎类
 * @author jifan
 *
 */
public class ReportEngine {
	private static Log log = LogFactory.getLog(ReportEngine.class);
	
	/**
	 * 
	 * 不用指定行索引，引擎会自动按顺序读取并按顺序输出
	 *功能描述：按顺序解析模板
	 * @param template
	 * @return
	 * @throws BizException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws DocumentException
	 */
	public static StyleSheet parseBySequence(ReportTemplate template) throws BizException,IllegalAccessException,InvocationTargetException,DocumentException {
		String config = template.getConfig();
		String templateId = template.getTemplateId();
		
		//加载报表配置文件
		XMLParser.load(config);
		
		//读取报表模板配置文件，并将其解析成相应的对象
		Element templateE = XMLParser.getNodeById(templateId);
		
		if(templateE != null) {
			Element sheetE = templateE.element("style-sheet");
			if(sheetE != null) {
				StyleSheet style = new StyleSheet();
				assemble(style,sheetE);
				
				for(Iterator sheetE_ite = sheetE.elementIterator();sheetE_ite.hasNext();) {
					Element e = (Element)sheetE_ite.next();
					String eName = e.getName();
					if(eName != null) {
						Element titleE = e;
						if(eName.equals("title")) {
							Title title = new Title();
							assemble(title,titleE);
							
							//初始化标题域Field
							Iterator ite = titleE.elementIterator("field");
							while(ite.hasNext()) {
								Element fieldE = (Element)ite.next();
								if(fieldE != null) {
									Field field = new Field();
									assemble(field,fieldE);
									if(field.isDisabled()) {
										//无效域不显示
										continue;
									}
									
									title.getFields().add(field);
								}
							}
							style.setTitle(title);
						}else if (eName.equals("page-header")) {
							//初始化PageHeader
							Element pageHeaderE = e;
							PageHeader pageHeader = new PageHeader();
							assemble(pageHeader,pageHeaderE);
							
							for(Iterator pageHeaderE_ite = pageHeaderE.elementIterator();pageHeaderE_ite.hasNext();) {
								Element e2 = (Element)pageHeaderE_ite.next();
								String e2Name = e2.getName();
								if(e2Name != null) {
									//初始化Field
									if(e2Name.equals("field")) {
										Element fieldE = e2;
										Field field = new Field();
										assemble(field,fieldE);
										if(field.isDisabled()) {
											//无效域不显示
											continue;
										}
										pageHeader.getFields().add(field);
									}else if (e2Name.equals("date-time")) {
										//初始化DateTime
										Element dateTimeE = e2;
										if(dateTimeE != null) {
											DateTime dateTime = new DateTime();
											assemble(dateTime,dateTimeE);
											pageHeader.getFields().add(dateTime);
										}
									}
								}
							}
							style.setPageHeader(pageHeader);
						}else if(eName.equals("body-content")) {
							//初始化BodyContent
							Element bodyContentE = e;
							BodyContent bodyContent = new BodyContent();
							assemble(bodyContent,bodyContentE);
							
							//初始化内容体的当前行索引值
							bodyContent.setCurrentRow(bodyContent.getRowIndex());

							
							/*****************************适用于列表 **********************************************/
							//初始化ColumnHead
							Element columnHeaderE = bodyContentE.element("column-header");
							if(columnHeaderE != null) {
								ColumnHeader columnHeader = new ColumnHeader();
								assemble(columnHeader,columnHeaderE);
								bodyContent.setColumnHeader(columnHeader);
							}
							//初始化Rownum
							Element rownumE = bodyContentE.element("rownum");
							if(rownumE != null) {
								Rownum rownum = new Rownum();
								assemble(rownum,rownumE);
								//根据索引号对应
								bodyContent.setRownum(rownum);
							}
							//初始化Column
							for(Iterator iterator = bodyContentE.elementIterator("column");iterator.hasNext();) {
								Element columnE = (Element)iterator.next();
								if(columnE != null) {
									Column column = new Column();
									assemble(column,columnE);
									
									//根据索引号对应
									if(column.isDisabled()) {
										//排除无效列
										continue;
									}
									try {
										bodyContent.getColumns().add(column.getIndex(),column);
									} catch(IndexOutOfBoundsException ioobe) {
										throw new BizException(new SysMessage("请为<column index=\"\"/>元素中的index属性设置正确的索引值！"));
									}
								}
							}
	
							//初始化virtualcol
							for(Iterator iterator = bodyContentE.elementIterator("virtualcol");iterator.hasNext();) {
								Element virtualcolE = (Element)iterator.next();
								if(virtualcolE != null) {
									Virtualcol virtualcol = new Virtualcol();
									assemble(virtualcol,virtualcolE);
									
									if(virtualcol.isDisabled()) {
										//排除无效虚拟列
										continue;
									}
									bodyContent.getVirtualcolList().add(virtualcol);
								}
							}
							
							
							/*****************************适用于详细信息 ********************************************/
							for(Iterator bodyContentE_ite = bodyContentE.elementIterator();bodyContentE_ite.hasNext();) {
								Element e3 = (Element)bodyContentE_ite.next();
								String e3Name = e3.getName();
								if(e3Name != null) {
									if(e3Name.equals("field")) {
										Element fieldE = e3;
										if(fieldE != null) {
											Field field = new Field();
											assemble(field,fieldE);
											if(field.isDisabled()) {
												//无效域不显示
												continue;
											}
											
											
											if(field.getRealRowIndex() > 0) {
												//更新当前行索引值
												bodyContent.setCurrentRow(field.getRealRowIndex());
												if(log.isDebugEnabled()) {
													log.debug("==========(field)解析报表模板时获取的当前行索引值=" + field.getRealRowIndex());
												}
											}else {
												int curRow = bodyContent.getCurrentRow() + 1;
												bodyContent.setCurrentRow(curRow);
												field.setRowIndex(String.valueOf(curRow));
											}
											
											
											bodyContent.getFields().add(field);
										}
									}else if (e3Name.equals("row")) {
										Element rowE = e3;
										if(rowE != null) {
											Row row = new Row();
											assemble(row,rowE);
											
											//初始化Field
											for(Iterator ite5 = rowE.elementIterator("field");ite5.hasNext();) {
												Element fieldE = (Element)ite5.next();
												if(fieldE != null) {
													Field field = new Field();
													assemble(field,fieldE);
													if(field.isDisabled()) {
														//无效域不显示
														continue;
													}
													row.getFields().add(field);
												}
											}
											
										
											if(row.isDisabled()) {
												//无效行不显示
												continue;
											}
											
											//当前行索引值
											int curRow = row.getIndex();
											
											if(curRow == 0 ) {
												//按顺序号设定行索引值
												curRow = bodyContent.getCurrentRow() + 1;
												row.setIndex(curRow);
											}
											if(row.isLoop()) {
												//循环时，计算循环行数
												int loopRows = row.getLoopCount() * row.getRowStep();
												if(log.isDebugEnabled()) {
													log.debug("========循环次数=" + row.getLoopCount());
													log.debug("========循环步长=" + row.getRowStep());
													log.debug("========循环行数=" + loopRows);
												}
												curRow += loopRows;
											}
											bodyContent.setCurrentRow(curRow);
											
											if(log.isDebugEnabled()) {
												log.debug("===========(row)解析报表模板时获取的当前行索引值=" + curRow);
											}
											
											
											bodyContent.getRows().add(row);
										}
									}
								}
							}
							
							style.setBodyContent(bodyContent);
						}else if(eName.equals("page-footer")) {
							//初始化PageFooter
							Element pageFooterE = e;
							PageFooter pageFooter = new PageFooter();
							assemble(pageFooter,pageFooterE);
							
							for(Iterator pageFooterE_ite = pageFooterE.elementIterator();pageFooterE_ite.hasNext();) {
								//初始化Field
								Element e4 = (Element)pageFooterE_ite.next();
								String e4Name = e4.getName();
								if(e4Name.equals("field")) {
									Element fieldE = e4;
									if(fieldE != null) {
										Field field = new Field();
										assemble(field,fieldE);
										if(field.isDisabled()) {
											//无效域不显示
											continue;
										}
										pageFooter.getFields().add(field);
									}
								}
							}
							style.setPageFooter(pageFooter);
						}
					}
				}
				return style;
			}
		}else {
			log.error("配置文件" + config + "中，ID为：" + templateId + "的模板不存在！");
			throw new BizException (new SysMessage("配置文件" + config + "中，ID为：" + templateId + "的模板不存在！"));
		}
		return null;
	}
	/**
	 * 暂时不用
	 *功能描述： 报表引擎 解析模板
	 * @param template
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static StyleSheet parse(ReportTemplate template) throws BizException,IllegalAccessException,InvocationTargetException,DocumentException {
		String config = template.getConfig();
		String templateId = template.getTemplateId();
		
		//加载报表配置文件
		XMLParser.load(config);
		
		//读取报表模板配置文件，并将其解析成相应的对象
		Element templateE = XMLParser.getNodeById(templateId);
		
		if(templateE != null) {
			Element sheetE = templateE.element("style-sheet");
			if(sheetE != null) {
				StyleSheet style = new StyleSheet();
				assemble(style,sheetE);
				//初始化Title
				Element titleE = sheetE.element("title");
				if(titleE != null) {
					Title title = new Title();
					assemble(title,titleE);
					
					//初始化标题域Field
					Iterator ite = titleE.elementIterator("field");
					while(ite.hasNext()) {
						Element fieldE = (Element)ite.next();
						if(fieldE != null) {
							Field field = new Field();
							assemble(field,fieldE);
							if(field.isDisabled()) {
								//无效域不显示
								continue;
							}
							
							title.getFields().add(field);
						}
					}
					
					style.setTitle(title);
				}
				//初始化PageHeader
				Element pageHeaderE = sheetE.element("page-header");
				if(pageHeaderE != null) {
					PageHeader pageHeader = new PageHeader();
					assemble(pageHeader,pageHeaderE);
					
					//初始化Field
					Iterator ite = pageHeaderE.elementIterator("field");
					while(ite.hasNext()) {
						Element fieldE = (Element)ite.next();
						if(fieldE != null) {
							Field field = new Field();
							assemble(field,fieldE);
							if(field.isDisabled()) {
								//无效域不显示
								continue;
							}
							pageHeader.getFields().add(field);
						}
					}
					//初始化DateTime
					for(Iterator ite2 = pageHeaderE.elementIterator("date-time");ite2.hasNext();) {
						Element dateTimeE = (Element)ite2.next();
						if(dateTimeE != null) {
							DateTime dateTime = new DateTime();
							assemble(dateTime,dateTimeE);
							pageHeader.getFields().add(dateTime);
						}
					}
					style.setPageHeader(pageHeader);
				}
				//初始化BodyContent
				Element bodyContentE = sheetE.element("body-content");
				if(bodyContentE != null) {
					BodyContent bodyContent = new BodyContent();
					assemble(bodyContent,bodyContentE);
					
					
					//初始化内容体的当前行索引值
					bodyContent.setCurrentRow(bodyContent.getRowIndex());
					
					
					/*****************************适用于列表 **********************************************/
					//初始化ColumnHead
					Element columnHeaderE = bodyContentE.element("column-header");
					if(columnHeaderE != null) {
						ColumnHeader columnHeader = new ColumnHeader();
						assemble(columnHeader,columnHeaderE);
						bodyContent.setColumnHeader(columnHeader);
					}
					//初始化Rownum
					Element rownumE = bodyContentE.element("rownum");
					if(rownumE != null) {
						Rownum rownum = new Rownum();
						assemble(rownum,rownumE);
						//根据索引号对应
						bodyContent.setRownum(rownum);
					}
					//初始化Column
					for(Iterator iterator = bodyContentE.elementIterator("column");iterator.hasNext();) {
						Element columnE = (Element)iterator.next();
						if(columnE != null) {
							Column column = new Column();
							assemble(column,columnE);
							
							//根据索引号对应
							if(column.isDisabled()) {
								//排除无效列
								continue;
							}
							try {
								bodyContent.getColumns().add(column.getIndex(),column);
							} catch(IndexOutOfBoundsException ioobe) {
								throw new BizException(new SysMessage("请为<column index=\"\"/>元素中的index属性设置正确的索引值！"));
							}
						}
					}
					
					//初始化virtualcol
					for(Iterator iterator = bodyContentE.elementIterator("virtualcol");iterator.hasNext();) {
						Element virtualcolE = (Element)iterator.next();
						if(virtualcolE != null) {
							Virtualcol virtualcol = new Virtualcol();
							assemble(virtualcol,virtualcolE);
							
							if(virtualcol.isDisabled()) {
								//排除无效虚拟列
								continue;
							}
							bodyContent.getVirtualcolList().add(virtualcol);
						}
					}
					
					
					/*****************************适用于详细信息 **********************************************/
					//初始化Field
					for(Iterator ite3 = bodyContentE.elementIterator("field");ite3.hasNext();) {
						Element fieldE = (Element)ite3.next();
						if(fieldE != null) {
							Field field = new Field();
							assemble(field,fieldE);
							if(field.isDisabled()) {
								//无效域不显示
								continue;
							}
							bodyContent.getFields().add(field);
						}
					}
					//初始化Row
					for(Iterator ite4 = bodyContentE.elementIterator("row");ite4.hasNext();) {
						Element rowE = (Element)ite4.next();
						if(rowE != null) {
							Row row = new Row();
							assemble(row,rowE);
							
							//初始化Field
							for(Iterator ite5 = rowE.elementIterator("field");ite5.hasNext();) {
								Element fieldE = (Element)ite5.next();
								if(fieldE != null) {
									Field field = new Field();
									assemble(field,fieldE);
									if(field.isDisabled()) {
										//无效域不显示
										continue;
									}
									row.getFields().add(field);
								}
							}
							bodyContent.getRows().add(row);
						}
					}
					style.setBodyContent(bodyContent);
				}
				//初始化PageFooter
				Element pageFooterE = sheetE.element("page-footer");
				if(pageFooterE != null) {
					PageFooter pageFooter = new PageFooter();
					assemble(pageFooter,pageFooterE);
					
					//初始化Field
					Iterator ite = pageFooterE.elementIterator("field");
					while(ite.hasNext()) {
						Element fieldE = (Element)ite.next();
						if(fieldE != null) {
							Field field = new Field();
							assemble(field,fieldE);
							if(field.isDisabled()) {
								//无效域不显示
								continue;
							}
							pageFooter.getFields().add(field);
						}
					}
					style.setPageFooter(pageFooter);
				}
				return style;
			}
		}else {
			log.error("配置文件" + config + "中，ID为：" + templateId + "的模板不存在！");
			throw new BizException (new SysMessage("配置文件" + config + "中，ID为：" + templateId + "的模板不存在！"));
		}
		return null;
	}
	/**
	 * 
	 *功能描述：将ELEMENT元素中的所有属性组装到Map中
	 * @param map
	 * @param element
	 */
	public static void assemble(Map map,Element element) throws BizException {
		if(element == null) {
			log.error("no specified element !");
			throw new BizException(new SysMessage("no specified element !"));
		}
		if(map == null) {
			log.error("no specified map !");
			throw new BizException(new SysMessage("no specified map !"));
		}
		//清理
		map.clear();
		for(Iterator ite = element.attributeIterator();ite.hasNext();) {
			Attribute attr = (Attribute)ite.next();
			map.put(attr.getName(),attr.getValue());
		}
	}
	/**
	 * 
	 *功能描述：将ELEMENT元素中的所有属性填充到BEAN中
	 * @param bean
	 * @param element
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void assemble(Object bean,Element element) throws IllegalAccessException,InvocationTargetException {
		for(Iterator ite = element.attributeIterator();ite.hasNext();) {
			Attribute attr = (Attribute)ite.next();
			String name = attr.getName();
			//必须先判断是否可写入
			if (PropertyUtils.isWriteable(bean, name)) {
				Object value = attr.getValue();
				BeanUtil.copyProperty(bean,name,value);
				//PropertyUtils.setProperty(bean,name,value);
			}
		}
	}
}
