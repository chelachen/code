package com.jifan.pssmis.foundation.excel.style;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;

import com.jifan.pssmis.foundation.excel.IStyle;
import com.jifan.pssmis.foundation.util.DateUtil;
import com.jifan.pssmis.foundation.util.ExpressionEvaluatorUtil;
import com.jifan.pssmis.foundation.util.MathUtil;
import com.jifan.pssmis.foundation.util.StringUtil;
import com.jifan.pssmis.foundation.util.StyleUtil;





/**
 * 
 * @author jifan
 *
 */
public class Field implements IStyle {

	private String fontName;
	private int fontSize;
	private String fontColor;
	private String bgColor;
	private String align;
	private String valign;
	private boolean bold;
	private boolean italic;
	private String border;
	private String borderLine;
	
	private String value;		//支持表达式
	/*************** start****支持表达式（主要用于循环）*********/
	private String rowIndex;
	private String colIndex;
	/*************** end*************************************/
	private int rowspan;
	private int colspan;
	
	//控制域是否有效，如果无效则不显示。
	private String disable;		//支持表达式，可动态计算
	
	/*********** start ******************/
	private boolean escape;		//是否换码,如果为TRUE则会转码 即将‘&lt;’ --> ‘<’
	/*********** end ******************/
	private String datatype;
	private String format;
	private int unit;
	

	/**
	 * 
	 *功能描述：支持表达式,以及混合表达式的形式
	 *参数说明：
	 * @return
	 */
	public String getRealValue() {
		String val = ExpressionEvaluatorUtil.getValue(value);
		if(datatype != null && datatype.equals("number")){
			if(val == null || "".equals(val)){
				return "";
			}
			if(unit == 0){
				unit = 1;
			}
			if(this.getFormat()==null||this.getFormat().trim().equals("")){
				this.setFormat("#,##0.00");
			}
			double num = MathUtil.divide(Double
					.parseDouble(val.toString()), unit, 2);
			//return MathUtil.formatToMoney(num);
			return new DecimalFormat(this.getFormat()).format(num);
		}else if (datatype != null && datatype.equals("datetime")){
			if(val==null||"".equals(val)){
				return val;
			}
			if(this.getFormat()==null||this.getFormat().trim().equals("")){
				this.setFormat("yyyy-MM-dd");
			}
			SimpleDateFormat sdf = new SimpleDateFormat(this.getFormat());
			return sdf.format(DateUtil.ToDateShort(val));
		}else{
			val = StringUtils.replace(val,"</br>","");
			
			/***************** start *******************/
			if(this.isEscape()) {
				val = StringUtil.escape(val);
			}
			return val;
			/***************** end *******************/
		}
		
	}
	
	/**
	 * 
	 *功能描述：支持表达式,以及混合表达式的形式,
	 *参数说明：
	 * @return 未进行格式化的数值
	 */
	public double getRealValueOfNumber() {
		String val = ExpressionEvaluatorUtil.getValue(value);
		if(datatype != null && datatype.equals("number")){
			if(val == null || "".equals(val)){
				return 0;
			}
			if(unit == 0){
				unit = 1;
			}
			double num = MathUtil.divide(Double
					.parseDouble(val.toString()), unit, 2);
			return num;
		}
		else return 0;
		
	}
	
	/**
	 * 
	 *功能描述：	判断域是否有效
	 * @return	如果表达式返回 true 或 "true" 时则有效，否则无效。
	 */
	public boolean isDisabled() {
		Object rtnVal = ExpressionEvaluatorUtil.getExprValue(disable);
		String val = rtnVal == null ? "false" : rtnVal.toString();
		return Boolean.valueOf(val.trim()).booleanValue();
	}
	/**
	 * 
	 *功能描述： 获取单元格样式
	 * @return
	 * @throws WriteException
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
	 *功能描述：设置工作单样式
	 * @param sheet
	 * @throws RowExcedException
	 */
	public void setSheetStyle(WritableSheet sheet) throws RowsExceededException ,WriteException,NumberFormatException {
		if(this.getColspan() == 0) {
			this.setColspan(1);
		}
		if(this.getRowspan() == 0) {
			this.setRowspan(1);
		}
		if(sheet != null) {
			sheet.mergeCells(this.getRealColIndex(),
					this.getRealRowIndex(),
					this.getRealColIndex() + this.getColspan() - 1,
					this.getRealRowIndex() + this.getRowspan() - 1);
		}
	}
	/**
	 * 
	 *功能描述：真实行索引
	 * @return
	 * @throws NumberFormatException
	 */
	public int getRealRowIndex() throws NumberFormatException {
		if(this.rowIndex == null || this.rowIndex.equals("")) {
			return 0;
		}
		return Integer.valueOf(this.rowIndex).intValue();
	}
	/**
	 * 
	 *功能描述：真实列索引
	 * @return
	 * @throws NumberFormatException
	 */
	public int getRealColIndex() throws NumberFormatException  {
		if(this.colIndex == null || this.colIndex.equals("")) {
			return 0;
		}
		return Integer.valueOf(this.colIndex).intValue();
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
	 * @return Returns the colIndex.
	 */
	public String getColIndex() {
		return colIndex;
	}
	/**
	 * @param colIndex The colIndex to set.
	 */
	public void setColIndex(String colIndex) {
		this.colIndex = colIndex;
	}
	/**
	 * @return Returns the rowIndex.
	 */
	public String getRowIndex() {
		return rowIndex;
	}
	/**
	 * @param rowIndex The rowIndex to set.
	 */
	public void setRowIndex(String rowIndex) {
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
	 * @return Returns the escape.
	 */
	public boolean isEscape() {
		return escape;
	}
	/**
	 * @param escape The escape to set.
	 */
	public void setEscape(boolean escape) {
		this.escape = escape;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}	
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	public Field() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Field(int fontSize, String align, String value, String rowIndex, String colIndex, int rowspan, int colspan) {
		super();
		this.fontSize = fontSize;
		this.align = align;
		this.value = value;
		this.rowIndex = rowIndex;
		this.colIndex = colIndex;
		this.rowspan = rowspan;
		this.colspan = colspan;
	}
	
}
