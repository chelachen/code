package com.jifan.pssmis.foundation.util;

import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.NumberFormat;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;
import jxl.write.WritableFont.FontName;

import com.jifan.pssmis.foundation.excel.style.BodyContent;
import com.jifan.pssmis.foundation.excel.style.Column;
import com.jifan.pssmis.foundation.excel.style.ColumnHeader;
import com.jifan.pssmis.foundation.excel.style.Field;
import com.jifan.pssmis.foundation.excel.style.PageFooter;
import com.jifan.pssmis.foundation.excel.style.PageHeader;
import com.jifan.pssmis.foundation.excel.style.Row;
import com.jifan.pssmis.foundation.excel.style.Virtualcol;

/**
 * 报表样式工具类
 * 
 * @author jifan
 * 
 */
public class StyleUtil {
	// 字体样式常量
	private static final UnderlineStyle[] underlineStyles = new UnderlineStyle[] {
			UnderlineStyle.DOUBLE, UnderlineStyle.DOUBLE_ACCOUNTING,
			UnderlineStyle.NO_UNDERLINE, UnderlineStyle.SINGLE,
			UnderlineStyle.SINGLE_ACCOUNTING };

	// 颜色样式常量
	private static final Colour[] colours = Colour.getAllColours();

	// 水平排列样式常量
	private static final Alignment[] alignments = new Alignment[] {
			Alignment.CENTRE, Alignment.FILL, Alignment.GENERAL,
			Alignment.JUSTIFY, Alignment.LEFT, Alignment.RIGHT };

	// 垂直排列样式常量
	private static final VerticalAlignment[] valignments = new VerticalAlignment[] {
			VerticalAlignment.CENTRE, VerticalAlignment.TOP,
			VerticalAlignment.BOTTOM, VerticalAlignment.JUSTIFY };

	// 边框样式常量
	private static final Border[] borders = new Border[] { Border.ALL,
			Border.LEFT, Border.RIGHT, Border.TOP, Border.BOTTOM, Border.NONE };

	// 边框线样式常量
	private static final BorderLineStyle[] borderLines = new BorderLineStyle[] {
			BorderLineStyle.DASH_DOT, BorderLineStyle.DASH_DOT_DOT,
			BorderLineStyle.DASHED, BorderLineStyle.DOTTED,
			BorderLineStyle.DOUBLE, BorderLineStyle.HAIR,
			BorderLineStyle.MEDIUM, BorderLineStyle.MEDIUM_DASH_DOT,
			BorderLineStyle.MEDIUM_DASH_DOT_DOT, BorderLineStyle.MEDIUM_DASHED,
			BorderLineStyle.NONE, BorderLineStyle.SLANTED_DASH_DOT,
			BorderLineStyle.THICK, BorderLineStyle.THIN };

	/**
	 * 
	 * 功能描述：获取字体
	 * 
	 * @param fontName
	 * @param fontSize
	 * @param fontColor
	 * @param isBold
	 * @param isItalic
	 * @return
	 */
	public static WritableFont getFont(String fontName, int fontSize,
			String fontColor, boolean isBold, boolean isItalic) {
		FontName fname = null;
		if (fontName == null) {
			fname = WritableFont.ARIAL;
		} else if (fontName.toUpperCase().equals("ARIAL")) {
			fname = WritableFont.ARIAL;
		} else if (fontName.toLowerCase().equals("COURIER")) {
			fname = WritableFont.COURIER;
		} else if (fontName.toLowerCase().equals("TAHOMA")) {
			fname = WritableFont.TAHOMA;
		} else if (fontName.toLowerCase().equals("TIMES")) {
			fname = WritableFont.TIMES;
		} else {
			fname = WritableFont.createFont(fontName);
		}
		// 设置默认大小
		if (fontSize == 0) {
			fontSize = 12;
		}
		if (isBold) {
			return new WritableFont(fname, fontSize, WritableFont.BOLD,
					isItalic, UnderlineStyle.NO_UNDERLINE,
					getFontColour(fontColor));
		} else {
			return new WritableFont(fname, fontSize, WritableFont.NO_BOLD,
					isItalic, UnderlineStyle.NO_UNDERLINE,
					getFontColour(fontColor));
		}
	}

