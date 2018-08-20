/**
 * ProjectName: 客户金库系统(CTMS)
 * FileName:IStyle.java
 * function:
 * Company: tansun
 * @author:  chenjiancong
 * @version 0.1
 * Date  2009-08-01
 **/
package com.jifan.pssmis.foundation.excel;

import jxl.write.WritableCellFormat;
import jxl.write.WriteException;




/**
 * 
 * @author jifan
 *
 */
public interface IStyle {
	 //报表类型
	public static final String TABLE_TYPE = "table";	
	public static final String DETAIL_TYPE = "detail";
	
	/**
	 * 
	 *功能描述：获取单元格样式
	 * @return
	 * @throws WriteException
	 */
	public WritableCellFormat getWritableCellFormat() throws WriteException;
}
