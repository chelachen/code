package com.jifan.pssmis.dao.sys;

import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.UserGroupRights;

public interface IUserGroupRightsDAO extends IBaseDAO<UserGroupRights,String>{

	public void deleteUserGroupRightsById(String id) throws BizException;

	void deleteUserRightsById(String rightID);

}