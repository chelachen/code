package com.jifan.pssmis.bs.sys;

import java.util.List;

import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.RightsBO;
import com.jifan.pssmis.model.bo.sys.UserBO;
import com.jifan.pssmis.model.bo.sys.UserGroupBO;
import com.jifan.pssmis.model.vo.sys.UserVO;

public interface IUserBS extends IBaseBS<UserBO,String>  {

	public List<UserBO> findUserByParam(UserVO param);
	
	public List<UserBO> login(UserVO param);
	
	public String saveNotExist(UserBO entity) throws BizException;
	
	public void updateNotExist(UserBO entity) throws BizException;
	
	public List<RightsBO> loadRightsOfUser(String id);
	
	/**
	 * 获取用户的所有角色信息列表
	 * @param id 用户ID
	 * @return
	 */
	public List<UserGroupBO> loadUserGroupOfUser(String id);
	
	public void delete (UserBO userBO);
	
}
