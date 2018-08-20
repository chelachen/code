package com.jifan.pssmis.foundation.excel.style;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.write.WritableCellFormat;
import jxl.write.WriteException;

import com.jifan.pssmis.foundation.excel.IStyle;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.util.ExpressionEvaluatorUtil;
import com.jifan.pssmis.foundation.util.StyleUtil;

/**
 * 
 * @author jifan
 * 
 */
public class BodyContent implements IStyle {
	private int rowIndex;
	private int colIndex;
	private int forzonColIndex;
	private int rowHeight;
	private int width;
	private String fontName;
	private int fontSize;
	private String fontColor;
	private String bgColor;
	private String border;
	private String borderLine;
	private String align;
	private String valign;
	private boolean bold;
	private boolean italic;
	private ColumnHeader columnHeader; // 列头
	private Rownum rownum; // 行号
	private List columns = new ArrayList();
	private String value; // 支持表达式
	private String virtualcols; // 支持表达式

	private String type; // 报表类型（table代表列表的形式；detail代表详细信息展示）
	private List fields = new ArrayList();
	private String columnWidths; // 控制列宽，多列用","隔开;例如："20,30,60"
	private List rows = new ArrayList(); // 对域分组

	private List virtualcolList = new ArrayList(); // 虚拟列

	private int unit = 1; // 金额单位

	private int currentRow; // 记录当前行数,中间存储变量

	/**
	 * 
	 * 功能描述：支持表达式,但不支持混合表达式
	 * 
	 * @return
	 */
	public Object getRealValue() {
		return ExpressionEvaluatorUtil.getExprValue(value);
	}

	/**
	 * 
	 * 功能描述：支持表达式,但不支持混合表达式
	 * 
	 * @return
	 */
	public Object getVirtualcols() {
		return ExpressionEvaluatorUtil.getExprValue(virtualcols);
	}

	/**
	 * 
	 * 功能描述： 判断是否含有序号列
	 * 
	 * @return
	 */
	public boolean hasRownum() {
		return this.getRownum() != null;
	}

	/**
	 * 获取单元样式
	 */
	public WritableCellFormat getWritableCellFormat() throws WriteException {
		WritableCellFormat wcf = new WritableCellFormat(StyleUtil.getFont(
				fontName, fontSize, fontColor, bold, italic));
		wcf.setBackground(StyleUtil.getBgColour(bgColor));
		wcf.setBorder(StyleUtil.getBorder(border), StyleUtil
				.getBorderLineStyle(borderLine));
		wcf.setAlignment(StyleUtil.getAlignment(align));
		wcf.setVerticalAlignment(StyleUtil.getVerticalAlignment(valign));
		return wcf;
	}

	/**
	 * 
	 * 功能描述： 获取列宽的值
	 * 
	 * @return
	 */
	public String[] getColumnWidthValue() {
		if (this.getColumnWidths() != null) {
			return this.getColumnWidths().split(",");
		}
		return new String[0];
	}

