package com.jifan.pssmis.bs.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.jifan.pssmis.bs.base.IBaseJdbcBS;
import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.sys.IUserGroupBS;
import com.jifan.pssmis.dao.sys.IRightsDAO;
import com.jifan.pssmis.dao.sys.IUserGroupDAO;
import com.jifan.pssmis.dao.sys.IUserGroupRightsDAO;
import com.jifan.pssmis.foundation.contants.SysContants;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.UserGroupBO;
import com.jifan.pssmis.model.vo.sys.RightsVO;
import com.jifan.pssmis.model.vo.sys.UserGroupQueryVO;
import com.jifan.pssmis.model.vo.sys.UserVO;

@Service("userGroupBS")
public class UserGroupBSImpl extends BaseBSImpl<UserGroupBO, String> implements IUserGroupBS {
	@Resource
	private IUserGroupDAO userGroupDAO;
	@Resource
	private IRightsDAO rightsDAO;
	@Resource
	private IUserGroupRightsDAO userGroupRightsDAO;
	
	@Resource
	private IBaseJdbcBS baseJdbcBS;

	@Resource
	public void setBaseDAO(IUserGroupDAO userGroupDAO) {
		super.setBaseDAO(userGroupDAO);
	}

	public List<UserGroupBO> findUserGroupByParam(UserGroupQueryVO param) {
		return this.userGroupDAO.findUserGroupByParam(param);
	}

	public String saveNotExist(UserGroupBO entity) throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserGroupBO.class);
		Disjunction dis = Restrictions.disjunction();
//		if (entity.getGroupCode() != null && !entity.getGroupCode().equals("")) {
//			dis.add(Restrictions.eq("groupCode", entity.getGroupCode()));
//		}
		if (entity.getGroupName() != null && !entity.getGroupName().equals("")) {
			dis.add(Restrictions.eq("groupName", entity.getGroupName()));
		}
		detachedCriteria.add(dis);
		List list = super.findByDetachedCriteria(detachedCriteria);
		if (list.size() > 0)
			throw new BizException( "名称:" + entity.getGroupName() + "已经存在!");
		return super.save(entity);
	}

	public void updateNotExist(UserGroupBO entity) throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserGroupBO.class);
		Disjunction dis = Restrictions.disjunction();
//		if (entity.getGroupCode() != null && !entity.getGroupCode().equals("")) {
//			dis.add(Restrictions.eq("groupCode", entity.getGroupCode()));
//		}
		if (entity.getGroupName() != null && !entity.getGroupName().equals("")) {
			dis.add(Restrictions.eq("groupName", entity.getGroupName()));
		}
		detachedCriteria.add(dis);
		detachedCriteria.add(Restrictions.ne("id", entity.getId()));
		List list = super.findByDetachedCriteria(detachedCriteria);
		if (list.size() > 0)
			throw new BizException("名称:" + entity.getGroupName() + "已经存在!");
		super.update(entity);
	}
	
	/**
	 * 获取用户组对应的用户列表
	 * @param entity
	 * @param exists true 获取用户组关联的用户列表，false 获取不再用户组里的用户列表
	 * @return
	 */
	public List<UserVO>  loadUsers(UserGroupBO entity,boolean exists){
		String sql="";
		if(exists){
			sql =" SELECT b.id,b.USER_CODE as userCode,b.USER_NAME as userName,b.MOBILE  as mobile from sys_group_user a left join sys_user b on a.USER_ID=b.id where GROUP_ID='"+entity.getId()+"'  and b.USER_CODE !='"+SysContants.ADMIN+"'";
		}else{
			sql =" SELECT b.id,b.USER_CODE as userCode,b.USER_NAME as userName,b.MOBILE as mobile from sys_user b where b.id not in (SELECT USER_ID from sys_group_user where GROUP_ID='"+entity.getId()+"' )  and b.USER_CODE !='"+SysContants.ADMIN+"'";
		}
		List<UserVO> userList= baseJdbcBS.runSQLReturnList(sql, null, UserVO.class);
		return userList;
	}
	
	/**
	 * 用户组组员保存
	 * @param entity 
	 * @param userList
	 */
	public void saveGroupUsers(UserGroupBO entity,List<UserVO> userList){
		List<String> sqls = new ArrayList();
		String sql= "DELETE from sys_group_user where group_id ='"+entity.getId()+"'";
		sqls.add(sql);
		for(Object vo :userList){
			sql = " insert into sys_group_user(GROUP_ID,USER_ID) VALUES ('"+entity.getId()+"','"+vo+"') ";
			sqls.add(sql);
		}
		this.baseJdbcBS.batchUpdate((String[])sqls.toArray(new String[sqls.size()]));
	}
	
	/**
	 * 用户角色保存
	 * @param entity 
	 * @param userList
	 */
	public void saveUserRoles(String userId,List<UserGroupBO> roles){
		List<String> sqls = new ArrayList();
		String sql= "DELETE from sys_group_user where user_id ='"+userId+"'";
		sqls.add(sql);
		for(UserGroupBO vo :roles){
			sql = " insert into sys_group_user(GROUP_ID,USER_ID) VALUES ('"+vo.getId()+"','"+userId+"') ";
			sqls.add(sql);
		}
		this.baseJdbcBS.batchUpdate((String[])sqls.toArray(new String[sqls.size()]));
	}
	
	/**
	 * 用户组权限保存
	 * @param entity 
	 * @param userList
	 */
	public void saveGroupRights(UserGroupBO entity,List<RightsVO> rightsList){
		List<String> sqls = new ArrayList();
		String sql= "DELETE from sys_user_group_rights where USER_GROUP_ID ='"+entity.getId()+"'";
		sqls.add(sql);
		for(Object vo :rightsList){
			sql = " insert into sys_user_group_rights(ID,ASSIGNABLE,USER_GROUP_ID,RIGHTS_ID) VALUES (uuid(),1,'"+entity.getId()+"','"+vo+"') ";
			sqls.add(sql);
		}
		this.baseJdbcBS.batchUpdate((String[])sqls.toArray(new String[sqls.size()]));
	}

	/**
	 * 删除自己和所有子数据
	 */
	public void deleteAll(UserGroupBO entity) throws BizException {
		// 树形结构删除，需递归删除子数据
		for (UserGroupBO dtl : this.getSubUserGroups(entity.getId())) {
			this.deleteAll(dtl);
		}
		this.delete(entity);
	}

	public List<UserGroupBO> getSubUserGroups(String id) {
		return this.userGroupDAO.findUserGroupByParentID(id);
	}

	public IUserGroupDAO getUserGroupDAO() {
		return userGroupDAO;
	}

	public void setUserGroupDAO(IUserGroupDAO userGroupDAO) {
		this.userGroupDAO = userGroupDAO;
	}

	public IRightsDAO getRightsDAO() {
		return rightsDAO;
	}

	public void setRightsDAO(IRightsDAO rightsDAO) {
		this.rightsDAO = rightsDAO;
	}

	public IUserGroupRightsDAO getUserGroupRightsDAO() {
		return userGroupRightsDAO;
	}

	public void setUserGroupRightsDAO(IUserGroupRightsDAO userGroupRightsDAO) {
		this.userGroupRightsDAO = userGroupRightsDAO;
	}

	public IBaseJdbcBS getBaseJdbcBS() {
		return baseJdbcBS;
	}

	public void setBaseJdbcBS(IBaseJdbcBS baseJdbcBS) {
		this.baseJdbcBS = baseJdbcBS;
	}

}
