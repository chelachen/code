package com.jifan.pssmis.bs.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.sys.IModuleFunctionBS;
import com.jifan.pssmis.dao.sys.IModuleFunctionDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.cdm.DataDicDtlBO;
import com.jifan.pssmis.model.bo.sys.ModuleFunctionBO;
import com.jifan.pssmis.model.vo.sys.ModuleFunctionVO;

@SuppressWarnings("serial")
@Service
public class ModuleFunctionBSImpl extends BaseBSImpl<ModuleFunctionBO,String> implements
		IModuleFunctionBS {

	@Resource
	 public IModuleFunctionDAO moduleFunctionDAO;
	
	 @Resource
   public void setBaseDAO(IModuleFunctionDAO moduleFunctionDAO) {
           super.setBaseDAO(moduleFunctionDAO);
	 }       
	
	public IModuleFunctionDAO getModuleFunctionDAO() {
		return moduleFunctionDAO;
	}

	public void setModuleFunctionDAO(IModuleFunctionDAO moduleFunctionDAO) {
		this.moduleFunctionDAO = moduleFunctionDAO;
	}

	@Override
	public List<ModuleFunctionBO> findModuleFunctionByParam(
			ModuleFunctionVO param) {
		return this.moduleFunctionDAO.findModuleFunctionByParam(param);
	}

	@Override
	public String saveNotExist(ModuleFunctionBO entity) throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ModuleFunctionBO.class);
        Disjunction dis = Restrictions.disjunction();
        if(entity.getFunctionCode() != null && !entity.getFunctionCode().equals("")){
                dis.add(Restrictions.eq("functionCode",entity.getFunctionCode()));
        }
        detachedCriteria.add(dis);
        List list = super.findByDetachedCriteria(detachedCriteria);
        if (list.size()>0)
                throw new BizException("模块功能代号:"+ entity.getFunctionCode() +"已经存在!");
        
        return super.save(entity);
	}

}
