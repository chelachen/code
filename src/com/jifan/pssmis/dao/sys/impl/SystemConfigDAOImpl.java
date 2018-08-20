package com.jifan.pssmis.dao.sys.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.sys.ISystemConfigDAO;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.sys.SystemConfigBO;
import com.jifan.pssmis.model.vo.sys.SystemConfigVO;


@Repository
public class SystemConfigDAOImpl extends BaseDAOImpl<SystemConfigBO,String> implements ISystemConfigDAO {



	@Override
	public List<SystemConfigBO> findSystemConfigByParam(SystemConfigVO param) {
		String hql = "from " + SystemConfigBO.class.getName() + " where 1=1";
        List list = new ArrayList();
        
		if (param != null && param.getConfigKey() != null && !param.getConfigKey().equals("")) {
			hql = hql + " and configKey = ?  ";
			list.add(param.getConfigKey());
		}
		if (param != null &&param.getConfigValue() != null
				&& !param.getConfigValue().equals("")) {
			hql = hql + " and configValue = ? ";
			list.add(param.getConfigValue());
		}
		if (param != null && param.getModuleCode() != null
				&& !param.getModuleCode().equals("")) {
			hql = hql + " and moduleCode = ? ";
			list.add(param.getModuleCode());
		}
		
		
        Object[] obj = DAOUtil.getObjectsByList(list);
        List<SystemConfigBO> rusultlist = this.getHibernateTemplate().find(hql, obj);
        return rusultlist;
	}

}
