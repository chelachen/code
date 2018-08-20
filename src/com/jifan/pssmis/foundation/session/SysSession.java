/*
 * Id: SysSession.java
 * Type Name: com.ccb.cclbm.common.SysSession
 * Create Date: 2005-3-16
 * Author: robert.luo
 * 
 *
 * Project: CCLBM
 *
 *
 *
 */
package com.jifan.pssmis.foundation.session;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.python.core.PyFunction;

import com.jifan.pssmis.foundation.contants.SysContants;
import com.jifan.pssmis.model.bo.sys.BusinessGroupBO;
import com.jifan.pssmis.model.vo.sys.UserSessionVO;


/**
 * this class acts as wrapper controlling all the read/write to http session
 * 
 * @author jifan
 * 
 */
@SuppressWarnings("unchecked")
public class SysSession {
	
	
	private static String bgNameShop;//商业组名称
	private static PyFunction pyFunction;
	private static int step;  //标记
	private static Map  map = new Hashtable() ;  //map

	
	/********************Session star***********************************/
	/**
	 * 获取sessionMap
	 * @return
	 */
	public static Map getSessionMap() {
//		FacesContext fc = FacesContext.getCurrentInstance();
		Map sessionMap  = map;
		if(FacesContext.getCurrentInstance() != null){
			sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		}		
		return sessionMap;
	}
	

	/**
	 * 根据登录用户，写入必要的Session信息，登录时调用
	 */
	public static void setUserInfoToSession(UserSessionVO userSession) {
		Map sessionMap = SysSession.getSessionMap();
		sessionMap.put(SysContants.SYS_USER_SESSION_VO, userSession);
	}
	

	/**
	 * 根据登录用户，写入必要的Session信息，登录时调用
	 */
	public static void setLoginCountToSession(int loginCount) {
		Map sessionMap = SysSession.getSessionMap();
		sessionMap.put(SysContants.LOGIN_COUNT_SESSION, loginCount);
	}
	
	/**
	 * 根据登录用户，获取用户登录次数，登录时调用
	 */
	public static int getLoginCountToSession() {
		Map sessionMap = SysSession.getSessionMap();
		return sessionMap.get(SysContants.LOGIN_COUNT_SESSION) == null ? 0 : (Integer)sessionMap.get(SysContants.LOGIN_COUNT_SESSION);		
	}
	
	
	

	/**
	 * 根据登录用户，写入必要的Session信息，登录时调用8
	 */
	public static UserSessionVO getUserInfo() {
		Map sessionMap = SysSession.getSessionMap();
		if (sessionMap.get(SysContants.SYS_USER_SESSION_VO) != null)
			return (UserSessionVO) sessionMap
					.get(SysContants.SYS_USER_SESSION_VO);
		
		return null;
	}

	/**
	 * 从session中取出当前用户的Id
	 * 
	 * @return
	 */
	public static Map getUserRightsMap() {
		UserSessionVO userSession = getUserInfo();
		Map userRightsMap = new HashMap();
		if (userSession != null)
			userRightsMap = userSession.getMap();
		 if(userRightsMap==null)
			 return new HashMap();
		return userRightsMap;
	}

	/**
	 * 从session中取出当前用户的Id
	 * 
	 * @return
	 */
	public static String getUserId() {
		UserSessionVO userSession = getUserInfo();
		String userId = "";
		if (userSession != null)
			userId = userSession.getUserID();
		return userId;
	}
	
	/**
	 * 从session中取出当前用户的名称
	 * 
	 * @return
	 */
	public static String getUserName() {
		UserSessionVO userSession = getUserInfo();
		String userId = "";
		if (userSession != null)
			userId = userSession.getUserName();
		return userId;
	}
	
	/**
	 * 从session中取出当前用户的Code
	 * 
	 * @return
	 */
	public static String getUserCode() {
		UserSessionVO userSession = getUserInfo();
		String userId = "";
		if (userSession != null)
			userId = userSession.getUserCode();
		return userId;
	}

