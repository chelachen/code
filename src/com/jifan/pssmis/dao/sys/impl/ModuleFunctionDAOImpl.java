package com.jifan.pssmis.dao.sys.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.sys.IModuleFunctionDAO;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.sys.ModuleFunctionBO;
import com.jifan.pssmis.model.vo.sys.ModuleFunctionVO;


@SuppressWarnings("serial")
@Repository
public class ModuleFunctionDAOImpl extends BaseDAOImpl<ModuleFunctionBO,String> implements
		IModuleFunctionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ModuleFunctionBO> findModuleFunctionByParam(
			ModuleFunctionVO param) {
		String hql = "from " + ModuleFunctionBO.class.getName() + " where 1=1";
        List list = new ArrayList();
        
		if (param.getFunctionName() != null
				&& !param.getFunctionName().equals("")) {
			hql = hql + " and functionName like '%' || ? || '%' ";
			list.add(param.getFunctionName());
		}
		if (param.getFunctionCode() != null
				&& !param.getFunctionCode().equals("")) {
			hql = hql + " and functionCode = ? ";
			list.add(param.getFunctionCode());
		}
		if (param.getFunctionBean() != null
				&& !param.getFunctionBean().equals("")) {
			hql = hql + " and functionBean = ? ";
			list.add(param.getFunctionBean());
		}
        Object[] moduleFunction = DAOUtil.getObjectsByList(list);
        List<ModuleFunctionBO> moduleFunctionList = this.getHibernateTemplate().find(hql, moduleFunction);
        return moduleFunctionList;
	}
}
