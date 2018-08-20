package com.jifan.pssmis.bs.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.sys.IModuleFunctionOperateBS;
import com.jifan.pssmis.dao.sys.IModuleFunctionOperateDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.paging.Paginator;
import com.jifan.pssmis.model.bo.sys.ModuleFunctionOperateBO;
import com.jifan.pssmis.model.vo.sys.ModuleFunctionOperateVO;
@SuppressWarnings("serial")
@Service
public class ModuleFunctionOperateBSImpl extends BaseBSImpl<ModuleFunctionOperateBO,String> 
implements IModuleFunctionOperateBS {
	@Resource
	public IModuleFunctionOperateDAO moduleFunctionOperateDAO;
	@Override
	public String saveNotExist(ModuleFunctionOperateBO entity)
			throws BizException {
		//存在性判断
 			List<ModuleFunctionOperateVO> list=moduleFunctionOperateDAO.ISModuleFunctionOperate(entity);
			if (list.size() > 0){
				return "1" ;
			}
			return super.save(entity);
		}

	@Override
	public void delete(ModuleFunctionOperateBO entity) {
		// TODO Auto-generated method stub
		//存在性判断
			
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ModuleFunctionOperateBO> findByDetachedCriteria(
			DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List findByDetachedCriteriaProjection(
			DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Paginator findByPaginator(Paginator paginator,
			DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleFunctionOperateBO get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModuleFunctionOperateBO> get(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModuleFunctionOperateBO get(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModuleFunctionOperateBO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ModuleFunctionOperateBO> getList(String propertyName,
			Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getTotalCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isExist(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String save(ModuleFunctionOperateBO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ModuleFunctionOperateBO entity) {
		// TODO Auto-generated method stub

	}

	public IModuleFunctionOperateDAO getModuleFunctionOperateDAO() {
		return moduleFunctionOperateDAO;
	}

	public void setModuleFunctionOperateDAO(
			IModuleFunctionOperateDAO moduleFunctionOperateDAO) {
		this.moduleFunctionOperateDAO = moduleFunctionOperateDAO;
	}
	@Resource
	public void setBaseDAO(IModuleFunctionOperateDAO moduleFunctionOperateDAO) {
		super.setBaseDAO(moduleFunctionOperateDAO);
	}

	@Override
	public void deleteModuleFunctionOperate(ModuleFunctionOperateBO entity)
			throws BizException {
		List<ModuleFunctionOperateVO> list=moduleFunctionOperateDAO.ISModuleFunctionOperate(entity);
		if (list.size() > 0)
 		super.delete(entity);
		
	}

	@Override
	public void updateModuleFunctionOperate(ModuleFunctionOperateBO entity)
			throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria
		.forClass(ModuleFunctionOperateBO.class);
	detachedCriteria.add(Restrictions.ne("id", entity.getId()));
	detachedCriteria.add(Restrictions.eq("operateName", entity
			.getOperateName()));
 	detachedCriteria.add(Restrictions.eq("moduleFunctionID", entity
			.getModuleFunctionID()));
	List list = super.findByDetachedCriteria(detachedCriteria);
	if (list.size() > 0)
			throw new BizException("操作重复，请重置！");
 			moduleFunctionOperateDAO.updateModuleFunctionOperate(entity);
		
	}

}
