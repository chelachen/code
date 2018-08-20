package com.jifan.pssmis.dao.sys.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jifan.pssmis.dao.base.IBaseJdbcDAO;
import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.sys.IUserGroupDAO;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.sys.UserGroupBO;
import com.jifan.pssmis.model.bo.sys.UserGroupRights;
import com.jifan.pssmis.model.vo.sys.UserGroupQueryVO;

@Repository
public class UserGroupDAOImpl extends BaseDAOImpl<UserGroupBO, String> implements IUserGroupDAO {
	@Resource
	private IBaseJdbcDAO baseJdbcDAO;

	public List<UserGroupBO> findUserGroupByParam(UserGroupQueryVO param) {
		String hql = "from " + UserGroupBO.class.getName() + " where 1=1";
		List list = new ArrayList();
		if (param.getId() != null && !param.getId().equals("")) {
			hql = hql + " and id = ? ";
			list.add(param.getId());
		}
		if (param.getGroupCode() != null && !param.getGroupCode().equals("")) {
			hql = hql + " and groupCode = ? ";
			list.add(param.getGroupCode());
		}
		if (param.getGroupName() != null && !param.getGroupName().equals("")) {
			hql = hql + " and groupName = ? ";
			list.add(param.getGroupName());
		}
		if (param.getParentGroup() != null && !param.getParentGroup().equals("")) {
			hql = hql + " and parentGroup = ? ";
			list.add(param.getParentGroup());
		}
		if (param.getRemark() != null && !param.getRemark().equals("")) {
			hql = hql + " and remark = ? ";
			list.add(param.getRemark());
		}

		if (CommonUtil.isNotEmpty(param.getUserGroupType()) && param.getUserGroupType() != 0) {
			hql = hql + " and type = ? ";
			list.add(param.getUserGroupType());
		}
		Object[] objects = DAOUtil.getObjectsByList(list);
		List<UserGroupBO> retultList = this.getHibernateTemplate().find(hql, objects);
		return retultList;
	}

	public List<UserGroupBO> findUserGroupByParentID(String id) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserGroupBO.class);
		if (CommonUtil.isNotEmpty(id))
			detachedCriteria.add(Restrictions.eq("parentGroup", id));
		else
			detachedCriteria.add(Restrictions.sqlRestriction(" (PARENT_GROUP_ID is null or PARENT_GROUP_ID='')"));
		return this.findByDetachedCriteria(detachedCriteria);
	}

	public Map<String, String> findUserGroupRightsMap(String templateBgNodeID, String nodeID) {
		Map<String, String> map = new HashMap<String, String>();
		if (CommonUtil.isNotEmpty(nodeID) && CommonUtil.isNotEmpty(templateBgNodeID)) {
			StringBuffer sql = new StringBuffer();
			sql
				.append(" SELECT a.ID as id1,b.ID as id2 from sys_rights a,sys_rights b where  a.RIGHTS_CODE = b.RIGHTS_CODE and a.ID IS NOT null and b.ID IS NOT null  and a.SYS_NODE_ID='" + templateBgNodeID + "' and b.SYS_NODE_ID='" + nodeID + "' ");
			List list = this.baseJdbcDAO.getListValue(sql.toString());
			Map map1 = new HashMap();
			for (Object obj : list) {
				map1 = (Map) obj;
				map.put(map1.get("id1") + "", map1.get("id2") + "");
			}
			// List<Object[]> list = super.findBySql(sql.toString(), null,
			// true);
			// for(Object[] object : list){
			// if(object != null && object[0] != null && object[1] != null){
			// map.put(object[0].toString(), object[1].toString());
			// }
			// }
		}
		return map;
	}

	public Map<String, String> findUserGroupMap(String templateBgNodeID, String nodeID) {
		Map<String, String> map = new HashMap<String, String>();
		if (CommonUtil.isNotEmpty(nodeID) && CommonUtil.isNotEmpty(templateBgNodeID)) {
			StringBuffer sql = new StringBuffer();
			sql
				.append(" SELECT a.ID as id1,b.ID as id2 from sys_user_group a , sys_user_group b  where a.GROUP_CODE = b.GROUP_CODE and a.ID IS NOT null and b.ID IS NOT null  and a.SYS_NODE_ID='" + templateBgNodeID + "' and b.SYS_NODE_ID='" + nodeID + "' ");
			List list = this.baseJdbcDAO.getListValue(sql.toString());
			Map map1 = new HashMap();
			for (Object obj : list) {
				map1 = (Map) obj;
				map.put(map1.get("id1") + "", map1.get("id2") + "");
			}
			// List<Object[]> list = super.findBySql(sql.toString(), null,
			// true);
			// for(Object[] object : list){
			// if(object != null && object[0] != null && object[1] != null){
			// map.put(object[0].toString(), object[1].toString());
			// }
			// }
		}
		return map;
	}

	public Map<String, String> findTemplateUserGroupMap(String templateBgNodeID) {
		Map<String, String> map = new HashMap<String, String>();
		if (CommonUtil.isNotEmpty(templateBgNodeID)) {
			StringBuffer sql = new StringBuffer();
			sql
				.append("  SELECT a.ID,a.GROUP_CODE from sys_user_group a where a.SYS_NODE_ID= '" + templateBgNodeID + "' ");
			List<Object[]> list = super.findBySql(sql.toString(), null, true);
			for (Object[] object : list) {
				if (object != null && object[0] != null && object[1] != null) {
					map.put(object[0].toString(), object[1].toString());
				}
			}
		}
		return map;
	}

	public List<UserGroupRights> findUserGroupRightsList(String templateBgNodeID, Integer userGroupType) {
		List<UserGroupRights> list = new ArrayList<UserGroupRights>();
		if (CommonUtil.isNotEmpty(templateBgNodeID) && userGroupType != null) {
			StringBuffer sql = new StringBuffer();
			sql
				.append("select a.ASSIGNABLE as assignable,a.RIGHTS_ID as rightID,a.SYS_NODE_ID as sysNodeID,a.USER_GROUP_ID as userGroupID from sys_user_group_rights a LEFT JOIN sys_user_group b on a.USER_GROUP_ID=b.ID  where b.ID IS not NULL and  a.SYS_NODE_ID='" + templateBgNodeID + "' and b.TYPE= " + userGroupType);
			list = super.findBySql(sql.toString(), UserGroupRights.class, true);
		}
		return list;
	}

	public IBaseJdbcDAO getBaseJdbcDAO() {
		return baseJdbcDAO;
	}

	public void setBaseJdbcDAO(IBaseJdbcDAO baseJdbcDAO) {
		this.baseJdbcDAO = baseJdbcDAO;
	}

}