	/**
	 * 
	 * 功能描述：获取字体修饰样式
	 * 
	 * @param style
	 *            符合Excel 的对应的样式常量
	 * @return
	 */
	public static UnderlineStyle getUnderlineStyle(String style) {
		if (style == null || style.trim().equals("")) {
			return UnderlineStyle.NO_UNDERLINE;
		}
		for (int i = 0; i < underlineStyles.length; i++) {
			UnderlineStyle underlineStyle = underlineStyles[i];
			if (underlineStyle.getDescription().equals(style.toLowerCase())) {
				return underlineStyle;
			}
		}
		return UnderlineStyle.NO_UNDERLINE;
	}

	/**
	 * 
	 * 功能描述：获取字体
	 * 
	 * @param fontName
	 *            名称符合EXCEL的字体名称
	 * @param fontSize
	 * @param fontColor
	 *            Excel的颜色描述
	 * @return
	 */
	public static WritableFont getFont(String fontName, int fontSize,
			String fontColor) {
		return getFont(fontName, fontSize, fontColor, false, false);
	}

	/**
	 * 
	 * 功能描述：根据颜色描述获取颜色
	 * 
	 * @param color
	 *            具体颜色描述参见 /doc/xml/color.xls
	 * @return
	 */
	public static Colour getColour(String color) {
		if (color == null || color.trim().equals("")) {
			return Colour.AUTOMATIC;
		}
		for (int i = 0; i < colours.length; i++) {
			Colour colour = colours[i];
			if (colour.getDescription().equals(color.toLowerCase())) {
				return colour;
			}
		}
		return Colour.AUTOMATIC;
	}

	/**
	 * 
	 * 功能描述：根据颜色描述获取背景颜色
	 * 
	 * @param color
	 *            符合excel 的颜色描述
	 * @return
	 */
	public static Colour getBgColour(String color) {
		if (getColour(color) == Colour.AUTOMATIC) {
			return Colour.WHITE;
		}
		return getColour(color);
	}

	/**
	 * 
	 * 功能描述：根据颜色描述获取字体颜色
	 * 
	 * @param color
	 *            符合excel 的颜色描述
	 * @return
	 */
	public static Colour getFontColour(String color) {
		return getColour(color);
	}

	/**
	 * 
	 * 功能描述：获取水平排列样式
	 * 
	 * @param algin
	 *            符合excel 的样式描述
	 * @return
	 */
	public static Alignment getAlignment(String algin) {
		if (algin == null || algin.trim().equals("")) {
			return Alignment.LEFT;
		}
		for (int i = 0; i < alignments.length; i++) {
			Alignment alignment = alignments[i];
			if (alignment.getDescription().equals(algin.toLowerCase())) {
				return alignment;
			}
		}
		return Alignment.LEFT;
	}

	/**
	 * 
	 * 功能描述：获取垂直排列样式
	 * 
	 * @param valgin
	 *            符合excel 的样式描述
	 * @return
	 */
	public static VerticalAlignment getVerticalAlignment(String valgin) {
		if (valgin == null || valgin.trim().equals("")) {
			return VerticalAlignment.CENTRE;
		}
		for (int i = 0; i < valignments.length; i++) {
			VerticalAlignment valignment = valignments[i];
			if (valignment.getDescription().equals(valgin.toLowerCase())) {
				return valignment;
			}
		}
		return VerticalAlignment.CENTRE;
	}

	/**
	 * 
	 * 功能描述：获取边框样式
	 * 
	 * @param borderStyle
	 *            符合excel 对应的样式描述
	 * @return
	 */
	public static Border getBorder(String borderStyle) {
		if (borderStyle == null || borderStyle.trim().equals("")) {
			return Border.ALL;
		}
		for (int i = 0; i < borders.length; i++) {
			Border border = borders[i];
			if (border.getDescription().equals(borderStyle.toLowerCase())) {
				return border;
			}
		}
		return Border.ALL;
	}

