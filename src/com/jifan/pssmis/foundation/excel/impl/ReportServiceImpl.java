package com.jifan.pssmis.foundation.excel.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import jxl.HeaderFooter;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Boolean;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jifan.pssmis.foundation.excel.IReportService;
import com.jifan.pssmis.foundation.excel.IStyle;
import com.jifan.pssmis.foundation.excel.ReportEngine;
import com.jifan.pssmis.foundation.excel.ReportTemplate;
import com.jifan.pssmis.foundation.excel.style.BodyContent;
import com.jifan.pssmis.foundation.excel.style.Column;
import com.jifan.pssmis.foundation.excel.style.ColumnHeader;
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
import com.jifan.pssmis.foundation.util.CalendarUtil;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.foundation.util.DateUtil;
import com.jifan.pssmis.foundation.util.MathUtil;
import com.jifan.pssmis.foundation.util.StyleUtil;

@Component("reportService")
public class ReportServiceImpl implements IReportService {
	private static Log log = LogFactory.getLog(ReportServiceImpl.class);

	private JdbcTemplate jdbcTemplate;

	private static final int LIMIT_EXPORT_COUNT = 65500; // 导出EXCEL的记录上限(EXCEL本身单个SHEET的上限为65536)

	// 报表体输出坐标，不包含列标题
	public static final int START_ROW = 0; // 大于等于三(模板中以不在使用)

	public static final int START_COL = 0; // (模板中以不在使用)

	private static final String FORMULA = "jfFormula:";// 公式

	private static final String PERCENT_FLOAT = ":jfpercent_float";// 百分比

	private static final String VARCHAR_1 = "varchar_1";

	private static final String VARCHAR = "varchar";

	private static final String NUMBER = "number";

	private static final String NUMBER_01 = "number_01";

	private static final String BOOLEAN = "boolean";

	private static final String DATETIME = "datetime";

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private static final String NUMBER_FORMAT = "###,##0.00";

	private static final String DEF_DATE = "1970-01-01"; // 自定义默认日期

	// 字体
	public static final WritableFont.FontName SONGTI = WritableFont.createFont("宋体");

	/**
	 * 此方法需要重写，目前只是适配器
	 */

