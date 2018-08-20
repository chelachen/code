package com.jifan.pssmis.model.vo.sys;

import java.util.HashMap;
import java.util.Map;

import com.jifan.pssmis.model.bo.sys.BusinessGroupBO;
import com.jifan.pssmis.model.vo.base.BaseVO;

public class UserSessionVO extends BaseVO{

	private static final long serialVersionUID = 1L;

	private String userID;
	private String userCode;
	private String userName;
	private String sysNodeID;
	private String sysNodeName;
    private BusinessGroupBO bgBO;
	private int screenWidth;
	private int screenHeight;
	//买单小数点处理
	private Integer bgnAttr36;
	//买单小数点位数
	private Integer bgnAttr37;
	
	private Map map=new HashMap();
	
	private String password;
	
	private String userOnlineId;

	private String tpassID;
	
	private int userStatus;
	
	private String bgServerUrl;
	
	private String nodeId;
	
	private String httpCookieValue;
	
	private boolean show;
	
	//节点类型
	private Integer bgnAttr11;

	public UserSessionVO(String userID, String userCode, String userName) {
		super();
		this.userID = userID;
		this.userCode = userCode;
		this.userName = userName;
	}

	public UserSessionVO() {
		super();
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
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

	public String getSysNodeID() {
		return sysNodeID;
	}

	public void setSysNodeID(String sysNodeID) {
		this.sysNodeID = sysNodeID;
	}

	public String getSysNodeName() {
		return sysNodeName;
	}

	public void setSysNodeName(String sysNodeName) {
		this.sysNodeName = sysNodeName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}



	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public Integer getBgnAttr36() {
		return bgnAttr36;
	}

	public void setBgnAttr36(Integer bgnAttr36) {
		this.bgnAttr36 = bgnAttr36;
	}

	public Integer getBgnAttr37() {
		return bgnAttr37;
	}

	public void setBgnAttr37(Integer bgnAttr37) {
		this.bgnAttr37 = bgnAttr37;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserOnlineId() {
		return userOnlineId;
	}

	public void setUserOnlineId(String userOnlineId) {
		this.userOnlineId = userOnlineId;
	}

	public String getTpassID() {
		return tpassID;
	}

	public void setTpassID(String tpassID) {
		this.tpassID = tpassID;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public String getBgServerUrl() {
		return bgServerUrl;
	}

	public void setBgServerUrl(String bgServerUrl) {
		this.bgServerUrl = bgServerUrl;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getHttpCookieValue() {
		return httpCookieValue;
	}

	public void setHttpCookieValue(String httpCookieValue) {
		this.httpCookieValue = httpCookieValue;
	}



	public boolean getShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public Integer getBgnAttr11() {
		return bgnAttr11;
	}

	public void setBgnAttr11(Integer bgnAttr11) {
		this.bgnAttr11 = bgnAttr11;
	}


	public BusinessGroupBO getBgBO() {
		return bgBO;
	}

	public void setBgBO(BusinessGroupBO bgBO) {
		this.bgBO = bgBO;
	}

}