	/**
	 * 
	 * 功能描述： 获取内容体的所有域(包括本身包含的域以及<row>里面包含的域)
	 * 
	 * @return 内容体的所有域
	 * @throws cn.ccb.zjtscbms.common.util.BizException
	 */
	public List getAllFields() throws BizException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		List allFields = new ArrayList();
		allFields.addAll(this.getFields());
		for (Iterator ite = this.getRows().iterator(); ite.hasNext();) {
			Row row = (Row) ite.next();
			// 填加每行的真实域
			if (row.isDisabled()) {
				continue;
			}

			allFields.addAll(row.getRealFields());
		}
		return allFields;
	}

	/**
	 * @return Returns the rows.
	 */
	public List getRows() {
		return rows;
	}

	/**
	 * @param rows
	 *            The rows to set.
	 */
	public void setRows(List rows) {
		this.rows = rows;
	}

	/**
	 * @return Returns the columnWidths.
	 */
	public String getColumnWidths() {
		return columnWidths;
	}

	/**
	 * @param columnWidths
	 *            The columnWidths to set.
	 */
	public void setColumnWidths(String columnWidths) {
		this.columnWidths = columnWidths;
	}

	/**
	 * @return Returns the fields.
	 */
	public List getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            The fields to set.
	 */
	public void setFields(List fields) {
		this.fields = fields;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return Returns the columns.
	 */
	public List getColumns() {
		return columns;
	}

	/**
	 * @param columns
	 *            The columns to set.
	 */
	public void setColumns(List columns) {
		this.columns = columns;
	}

	/**
	 * @return Returns the align.
	 */
	public String getAlign() {
		return align;
	}

	/**
	 * @param align
	 *            The align to set.
	 */
	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * @return Returns the bgColor.
	 */
	public String getBgColor() {
		return bgColor;
	}

	/**
	 * @param bgColor
	 *            The bgColor to set.
	 */
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	/**
	 * @return Returns the border.
	 */
	public String getBorder() {
		return border;
	}

	/**
	 * @param border
	 *            The border to set.
	 */
	public void setBorder(String border) {
		this.border = border;
	}

	/**
	 * @return Returns the fontColor.
	 */
	public String getFontColor() {
		return fontColor;
	}

	/**
	 * @param fontColor
	 *            The fontColor to set.
	 */
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	/**
	 * @return Returns the fontName.
	 */
	public String getFontName() {
		return fontName;
	}

	/**
	 * @param fontName
	 *            The fontName to set.
	 */
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	/**
	 * @return Returns the valign.
	 */
	public String getValign() {
		return valign;
	}

	/**
	 * @param valign
	 *            The valign to set.
	 */
	public void setValign(String valign) {
		this.valign = valign;
	}

	/**
	 * @return Returns the fontSize.
	 */
	public int getFontSize() {
		return fontSize;
	}

	/**
	 * @param fontSize
	 *            The fontSize to set.
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	/**
	 * @return Returns the rowHeight.
	 */
	public int getRowHeight() {
		return rowHeight;
	}

	/**
	 * @param rowHeight
	 *            The rowHeight to set.
	 */
	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}

	/**
	 * @return Returns the width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            The width to set.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return Returns the columnHeader.
	 */
	public ColumnHeader getColumnHeader() {
		return columnHeader;
	}

	/**
	 * @param columnHeader
	 *            The columnHeader to set.
	 */
	public void setColumnHeader(ColumnHeader columnHeader) {
		this.columnHeader = columnHeader;
	}

	/**
	 * @return Returns the rownum.
	 */
	public Rownum getRownum() {
		return rownum;
	}

	/**
	 * @param rownum
	 *            The rownum to set.
	 */
	public void setRownum(Rownum rownum) {
		this.rownum = rownum;
	}

	/**
	 * @return Returns the bold.
	 */
	public boolean isBold() {
		return bold;
	}

	/**
	 * @param bold
	 *            The bold to set.
	 */
	public void setBold(boolean bold) {
		this.bold = bold;
	}

	/**
	 * @return Returns the italic.
	 */
	public boolean isItalic() {
		return italic;
	}

	/**
	 * @param italic
	 *            The italic to set.
	 */
	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	/**
	 * @return Returns the colIndex.
	 */
	public int getColIndex() {
		return colIndex;
	}

	/**
	 * @param colIndex
	 *            The colIndex to set.
	 */
	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}

	/**
	 * @return Returns the rowIndex.
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	/**
	 * @param rowIndex
	 *            The rowIndex to set.
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * @return Returns the borderLine.
	 */
	public String getBorderLine() {
		return borderLine;
	}

	/**
	 * @param borderLine
	 *            The borderLine to set.
	 */
	public void setBorderLine(String borderLine) {
		this.borderLine = borderLine;
	}

	/**
	 * @return Returns the virtualcolList.
	 */
	public List getVirtualcolList() {
		return virtualcolList;
	}

	/**
	 * @param virtualcolList
	 *            The virtualcolList to set.
	 */
	public void setVirtualcolList(List virtualcolList) {
		this.virtualcolList = virtualcolList;
	}

	/**
	 * @return Returns the unit.
	 */
	public int getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            The unit to set.
	 */
	public void setUnit(int unit) {
		this.unit = unit;
	}

	/**
	 * @return Returns the currentRow.
	 */
	public int getCurrentRow() {
		return currentRow;
	}

	/**
	 * @param currentRow
	 *            The currentRow to set.
	 */
	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public void setVirtualcols(String virtualcols) {
		this.virtualcols = virtualcols;
	}

	public int getForzonColIndex() {
		return forzonColIndex;
	}

	public void setForzonColIndex(int forzonColIndex) {
		this.forzonColIndex = forzonColIndex;
	}
	
}