	@SuppressWarnings("unchecked")
	public List readExcelReport(String fileName, String bean, String[] properties, int[] columnTiles)
			throws BizException {

		return null;
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream createExcelReport(String title, String[] columnTiles, String[] columns, List mapList)
			throws BizException {
		return null;
	}

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
	 * @return
	 * @throws BizException
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream createExcelReport2(String title, String[] columnTiles, String[] properties,
			List dataList, boolean showTime) throws BizException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		boolean showRownum = true;
		try {
			/*
			 * File file = new File("d:\\source.xls"); WritableWorkbook book =
			 * Workbook.createWorkbook(file);
			 */
			WritableWorkbook book = Workbook.createWorkbook(baos);
			if (book == null) {
				throw new BizException(new SysMessage("创建工作簿失败！"));
			}
			WritableSheet sheet = book.createSheet(title, 0);
			WritableCellFormat wcf = null;

			Label labelCF = null;
			try {
				// 设置为保护
				book.setProtected(false);
				// 设置列标题冻结
				sheet.getSettings().setVerticalFreeze(START_ROW + 1);
				// 是否显示网格线
				sheet.getSettings().setShowGridLines(false);

				// 设置列宽
				sheet.setColumnView(0, 8);
				sheet.setColumnView(1, 12);
				sheet.setColumnView(2, 20);
				sheet.setColumnView(3, 20);
				sheet.setColumnView(4, 25);
				sheet.setColumnView(5, 10);
				sheet.setColumnView(6, 22);
				sheet.setColumnView(7, 18);
				sheet.setColumnView(8, 16);
				sheet.setColumnView(9, 16);
				sheet.setColumnView(10, 30);
				sheet.setColumnView(11, 10);

				// 设置行高
				int rows = dataList.size();
				for (int i = START_ROW + 1; i < rows + (START_ROW + 1); i++) {
					sheet.setRowView(i, 400);
				}

				int columnLength = columnTiles.length;

				// 标题
				wcf = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 15, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED));
				if (showRownum) {
					sheet.mergeCells(0, 0, columnLength, START_ROW - 2);
				} else {
					sheet.mergeCells(0, 0, columnLength - 1, START_ROW - 2);
				}
				wcf.setAlignment(Alignment.CENTRE);
				wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
				wcf.setBackground(Colour.VERY_LIGHT_YELLOW);
				labelCF = new Label(0, 0, title, wcf);
				sheet.addCell(labelCF);

				// 页眉
				wcf = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 10, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED));
				wcf.setBackground(Colour.AQUA);
				// 是否显示时间
				String time = null;
				if (showTime) {
					time = "日期：" + DateUtil.getStrNowDateShort();
				}
				labelCF = new Label(0, START_ROW - 1, time, wcf);
				sheet.addCell(labelCF);

				if (showRownum) {
					sheet.mergeCells(0, START_ROW - 1, columnLength - 1, START_ROW - 1);
					labelCF = new Label(columnLength, START_ROW - 1, "单位：元(人民币)", wcf);
				} else {
					sheet.mergeCells(0, START_ROW - 1, columnLength - 2, START_ROW - 1);
					labelCF = new Label(columnLength - 1, START_ROW - 1, "单位：元(人民币)", wcf);
				}
				sheet.addCell(labelCF);

				// 列头
				wcf = new WritableCellFormat(new WritableFont(SONGTI, 10, WritableFont.BOLD, false));
				// 列标题背景颜色
				wcf.setBackground(Colour.GRAY_25);
				wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
				wcf.setAlignment(Alignment.CENTRE);

				if (showRownum) {
					labelCF = new Label(START_COL, START_ROW, "序号", wcf);
					sheet.addCell(labelCF);
					for (int i = START_COL + 1; i < columnLength + START_COL + 1; i++) {
						labelCF = new Label(i, START_ROW, columnTiles[i - (START_COL + 1)], wcf);
						sheet.addCell(labelCF);
					}
				} else {
					for (int i = START_COL; i < columnLength + START_COL; i++) {
						labelCF = new Label(i, START_ROW, columnTiles[i - (START_COL)], wcf);
						sheet.addCell(labelCF);
					}
				}

				// 数据体
				wcf = new WritableCellFormat(new WritableFont(SONGTI, 9, WritableFont.NO_BOLD, false));
				wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
				wcf.setAlignment(Alignment.CENTRE);
				wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
				// 自动断行
				// wcf.setWrap(true);

				Object propValue = null;
				if (showRownum) {
					for (int i = 0; i < dataList.size(); i++) {
						labelCF = new Label(START_COL, i + START_ROW + 1, String.valueOf(i + 1), wcf);
						sheet.addCell(labelCF);
						for (int j = START_COL + 1; j < properties.length + START_COL + 1; j++) {
							propValue = PropertyUtils.getProperty(dataList.get(i), properties[j - (START_COL + 1)]);
							if (propValue == null) {
								propValue = "";
							}
							propValue = StringUtils.replace(propValue.toString(), "</br>", "");
							labelCF = new Label(j, i + START_ROW + 1, propValue.toString(), wcf);
							sheet.addCell(labelCF);
						}
					}
				} else {
					for (int i = 0; i < dataList.size(); i++) {
						for (int j = START_COL; j < properties.length + START_COL; j++) {
							propValue = PropertyUtils.getProperty(dataList.get(i), properties[j - START_COL]);
							if (propValue == null) {
								propValue = "";
							}
							propValue = StringUtils.replace(propValue.toString(), "</br>", "");
							labelCF = new Label(j, i + START_ROW + 1, propValue.toString(), wcf);
							sheet.addCell(labelCF);
						}
					}
				}
			} finally {
				if (book != null) {
					book.write();
					book.close();
				}
			}
		} catch (RowsExceededException ree) {
			log.error(ree);
			// throw new BizException(ree);
		} catch (WriteException we) {
			log.error(we);
			// throw new BizException(we);
		} catch (IOException ioe) {
			log.error(ioe);
			// throw new BizException(ioe);
		} catch (IllegalAccessException iae) {
			log.error(iae);
			// throw new BizException(iae);
		} catch (NoSuchMethodException nsme) {
			log.error(nsme);
			// throw new BizException(nsme);
		} catch (InvocationTargetException ite) {
			log.error(ite);
			// throw new BizException(ite);
		} catch (Exception ex) {
			log.error(ex);
		}
		return baos;
	}

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
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream createExcelReport2(String title, String[] columnTiles, String[] properties,
			List dataList, boolean showTime, String units) throws BizException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		boolean showRownum = true;
		try {
			WritableWorkbook book = Workbook.createWorkbook(baos);
			if (book == null) {
				throw new BizException(new SysMessage("创建工作簿失败！"));
			}
			WritableSheet sheet = book.createSheet(title, 0);
			WritableCellFormat wcf = null;
			Label labelCF = null;
			try {
				// 设置为保护
				book.setProtected(false);
				// 设置列标题冻结
				sheet.getSettings().setVerticalFreeze(START_ROW + 1);
				// 是否显示网格线
				sheet.getSettings().setShowGridLines(false);

				// 设置列宽
				sheet.setColumnView(0, 8);
				sheet.setColumnView(1, 12);
				sheet.setColumnView(2, 20);
				sheet.setColumnView(3, 20);
				sheet.setColumnView(4, 25);
				sheet.setColumnView(5, 10);
				sheet.setColumnView(6, 22);
				sheet.setColumnView(7, 18);
				sheet.setColumnView(8, 16);
				sheet.setColumnView(9, 16);
				sheet.setColumnView(10, 30);
				sheet.setColumnView(11, 10);

				// 设置行高
				int rows = dataList.size();
				for (int i = START_ROW + 1; i < rows + (START_ROW + 1); i++) {
					sheet.setRowView(i, 400);
				}

				int columnLength = columnTiles.length;

				// 标题
				wcf = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 15, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED));
				if (showRownum) {
					sheet.mergeCells(0, 0, columnLength, START_ROW - 2);
				} else {
					sheet.mergeCells(0, 0, columnLength - 1, START_ROW - 2);
				}
				wcf.setAlignment(Alignment.CENTRE);
				wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
				wcf.setBackground(Colour.VERY_LIGHT_YELLOW);
				labelCF = new Label(0, 0, title, wcf);
				sheet.addCell(labelCF);

				// 页眉
				wcf = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 10, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED));
				wcf.setBackground(Colour.AQUA);
				// 是否显示时间
				String time = null;
				if (showTime) {
					time = "截止日期：" + DateUtil.getStrNowDateShort();
				}
				labelCF = new Label(0, START_ROW - 1, time, wcf);
				sheet.addCell(labelCF);

				if (showRownum) {
					sheet.mergeCells(0, START_ROW - 1, columnLength - 1, START_ROW - 1);
					labelCF = new Label(columnLength, START_ROW - 1, units, wcf);
				} else {
					sheet.mergeCells(0, START_ROW - 1, columnLength - 2, START_ROW - 1);
					labelCF = new Label(columnLength - 1, START_ROW - 1, units, wcf);
				}
				sheet.addCell(labelCF);

				// 列头
				wcf = new WritableCellFormat(new WritableFont(SONGTI, 10, WritableFont.BOLD, false));
				// 列标题背景颜色
				wcf.setBackground(Colour.GRAY_25);
				wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
				wcf.setAlignment(Alignment.CENTRE);

				if (showRownum) {
					labelCF = new Label(START_COL, START_ROW, "序号", wcf);
					sheet.addCell(labelCF);
					for (int i = START_COL + 1; i < columnLength + START_COL + 1; i++) {
						labelCF = new Label(i, START_ROW, columnTiles[i - (START_COL + 1)], wcf);
						sheet.addCell(labelCF);
					}
				} else {
					for (int i = START_COL; i < columnLength + START_COL; i++) {
						labelCF = new Label(i, START_ROW, columnTiles[i - (START_COL)], wcf);
						sheet.addCell(labelCF);
					}
				}

				// 数据体
				wcf = new WritableCellFormat(new WritableFont(SONGTI, 9, WritableFont.NO_BOLD, false));
				wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
				wcf.setAlignment(Alignment.CENTRE);
				wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
				// 自动断行
				// wcf.setWrap(true);

				Object propValue = null;
				if (showRownum) {
					for (int i = 0; i < dataList.size(); i++) {
						labelCF = new Label(START_COL, i + START_ROW + 1, String.valueOf(i + 1), wcf);
						sheet.addCell(labelCF);
						for (int j = START_COL + 1; j < properties.length + START_COL + 1; j++) {
							propValue = PropertyUtils.getProperty(dataList.get(i), properties[j - (START_COL + 1)]);
							if (propValue == null) {
								propValue = "";
							}
							propValue = StringUtils.replace(propValue.toString(), "</br>", "");
							labelCF = new Label(j, i + START_ROW + 1, propValue.toString(), wcf);
							sheet.addCell(labelCF);
						}
					}
				} else {
					for (int i = 0; i < dataList.size(); i++) {
						for (int j = START_COL; j < properties.length + START_COL; j++) {
							propValue = PropertyUtils.getProperty(dataList.get(i), properties[j - START_COL]);
							if (propValue == null) {
								propValue = "";
							}
							propValue = StringUtils.replace(propValue.toString(), "</br>", "");
							labelCF = new Label(j, i + START_ROW + 1, propValue.toString(), wcf);
							sheet.addCell(labelCF);
						}
					}
				}
			} finally {
				if (book != null) {
					book.write();
					book.close();
				}
			}
		} catch (RowsExceededException ree) {
			log.error(ree);
		} catch (WriteException we) {
			log.error(we);
		} catch (IOException ioe) {
			log.error(ioe);
		} catch (IllegalAccessException iae) {
			log.error(iae);
		} catch (NoSuchMethodException nsme) {
			log.error(nsme);
		} catch (InvocationTargetException ite) {
			log.error(ite);
		} catch (Exception ex) {
			log.error(ex);
		}
		return baos;
	}

	/**
	 * 导入Excel
	 * 
	 * @param fileName
	 *            上传后的Excel文件名（带路径）
	 * @param bean
	 *            导入数据的bo名
	 * @param properties
	 *            要插入数据的bo属性名集合
	 * @return 返回从Excel读取数据的bo集合
	 * @throws BizException
	 */
	@SuppressWarnings("unchecked")
	public Set readExcelReport(String fileName, Class bean, String[] properties) throws BizException {
		InputStream is = null;
		Workbook rbook = null;
		Set beans = null;
		Class bo = null;
		Object systemParamer = null;
		String value = null;
		int i = 0, j = 0;
		try {
			beans = new LinkedHashSet();
			is = new FileInputStream(fileName);
			rbook = Workbook.getWorkbook(is);
			Sheet sheet = rbook.getSheet(0);
			int rows = sheet.getRows();

			// int cols = sheet.getColumns();

			// if (cols != properties.length + 1) {
			// throw new BizException("请确认Excel上的数据列数跟页面上的一致!");
			// }

			String className = bean.getName();
			if (log.isDebugEnabled()) {
				log.debug("填充类：" + className);
			}
			bo = Class.forName(className);
			for (i = 1; i < rows - START_ROW; i++) {
				systemParamer = bo.newInstance();
				for (j = 0; j < properties.length; j++) {
					// value = sheet.getCell(j + 1, i + START_ROW)
					value = sheet.getCell(j, i + START_ROW).getContents().trim();
					if (value != null && !value.equals("")) {
						// if(DataTableUtil.getProperty(systemParamer,
						// properties[j]).equals("cn.ccb.foundation.common.util.CCBCalendar")){
						// BeanUtils.copyProperty(systemParamer,
						// properties[j],new
						// CCBCalendar(DateUtil.ToDateShort(value)));
						// }else{
						BeanUtils.copyProperty(systemParamer, properties[j], value);
						// }
					}
				}
				beans.add(systemParamer);
			}
			rbook.close();
			is.close();
			return beans;
		} catch (ConversionException e) {
			throw new BizException(new SysMessage(
				"第" + (i + START_ROW + 1) + "行第" + (j + 2) + "列的值'" + value + "'值类型错误，请检查!"));
		} catch (ClassNotFoundException e) {
			throw new BizException(new SysMessage(e.getMessage()));
		} catch (IOException e) {
			throw new BizException(new SysMessage(e.getMessage()));
		} catch (IllegalAccessException e) {
			throw new BizException(new SysMessage(e.getMessage()));
		} catch (InvocationTargetException e) {
			throw new BizException(new SysMessage(e.getMessage()));
		} catch (Exception e) {
			throw new BizException(new SysMessage(
				"第" + (i + START_ROW + 1) + "行第" + (j + 2) + "列的值'" + value + "'读取错误，请检查!"));
		}
	}

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
	 *             chelachen 2010-05-06
	 */
	@SuppressWarnings("unchecked")
	public Set readExcelReport2(String fileName, Class bean, String[] properties, int startRow) throws BizException {
		InputStream is = null;
		Workbook rbook = null;
		Set beans = null;
		Class bo = null;
		Object systemParamer = null;
		String value = null;
		int i = 0, j = 0;
		try {
			beans = new LinkedHashSet();
			is = new FileInputStream(fileName);
			rbook = Workbook.getWorkbook(is);
			Sheet sheet = rbook.getSheet(0);
			int rows = sheet.getRows();

			// int cols = sheet.getColumns();

			String className = bean.getName();
			if (log.isDebugEnabled()) {
				log.debug("填充类：" + className);
			}
			bo = Class.forName(className);
			for (i = 1; i < rows - startRow; i++) {
				systemParamer = bo.newInstance();
				for (j = 0; j < properties.length; j++) {

					value = sheet.getCell(j, i + startRow).getContents().trim();
					if (value != null && !value.equals("")) {
						// if(DataTableUtil.getProperty(systemParamer,
						// properties[j]).equals("cn.ccb.foundation.common.util.CCBCalendar")){
						// BeanUtils.copyProperty(systemParamer,
						// properties[j],new
						// CCBCalendar(DateUtil.ToDateShort(value)));
						// }else{
						BeanUtils.copyProperty(systemParamer, properties[j], value);
						// }
					}
				}
				beans.add(systemParamer);
			}
			rbook.close();
			is.close();
			return beans;
		} catch (ConversionException e) {
			throw new BizException(new SysMessage(
				"第" + (i + startRow + 1) + "行第" + (j + 2) + "列的值'" + value + "'值类型错误，请检查!"));
		} catch (ClassNotFoundException e) {
			throw new BizException(new SysMessage(e.getMessage()));
		} catch (IOException e) {
			throw new BizException(new SysMessage(e.getMessage()));
		} catch (IllegalAccessException e) {
			throw new BizException(new SysMessage(e.getMessage()));
		} catch (InvocationTargetException e) {
			throw new BizException(new SysMessage(e.getMessage()));
		} catch (Exception e) {
			throw new BizException(new SysMessage(
				"第" + (i + startRow + 1) + "行第" + (j + 2) + "列的值'" + value + "'读取错误，请检查!"));
		}
	}

	/**
	 * 默认显示时间的报表
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream createExcelReport2(String title, String[] columnTiles, String[] properties,
			List dataList) throws BizException {
		return createExcelReport2(title, columnTiles, properties, dataList, true);
	}

	/**
	 * *************************************可配置模板方式的EXCEL报表*********************
	 * ********************************
	 */

	/**
	 * 多个List写入多个sheet
	 */

	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream createExcelReportMulSheet(List templates) throws BizException, IOException,
			WriteException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, DocumentException {
		long startTime = System.currentTimeMillis();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		WritableWorkbook book = Workbook.createWorkbook(baos);
		try {
			if (book == null) {
				throw new BizException(new SysMessage("创建工作簿失败！"));
			}
			for (int i = 0; i < templates.size(); i++) {
				ReportTemplate template = (ReportTemplate) templates.get(i);
				StyleSheet style = ReportEngine.parseBySequence(template);// 获取样式
				if (style == null) {
					throw new BizException(new SysMessage("获取报表样式失败！"));
				}
				book.setProtected(style.isReadOnly());// 设置工作簿为保护
				BodyContent bodyContent = style.getBodyContent();// 生成内容体
				String type = bodyContent.getType();
				if (type == null || type.equals("")) {
					type = IStyle.DETAIL_TYPE;// 设置默认报表类型(detail)
				}
				if (type.equals(IStyle.TABLE_TYPE)) {
					Object value = bodyContent.getRealValue();
					if (value instanceof List) {
						List dataList = (List) value;
						int size = dataList.size();// 导出上限
						if (size > LIMIT_EXPORT_COUNT) {
							if (log.isDebugEnabled()) {
								log.debug("要导出的记录数超过了excel允许的上限值");
							}
							dataList = dataList.subList(0, LIMIT_EXPORT_COUNT);
							// throw new BizException("你要导出的记录数超过了EXCEL允许的上限值("
							// + LIMIT_EXPORT_COUNT + ")!");
						}
						style.setSingle(false);
						this.createMulSheet(book, style, dataList, i);
					} else {
						throw new BizException(new SysMessage(
							"类型转换错误，请为报表模板中：<body-content>元素的 value 属性指定 <List> 类型数据!"));
					}
				} else if (type.equals(IStyle.DETAIL_TYPE)) {
					if (log.isDebugEnabled()) {
						log.debug("=======进入详细信息类型报表输出。。。。。。");
					}
					createSheet(book, style);
				} else {
					throw new BizException(new SysMessage(
						"报表类型错误，请为报表模板中：<body-content>元素的 type 属性指定正确的类型(table | detail)!"));
				}

			}
			// 工作簿输出
			if (book.getSheets().length > 0) {
				book.write();
			}
		} finally {
			// 关闭
			if (book != null) {
				book.close();
			}
		}
		long endTime = System.currentTimeMillis();
		if (log.isInfoEnabled()) {
			log.info("生成EXCEL报表性能消耗(s) :createExcelReport2 total time = " + (endTime - startTime) / 1000.0 + " s");
		}
		return baos;
	}

	/**
	 * 可配置模板方式的EXCEL报表,模板的详细配置见 /doc/xml/readme.txt; 生成报表
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream createExcelReport4(String titleName, String sheetName, List<Column> coloums,
			List<Field> fields, ReportTemplate template) throws BizException, IOException, WriteException,
			IllegalAccessException, NoSuchMethodException, InvocationTargetException, DocumentException {

		long startTime = System.currentTimeMillis();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		WritableWorkbook book = Workbook.createWorkbook(baos);
		if (book == null) {
			throw new BizException("创建工作簿失败！");
		}
		try {
			// 获取样式
			// StyleSheet style = ReportEngine.parse(template);

			/** ************** start ********************** */
			StyleSheet style = ReportEngine.parseBySequence(template);
			/** ************** end ********************** */

			if (style == null) {
				throw new BizException("获取报表样式失败！");
			}
			style.setName(sheetName);

			// 设置工作簿为保护
			book.setProtected(style.isReadOnly());

			if (null != style.getTitle()) {
				// 设置标题合并行数
				style.getTitle().setColspan(coloums.size());
				style.getTitle().setName(titleName);
			}

			// 生成内容体
			BodyContent bodyContent = style.getBodyContent();
			bodyContent.setColumns(coloums);
			String type = bodyContent.getType();
			if (null != fields) {
				PageFooter pageFooter = style.getPageFooter();
				pageFooter.setFields(fields);
			}
			if (type == null || type.equals("")) {
				// 设置默认报表类型(detail)
				type = IStyle.DETAIL_TYPE;
			}
			if (type.equals(IStyle.TABLE_TYPE)) {
				if (log.isDebugEnabled()) {
					log.debug("=======进入列表类型报表输出。。。。。。");
				}
				Object value = bodyContent.getRealValue();
				if (value instanceof List) {
					List dataList = (List) value;
					int size = dataList.size();

					// 导出上限
					if (size > LIMIT_EXPORT_COUNT) {
						throw new BizException("你要导出的记录数超过了EXCEL允许的上限值(" + LIMIT_EXPORT_COUNT + ")!");
					}

					boolean single = style.isSingle();
					if (!single) {
						int sheetSize = style.getSheetSize();
						int sheetCount = 0;
						if (sheetSize != 0)
							sheetCount = ((size + sheetSize) - 1) / sheetSize;

						if (log.isDebugEnabled()) {
							log.debug("===========size =" + size);
							log.debug("===========sheetSize =" + sheetSize);
							log.debug("===========sheetCount =" + sheetCount);
						}

						if (sheetCount <= 0) {
							// 没有数据记录时，强制输出
							this.createSheet(book, style, dataList, 0);
						} else {
							// 分页处理
							for (int sheetIndex = 0; sheetIndex < sheetCount; sheetIndex++) {
								int start, end;
								start = sheetSize * sheetIndex;
								if (sheetIndex == sheetCount - 1) {
									end = size;
								} else {
									end = sheetSize * (sheetIndex + 1);
								}
								List sheetList = dataList.subList(start, end);
								this.createSheet(book, style, sheetList, sheetIndex);
							}
						}
					} else {
						this.createSheet(book, style, dataList, 0);
					}
				} else {
					throw new BizException(new SysMessage("类型转换错误，请为报表模板中：<body-content>元素的 value 属性指定 <List> 类型数据!"));
				}
			} else if (type.equals(IStyle.DETAIL_TYPE)) {
				if (log.isDebugEnabled()) {
					log.debug("=======进入详细信息类型报表输出。。。。。。");
				}
				createSheet(book, style);
			} else {
				throw new BizException(new SysMessage(
					"报表类型错误，请为报表模板中：<body-content>元素的 type 属性指定正确的类型(table | detail)!"));
			}
			// 工作簿输出
			if (book.getSheets().length > 0) {
				book.write();
			}
		} finally {
			// 关闭
			if (book != null) {
				book.close();
			}
		}
		long endTime = System.currentTimeMillis();
		if (log.isInfoEnabled()) {
			log.info("生成EXCEL报表性能消耗(s) :createExcelReport2 total time = " + (endTime - startTime) / 1000.0 + " s");
		}
		return baos;
	}

	/**
	 * 可配置模板方式的EXCEL报表,模板的详细配置见 /doc/xml/readme.txt; 生成报表
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream createExcelReport3(List<Column> coloums, List<Field> headerFields,
			List<Field> footerFields, ReportTemplate template) throws BizException, IOException, WriteException,
			IllegalAccessException, NoSuchMethodException, InvocationTargetException, DocumentException {

		long startTime = System.currentTimeMillis();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		WritableWorkbook book = Workbook.createWorkbook(baos);
		if (book == null) {
			throw new BizException("创建工作簿失败！");
		}
		try {
			// 获取样式
			// StyleSheet style = ReportEngine.parse(template);

			/** ************** start ********************** */
			StyleSheet style = ReportEngine.parseBySequence(template);
			/** ************** end ********************** */

			if (style == null) {
				throw new BizException("获取报表样式失败！");
			}

			// 设置工作簿为保护
			book.setProtected(style.isReadOnly());

			if (null != style.getTitle()) {
				int columnLength = 0;
				for (int i = 0; i < coloums.size(); i++) {
					Column cul = (Column) coloums.get(i);
					if (cul.getProperty() != null)
						columnLength += 1;

				}
				// 设置标题合并行数
				style.getTitle().setColspan(columnLength);
			}

			// 生成内容体
			BodyContent bodyContent = style.getBodyContent();
			bodyContent.setColumns(coloums);
			String type = bodyContent.getType();
			if (null != footerFields) {
				PageFooter pageFooter = style.getPageFooter();
				pageFooter.setFields(footerFields);
			}
			if (null != headerFields) {
				PageHeader pageHeader = style.getPageHeader();
				pageHeader.setFields(headerFields);
			}
			if (type == null || type.equals("")) {
				// 设置默认报表类型(detail)
				type = IStyle.DETAIL_TYPE;
			}
			if (type.equals(IStyle.TABLE_TYPE)) {
				if (log.isDebugEnabled()) {
					log.debug("=======进入列表类型报表输出。。。。。。");
				}
				Object value = bodyContent.getRealValue();
				if (value instanceof List) {
					List dataList = (List) value;
					int size = dataList.size();

					// 导出上限
					if (size > LIMIT_EXPORT_COUNT) {
						throw new BizException("你要导出的记录数超过了EXCEL允许的上限值(" + LIMIT_EXPORT_COUNT + ")!");
					}

					boolean single = style.isSingle();
					if (!single) {
						int sheetSize = style.getSheetSize();
						int sheetCount = 0;
						if (sheetSize != 0)
							sheetCount = ((size + sheetSize) - 1) / sheetSize;

						if (log.isDebugEnabled()) {
							log.debug("===========size =" + size);
							log.debug("===========sheetSize =" + sheetSize);
							log.debug("===========sheetCount =" + sheetCount);
						}

						if (sheetCount <= 0) {
							// 没有数据记录时，强制输出
							this.createSheet(book, style, dataList, 0);
						} else {
							// 分页处理
							for (int sheetIndex = 0; sheetIndex < sheetCount; sheetIndex++) {
								int start, end;
								start = sheetSize * sheetIndex;
								if (sheetIndex == sheetCount - 1) {
									end = size;
								} else {
									end = sheetSize * (sheetIndex + 1);
								}
								List sheetList = dataList.subList(start, end);
								this.createSheet(book, style, sheetList, sheetIndex);
							}
						}
					} else {
						this.createSheet(book, style, dataList, 0);
					}
				} else {
					throw new BizException(new SysMessage("类型转换错误，请为报表模板中：<body-content>元素的 value 属性指定 <List> 类型数据!"));
				}
			} else if (type.equals(IStyle.DETAIL_TYPE)) {
				if (log.isDebugEnabled()) {
					log.debug("=======进入详细信息类型报表输出。。。。。。");
				}
				createSheet(book, style);
			} else {
				throw new BizException(new SysMessage(
					"报表类型错误，请为报表模板中：<body-content>元素的 type 属性指定正确的类型(table | detail)!"));
			}
			// 工作簿输出
			if (book.getSheets().length > 0) {
				book.write();
			}
		} finally {
			// 关闭
			if (book != null) {
				book.close();
			}
		}
		long endTime = System.currentTimeMillis();
		if (log.isInfoEnabled()) {
			log.info("生成EXCEL报表性能消耗(s) :createExcelReport2 total time = " + (endTime - startTime) / 1000.0 + " s");
		}
		return baos;
	}

	/**
	 * 可配置模板方式的EXCEL报表,模板的详细配置见 /doc/xml/readme.txt; 生成报表
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream createExcelReport2(ReportTemplate template) throws BizException, IOException,
			WriteException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, DocumentException {

		long startTime = System.currentTimeMillis();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		WritableWorkbook book = Workbook.createWorkbook(baos);
		if (book == null) {
			throw new BizException("创建工作簿失败！");
		}
		try {
			// 获取样式
			// StyleSheet style = ReportEngine.parse(template);

			/** ************** start ********************** */
			StyleSheet style = ReportEngine.parseBySequence(template);
			/** ************** end ********************** */

			if (style == null) {
				throw new BizException("获取报表样式失败！");
			}

			// 设置工作簿为保护
			book.setProtected(style.isReadOnly());

			// 生成内容体
			BodyContent bodyContent = style.getBodyContent();
			String type = bodyContent.getType();
			if (type == null || type.equals("")) {
				// 设置默认报表类型(detail)
				type = IStyle.DETAIL_TYPE;
			}
			if (type.equals(IStyle.TABLE_TYPE)) {
				if (log.isDebugEnabled()) {
					log.debug("=======进入列表类型报表输出。。。。。。");
				}
				Object value = bodyContent.getRealValue();
				if (value instanceof List) {
					List dataList = (List) value;
					int size = dataList.size();

					// 导出上限
					if (size > LIMIT_EXPORT_COUNT) {
						throw new BizException("你要导出的记录数超过了EXCEL允许的上限值(" + LIMIT_EXPORT_COUNT + ")!");
					}

					boolean single = style.isSingle();
					if (!single) {
						int sheetSize = style.getSheetSize();
						int sheetCount = 0;
						if (sheetSize != 0)
							sheetCount = ((size + sheetSize) - 1) / sheetSize;

						if (log.isDebugEnabled()) {
							log.debug("===========size =" + size);
							log.debug("===========sheetSize =" + sheetSize);
							log.debug("===========sheetCount =" + sheetCount);
						}

						if (sheetCount <= 0) {
							// 没有数据记录时，强制输出
							this.createSheet(book, style, dataList, 0);
						} else {
							// 分页处理
							for (int sheetIndex = 0; sheetIndex < sheetCount; sheetIndex++) {
								int start, end;
								start = sheetSize * sheetIndex;
								if (sheetIndex == sheetCount - 1) {
									end = size;
								} else {
									end = sheetSize * (sheetIndex + 1);
								}
								List sheetList = dataList.subList(start, end);
								this.createSheet(book, style, sheetList, sheetIndex);
							}
						}
					} else {
						this.createSheet(book, style, dataList, 0);
					}
				} else {
					throw new BizException(new SysMessage("类型转换错误，请为报表模板中：<body-content>元素的 value 属性指定 <List> 类型数据!"));
				}
			} else if (type.equals(IStyle.DETAIL_TYPE)) {
				if (log.isDebugEnabled()) {
					log.debug("=======进入详细信息类型报表输出。。。。。。");
				}
				createSheet(book, style);
			} else {
				throw new BizException(new SysMessage(
					"报表类型错误，请为报表模板中：<body-content>元素的 type 属性指定正确的类型(table | detail)!"));
			}
			// 工作簿输出
			if (book.getSheets().length > 0) {
				book.write();
			}

		} finally {
			// 关闭
			if (book != null) {
				book.close();
			}
		}
		long endTime = System.currentTimeMillis();
		if (log.isInfoEnabled()) {
			log.info("生成EXCEL报表性能消耗(s) :createExcelReport2 total time = " + (endTime - startTime) / 1000.0 + " s");
		}
		return baos;
	}

	/**
	 * 
	 * 功能描述：主要用于生成多个工作单 主要是去掉single判断问题
	 * 
	 * @param book
	 * @param style
	 * @param dataList
	 * @param sheetNO
	 * @throws BizException
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	private void createMulSheet(WritableWorkbook book, StyleSheet style, List dataList, int sheetNO)
			throws BizException, WriteException, IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {

		String reportTitle = style.getRealName();
		if (reportTitle == null) {
			reportTitle = "no specilized report title!";
		}
		WritableSheet sheet = book.createSheet(reportTitle, sheetNO);
		// 设置工作单
		this.setSheetSetting(sheet, style);

		// 生成标题
		this.createTitle(sheet, style);

		// 生成页眉
		this.createPageHeader(sheet, style);

		// 生成内容体
		this.createBodyContent(sheet, style, dataList, sheetNO);

		// 生成页脚
		this.createPageFooter(sheet, style);
	}

	/**
	 * 
	 * 功能描述：生成工作单 参数说明：
	 * 
	 * @param book
	 * @param style
	 * @param dataList
	 * @param sheetNO
	 * @throws BizException
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	private void createSheet(WritableWorkbook book, StyleSheet style, List dataList, int sheetNO) throws BizException,
			WriteException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

		String reportTitle = style.getRealName();
		if (reportTitle == null) {
			reportTitle = "no specilized report title!";
		}

		if (!style.isSingle()) {
			reportTitle += "_" + (sheetNO + 1);
		}

		WritableSheet sheet = book.createSheet(reportTitle, sheetNO);
		// 设置工作单
		this.setSheetSetting(sheet, style);

		// 生成标题
		this.createTitle(sheet, style);

		// 生成页眉
		this.createPageHeader(sheet, style);

		// 生成内容体
		this.createBodyContent(sheet, style, dataList, sheetNO);

		// 生成页脚
		this.createPageFooter(sheet, style);
	}

	/**
	 * 
	 * 功能描述：创建报表体（详细信息类型）
	 * 
	 * @param book
	 * @param style
	 * @throws BizException
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	private void createSheet(WritableWorkbook book, StyleSheet style) throws BizException, WriteException,
			IllegalAccessException, NoSuchMethodException, InvocationTargetException {

		String reportTitle = style.getRealName();
		if (reportTitle == null) {
			reportTitle = "no specilized report title!";
		}
		WritableSheet sheet = book.createSheet(reportTitle, 0);

		// 设置工作单
		this.setSheetSetting(sheet, style);

		// 生成标题
		this.createTitle(sheet, style);

		// 生成页眉
		this.createPageHeader(sheet, style);

		// 生成内容体
		this.createBodyContent(sheet, style);

		// 生成页脚
		this.createPageFooter(sheet, style);
	}

	/**
	 * 
	 * 功能描述：工作单设置
	 * 
	 * @param sheet
	 * @param style
	 */
	private void setSheetSetting(WritableSheet sheet, StyleSheet style) {
		int startRowIndex = style.getBodyContent().getRowIndex();
		int startColIndex = style.getBodyContent().getForzonColIndex();

		// 设置列冻结
		sheet.getSettings().setHorizontalFreeze(startColIndex);

		// 设置列标题冻结（即行冻结）
		sheet.getSettings().setVerticalFreeze(startRowIndex);

		// 是否显示网格线
		sheet.getSettings().setShowGridLines(style.isShowGrid());

		// 设置工作单为只读
		sheet.getSettings().setProtected(style.isReadOnly());

		// 设置页边距
		sheet.getSettings().setHeaderMargin(1);
		sheet.getSettings().setFooterMargin(1);

		sheet.getSettings().setTopMargin(2);
		sheet.getSettings().setBottomMargin(2);

		// 设置方向
		/*
		 * sheet.getSettings().setPaperSize(PaperSize.A4);
		 * sheet.getSettings().setOrientation(PageOrientation.LANDSCAPE);
		 */

		// 设置
		HeaderFooter header = new HeaderFooter();
		// header.getCentre().append("几凡科技--");
		// header.getCentre().appendWorkSheetName();
		sheet.getSettings().setHeader(header);

		HeaderFooter footer = new HeaderFooter();
		footer.getRight().append("page ");
		footer.getRight().appendPageNumber();
		sheet.getSettings().setFooter(footer);

		/*
		 * // Add a page break and insert a couple of rows
		 * sheet.addRowPageBreak(18); sheet.insertRow(17); sheet.insertRow(17);
		 * sheet.removeRow(17); // Add a page break off the screen
		 * sheet.addRowPageBreak(30);
		 */

	}

	/**
	 * 
	 * 功能描述：创建报表体（用于详细信息类型）
	 * 
	 * @param sheet
	 * @param style
	 * @param sheetNO
	 * @throws BizException
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	private void createBodyContent(WritableSheet sheet, StyleSheet style) throws BizException, WriteException,
			IllegalAccessException, NoSuchMethodException, InvocationTargetException, NumberFormatException {

		BodyContent bodyContent = style.getBodyContent();

		if (bodyContent == null) {
			throw new BizException(new SysMessage("名称为：" + style.getName() + " 的EXCEL模板文件配置错误！原因：缺少报表体。"));
		}

		String[] widths = bodyContent.getColumnWidthValue();
		// 设置列宽
		for (int i = 0; i < widths.length; i++) {
			sheet.setColumnView(i, Integer.parseInt(widths[i]));
		}
		// 获取所有域，填充内容体
		List allFields = bodyContent.getAllFields();
		for (Iterator ite = allFields.iterator(); ite.hasNext();) {
			Field field = (Field) ite.next();
			field.setSheetStyle(sheet);

			WritableCellFormat wcf = StyleUtil.getWritableCellFormat(bodyContent, field);
			wcf.setWrap(true);
			Label labelCF = new Label(field.getRealColIndex(), field.getRealRowIndex(), field.getRealValue(), wcf);
			sheet.addCell(labelCF);
		}
		// 根据域设置行高
		for (Iterator ite2 = bodyContent.getFields().iterator(); ite2.hasNext();) {
			Field field = (Field) ite2.next();
			sheet.setRowView(field.getRealRowIndex(), bodyContent.getRowHeight());
		}
		// 根据域组设置行高
		for (Iterator ite3 = bodyContent.getRows().iterator(); ite3.hasNext();) {
			Row row = (Row) ite3.next();

			if (row.isDisabled()) {
				continue;
			}

			int height = StyleUtil.getRowHeight(bodyContent, row);
			sheet.setRowView(row.getIndex(), height);
		}
	}

	/**
	 * 
	 * 功能描述：生成报表内容体(表格类型) 参数说明：
	 * 
	 * @param sheet
	 * @param style
	 * @param dataList
	 * @param sheetNO
	 * @throws BizException
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings( { "unchecked", "static-access" })
	private void createBodyContent(WritableSheet sheet, StyleSheet style, List dataList, int sheetNO)
			throws BizException, WriteException, IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {

		BodyContent bodyContent = style.getBodyContent();
		if (bodyContent == null) {
			throw new BizException(new SysMessage("名称为：" + style.getName() + " 的EXCEL模板文件配置错误！原因：缺少报表体。"));
		}

		// 每页显示记录数
		int sheetSize = style.getSheetSize();

		WritableCellFormat wcf = null;
		Label labelCF = null;

		int startRowIndex = bodyContent.getRowIndex(); // 数据体的开始行索引，不含列头
		int startColIndex = bodyContent.getColIndex(); // 数据体的开始列索引，不含列头

		List columns = bodyContent.getColumns();
		int columnLength = 0;
		for (int i = 0; i < columns.size(); i++) {
			Column cul = (Column) columns.get(i);
			if (cul.getProperty() != null)
				columnLength += 1;

		}
		// int columnLength = columns.size();
		if (columnLength > StyleSheet.MAXCOLUMNNUM) {
			throw new BizException("数据量过大，导致列数超出excel的范围！请减少数据量再进行导出数据！");
		}
		String[] datatypes = new String[columns.size()]; // 数据类型
		String[] properties = new String[columnLength]; // 属性

		boolean showRownum = bodyContent.hasRownum();
		Rownum rownum = bodyContent.getRownum();
		ColumnHeader columnHeader = bodyContent.getColumnHeader();
		int colWidth = columnHeader.getWidth();
		Column column = null;

		/**
		 * ***********************************把日期格式数字格式扩展为可配置的，每列支持各自配的日期格式或数字格式
		 * start***********************************************
		 */
		WritableCellFormat[] wcfs = new WritableCellFormat[columns.size()];
		// 设置列宽
		if (showRownum) {
			// 序号列宽
			sheet.setColumnView(0, rownum.getWidth());
			int propindex=0;
			// 列宽
			for (int i = 0; i < columns.size(); i++) {
				column = (Column) columns.get(i);
				// 默认为字符串类型
				if (column.getDatatype() == null || column.getDatatype().equals("")) {
					datatypes[i] = VARCHAR;
					wcfs[i] = StyleUtil.getWritableCellFormat(bodyContent, column);
				} else if (column.getDatatype().equals(VARCHAR_1)) {
					datatypes[i] = VARCHAR_1;
					wcfs[i] = StyleUtil.getWritableCellFormat(bodyContent, column);
				} else if (column.getDatatype().equals(NUMBER)) {
					datatypes[i] = NUMBER;
					if (column.getFormat() != null && !column.getFormat().trim().equals("")) {
						wcfs[i] = StyleUtil.getNumberWritableCellFormat(bodyContent, column,
							new jxl.write.NumberFormat(column.getFormat()));
					} else
						wcfs[i] = StyleUtil.getNumberWritableCellFormat(bodyContent, column,
							new jxl.write.NumberFormat(NUMBER_FORMAT));
				} else if (column.getDatatype().equals(NUMBER_01)) {
					datatypes[i] = NUMBER_01;
					if (column.getFormat() != null && !column.getFormat().trim().equals("")) {
						wcfs[i] = StyleUtil.getNumberWritableCellFormat(bodyContent, column,
							new jxl.write.NumberFormat(column.getFormat()));
					} else
						wcfs[i] = StyleUtil.getNumberWritableCellFormat(bodyContent, column,
							new jxl.write.NumberFormat(NUMBER_FORMAT));
				} else if (column.getDatatype().equals(DATETIME)) {
					datatypes[i] = DATETIME;
					if (column.getFormat() != null && !column.getFormat().trim().equals("")) {
						wcfs[i] = StyleUtil.getDateWritableCellFormat(bodyContent, column, new DateFormat(column
							.getFormat()));
					} else
						wcfs[i] = StyleUtil.getDateWritableCellFormat(bodyContent, column, new DateFormat(DATE_FORMAT));
				} else {
					datatypes[i] = column.getDatatype();
					wcfs[i] = StyleUtil.getWritableCellFormat(bodyContent, column);
				}
				if( column.getProperty()!=null){
					properties[propindex] = column.getProperty();
					propindex+=1;
				}
				colWidth = StyleUtil.getColumnWidth(bodyContent, column);
				sheet.setColumnView(i + 1, colWidth);

			}
		} else {
			int propindex=0;
			for (int i = 0; i < columns.size(); i++) {
				column = (Column) columns.get(i);
				// 默认为字符串类型
				if (column.getDatatype() == null || column.getDatatype().equals("")) {
					datatypes[i] = VARCHAR;
					wcfs[i] = StyleUtil.getWritableCellFormat(bodyContent, column);
				} else if (column.getDatatype().equals(NUMBER_01)) {
					datatypes[i] = NUMBER_01;
					if (column.getFormat() != null && !column.getFormat().trim().equals("")) {
						wcfs[i] = StyleUtil.getNumberWritableCellFormat(bodyContent, column,
							new jxl.write.NumberFormat(column.getFormat()));
					} else
						wcfs[i] = StyleUtil.getNumberWritableCellFormat(bodyContent, column,
							new jxl.write.NumberFormat(NUMBER_FORMAT));
				} else if (column.getDatatype().equals(NUMBER)) {
					datatypes[i] = NUMBER;
					if (column.getFormat() != null && !column.getFormat().trim().equals("")) {
						wcfs[i] = StyleUtil.getNumberWritableCellFormat(bodyContent, column,
							new jxl.write.NumberFormat(column.getFormat()));
					} else
						wcfs[i] = StyleUtil.getNumberWritableCellFormat(bodyContent, column,
							new jxl.write.NumberFormat(NUMBER_FORMAT));
				} else if (column.getDatatype().equals(DATETIME)) {
					datatypes[i] = DATETIME;
					if (column.getFormat() != null && !column.getFormat().trim().equals("")) {
						wcfs[i] = StyleUtil.getDateWritableCellFormat(bodyContent, column, new DateFormat(column
							.getFormat()));
					} else
						wcfs[i] = StyleUtil.getDateWritableCellFormat(bodyContent, column, new DateFormat(DATE_FORMAT));
				} else {
					datatypes[i] = column.getDatatype();
					wcfs[i] = StyleUtil.getWritableCellFormat(bodyContent, column);
				}
				if( column.getProperty()!=null){
					properties[propindex] = column.getProperty();
					propindex+=1;
				}
				colWidth = StyleUtil.getColumnWidth(bodyContent, column);
				sheet.setColumnView(i, colWidth);
				if(i==(columns.size()-1) && i>=propindex)
					sheet.setColumnView(propindex-1, 20);
			}
		}
		/**
		 * ***********************************把日期格式数字格式扩展为可配置的，每列支持各自配的日期格式或数字格式
		 * end***********************************************
		 */
		// 设置列头行高
		int colrows = bodyContent.getRowIndex() - columnHeader.getRowIndex();
		if (colrows < 0) {
			throw new BizException(new SysMessage(
				"<column-header>的rowIndex属性与<body-content>的rowIndex属性设置不正确，影响列头的正常显示！"));
		}
		for (int i = columnHeader.getRowIndex(); i < bodyContent.getRowIndex(); i++) {
			sheet.setRowView(i, columnHeader.getHeight());
		}

		// 设置数据体行高
		int rows = dataList.size();
		for (int i = startRowIndex, count = rows + startRowIndex; i < count; i++) {
			sheet.setRowView(i, bodyContent.getRowHeight());
		}

		/**
		 * *****************************************start***********************
		 * ***************
		 */

		// 列标题
		if (showRownum) {
			// 序号样式
			wcf = rownum.getWritableCellFormat();
			// 设置样式，并完成索引初始化
			rownum.setSheetStyle(sheet, columnHeader);
			labelCF = new Label(rownum.getColIndex(), rownum.getRowIndex(), rownum.getName(), wcf);
			sheet.addCell(labelCF);
		}
		//表头列生成
		for (int i = 0; i < columns.size(); i++) {
			column = (Column) columns.get(i);

			/**
			 * ********列标题样式,如果每个列<column>没有设定，则以<column-header>的设置为主***********
			 * ***
			 */

			wcf = StyleUtil.getWritableCellFormat(columnHeader, column);
			// 设置样式，并完成索引初始化
			column.setSheetStyle(sheet, columnHeader, showRownum);

			if (log.isDebugEnabled()) {
				log.debug("列头的 colIndex=" + column.getColIndex() + ";rowIndex=" + column.getRowIndex());
			}
			wcf.setWrap(true);
			labelCF = new Label(column.getColIndex(), column.getRowIndex(), column.getName(), wcf);
			sheet.addCell(labelCF);

			/**
			 * *****************************************************************
			 * *****************
			 */
		}
		// 显示虚拟列
		// List virtualcolList = bodyContent.getVirtualcolList();
		// Virtualcol virtualcol = null;
		Virtualcol virtualcol = null;
		Object virtualcols = bodyContent.getVirtualcols();
		List virtualcolList = new ArrayList();// =
		// bodyContent.getVirtualcolList();
		if (virtualcols instanceof List) {
			virtualcolList = (List) virtualcols;
		}

		for (int k = 0, size = virtualcolList.size(); k < size; k++) {
			virtualcol = (Virtualcol) virtualcolList.get(k);

			wcf = StyleUtil.getWritableCellFormat(columnHeader, virtualcol);
			// 设置样式，并完成索引初始化
			virtualcol.setSheetStyle(sheet, columnHeader);
			labelCF = new Label(virtualcol.getColIndex(), virtualcol.getRowIndex(), virtualcol.getName(), wcf);
			sheet.addCell(labelCF);
		}
		/**
		 * ****************************************************end**************
		 * **************************
		 */

		// 数据体样式
		wcf = bodyContent.getWritableCellFormat();
		// 自动断行
		// wcf.setWrap(true);

		Number numberCF = null;
		Boolean blnCF = null;
		DateTime datetimeCF = null;

		// 数字型格式化
		jxl.write.NumberFormat nf = new jxl.write.NumberFormat(NUMBER_FORMAT);
		WritableCellFormat wcfNF = StyleUtil.getNumberWritableCellFormat(bodyContent, nf);

		// 日期型格式化
		DateFormat df = new DateFormat(DATE_FORMAT);
		WritableCellFormat wcfDF = StyleUtil.getDateWritableCellFormat(bodyContent, df);

		// 获取单位
		double unit = Double.parseDouble(String.valueOf(bodyContent.getUnit()));
		if (unit == 0) {
			unit = 1;
		}
		if (log.isDebugEnabled()) {
			log.debug("货币单位为：" + unit);
		}

		Object propValue = null;
		if (showRownum) {
			for (int i = 0; i < rows; i++) {
				// 计算序号
				int rowNO = (i + 1);
				if (style.isReorder()) {
					rowNO += (sheetNO * sheetSize);
				}
				numberCF = new Number(startColIndex, i + startRowIndex, rowNO, wcf);
				sheet.addCell(numberCF);
				for (int j = startColIndex + 1; j < properties.length + startColIndex + 1; j++) {

					/**
					 * *******************************写单元格开始********************
					 * ****************************
					 */

					if (properties[j - (startColIndex + 1)].indexOf(".") > 0) {
						Object firstValue = PropertyUtils.getProperty(dataList.get(i),
							properties[j - (startColIndex + 1)].substring(0, properties[j - (startColIndex + 1)]
								.indexOf(".")));
						if (firstValue == null) {
							propValue = "";
						} else {
							propValue = PropertyUtils.getProperty(dataList.get(i), properties[j - (startColIndex + 1)]);
						}
					} else {
						propValue = PropertyUtils.getProperty(dataList.get(i), properties[j - (startColIndex + 1)]);
					}

					// 增加Excel导出计算公式
					if (datatypes[j - (startColIndex + 1)].equals(VARCHAR)) {
						if (propValue == null) {
							propValue = "";
						}
						// 如果行有HTML断行符
						propValue = StringUtils.replace(propValue.toString(), "</br>", "");
						// 判断是否是数值类型
						if (MathUtil.isNumber(propValue.toString())) {
							if (propValue == null) {
								labelCF = new Label(j, i + startRowIndex, "", wcfs[j - (startColIndex + 1)]);
								sheet.addCell(labelCF);
							} else {
								// 考虑单位
								double num = MathUtil.divide(Double.parseDouble(propValue.toString()), unit, 2);
								numberCF = new Number(j, i + startRowIndex, num, wcfs[j - (startColIndex + 1)]);
								sheet.addCell(numberCF);
							}

						} else {
							if (propValue.toString().indexOf(this.FORMULA) < 0) {
								if (propValue.toString().indexOf(this.PERCENT_FLOAT) < 0) {
									labelCF = new Label(j, i + startRowIndex, propValue.toString(),
										wcfs[j - (startColIndex + 1)]);
									sheet.addCell(labelCF);
								} else {
									labelCF = new Label(j, i + startRowIndex, propValue.toString().replace(
										this.PERCENT_FLOAT, ""), StyleUtil.getPercentFloat(bodyContent));
									sheet.addCell(labelCF);
								}

							} else {
								if (propValue.toString().indexOf(this.PERCENT_FLOAT) < 0) {
									Formula f = new Formula(j, i + startRowIndex, propValue.toString().replace(
										this.FORMULA, ""), wcfNF);
									sheet.addCell(f);
								} else {
									Formula f = new Formula(j, i + startRowIndex, propValue.toString().replace(
										this.FORMULA, "").replace(this.PERCENT_FLOAT, ""), StyleUtil
										.getPercentFloat(bodyContent));
									sheet.addCell(f);
								}
							}
						}
					} else if (datatypes[j - (startColIndex + 1)].equals(VARCHAR_1)) {
						labelCF = new Label(j, i + startRowIndex, propValue == null ? "" : propValue.toString(),
							wcfs[j - (startColIndex + 1)]);
						sheet.addCell(labelCF);
					} else if (datatypes[j - (startColIndex + 1)].equals(NUMBER)) {
						if (propValue == null) {
							labelCF = new Label(j, i + startRowIndex, "", wcfs[j - (startColIndex + 1)]);
							sheet.addCell(labelCF);
						} else {
							// 考虑单位
							double num = MathUtil.divide(Double.parseDouble(propValue.toString()), unit, 2);
							numberCF = new Number(j, i + startRowIndex, num, wcfs[j - (startColIndex + 1)]);
							sheet.addCell(numberCF);
						}
					} else if (datatypes[j - (startColIndex + 1)].equals(NUMBER_01)) {
						if (propValue == null) {

							labelCF = new Label(j, i + startRowIndex, "", wcfs[j - (startColIndex + 1)]);
							sheet.addCell(labelCF);
						} else {

							double num = MathUtil.divide(Double.parseDouble(propValue.toString()), 10000, 2);
							numberCF = new Number(j, i + startRowIndex, num, wcfs[j - (startColIndex + 1)]);
							sheet.addCell(numberCF);
						}
					} else if (datatypes[j - (startColIndex + 1)].equals(BOOLEAN)) {
						if (propValue == null) {
							propValue = "false";
						}
						blnCF = new jxl.write.Boolean(j, i + startRowIndex, java.lang.Boolean.getBoolean(propValue
							.toString()), wcfs[j - (startColIndex + 1)]);
						sheet.addCell(blnCF);
					} else if (datatypes[j - (startColIndex + 1)].equals(DATETIME)) {
						if (log.isDebugEnabled()) {
							log.debug("propValue===" + propValue);
						}
						if (propValue == null) {
							labelCF = new Label(j, i + startRowIndex, "", wcfs[j - (startColIndex + 1)]);
							sheet.addCell(labelCF);
						} else {
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(CalendarUtil.parseDate(propValue));
							datetimeCF = new DateTime(j, i + startRowIndex, calendar.getTime(),
								wcfs[j - (startColIndex + 1)]);
							sheet.addCell(datetimeCF);
						}

					} else {
						throw new BizException(new SysMessage("请为<column> 指定正确的数据类型！"));
					}

					/**
					 * *******************************写单元格结束********************
					 * ****************************
					 */
				}
			}
		} else {
			for (int i = 0; i < rows; i++) {
				for (int j = startColIndex; j < properties.length + startColIndex; j++) {

					/**
					 * *******************************写单元格开始********************
					 * ****************************
					 */
					if (properties[j - startColIndex] != null) {
						if (properties[j - startColIndex].indexOf(".") > 0) {
							Object firstValue = PropertyUtils.getProperty(dataList.get(i),
								properties[j - startColIndex].substring(0, properties[j - startColIndex].indexOf(".")));
							if (firstValue == null) {
								propValue = "";
							} else {
								propValue = PropertyUtils.getProperty(dataList.get(i), properties[j - startColIndex]);
							}
						} else {
							propValue = PropertyUtils.getProperty(dataList.get(i), properties[j - startColIndex]);
						}
					} else
						propValue = "";

					if (datatypes[j - (startColIndex)].equals(VARCHAR)) {
						if (propValue == null) {
							propValue = "";
						}
						// 如果行有HTML断行符
						propValue = StringUtils.replace(propValue.toString(), "</br>", "");
						wcf=StyleUtil.getWritableCellFormat(bodyContent, column);
						//改变单元格的颜色和值
						if ( propValue.toString().equals("绿色")) {
							try {
								wcf.setBackground(Colour.GREEN);
								propValue="";
							} catch (Exception e) {
//								System.out.println(propValue.toString());
							}
						}
						if ( propValue.toString().equals("计划")) {
							try {
								wcf.setBackground(Colour.BRIGHT_GREEN);
							} catch (Exception e) {
							}
						}
						if ( propValue.toString().equals("实际")) {
							try {
								wcf.setBackground(Colour.YELLOW);
							} catch (Exception e) {
							}
						}
						
						labelCF = new Label(j, i + startRowIndex, propValue.toString(), wcf);
						wcf.setWrap(true);
						sheet.addCell(labelCF);
					} else if (datatypes[j - (startColIndex)].equals(NUMBER)) {
						if (CommonUtil.isEmpty(propValue)) {
							labelCF = new Label(j, i + startRowIndex, "", wcfs[j - (startColIndex)]);
							sheet.addCell(labelCF);
						} else {
							// 考虑单位
							double num = MathUtil.divide(Double.parseDouble(propValue.toString()), unit, 2);
							numberCF = new Number(j, i + startRowIndex, num, wcfs[j - (startColIndex)]);
							sheet.addCell(numberCF);
						}

					} else if (datatypes[j - (startColIndex)].equals(NUMBER_01)) {
						if (propValue == null) {
							labelCF = new Label(j, i + startRowIndex, "", wcfs[j - (startColIndex)]);
							sheet.addCell(labelCF);
						} else {

							double num = MathUtil.divide(Double.parseDouble(propValue.toString()), 10000, 2);
							numberCF = new Number(j, i + startRowIndex, num, wcfs[j - (startColIndex)]);
							sheet.addCell(numberCF);
						}
					} else if (datatypes[j - (startColIndex)].equals(BOOLEAN)) {
						if (propValue == null) {
							propValue = "false";
						}
						blnCF = new jxl.write.Boolean(j, i + startRowIndex, java.lang.Boolean.getBoolean(propValue
							.toString()), wcfs[j - (startColIndex)]);
						sheet.addCell(blnCF);
					} else if (datatypes[j - (startColIndex)].equals(DATETIME)) {
						if (log.isDebugEnabled()) {
							log.debug("propValue===" + propValue);
						}
						// cql 2014-7-4 去掉日期为空时，给予的初值DEF_DATE begin
						if (propValue == null) {
							labelCF = new Label(j, i + startRowIndex, "", wcfs[j - (startColIndex + 1)]);
							sheet.addCell(labelCF);
							// propValue = DEF_DATE;
						} else {
							datetimeCF = new DateTime(j, i + startRowIndex, Date.valueOf(propValue.toString()), wcfDF);
							sheet.addCell(datetimeCF);
							// CCBCalendar calendar = (CCBCalendar)propValue ;
							// datetimeCF = new DateTime(j, i + startRowIndex,
							// calendar.getSqlDate(), wcfs[j - (startColIndex +
							// 1)]);
							// sheet.addCell(datetimeCF);
						}
						// cql 2014-7-4 去掉日期为空时，给予的初值DEF_DATE begin
					} else {
						throw new BizException(new SysMessage("请为<column> 指定正确的数据类型！"));
					}

					/*
					 * labelCF = new Label(j, i + startRowIndex,
					 * propValue.toString(), wcf); sheet.addCell(labelCF);
					 */
					/**
					 * *******************************写单元格结束********************
					 * ****************************
					 */
				}
			}
		}
	}

	/**
	 * 
	 * 功能描述：生成报表标题 参数说明：
	 * 
	 * @param sheet
	 * @param style
	 * @throws BizException
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	private void createTitle(WritableSheet sheet, StyleSheet style) throws BizException, WriteException,
			IllegalAccessException, NoSuchMethodException, InvocationTargetException, NumberFormatException {
		// 标题样式
		Title title = style.getTitle();
		if (title != null) {
			title.setSheetStyle(sheet);
			WritableCellFormat wcf = title.getWritableCellFormat();
			wcf.setWrap(true);
			Label labelCF = new Label(title.getColIndex(), title.getRowIndex(), title.getRealName(), wcf);
			sheet.addCell(labelCF);

			// 标题的域
			List fields = title.getFields();
			for (Iterator ite = fields.iterator(); ite.hasNext();) {
				Field field = (Field) ite.next();
				field.setSheetStyle(sheet);
				wcf = field.getWritableCellFormat();
				labelCF = new Label(field.getRealColIndex(), field.getRealRowIndex(), field.getRealValue(), wcf);
				sheet.addCell(labelCF);
			}
		}
	}

	/**
	 * 
	 * 功能描述：生成报表页眉 参数说明：
	 * 
	 * @param sheet
	 * @param style
	 * @throws BizException
	 * @throws WriteException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unchecked")
	private void createPageHeader(WritableSheet sheet, StyleSheet style) throws BizException, WriteException,
			IllegalAccessException, NoSuchMethodException, InvocationTargetException, NumberFormatException {
		// 页眉
		PageHeader pageHeader = style.getPageHeader();
		if (pageHeader != null) {
			int rowHeight = pageHeader.getRowHeight();
			if (rowHeight == 0) {
				// 默认值
				rowHeight = 400;
			}
			List fields = pageHeader.getFields();
			for (Iterator ite = fields.iterator(); ite.hasNext();) {
				Field field = (Field) ite.next();
				field.setSheetStyle(sheet);
				WritableCellFormat wcf = StyleUtil.getWritableCellFormat(pageHeader, field);
				wcf.setWrap(true);
				Label labelCF = new Label(field.getRealColIndex(), field.getRealRowIndex(), field.getRealValue(), wcf);
				sheet.addCell(labelCF);

				// 根据域索引，设置页眉行高
				sheet.setRowView(field.getRealRowIndex(), rowHeight);
			}
		}
	}

	/**
	 * 
	 * 功能描述：生成报表页脚
	 * 
	 * @param sheet
	 * @param style
	 */
	@SuppressWarnings("unchecked")
	private void createPageFooter(WritableSheet sheet, StyleSheet style) throws BizException, WriteException,
			IllegalAccessException, NoSuchMethodException, InvocationTargetException, NumberFormatException {

		PageFooter pageFooter = style.getPageFooter();
		if (pageFooter != null) {
			int rowHeight = pageFooter.getRowHeight();
			if (rowHeight == 0) {
				// 默认值
				rowHeight = 400;
			}
			List fields = pageFooter.getFields();
			// chenjiancong add ：页脚随内容体的行数向下移动的行数开始
			int size = 0;
			if (style.getBodyContent() != null) {
				List list = (List) style.getBodyContent().getRealValue();
				if (list != null) {
					if (log.isDebugEnabled()) {
						log.debug("list !=null");
					}
					size = list.size();
				}
			}
			if (log.isDebugEnabled()) {
				log.debug("size =" + size);
			}
			// 页脚随内容体的行数向下移动的行数结束,
			for (Iterator ite = fields.iterator(); ite.hasNext();) {
				Field field = (Field) ite.next();
				// 根据内容体重新设置页脚的行索引
				field.setRowIndex((field.getRealRowIndex() + size) + "");
				field.setSheetStyle(sheet);
				if (log.isDebugEnabled()) {
					log.debug("datatype=" + field.getDatatype());
					log.debug("x=" + field.getRealColIndex());
					log.debug("y=" + field.getRealRowIndex());
					log.debug("w=" + field.getColspan());
					log.debug("h=" + field.getRowspan());
				}
				// 导出图片
				if (field.getDatatype() != null && field.getDatatype().equals("image")) {
					if (log.isDebugEnabled()) {
						log.debug("imageURL=" + System.getProperty("java.io.tmpdir") + File.separator + field
							.getRealValue());
					}
					java.io.File image = new File(System.getProperty("java.io.tmpdir") + File.separator + field
						.getRealValue());
					sheet.addImage(new WritableImage(field.getRealColIndex(), field.getRealRowIndex(), field
						.getColspan(), field.getRowspan(), image));
				} else if (field.getDatatype() != null && field.getDatatype().equals(NUMBER)) {
					double num = field.getRealValueOfNumber();
					WritableCellFormat wcf = StyleUtil.getWritableCellFormat(pageFooter, field);
					Number numberCF = new Number(field.getRealColIndex(), field.getRealRowIndex(), num, wcf);
					sheet.addCell(numberCF);
				} else {
					WritableCellFormat wcf = StyleUtil.getWritableCellFormat(pageFooter, field);
					Label labelCF = new Label(field.getRealColIndex(), field.getRealRowIndex(), field.getRealValue(),
						wcf);
					sheet.addCell(labelCF);
				}
				// 根据域索引，设置页脚行高
				sheet.setRowView(field.getRealRowIndex(), rowHeight);

			}
		}
	}

	/**
	 * *********************************导出CSV格式的文件********
	 * 20071117***************************************
	 */

	/**
	 * 
	 * 功能描述：创建CSV文件
	 * 
	 * @param dataList
	 *            (该LIST 为使用JDBCTEMPLATE 查询所得到的MAP 的LIST)
	 * @return
	 * @throws BizException
	 */
	@SuppressWarnings("unchecked")
	private String createCSVReport(List dataList) throws BizException {
		StringBuffer text = new StringBuffer();
		try {
			if (dataList != null) {
				Map datamap = null;
				for (int i = 0, size = dataList.size(); i < size; i++) {
					datamap = (Map) dataList.get(i);

					// 写列名
					if (i == 0) {
						Object[] columns = datamap.keySet().toArray();
						for (int j = 0, len = columns.length; j < len; j++) {
							text.append(columns[j].toString());
							text.append(",");
						}
						text.append("\n");
					}

					// 写数据
					Iterator it = datamap.entrySet().iterator();
					while (it.hasNext()) {
						Entry entry = (Entry) it.next();
						Object value = entry.getValue();
						text.append(value);
						text.append(",");
					}
					text.append("\n");
				}
			}
			return text.toString();
		} catch (Exception ex) {
			throw new BizException(new SysMessage(ex.getMessage()));
		}
	}

	/**
	 * 
	 * 功能描述：跑批数据查询
	 * 
	 * @return
	 * @throws BizException
	 */
	@SuppressWarnings("unchecked")
	public String queryRunData() throws BizException {
		String sql = "select sql_text from (select sql_text,state from crms_run_sqltext order by update_date desc) where state = '1' and rownum = 1";
		List list = this.getJdbcTemplate().queryForList(sql, String.class);
		if (list != null && !list.isEmpty()) {
			String sqltext = (String) list.get(0);

			if (log.isDebugEnabled()) {
				log.debug("=========跑批查询,当前启用的SQL =" + sqltext);
			}

			list = this.getJdbcTemplate().queryForList(sqltext);
		}
		return createCSVReport(list);
	}

	/**
	 * 测试EXCEL颜色代码 功能描述： 参数说明：
	 * 
	 * @param args
	 */
	/*
	 * public static void main (String[] args) { try { SimpleDateFormat sf = new
	 * SimpleDateFormat("yyyy-MM-dd"); Calendar cl = Calendar.getInstance();
	 * 
	 * cl.set(Calendar.DAY_OF_WEEK,0); ntln("date =" + sf.format(cl.getTime()));
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	/**
	 * @return Returns the jdbcTemplate.
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate
	 *            The jdbcTemplate to set.
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
