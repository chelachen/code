package com.jifan.pssmis.web.backbean.sys;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;

import com.jifan.pssmis.foundation.session.SysSession;
import com.jifan.pssmis.web.backbean.base.BaseBean;

@ManagedBean(name = "popupPanelBB")
public class PopupPanelBB extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4407477180598037872L;
	
	private Boolean autosized;
	private Boolean modal;
	private Boolean resizeable;
	private Boolean moveable;
	private int width;
	private int height;
	private int width1;
	private int height1;
	
	private String styleClass;
	
	private String invoiceStyleClass;
	private int invoiceWidth;
	private int invoiceHeight;
	private int left;
	private String closeImg;//关闭图标
	private String closeImg1;
	private String closeImg2;

	public PopupPanelBB() {
		autosized=false;
		modal =false;
		resizeable=true;
		moveable=true;
		width=920;
		height=540;
		left=-10; 
		styleClass="jfPopupPennel";
		
		width1 = 600;
		height1 = 400;		
		
		invoiceWidth=0;
		invoiceHeight = 0;
		invoiceStyleClass="jfInvoicePopupPennel";
		//获取屏幕分辨率
//		Toolkit toolkit = Toolkit.getDefaultToolkit();
//        Dimension scrnsize = toolkit.getScreenSize();
//        invoiceWidth=new BigDecimal(scrnsize.width*0.9).intValue();
//        invoiceHeight=new BigDecimal(scrnsize.height*0.71).intValue();
        
        closeImg = "images:icons/delete.gif";
        closeImg1 = "images:panelclose.png";
        closeImg2 = "images:favicon.ico";
	}

	public Boolean getAutosized() {
		return autosized;
	}

	public void setAutosized(Boolean autosized) {
		this.autosized = autosized;
	}

	public Boolean getModal() {
		return modal;
	}

	public void setModal(Boolean modal) {
		this.modal = modal;
	}

	public Boolean getResizeable() {
		return resizeable;
	}

	public void setResizeable(Boolean resizeable) {
		this.resizeable = resizeable;
	}

	public Boolean getMoveable() {
		return moveable;
	}

	public void setMoveable(Boolean moveable) {
		this.moveable = moveable;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}



	public String getInvoiceStyleClass() {
		return invoiceStyleClass;
	}

	public void setInvoiceStyleClass(String invoiceStyleClass) {
		this.invoiceStyleClass = invoiceStyleClass;
	}

	public int getInvoiceWidth() {
		if(invoiceWidth==0)
			invoiceWidth=SysSession.getScreenWidth()==0?width:SysSession.getScreenWidth();
		return new BigDecimal(invoiceWidth*0.9).intValue();
	}

	public void setInvoiceWidth(int invoiceWidth) {
		this.invoiceWidth = invoiceWidth;
	}

	public int getInvoiceHeight() {
		if(invoiceHeight==0)
			invoiceHeight=SysSession.getScreenHeight()==0?height:new BigDecimal(SysSession.getScreenHeight()).intValue();
		return new BigDecimal(invoiceHeight*0.8).intValue();
	}

	public void setInvoiceHeight(int invoiceHeight) {
		this.invoiceHeight = invoiceHeight;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public String getCloseImg() {
		return closeImg;
	}

	public void setCloseImg(String closeImg) {
		this.closeImg = closeImg;
	}

	public int getWidth1() {
		return width1;
	}

	public void setWidth1(int width1) {
		this.width1 = width1;
	}

	public int getHeight1() {
		return height1;
	}

	public void setHeight1(int height1) {
		this.height1 = height1;
	}

	public String getCloseImg1() {
		return closeImg1;
	}

	public void setCloseImg1(String closeImg1) {
		this.closeImg1 = closeImg1;
	}

	public String getCloseImg2() {
		return closeImg2;
	}

	public void setCloseImg2(String closeImg2) {
		this.closeImg2 = closeImg2;
	}
	
}
