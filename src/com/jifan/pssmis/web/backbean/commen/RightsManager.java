package com.jifan.pssmis.web.backbean.commen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jifan.pssmis.foundation.contants.SysContants;
import com.jifan.pssmis.foundation.session.SysSession;
import com.jifan.pssmis.model.bo.sys.RightsBO;

public class RightsManager {

	/**
	 * 获取用户的所有权限Map(权限类型,Map(资源信息,权限))
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map getUserAllRights(List<RightsBO> rightsList) {
		Map mapFunc = new HashMap();// 功能权限Map
		Map mapFlow = new HashMap();// 流程权限Map
		Map mapData = new HashMap();// 流程权限Map
		Map mapUser = new HashMap();// 用户权限Map

		for (RightsBO rightsBO : rightsList) {
			if (rightsBO.getRightsType() == SysContants.RIGHTS_TYPE_FUNC) {
				if (!mapFunc.containsKey(rightsBO.getSourceInfo()))
					mapFunc.put(rightsBO.getSourceInfo().toLowerCase(), rightsBO);
			} else if (rightsBO.getRightsType() == SysContants.RIGHTS_TYPE_FLOW) {
				if (!mapFlow.containsKey(rightsBO.getSourceInfo()))
					mapFlow.put(rightsBO.getSourceInfo().toLowerCase(), rightsBO);
			} else if (rightsBO.getRightsType() == SysContants.RIGHTS_TYPE_DATA) {
				if (!mapData.containsKey(rightsBO.getSourceInfo()))
					mapData.put(rightsBO.getSourceInfo().toLowerCase(), rightsBO);
			}
		}

		mapUser.put(SysContants.RIGHTS_TYPE_FUNC, mapFunc);
		mapUser.put(SysContants.RIGHTS_TYPE_DATA, mapData);
		return mapUser;
	}

	/**
	 * 权限判断，从session中取得用户权限
	 * 
	 * @param sourceInfo
	 *            功能权限-传入权限代码，流程权限-传入操作ID
	 * @param rightsType
	 *            SysContants.RIGHTS_TYPE_FUNC,SysContants.RIGHTS_TYPE_FLOW
	 * @return true:有权限，false:没权限
	 */
	public static boolean checkRightsFromSession(String sourceInfo, int rightsType) {
		if (SysSession.getUserRightsMap() != null) {
			Map map = (Map) SysSession.getUserRightsMap().get(rightsType);
			if (map != null) {
				RightsBO rightsBO = (RightsBO) map.get(sourceInfo.toLowerCase());
				if (rightsBO == null)
					return false;
				else
					return true;
			}
		}
		return false;
	}

	public static boolean checkModuleRightsFromSession(String sourceInfo, int rightsType) {
		if (SysSession.getUserInfo().getUserCode().equalsIgnoreCase(SysContants.ADMIN))
			return true;
		if (SysSession.getUserRightsMap() != null) {
			Map map = (Map) SysSession.getUserRightsMap().get(rightsType);
			for (Object key : map.keySet()) {
				// chencl 2011-11-23 >= 0改为==0 避免 sourceInfo = "SaleInvoiceBB"
				// 与"MemberSaleInvoiceBB"的冲突问题
				if (null != key)
					if (((String) key).indexOf(sourceInfo.toLowerCase()) == 0) {
						return true;
					}
			}
		}
		return false;
	}

	/**
	 * 权限判断，从session中取得用户权限
	 * 
	 * @param rightsType
	 *            SysContants.RIGHTS_TYPE_FUNC,SysContants.RIGHTS_TYPE_FLOW
	 * @return true:有权限，false:没权限
	 */
	public static boolean checkRightTypeFromSession(int rightsType) {
		if (SysSession.getUserRightsMap() != null) {
			Map map = (Map) SysSession.getUserRightsMap().get(rightsType);
			if (map.size() > 0)
				return true;
		}
		return false;
	}

	/**
	 * 权限判断，从session中取得用户权限
	 * 
	 * @param rightsType
	 *            SysContants.RIGHTS_TYPE_FUNC,SysContants.RIGHTS_TYPE_FLOW
	 * @return true:有权限，false:没权限
	 */
	public static Map getRightTypeFromSession(int rightsType) {
		if (SysSession.getUserRightsMap() != null)
			return (Map) SysSession.getUserRightsMap().get(rightsType);
		return new HashMap();
	}

}
