package com.jifan.pssmis.dao.sys;

import java.util.List;

import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.sys.UserBO;
import com.jifan.pssmis.model.vo.sys.UserVO;

public interface IUserDAO extends IBaseDAO<UserBO,String> {

	public List<UserBO> findUserByParam(UserVO param);
	
	public List<UserBO> login(UserVO param);
	
}