	/**
	 * 
	 * 功能描述：获取边框线样式
	 * 
	 * @param borderLineStyle
	 *            符合excel 对应的样式描述
	 * @return
	 */
	public static BorderLineStyle getBorderLineStyle(String borderLineStyle) {
		if (borderLineStyle == null || borderLineStyle.trim().equals("")) {
			return BorderLineStyle.THIN;
		}
		for (int i = 0; i < borderLines.length; i++) {
			BorderLineStyle borderLine = borderLines[i];
			if (borderLine.getDescription().equals(
					borderLineStyle.toLowerCase())) {
				return borderLine;
			}
		}
		return BorderLineStyle.THIN;
	}

	/**
	 * 
	 * 功能描述：获取单元样式，以column中设置为主，以columnHeader中设置为辅
	 * 
	 * @param columnHeader
	 * @param column
	 * @return
	 * @throws WriteException
	 */
	public static WritableCellFormat getWritableCellFormat(
			ColumnHeader columnHeader, Column column) throws WriteException {
		String fontName = column.getFontName();
		int fontSize = column.getFontSize();
		String fontColor = column.getFontColor();
		String bgColor = column.getBgColor();
		String align = column.getAlign();
		String valign = column.getValign();
		boolean isBold = column.isBold();
		boolean isItalic = column.isItalic();
		String border = column.getBorder();
		String borderLine = column.getBorderLine();
		if (fontName == null || fontName.trim().equals("")) {
			fontName = columnHeader.getFontName();
		}
		if (fontSize == 0) {
			fontSize = columnHeader.getFontSize();
		}
		if (fontColor == null || fontColor.trim().equals("")) {
			fontColor = columnHeader.getFontColor();
		}
		if (bgColor == null || bgColor.trim().equals("")) {
			bgColor = columnHeader.getBgColor();
		}
		if (align == null || align.trim().equals("")) {
			align = columnHeader.getAlign();
		}
		if (valign == null || valign.trim().equals("")) {
			valign = columnHeader.getValign();
		}
		// 如果在column中设置TRUE，则以column为主；否则以column-header的设置为主；
		if (!isBold) {
			isBold = columnHeader.isBold();
		}
		// 如果在column中设置TRUE，则以column为主；否则以column-header的设置为主；
		if (!isItalic) {
			isItalic = columnHeader.isItalic();
		}
		if (border == null || border.trim().equals("")) {
			border = columnHeader.getBorder();
		}
		if (borderLine == null || borderLine.trim().equals("")) {

			borderLine = columnHeader.getBorderLine();
		}
		WritableCellFormat wcf = new WritableCellFormat(StyleUtil.getFont(
				fontName, fontSize, fontColor, isBold, isItalic));
		wcf.setBorder(StyleUtil.getBorder(border), StyleUtil
				.getBorderLineStyle(borderLine));
		Colour colour = StyleUtil.getBgColour(bgColor);
		wcf.setBackground(colour);
		Alignment alignment = StyleUtil.getAlignment(align);
		wcf.setAlignment(alignment);
		VerticalAlignment valignment = StyleUtil.getVerticalAlignment(valign);
		wcf.setVerticalAlignment(valignment);
		return wcf;
	}

