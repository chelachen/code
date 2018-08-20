/**
 * ProjectName: 客户金库系统(CTMS)
 * FileName:ColumnHeader.java
 * function:
 * Company: tansun
 * @author:  chenjiancong
 * @version 0.1
 * Date  2009-08-01
 **/
package com.jifan.pssmis.foundation.excel.style;

import jxl.write.WritableCellFormat;
import jxl.write.WriteException;

import com.jifan.pssmis.foundation.excel.IStyle;
import com.jifan.pssmis.foundation.util.StyleUtil;



/**
 * 
 * @author jifan
 *
 */
public class ColumnHeader implements IStyle {
	private int height;
	private int width;
	private String align;
	private String valign;
	private String fontName;
	private int fontSize;
	private String fontColor;
	private String bgColor;
	private boolean bold;
	private boolean italic;
	private String border;
	private String borderLine;
	
	private int rowIndex;	//列头的行索引
	private int colIndex;	//列头的列索引
	
	/**
	 * 
	 *功能描述：获取单元格样式
	 * @return
	 * @throws WriteException
	 */
	public WritableCellFormat getWritableCellFormat() throws WriteException {
		WritableCellFormat wcf = new WritableCellFormat(StyleUtil.getFont(fontName,fontSize,fontColor,bold,italic));
		wcf.setBackground(StyleUtil.getBgColour(bgColor));
		wcf.setAlignment(StyleUtil.getAlignment(align));
		wcf.setVerticalAlignment(StyleUtil.getVerticalAlignment(valign));
		wcf.setBorder(StyleUtil.getBorder(border),StyleUtil.getBorderLineStyle(borderLine));
		return wcf;
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
	
}
