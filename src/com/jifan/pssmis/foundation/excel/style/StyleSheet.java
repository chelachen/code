package com.jifan.pssmis.foundation.excel.style;

import com.jifan.pssmis.foundation.util.ExpressionEvaluatorUtil;

/**
 * 
 * @author jifan
 *
 */
public class StyleSheet {
	private String name;	//支持表达式，可动态计算
	private boolean showGrid;
	private boolean readOnly = true;
	private boolean single = true;		//是否单页签
	private int sheetSize = 50;			//每页显示记录数
	private boolean reorder = true;		//是否重新排序
	
	private Title title;
	private PageHeader pageHeader;
	private BodyContent bodyContent;
	private PageFooter pageFooter;
	
	public static final int MAXCOLUMNNUM = 256-5;//03版本最大列数为256
	
	/**
	 * 
	 *功能描述：	获取真实名称
	 * @return
	 */
	public String getRealName() {
		return ExpressionEvaluatorUtil.getValue(name);
	}
	/**
	 * @return Returns the bodyContent.
	 */
	public BodyContent getBodyContent() {
		return bodyContent;
	}
	/**
	 * @param bodyContent The bodyContent to set.
	 */
	public void setBodyContent(BodyContent bodyContent) {
		this.bodyContent = bodyContent;
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
	 * @return Returns the pageFooter.
	 */
	public PageFooter getPageFooter() {
		return pageFooter;
	}
	/**
	 * @param pageFooter The pageFooter to set.
	 */
	public void setPageFooter(PageFooter pageFooter) {
		this.pageFooter = pageFooter;
	}
	/**
	 * @return Returns the pageHeader.
	 */
	public PageHeader getPageHeader() {
		return pageHeader;
	}
	/**
	 * @param pageHeader The pageHeader to set.
	 */
	public void setPageHeader(PageHeader pageHeader) {
		this.pageHeader = pageHeader;
	}
	/**
	 * @return Returns the readOnly.
	 */
	public boolean isReadOnly() {
		return readOnly;
	}
	/**
	 * @param readOnly The readOnly to set.
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
	/**
	 * @return Returns the showGrid.
	 */
	public boolean isShowGrid() {
		return showGrid;
	}
	/**
	 * @param showGrid The showGrid to set.
	 */
	public void setShowGrid(boolean showGrid) {
		this.showGrid = showGrid;
	}
	/**
	 * @return Returns the title.
	 */
	public Title getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(Title title) {
		this.title = title;
	}
	/**
	 * @return Returns the sheetSize.
	 */
	public int getSheetSize() {
		return sheetSize;
	}
	/**
	 * @param sheetSize The sheetSize to set.
	 */
	public void setSheetSize(int sheetSize) {
		this.sheetSize = sheetSize;
	}
	/**
	 * @return Returns the single.
	 */
	public boolean isSingle() {
		return single;
	}
	/**
	 * @param single The single to set.
	 */
	public void setSingle(boolean single) {
		this.single = single;
	}
	/**
	 * @return Returns the reorder.
	 */
	public boolean isReorder() {
		return reorder;
	}
	/**
	 * @param reorder The reorder to set.
	 */
	public void setReorder(boolean reorder) {
		this.reorder = reorder;
	}
	
}