	/**
	 * 
	 * 功能描述：获取单元样式，以virtualcol中设置为主，以columnHeader中设置为辅
	 * 
	 * @param columnHeader
	 * @param virtualcol
	 * @return
	 * @throws WriteException
	 */
	public static WritableCellFormat getWritableCellFormat(
			ColumnHeader columnHeader, Virtualcol virtualcol)
			throws WriteException {
		String fontName = virtualcol.getFontName();
		int fontSize = virtualcol.getFontSize();
		String fontColor = virtualcol.getFontColor();
		String bgColor = virtualcol.getBgColor();
		String align = virtualcol.getAlign();
		String valign = virtualcol.getValign();
		boolean isBold = virtualcol.isBold();
		boolean isItalic = virtualcol.isItalic();
		String border = virtualcol.getBorder();
		String borderLine = virtualcol.getBorderLine();

		if (fontName == null || fontName.trim().equals("")) {
			fontName = columnHeader.getFontName();
		}
		if (fontSize == 0) {
			fontSize = columnHeader.getFontSize();
		}
		if (fontColor == null || fontColor.trim().equals("")) {
			fontColor = columnHeader.getFontColor();
		}
		if (bgColor == null || bgColor.trim().equals("")) {
			bgColor = columnHeader.getBgColor();
		}
		if (align == null || align.trim().equals("")) {
			align = columnHeader.getAlign();
		}
		if (valign == null || valign.trim().equals("")) {
			valign = columnHeader.getValign();
		}
		// 如果在column中设置TRUE，则以column为主；否则以column-header的设置为主；
		if (!isBold) {
			isBold = columnHeader.isBold();
		}
		// 如果在column中设置TRUE，则以column为主；否则以column-header的设置为主；
		if (!isItalic) {
			isItalic = columnHeader.isItalic();
		}
		if (border == null || border.trim().equals("")) {
			border = columnHeader.getBorder();
		}
		if (borderLine == null || borderLine.trim().equals("")) {

			borderLine = columnHeader.getBorderLine();
		}
		WritableCellFormat wcf = new WritableCellFormat(StyleUtil.getFont(
				fontName, fontSize, fontColor, isBold, isItalic));
		wcf.setBorder(StyleUtil.getBorder(border), StyleUtil
				.getBorderLineStyle(borderLine));
		Colour colour = StyleUtil.getBgColour(bgColor);
		wcf.setBackground(colour);
		Alignment alignment = StyleUtil.getAlignment(align);
		wcf.setAlignment(alignment);
		VerticalAlignment valignment = StyleUtil.getVerticalAlignment(valign);
		wcf.setVerticalAlignment(valignment);
		return wcf;
	}

	/**
	 * 
	 * 功能描述：获取单元样式，以field中设置为主，以pageHeader中设置为辅
	 * 
	 * @param pageHeader
	 * @param field
	 * @return
	 * @throws WriteException
	 */
	public static WritableCellFormat getWritableCellFormat(
			PageHeader pageHeader, Field field) throws WriteException {
		String fontName = field.getFontName();
		int fontSize = field.getFontSize();
		String fontColor = field.getFontColor();
		String bgColor = field.getBgColor();
		String align = field.getAlign();
		String valign = field.getValign();
		String border = field.getBorder();
		String borderLine = field.getBorderLine();

		boolean isBold = field.isBold();
		boolean isItalic = field.isItalic();

		if (fontName == null || fontName.trim().equals("")) {
			fontName = pageHeader.getFontName();
		}
		if (fontSize == 0) {
			fontSize = pageHeader.getFontSize();
		}
		if (fontColor == null || fontColor.trim().equals("")) {
			fontColor = pageHeader.getFontColor();
		}
		if (bgColor == null || bgColor.trim().equals("")) {
			bgColor = pageHeader.getBgColor();
		}
		if (align == null || align.trim().equals("")) {
			align = pageHeader.getAlign();
		}
		if (valign == null || valign.trim().equals("")) {
			valign = pageHeader.getValign();
		}
		// 如果在field中设置TRUE，则以field为主；否则以page-header的设置为主；
		if (!isBold) {
			isBold = pageHeader.isBold();
		}
		// 如果在field中设置TRUE，则以field为主；否则以page-header的设置为主；
		if (!isItalic) {
			isItalic = pageHeader.isItalic();
		}

		if (border == null || border.trim().equals("")) {
			border = pageHeader.getBorder();
		}
		if (borderLine == null || borderLine.trim().equals("")) {

			borderLine = pageHeader.getBorderLine();
		}

		WritableCellFormat wcf = new WritableCellFormat(StyleUtil.getFont(
				fontName, fontSize, fontColor, isBold, isItalic));
		wcf.setBorder(StyleUtil.getBorder(border), StyleUtil
				.getBorderLineStyle(borderLine));
		Colour colour = StyleUtil.getBgColour(bgColor);
		wcf.setBackground(colour);
		Alignment alignment = StyleUtil.getAlignment(align);
		wcf.setAlignment(alignment);
		VerticalAlignment valignment = StyleUtil.getVerticalAlignment(valign);
		wcf.setVerticalAlignment(valignment);
		return wcf;
	}