	/**
	 * 从session中取出当前用户的sysNodeId
	 * 
	 * @return
	 */
	public static String getSysNodeId() {
		UserSessionVO userSession = getUserInfo();
		String sysNodeId = "";
		if (userSession != null)
			sysNodeId = userSession.getSysNodeID();
//		if(CommonUtil.isEmpty(sysNodeId))
//			sysNodeId=webSysNodeId;
		if (sysNodeId == null || sysNodeId.equals(""))
			sysNodeId = "1";
		return sysNodeId;
	}
	/**
	 * 从session中取出当前用户的bgName
	 * 
	 * @return
	 */
	public static String getBgName() {
		return getBgBO().getBgName();
	}
	
	/**
	 * 从session中取出当前用户的bgName
	 * 
	 * @return
	 */
	public static String getBgEngName() {
		return getBgBO().getEngName();
	}
	
	/**
	 * 从session中取出当前用户的bgAliasName
	 * 
	 * @return
	 */
	public static String getBgAliasName() {
		return getBgBO().getAliasName();
	}
	
	/**
	 * 从session中取出当前用户的bgBO
	 * 
	 * @return
	 */
	public static BusinessGroupBO getBgBO() {
		UserSessionVO userSession = getUserInfo();
		BusinessGroupBO bg = new BusinessGroupBO();
		if (userSession != null)
			bg = userSession.getBgBO();
		return bg;
	}
	
	/**
	 * 从session中取出当前用户的bgnAttr36
	 * 
	 * @return
	 */
	public static Integer getBgnAttr36() {
		UserSessionVO userSession = getUserInfo();
		Integer bgnAttr36 = null;
		if (userSession != null)
			bgnAttr36 = userSession.getBgnAttr36();
		if (bgnAttr36 == null || bgnAttr36.equals(""))
			bgnAttr36 = 1;
		return bgnAttr36;
	}
	
	/**
	 * 从session中取出当前用户的bgnAttr36
	 * 
	 * @return
	 */
	public static Integer getBgnAttr37() {
		UserSessionVO userSession = getUserInfo();
		Integer bgnAttr37 = null;
		if (userSession != null)
			bgnAttr37 = userSession.getBgnAttr37();
		if (bgnAttr37 == null || bgnAttr37.equals(""))
			bgnAttr37 = 0;
		return bgnAttr37;
	}
	
	/**
	 * 从session中取出当前用户的bgnAttr11
	 * 
	 * @return
	 */
	public static Integer getBgnAttr11() {
		UserSessionVO userSession = getUserInfo();
		Integer bgnAttr11 = null;
		if (userSession != null)
			bgnAttr11 = userSession.getBgnAttr11();
		if (bgnAttr11 == null || bgnAttr11.equals(""))
			bgnAttr11 = 1;
		return bgnAttr11;
	}

	/**
	 * 从session中取出当前用户的sysNodeName
	 * 
	 * @return
	 */
	public static String getSysNodeName() {
		UserSessionVO userSession = getUserInfo();
		String sysNodeName = "";
		if (userSession != null)
			sysNodeName = userSession.getSysNodeName();
		return sysNodeName;
	}

	/**
	 * 从session中取出当前用户的sysNodeId
	 * 
	 * @return
	 */
	public static String getSysUserCode() {
		UserSessionVO userSession = getUserInfo();
		String sysUserCode = "";
		if (userSession != null)
			sysUserCode = userSession.getUserCode();
		return sysUserCode;
	}

	/**
	 * 从session中取出当前用户的sysNodeName
	 * 
	 * @return
	 */
	public static String getSysUserName() {
		UserSessionVO userSession = getUserInfo();
		String sysUserName = "";
		if (userSession != null)
			sysUserName = userSession.getUserName();
		return sysUserName;
	}

	/**
	 * 清除session中的所有数据，用户登出时调用（MainBB.logout）
	 */
	public static void clearSession() {
		Map sessionMap = SysSession.getSessionMap();
		sessionMap.clear();
	}

