package com.jifan.pssmis.dao.sys.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.sys.IBusinessGroupDAO;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.sys.BusinessGroupBO;
import com.jifan.pssmis.model.vo.sys.BusinessGroupQueryVO;

@Repository
public class BusinessGroupDAOImpl extends BaseDAOImpl<BusinessGroupBO, String>
		implements IBusinessGroupDAO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5942403191844808071L;

	public List<BusinessGroupBO> findBusinessGroupByParam(
			BusinessGroupQueryVO param) {
		String hql = "from " + BusinessGroupBO.class.getName() + " where 1=1";
		List<Object> list = new ArrayList<Object>();
		if (param.getBgCode() != null && !param.getBgCode().equals("")) {
			hql = hql + " and bgCode = ? ";
			list.add(param.getBgCode());
		}
		if (param.getBgName() != null && !param.getBgName().equals("")) {
			hql = hql + " and bgName = ? ";
			list.add(param.getBgName());
		}
		if (param.getSysNodeID() != null && !param.getSysNodeID().equals("")) {
			hql = hql + " and sysNodeID = ? ";
			list.add(param.getSysNodeID());
		}
		Object[] objects = DAOUtil.getObjectsByList(list);
		List<BusinessGroupBO> retultList = this.getHibernateTemplate().find(
				hql, objects);
		return retultList;
	}
	


}
