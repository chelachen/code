package com.jifan.pssmis.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.sys.IUserGroupRightsDAO;
import com.jifan.pssmis.model.bo.sys.UserGroupRights;

@Repository
public class UserGroupRightsDAOImpl extends
		BaseDAOImpl<UserGroupRights, String> implements IUserGroupRightsDAO {
	public void deleteUserGroupRightsById(String id) {
     String sql="delete from sys_user_group_rights where USER_GROUP_ID ='"+id+"'";
     super.findBySql(sql, null,false);
	}
	public void deleteUserRightsById(String rightID) {
	     String sql="delete from sys_user_rights where  RIGHTS_ID ='"+rightID+"'";
	     super.findBySql(sql, null,false);
	}
}
