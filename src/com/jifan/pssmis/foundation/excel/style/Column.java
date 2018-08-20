package com.jifan.pssmis.foundation.excel.style;

import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.jifan.pssmis.foundation.excel.IStyle;
import com.jifan.pssmis.foundation.util.ExpressionEvaluatorUtil;
import com.jifan.pssmis.foundation.util.StyleUtil;


/**
 * 
 * @author jifan
 *
 */
public class Column implements IStyle {
	private int index;
	private String name;
	private String property;
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
	private int width;
	
	//控制列是否有效，如果无效则不显示。
	private String disable;	//支持表达式，可动态计算
	

	private int rowIndex;
	private int colIndex;		//尚未支持表达式(有待扩展)
	private int rowspan;
	private int colspan;
	private int rowOffset;		//行偏移量，以为bodyContent的rowIndex基准
	/****************************************************************************/
	private String datatype;	//数据类型
	private String format; //数据显示格式
	private String valalign;//数据对齐方式
	
	/**
	 * 
	 *功能描述：	判断列是否有效
	 * @return	如果表达式返回 true 或 "true" 时则有效，否则无效。
	 */
	public boolean isDisabled() {
		Object rtnVal = ExpressionEvaluatorUtil.getExprValue(disable);
		String val = rtnVal == null ? "false" : rtnVal.toString();
		return Boolean.valueOf(val.trim()).booleanValue();
	}
	/**
	 * 获取单元格格式
	 */
	public WritableCellFormat getWritableCellFormat() throws WriteException {
		WritableCellFormat wcf = new WritableCellFormat(StyleUtil.getFont(fontName,fontSize,fontColor,bold,italic));
		wcf.setBackground(StyleUtil.getBgColour(bgColor));
		wcf.setBorder(StyleUtil.getBorder(border),StyleUtil.getBorderLineStyle(borderLine));
		wcf.setAlignment(StyleUtil.getAlignment(align));
		wcf.setVerticalAlignment(StyleUtil.getVerticalAlignment(valign));
		return wcf;
	}
	/**
	 * 
	 *功能描述：设置工作单样式,并完成索引初始化
	 * @param sheet
	 * @param columnHeaderl
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public void setSheetStyle(WritableSheet sheet,ColumnHeader columnHeader,boolean hasRownum) throws RowsExceededException ,WriteException {
		//设置行索引
		if(this.getRowIndex() == 0) {
			this.setRowIndex(columnHeader.getRowIndex() + this.getRowOffset());
		}
		//设置列索引
		if(this.getColIndex() == 0) {
			if(hasRownum) {
				this.setColIndex(columnHeader.getColIndex() + this.getIndex() + 1);
			}else {
				this.setColIndex(columnHeader.getColIndex() + this.getIndex());
			}
		}
		
		if(this.getColspan() == 0) {
			this.setColspan(1);
		}
		if(this.getRowspan() == 0) {
			this.setRowspan(1);
		}
		if(sheet != null) {
			sheet.mergeCells(this.getColIndex(),
					this.getRowIndex(),
					this.getColIndex() + this.getColspan() - 1,
					this.getRowIndex() + this.getRowspan() - 1);
		}
	}
	
	/**
	 * @return Returns the align.
	 */
	public String getAlign() {
		return align;
	}
	/**
	 * @param align The align to set.
	 */
	public void setAlign(String align) {
		this.align = align;
	}
	/**
	 * @return Returns the valign.
	 */
	public String getValign() {
		return valign;
	}
	/**
	 * @param valign The valign to set.
	 */
	public void setValign(String valign) {
		this.valign = valign;
	}
	/**
	 * @return Returns the bgColor.
	 */
	public String getBgColor() {
		return bgColor;
	}
	/**
	 * @param bgColor The bgColor to set.
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
	 * @param border The border to set.
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
	 * @param fontColor The fontColor to set.
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
	 * @param fontName The fontName to set.
	 */
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the fontSize.
	 */
	public int getFontSize() {
		return fontSize;
	}
	/**
	 * @param fontSize The fontSize to set.
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
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
	 * @return Returns the width.
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width The width to set.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return Returns the property.
	 */
	public String getProperty() {
		return property;
	}
	/**
	 * @param property The property to set.
	 */
	public void setProperty(String property) {
		this.property = property;
	}
	/**
	 * @return Returns the bold.
	 */
	public boolean isBold() {
		return bold;
	}
	/**
	 * @param bold The bold to set.
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
	 * @param italic The italic to set.
	 */
	public void setItalic(boolean italic) {
		this.italic = italic;
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
	 * @return Returns the borderLine.
	 */
	public String getBorderLine() {
		return borderLine;
	}
	/**
	 * @param borderLine The borderLine to set.
	 */
	public void setBorderLine(String borderLine) {
		this.borderLine = borderLine;
	}
	/**
	 * @return Returns the colIndex.
	 */
	public int getColIndex() {
		return colIndex;
	}
	/**
	 * @param colIndex The colIndex to set.
	 */
	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}
	/**
	 * @return Returns the colspan.
	 */
	public int getColspan() {
		return colspan;
	}
	/**
	 * @param colspan The colspan to set.
	 */
	public void setColspan(int colspan) {
		this.colspan = colspan;
	}
	/**
	 * @return Returns the rowIndex.
	 */
	public int getRowIndex() {
		return rowIndex;
	}
	/**
	 * @param rowIndex The rowIndex to set.
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	/**
	 * @return Returns the rowspan.
	 */
	public int getRowspan() {
		return rowspan;
	}
	/**
	 * @param rowspan The rowspan to set.
	 */
	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}
	/**
	 * @return Returns the rowOffset.
	 */
	public int getRowOffset() {
		return rowOffset;
	}
	/**
	 * @param rowOffset The rowOffset to set.
	 */
	public void setRowOffset(int rowOffset) {
		this.rowOffset = rowOffset;
	}
	/**
	 * @return Returns the datatype.
	 */
	public String getDatatype() {
		return datatype;
	}
	/**
	 * @param datatype The datatype to set.
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getValalign() {
		return valalign;
	}
	public void setValalign(String valalign) {
		this.valalign = valalign;
	}
	public Column() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Column(int index, String name, String property, String align, int width, int colIndex, int rowspan, int colspan, String datatype, String format) {
		super();
		this.index = index;
		this.name = name;
		this.property = property;
		this.align = align;
		this.width = width;
		this.colIndex = colIndex;
		this.rowspan = rowspan;
		this.colspan = colspan;
		this.datatype = datatype;
		this.format = format;
	}
	
}