	/**
	 * 
	 * 功能描述：获取单元样式，以field中设置为主，以bodyContent中设置为辅
	 * 
	 * @param bodyContent
	 * @param field
	 * @return
	 * @throws WriteException
	 */
	public static WritableCellFormat getWritableCellFormat(
			BodyContent bodyContent, Field field) throws WriteException {
		String fontName = field.getFontName();
		int fontSize = field.getFontSize();
		String fontColor = field.getFontColor();
		String bgColor = field.getBgColor();
		String align = field.getAlign();
		String valign = field.getValign();
		boolean isBold = field.isBold();
		boolean isItalic = field.isItalic();
		String border = field.getBorder();
		String borderLine = field.getBorderLine();
		if (fontName == null || fontName.trim().equals("")) {
			fontName = bodyContent.getFontName();
		}
		if (fontSize == 0) {
			fontSize = bodyContent.getFontSize();
		}
		if (fontColor == null || fontColor.trim().equals("")) {
			fontColor = bodyContent.getFontColor();
		}
		if (bgColor == null || bgColor.trim().equals("")) {
			bgColor = bodyContent.getBgColor();
		}
		if (align == null || align.trim().equals("")) {
			align = bodyContent.getAlign();
		}
		if (valign == null || valign.trim().equals("")) {
			valign = bodyContent.getValign();
		}
		// 如果在field中设置TRUE，则以field为主；否则以body-content的设置为主；
		if (!isBold) {
			isBold = bodyContent.isBold();
		}
		// 如果在field中设置TRUE，则以field为主；否则以body-content的设置为主；
		if (!isItalic) {
			isItalic = bodyContent.isItalic();
		}
		if (border == null || border.trim().equals("")) {
			border = bodyContent.getBorder();
		}
		if (borderLine == null || borderLine.trim().equals("")) {

			borderLine = bodyContent.getBorderLine();
		}
		WritableCellFormat wcf = new WritableCellFormat(StyleUtil.getFont(
				fontName, fontSize, fontColor, isBold, isItalic));
		wcf.setBorder(StyleUtil.getBorder(border), StyleUtil
				.getBorderLineStyle(borderLine));
		Colour colour = StyleUtil.getBgColour(bgColor);
		wcf.setBackground(colour);
		Alignment alignment = StyleUtil.getAlignment(align);
		wcf.setAlignment(alignment);
		VerticalAlignment valignment = StyleUtil.getVerticalAlignment(valign);
		wcf.setVerticalAlignment(valignment);
		return wcf;
	}

	/**
	 * 
	 * 功能描述：获取单元样式，以field中设置为主，以pageFooter中设置为辅
	 * 
	 * @param pageFooter
	 * @param field
	 * @return
	 * @throws WriteException
	 */
	public static WritableCellFormat getWritableCellFormat(
			PageFooter pageFooter, Field field) throws WriteException {
		String fontName = field.getFontName();
		int fontSize = field.getFontSize();
		String fontColor = field.getFontColor();
		String bgColor = field.getBgColor();
		String align = field.getAlign();
		String valign = field.getValign();
		boolean isBold = field.isBold();
		boolean isItalic = field.isItalic();
		String border = field.getBorder();
		String borderLine = field.getBorderLine();
		if (fontName == null || fontName.trim().equals("")) {
			fontName = pageFooter.getFontName();
		}
		if (fontSize == 0) {
			fontSize = pageFooter.getFontSize();
		}
		if (fontColor == null || fontColor.trim().equals("")) {
			fontColor = pageFooter.getFontColor();
		}
		if (bgColor == null || bgColor.trim().equals("")) {
			bgColor = pageFooter.getBgColor();
		}
		if (align == null || align.trim().equals("")) {
			align = pageFooter.getAlign();
		}
		if (valign == null || valign.trim().equals("")) {
			valign = pageFooter.getValign();
		}
		// 如果在field中设置TRUE，则以field为主；否则以page-footer的设置为主；
		if (!isBold) {
			isBold = pageFooter.isBold();
		}
		// 如果在field中设置TRUE，则以field为主；否则以page-footer的设置为主；
		if (!isItalic) {
			isItalic = pageFooter.isItalic();
		}
		if (border == null || border.trim().equals("")) {
			border = pageFooter.getBorder();
		}
		if (borderLine == null || borderLine.trim().equals("")) {

			borderLine = pageFooter.getBorderLine();
		}
		/********************解决数字格式化 start**********************************************/
		WritableCellFormat wcf = null;
		if (field.getFormat() != null && !"".equals(field.getFormat()))
			wcf = new WritableCellFormat(StyleUtil.getFont(fontName, fontSize,
					fontColor, isBold, isItalic), new jxl.write.NumberFormat(
					field.getFormat()));
		else
			wcf = new WritableCellFormat(StyleUtil.getFont(fontName, fontSize,
					fontColor, isBold, isItalic));
		/*******************解决数字格式化 end***********************************************/
		wcf.setBorder(StyleUtil.getBorder(border), StyleUtil
				.getBorderLineStyle(borderLine));
		Colour colour = StyleUtil.getBgColour(bgColor);
		wcf.setBackground(colour);
		Alignment alignment = StyleUtil.getAlignment(align);
		wcf.setAlignment(alignment);
		VerticalAlignment valignment = StyleUtil.getVerticalAlignment(valign);
		wcf.setVerticalAlignment(valignment);
		return wcf;
	}

