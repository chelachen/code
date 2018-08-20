package com.jifan.pssmis.bs.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.jifan.pssmis.bs.base.IBaseJdbcBS;
import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.cdm.IDataDicDtlBS;
import com.jifan.pssmis.bs.sys.IBusinessGroupBS;
import com.jifan.pssmis.bs.sys.IModuleBS;
import com.jifan.pssmis.bs.sys.IRightsBS;
import com.jifan.pssmis.bs.sys.IUserGroupBS;
import com.jifan.pssmis.dao.sys.IRightsDAO;
import com.jifan.pssmis.dao.sys.IUserDAO;
import com.jifan.pssmis.foundation.contants.SysContants;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.ModuleBO;
import com.jifan.pssmis.model.bo.sys.ModuleFunctionBO;
import com.jifan.pssmis.model.bo.sys.ModuleFunctionOperateBO;
import com.jifan.pssmis.model.bo.sys.RightsBO;
import com.jifan.pssmis.model.bo.sys.UserBO;
import com.jifan.pssmis.model.bo.sys.UserGroupBO;
import com.jifan.pssmis.model.vo.sys.ModuleVO;
import com.jifan.pssmis.model.vo.sys.RightsQueryVO;
import com.jifan.pssmis.model.vo.sys.RightsVO;
import com.jifan.pssmis.model.vo.sys.UserGroupQueryVO;

@Service("rightsBS")
public class RightsBSImpl extends BaseBSImpl<RightsBO, String> implements IRightsBS {

	private static final long serialVersionUID = 4631534970751081408L;

	public final static String SPERETOR = "_";
	@Resource
	private IRightsDAO rightsDAO;

	@Resource
	private IUserDAO userDAO;

	@Resource
	private IUserGroupBS userGroupBS;
	
	@Resource
	private IModuleBS moduleBS;


	@Resource
	private IDataDicDtlBS dataDicDtlBS;

	@Resource
	private IBusinessGroupBS businessGroupBS;
	
	@Resource
	private IBaseJdbcBS baseJdbcBS;

	public IUserGroupBS getUserGroupBS() {
		return userGroupBS;
	}

	public void setUserGroupBS(IUserGroupBS userGroupBS) {
		this.userGroupBS = userGroupBS;
	}

	@Resource
	public void setBaseDAO(IRightsDAO rightsDAO) {
		super.setBaseDAO(rightsDAO);
	}

	public List<RightsBO> findRightsByParam(RightsQueryVO param) {
		return this.rightsDAO.findRightsByParam(param);
	}
	
	/**
	 * 获取用户组的权限信息
	 * @param userGroupID 用户组ID
	 * @param exists true：获取已关联的权限，false：获取未关联的权限
	 * @return
	 */
	public List<RightsVO> findAllFuncRights(String userGroupID,boolean exists){
		String sql ="SELECT  a.ID as id,a.RIGHTS_CODE as rightsCode,a.RIGHTS_NAME as rightsName from sys_rights a  where a.RIGHTS_TYPE=1 ";
		if(exists)
			sql =sql +"  and  exists(SELECT 1 from sys_user_group_rights b where a.ID =b.RIGHTS_ID and b.USER_GROUP_ID='"+userGroupID+"')";
		else
			sql =sql +"  and not exists(SELECT 1 from sys_user_group_rights b where a.ID =b.RIGHTS_ID and  b.USER_GROUP_ID='"+userGroupID+"')";
		
		sql = sql +" order by a.RIGHTS_CODE";
		return this.baseJdbcBS.runSQLReturnList(sql, null, RightsVO.class); 
	}

