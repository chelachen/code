package com.jifan.pssmis.foundation.excel.style;

import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.write.WritableCellFormat;
import jxl.write.WriteException;

import com.jifan.pssmis.foundation.excel.IStyle;
import com.jifan.pssmis.foundation.util.ExpressionEvaluatorUtil;
import com.jifan.pssmis.foundation.util.StyleUtil;

/**
 * 
 * @author jifan
 *
 */
public class DateTime extends Field implements IStyle {
	private static final String DEFAULT_PATTERN = "yyyy-MM-dd";
	
	//pattern控制日期的样式：例如：yyyy-MM-dd
	private String pattern;
	
	/**
	 * 
	 *功能描述：日期显示,如果value求解的值为Date类型，则根据pattern进行格式化输出；
	 *					否则，则将其转换成字符串输出;
	 *					不支持混合表达式
	 * @return
	 */
	public String getRealValue() {
		Object dateVal = ExpressionEvaluatorUtil.getExprValue(this.getValue());
		
		if(dateVal instanceof Date) {
			Date date = (Date)dateVal;
			if(pattern == null || pattern.trim().equals("")) {
				//默认的日期格式样式
				this.setPattern(DEFAULT_PATTERN);
			}
			SimpleDateFormat sdf = new SimpleDateFormat(this.getPattern());
			return sdf.format(date);
		}else {
			return String.valueOf(dateVal);
		}
	}
	/**
	 * 获取时间域的格式
	 */
	public WritableCellFormat getWritableCellFormat() throws WriteException {
		WritableCellFormat wcf = new WritableCellFormat(StyleUtil.getFont(this.getFontName(),this.getFontSize(),this.getFontColor(),this.isBold(),this.isItalic()));
		wcf.setBackground(StyleUtil.getBgColour(this.getBgColor()));
		wcf.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
		wcf.setAlignment(StyleUtil.getAlignment(this.getAlign()));
		wcf.setVerticalAlignment(StyleUtil.getVerticalAlignment(this.getValign()));
		return wcf;
	}
	/**
	 * 
	 *功能描述：设置工作单样式包括（索引及合并的样式）
	 * @param sheet
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	/*public void setSheetStyle(WritableSheet sheet) throws RowsExceededException ,WriteException {
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
	}*/
	/**
	 * @return Returns the pattern.
	 */
	public String getPattern() {
		return pattern;
	}
	/**
	 * @param pattern The pattern to set.
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