	/**
	 * 
	 * 功能描述：获取列宽，以<column>中设置的列宽为主
	 * 
	 * @param bodyContent
	 * @param column
	 * @return
	 */
	public static int getColumnWidth(BodyContent bodyContent, Column column) {
		String[] widths = bodyContent.getColumnWidthValue();
		int index = column.getIndex();
		int width = 0;
		if (widths != null && index < widths.length) {
			width = Integer.parseInt(widths[index]);
		}
		// 如果<column>中设置的行高大于0，则覆盖掉<body-content>中的对应设置
		if (column.getWidth() > 0) {
			width = column.getWidth();
		}
		return width;
	}

	/**
	 * 
	 * 功能描述：获取行高，以<row>中设置的行高为主（详细信息的报表类型）
	 * 
	 * @param bodyContent
	 * @param row
	 * @return
	 */
	public static int getRowHeight(BodyContent bodyContent, Row row) {
		int height = bodyContent.getRowHeight();
		// 如果<row>中设置的行高大于0，则覆盖掉<body-content>中的对应设置
		if (row.getHeight() > 0) {
			height = row.getHeight();
		}
		return height;
	}

	/**
	 * 
	 * 功能描述：获取数字型格式
	 * 
	 * @param bodyContent
	 * @param nf
	 * @return
	 * @throws WriteException
	 */
	public static WritableCellFormat getNumberWritableCellFormat(
			BodyContent bodyContent, NumberFormat nf) throws WriteException {
		WritableCellFormat wcfNF = new WritableCellFormat(nf);
		wcfNF.setBackground(StyleUtil.getBgColour(bodyContent.getBgColor()));
		wcfNF.setBorder(StyleUtil.getBorder(bodyContent.getBorder()), StyleUtil
				.getBorderLineStyle(bodyContent.getBorderLine()));
		wcfNF.setAlignment(StyleUtil.getAlignment(bodyContent.getAlign()));
		wcfNF.setVerticalAlignment(StyleUtil.getVerticalAlignment(bodyContent
				.getValign()));
		return wcfNF;
	}

	/**
	 * 
	 * 功能描述：获取日期型格式
	 * 
	 * @param bodyContent
	 * @param df
	 * @return
	 * @throws WriteException
	 */
	public static WritableCellFormat getDateWritableCellFormat(
			BodyContent bodyContent, DateFormat df) throws WriteException {
		WritableCellFormat wcfNF = new WritableCellFormat(df);
		wcfNF.setBackground(StyleUtil.getBgColour(bodyContent.getBgColor()));
		wcfNF.setBorder(StyleUtil.getBorder(bodyContent.getBorder()), StyleUtil
				.getBorderLineStyle(bodyContent.getBorderLine()));
		wcfNF.setAlignment(StyleUtil.getAlignment(bodyContent.getAlign()));
		wcfNF.setVerticalAlignment(StyleUtil.getVerticalAlignment(bodyContent
				.getValign()));
		return wcfNF;
	}