	public void updateNotExist(RightsBO entity) throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RightsBO.class);
		if (entity.getRightsCode() != null && !entity.getRightsCode().equals("")) {
			detachedCriteria.add(Restrictions.eq("rightsCode", entity.getRightsCode()));
		}
		if (entity.getRightsName() != null && !entity.getRightsName().equals("")) {
			detachedCriteria.add(Restrictions.eq("rightsType", entity.getRightsType()));
		}
		List<RightsBO> list = super.findByDetachedCriteria(detachedCriteria);
		if (list.size() > 0) {
			if (!list.get(0).getId().equals(entity.getId()))
				throw new BizException("权限代码：" + entity.getRightsCode() + " 权限类型：" + entity.getRightsType() + "已经存在!");
		}
		super.update(entity);
	}

	public String saveNotExist2(RightsBO entity) throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RightsBO.class);
		if (entity.getRightsCode() != null && !entity.getRightsCode().equals("")) {
			detachedCriteria.add(Restrictions.eq("rightsCode", entity.getRightsCode()));
		}
		if (entity.getRightsName() != null && !entity.getRightsName().equals("")) {
			detachedCriteria.add(Restrictions.eq("rightsType", entity.getRightsType()));
		}

		List<RightsBO> list = super.findByDetachedCriteria(detachedCriteria);
		if (list.size() > 0) {
			throw new BizException("权限代码：" + entity.getRightsCode() + " 权限类型：" + entity.getRightsType() + "已经存在!");
		}
		return super.save(entity);
	}

	public String saveNotExist(RightsBO entity) throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RightsBO.class);
		if (entity.getRightsCode() != null && !entity.getRightsCode().equals("")) {
			detachedCriteria.add(Restrictions.eq("rightsCode", entity.getRightsCode()));
		}
		if (entity.getRightsName() != null && !entity.getRightsName().equals("")) {
			detachedCriteria.add(Restrictions.eq("rightsType", entity.getRightsType()));
		}
		List<RightsBO> list = super.findByDetachedCriteria(detachedCriteria);
		if (list.size() > 0) {
			list.get(0).setRightsCode(entity.getRightsCode());
			list.get(0).setRightsName(entity.getRightsName());
			list.get(0).setRightsType(entity.getRightsType());
			list.get(0).setSourceInfo(entity.getSourceInfo());
			list.get(0).setFunctionInfo(entity.getFunctionInfo());
			super.update(list.get(0));
			return list.get(0).getId();
		} else {
			return super.save(entity);
		}
	}

	/**
	 * 权限控制接口，判断一个具体的权限 chencl 2011-06-28
	 * 
	 * @param userID 用户ID
	 * @param sourceInfo 资源信息
	 * @param rightsType 权限类型
	 * @return true:有权限，false:没有权限
	 */
	public boolean rightsCheck(String userID, String sourceInfo, int rightsType) {
		// 根据userID取得User
		UserBO userBO = this.getUserDAO().get(userID);
		if (userBO != null) {
			UserGroupQueryVO userGroupQueryVO = new UserGroupQueryVO();
			List<UserGroupBO> userGroupList = this.userGroupBS.findUserGroupByParam(userGroupQueryVO);
			Map map = (Map) userBO.getUserAllRights(userGroupList).get(rightsType);
			if (map != null) {
				RightsBO rightsBO = (RightsBO) map.get(sourceInfo);
				if (rightsBO == null)
					return false;
				else
					return true;
			}
		}
		return false;
	}

	/**
	 * 权限控制接口，取得某用户的某权限类型的所有权限信息Map(资源信息,权限BO) chencl 2011-06-28
	 * 
	 * @param userID 用户ID
	 * @param sourceInfo 资源信息
	 * @param rightsType 权限类型
	 * @return true:有权限，false:没有权限
	 */
	public Map getRightsByUserAndType(String userID, int rightsType) {
		// 根据userID取得User
		UserBO userBO = this.getUserDAO().get(userID);
		if (userBO != null) {
			UserGroupQueryVO userGroupQueryVO = new UserGroupQueryVO();
			List<UserGroupBO> userGroupList = this.userGroupBS.findUserGroupByParam(userGroupQueryVO);
			Map map = (Map) userBO.getUserAllRights(userGroupList).get(rightsType);
			if (map != null)
				return map;
			else
				return new HashMap();
		}
		return null;
	}

	public void CreateFuncRights(String sysNodeID, boolean existsValidate) throws Exception {
		List<RightsBO> rightList = new ArrayList<RightsBO>();
		ModuleVO moduleVO = new ModuleVO();
		moduleVO.setSysNodeID(sysNodeID);
		List<ModuleBO> moduleList = moduleBS.findModuleByParam(moduleVO);
		for (ModuleBO moduleBO : moduleList) {
			rightList.add(new RightsBO(moduleBO.getModuleCode() + SysContants.MANAGER, moduleBO.getModuleName(), SysContants.RIGHTS_TYPE_FUNC, null, moduleBO.getModuleCode()));
			for (ModuleFunctionBO functionBO : moduleBO.getModuleFunctions()) {
				rightList.add(new RightsBO(moduleBO.getModuleCode() + "_" + functionBO.getFunctionCode() + SysContants.MANAGER, moduleBO.getModuleName() + "_" + functionBO.getFunctionName(),
						SysContants.RIGHTS_TYPE_FUNC, functionBO.getFunctionBean() + SysContants.MANAGER, moduleBO.getModuleCode() + "_" + functionBO.getFunctionCode()));
				for (ModuleFunctionOperateBO moduleFunctionOperateBO : functionBO.getModuleFunctionOperates()) {
					rightList.add(new RightsBO(moduleBO.getModuleCode() + "_" + functionBO.getFunctionCode() + "_" + moduleFunctionOperateBO.getOperateCode(), moduleBO.getModuleName() + "_"
							+ functionBO.getFunctionName() + "_" + moduleFunctionOperateBO.getOperateName(), SysContants.RIGHTS_TYPE_FUNC, functionBO.getFunctionBean() + "_"
							+ moduleFunctionOperateBO.getOperateCode(), moduleBO.getModuleCode() + "_" + functionBO.getFunctionCode()));

				}
			}
		}
//		this.saveRights(rightList, existsValidate);
	}


	public IRightsDAO getRightsDAO() {
		return rightsDAO;
	}

	public void setRightsDAO(IRightsDAO rightsDAO) {
		this.rightsDAO = rightsDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IDataDicDtlBS getDataDicDtlBS() {
		return dataDicDtlBS;
	}

	public void setDataDicDtlBS(IDataDicDtlBS dataDicDtlBS) {
		this.dataDicDtlBS = dataDicDtlBS;
	}



	@Override
	public List findByParam(List<String> param) {
		return this.rightsDAO.findByParam(param);
	}



	public IBusinessGroupBS getBusinessGroupBS() {
		return businessGroupBS;
	}

	public void setBusinessGroupBS(IBusinessGroupBS businessGroupBS) {
		this.businessGroupBS = businessGroupBS;
	}

	public IModuleBS getModuleBS() {
		return moduleBS;
	}

	public void setModuleBS(IModuleBS moduleBS) {
		this.moduleBS = moduleBS;
	}

	public IBaseJdbcBS getBaseJdbcBS() {
		return baseJdbcBS;
	}

	public void setBaseJdbcBS(IBaseJdbcBS baseJdbcBS) {
		this.baseJdbcBS = baseJdbcBS;
	}


}
