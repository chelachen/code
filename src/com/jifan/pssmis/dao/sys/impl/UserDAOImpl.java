package com.jifan.pssmis.dao.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import sun.reflect.misc.ReflectUtil;

import com.jifan.pssmis.dao.base.IBaseJdbcDAO;
import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.sys.IUserDAO;
import com.jifan.pssmis.foundation.contants.DataCodeContants;
import com.jifan.pssmis.foundation.contants.SysContants;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.sys.UserBO;
import com.jifan.pssmis.model.vo.sys.UserVO;


@Repository
@SuppressWarnings({ "unchecked", "unchecked", "serial" })
public class UserDAOImpl extends BaseDAOImpl<UserBO,String> implements IUserDAO {

	
	@Resource
	private IBaseJdbcDAO baseJdbcDAO;

	@Override
	public List<UserBO> findUserByParam(UserVO param) {
        String hql = "from " + UserBO.class.getName() + " where 1=1";
        List list = new ArrayList();
        hql = hql + " and  user_code != ? ";
        list.add(SysContants.ADMIN);
        if (param.getId() != null
				&& !param.getId().equals("")) {
			hql = hql + " and  id = ? ";
			list.add(param.getId());
		}
		if (param.getUserName() != null
				&& !param.getUserName().equals("")) {
			hql = hql + " and userName like '%' || ? || '%'  ";
			list.add(param.getUserName());
		}
		if (param.getUserCode() != null
				&& !param.getUserCode().equals("")) {
			hql = hql + " and userCode = ? ";
			list.add(param.getUserCode());
		}
		if (param.getSysNodeID() != null
				&& !param.getSysNodeID().equals("")) {
			hql = hql + " and sysNodeID = ? ";
			list.add(param.getSysNodeID());
		}
		if(CommonUtil.isNotEmpty(param.getTpassID())){
			hql = hql + " and tpassID = ? ";
			list.add(param.getTpassID());
		}
		if(CommonUtil.isNotEmpty(param.getMobile())){
		    hql = hql + " and mobile = ? ";
		    list.add(param.getMobile());
		}
		if(CommonUtil.isNotEmpty(param.getIsEnabled())){
			hql = hql + " and IS_ENABLED = ? ";
			list.add(param.getIsEnabled());
		}
		
        Object[] user = DAOUtil.getObjectsByList(list);
        List<UserBO> userList = this.getHibernateTemplate().find(hql, user);
        return userList;
	}

	@Override
	public List<UserBO> login(UserVO param) {
		String hql = "from " + UserBO.class.getName() + " where 1=1";
        List list = new ArrayList();
        hql = hql + " and isEnabled = ? ";
        list.add(DataCodeContants.SYS_USER_STATUS_ONE);
		if (param.getUserCode() != null
				&& !param.getUserCode().equals("")) {
			hql = hql + " and userCode = ? ";
			list.add(param.getUserCode());
		}
        Object[] user = DAOUtil.getObjectsByList(list);
        List<UserBO> userList = this.getHibernateTemplate().find(hql, user);
        return userList;
	}
	
}