	/**
	 * 
	 * 功能描述：获取column日期型格式
	 * 
	 * @param bodyContent
	 * @param df
	 * @return
	 * @throws WriteException
	 */
	public static WritableCellFormat getDateWritableCellFormat(
			BodyContent bodyContent, Column column, DateFormat df)
			throws WriteException {
		String bgColor = bodyContent.getBgColor();
		String border = bodyContent.getBorder();
		String borderLine = bodyContent.getBorderLine();
		String align = column.getValalign();
		String valign = bodyContent.getValign();
		if (align == null || align.trim().equals("")) {
			align = bodyContent.getAlign();
		}
		WritableCellFormat wcf = new WritableCellFormat(df);
		wcf.setBackground(StyleUtil.getBgColour(bgColor));
		wcf.setBorder(StyleUtil.getBorder(border), StyleUtil
				.getBorderLineStyle(borderLine));
		wcf.setAlignment(StyleUtil.getAlignment(align));
		wcf.setVerticalAlignment(StyleUtil.getVerticalAlignment(valign));
		return wcf;
	}

	/**
	 * 
	 * 功能描述：获取column数字型格式
	 * 
	 * @param bodyContent
	 * @param nf
	 * @return
	 * @throws WriteException
	 */
	public static WritableCellFormat getNumberWritableCellFormat(
			BodyContent bodyContent, Column column, NumberFormat nf)
			throws WriteException {
		String bgColor = bodyContent.getBgColor();
		String border = bodyContent.getBorder();
		String borderLine = bodyContent.getBorderLine();
		String align = column.getValalign();
		String valign = bodyContent.getValign();
		if (align == null || align.trim().equals("")) {
			align = bodyContent.getAlign();
		}
		WritableCellFormat wcf = new WritableCellFormat(nf);
		wcf.setBackground(StyleUtil.getBgColour(bgColor));
		wcf.setBorder(StyleUtil.getBorder(border), StyleUtil
				.getBorderLineStyle(borderLine));
		wcf.setAlignment(StyleUtil.getAlignment(align));
		wcf.setVerticalAlignment(StyleUtil.getVerticalAlignment(valign));
		return wcf;
	}

	public static WritableCellFormat getWritableCellFormat(
			BodyContent bodyContent, Column column) throws WriteException {
		String bgColor = bodyContent.getBgColor();
		String border = bodyContent.getBorder();
		String borderLine = bodyContent.getBorderLine();
		String align = column.getValalign();
		String valign = bodyContent.getValign();
		if (align == null || align.trim().equals("")) {
			align = bodyContent.getAlign();
		}
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBackground(StyleUtil.getBgColour(bgColor));
		wcf.setBorder(StyleUtil.getBorder(border), StyleUtil
				.getBorderLineStyle(borderLine));
		wcf.setAlignment(StyleUtil.getAlignment(align));
		wcf.setVerticalAlignment(StyleUtil.getVerticalAlignment(valign));
		return wcf;
	}
	/**
	 * 
	 * 功能描述：获取百分比%数据格式
	 * 
	 * @param bodyContent
	 * @param nf
	 * @return
	 * @throws WriteException 
	 * @throws WriteException
	 */
	public static WritableCellFormat getPercentFloat(BodyContent bodyContent) throws WriteException {
		DisplayFormat displayFormat = NumberFormats.PERCENT_FLOAT;
		WritableCellFormat wcfF = new WritableCellFormat(displayFormat);
		wcfF.setBackground(StyleUtil.getBgColour(bodyContent.getBgColor()));
		wcfF.setBorder(StyleUtil.getBorder(bodyContent.getBorder()), StyleUtil
				.getBorderLineStyle(bodyContent.getBorderLine()));
		wcfF.setAlignment(StyleUtil.getAlignment(bodyContent.getAlign()));
		wcfF.setVerticalAlignment(StyleUtil.getVerticalAlignment(bodyContent
				.getValign()));
		return wcfF;
	}
}