	/**
	 * 清除session中对应key值的对象,关闭标签时调用
	 */
	public static void clearSessionByKey(String key) {
		Map sessionMap = SysSession.getSessionMap();
		sessionMap.remove(key);
	}
	
	/**
	 * 获取session中的对象，一般用来取SessionScoaped的BackBean对象
	 * @param key
	 * @return
	 */
	public static Object getObjByKey(String key){
		Map sessionMap = SysSession.getSessionMap();
		return sessionMap.get(key);
	}

	/**
	 * 获取当前登录用户所属节点所在的商业组的创建节点ID
	 */
	public static String getBgNodeId() {
		return getBgBO().getId();
	}
	
	/**
	 * 获取当前登录用户所属节点所在的商业组的创建节点ID
	 */
	public static String getBgCode() {
		return getBgBO().getBgCode();
	}
	/**
	 * 获取当前登录用户所属节点所在的商业组的ID
	 */
	public static String getBgId() {
		return getBgBO().getId();
	}
	/**
	 * 获取当前登录用户客户端屏幕宽度
	 */
	public static int getScreenWidth() {
		UserSessionVO userSession = getUserInfo();
		int screenWidth = 0;
		if (userSession != null)
			screenWidth = userSession.getScreenWidth();
		return screenWidth;
	}
	/**
	 * 获取当前登录用户客户端屏幕宽度
	 */
	public static int getScreenHeight() {
		UserSessionVO userSession = getUserInfo();
		int screenHeight = 0;
		if (userSession != null)
			screenHeight = userSession.getScreenHeight();
		return screenHeight;
	}
	
	/**
	 * 获取epos的tpassID
	 */
	public static String getTpassID() {
		UserSessionVO userSession = getUserInfo();
		String tpassID = "";
		if (userSession != null)
			tpassID = userSession.getTpassID();
		return tpassID;
	}
	/**
	 * 获取epos的用户状态
	 */
	public static int getUserStatus() {
		UserSessionVO userSession = getUserInfo();
		int  userStatus = 0;
		if (userSession != null)
			userStatus = userSession.getUserStatus();
		return userStatus;
	}
	/**
	 *  setSessionKey 往当前session里面插入值
	 * @param key
	 * @param value
	 */
	public static void setSessionKey(String key,Object value){
		Map sessionMap = SysSession.getSessionMap();
		sessionMap.put(key, value);
	}
	
	
	/********************Session end***********************************/
	
	/********************RequestParameter star***********************************/
	private static Map getRequestParameterMap(){
		Map requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		return requestParameterMap;
	}
	
	public static String getNodeIdFromRequest(){
		String nodeId = (String) getRequestParameterMap().get("node");
		return nodeId;
	}


	public static String getBgNameShop() {
		return bgNameShop;
	}


	public static void setBgNameShop(String bgNameShop) {
		SysSession.bgNameShop = bgNameShop;
	}

	
	
	/********************RequestParameter end***********************************/
	
	public static int getStep() {
		return step;
	}
	public static void setStep(int step) {
		SysSession.step = step;
	}

	
	/**
	 * 获取HttpCookieValue
	 */
	public static String getHttpCookieValue() {
		UserSessionVO userSession = getUserInfo();
		String tpassID = "";
		if (userSession != null)
			tpassID = userSession.getHttpCookieValue();
		return tpassID;
	}
	
	/**
	 * 判断是否为商业组节点
	 * 
	 * @return
	 */
	public static boolean isBgNode() {
		return getBgNodeId().equals(getSysNodeId());

	}

//	public static String getWebBgNodeId() {
//		return webBgNodeId;
//	}
//
//
//	public static void setWebBgNodeId(String webBgNodeId) {
//		SysSession.webBgNodeId = webBgNodeId;
//	}
//
//
//	public static String getWebSysNodeId() {
//		return webSysNodeId;
//	}
//
//
//	public static void setWebSysNodeId(String webSysNodeId) {
//		SysSession.webSysNodeId = webSysNodeId;
//	}


	

}
