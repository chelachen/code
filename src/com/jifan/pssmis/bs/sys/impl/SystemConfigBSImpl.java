package com.jifan.pssmis.bs.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.sys.ISystemConfigBS;
import com.jifan.pssmis.dao.sys.ISystemConfigDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.SystemConfigBO;
import com.jifan.pssmis.model.vo.sys.SystemConfigVO;

@Service("systemConfigBS")
public class SystemConfigBSImpl extends BaseBSImpl<SystemConfigBO, String> implements ISystemConfigBS {

	@Resource
	public ISystemConfigDAO systemConfigDAO;

	@Resource
	public void setBaseDAO(ISystemConfigDAO systemConfigDAO) {
		super.setBaseDAO(systemConfigDAO);
	}

	public String saveNotExist(SystemConfigBO entity) throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SystemConfigBO.class);
		detachedCriteria.add(Restrictions.eq("configKey", entity.getConfigKey()));
		List list = this.systemConfigDAO.findByDetachedCriteria(detachedCriteria);
		if (list != null && list.size() > 0) {
			throw new BizException("配置键:" + entity.getConfigKey() + "已存在，请重新输入！");
		}
		return super.save(entity);
	}

	@Override
	public List<SystemConfigBO> findSystemConfigByParam(SystemConfigVO param) {
		return this.systemConfigDAO.findSystemConfigByParam(param);
	}

	public ISystemConfigDAO getSystemConfigDAO() {
		return systemConfigDAO;
	}

	public void setSystemConfigDAO(ISystemConfigDAO systemConfigDAO) {
		this.systemConfigDAO = systemConfigDAO;
	}

}
