package com.jifan.pssmis.model.vo.sys;

import java.math.BigDecimal;
import java.util.Date;

import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.model.vo.base.BaseVO;

public class UserVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

	private String id;

	private String passWord;

	private String userCode;

	private String sysNodeID;

	private String sysNodeCode;

	private boolean choiced;

	private String tpassID;

	private Integer isEnabled;

	private String mobile;

	private String remark;

	private String email;

	private String introducer; // 介绍人

	private String portionRankName;

	private String shortUserName;

	private String qrCode;

	private Date createTime;

	private BigDecimal memberPresenterMoney; // 受益店员利润
	private BigDecimal invoiceMoney; // 受益店员销售金额

	private String headImg; // 会员头像
	
	private Integer payGrade; // 薪酬等级

	private Integer bgnAttr59; //店铺绩效等级
	
	private String bgNodeID;
	
	public UserVO() {

	}
	
	@Override
    public String toString() {
        return userName;
    }
	
	public String getUserInfo(){
		if(CommonUtil.isNotEmpty(this.mobile))
			return this.mobile;
		else if(CommonUtil.isNotEmpty(this.userName))
			return this.userName;
		else if(CommonUtil.isNotEmpty(this.userCode))
			return this.userCode;
		else
			return "";
	}

	public UserVO(String id, String userCode, String userName) {
		this.id = id;
		this.userCode = userCode;
		this.userName = userName;
	}

	public UserVO(String id, String userCode, String userName, String sysNodeID) {
		this.id = id;
		this.userCode = userCode;
		this.sysNodeID = sysNodeID;
		this.userName = userName;
	}

	public UserVO(String sysNodeID, String tpassID) {
		this.sysNodeID = sysNodeID;
		this.tpassID = tpassID;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getSysNodeID() {
		return sysNodeID;
	}

	public void setSysNodeID(String sysNodeID) {
		this.sysNodeID = sysNodeID;
	}

	public boolean isChoiced() {
		return choiced;
	}

	public void setChoiced(boolean choiced) {
		this.choiced = choiced;
	}

	public String getTpassID() {
		return tpassID;
	}

	public void setTpassID(String tpassID) {
		this.tpassID = tpassID;
	}

	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

	public String getSysNodeCode() {
		return sysNodeCode;
	}

	public void setSysNodeCode(String sysNodeCode) {
		this.sysNodeCode = sysNodeCode;
	}

	public String getPortionRankName() {
		return portionRankName;
	}

	public void setPortionRankName(String portionRankName) {
		this.portionRankName = portionRankName;
	}

	public String getShortUserName() {
		return shortUserName;
	}

	public void setShortUserName(String shortUserName) {
		this.shortUserName = shortUserName;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getMemberPresenterMoney() {
		return memberPresenterMoney;
	}

	public void setMemberPresenterMoney(BigDecimal memberPresenterMoney) {
		this.memberPresenterMoney = memberPresenterMoney;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public Integer getPayGrade() {
		return payGrade;
	}

	public void setPayGrade(Integer payGrade) {
		this.payGrade = payGrade;
	}

	public Integer getBgnAttr59() {
		return bgnAttr59;
	}

	public void setBgnAttr59(Integer bgnAttr59) {
		this.bgnAttr59 = bgnAttr59;
	}

	public String getBgNodeID() {
		return bgNodeID;
	}

	public void setBgNodeID(String bgNodeID) {
		this.bgNodeID = bgNodeID;
	}

	public BigDecimal getInvoiceMoney() {
		return invoiceMoney;
	}

	public void setInvoiceMoney(BigDecimal invoiceMoney) {
		this.invoiceMoney = invoiceMoney;
	}

}
