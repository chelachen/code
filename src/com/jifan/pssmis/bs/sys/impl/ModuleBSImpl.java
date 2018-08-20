package com.jifan.pssmis.bs.sys.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.sys.IModuleBS;
import com.jifan.pssmis.dao.sys.IModuleDAO;
import com.jifan.pssmis.dao.sys.IModuleFunctionDAO;
import com.jifan.pssmis.dao.sys.IModuleFunctionOperateDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.model.bo.sys.ModuleBO;
import com.jifan.pssmis.model.bo.sys.ModuleFunctionBO;
import com.jifan.pssmis.model.bo.sys.ModuleFunctionOperateBO;
import com.jifan.pssmis.model.vo.sys.ModuleVO;

@SuppressWarnings("serial")
@Service("moduleBS")
public class ModuleBSImpl extends BaseBSImpl<ModuleBO, String> implements
		IModuleBS {

	@Resource
	public IModuleDAO moduleDAO;
	@Resource
	public IModuleFunctionDAO moduleFunctionDAO;
	@Resource
	public IModuleFunctionOperateDAO moduleFunctionOperateDAO;
	

	@Resource
	public void setBaseDAO(IModuleDAO moduleDAO) {
		super.setBaseDAO(moduleDAO);
	}

	public IModuleDAO getModuleDAO() {
		return moduleDAO;
	}

	public void setModuleDAO(IModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}

	@Override
	public List<ModuleBO> findModuleByParam(ModuleVO param) {
		return this.moduleDAO.findModuleByParam(param);
	}

	@Override
	public String saveNotExist(ModuleBO entity) throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(ModuleBO.class);
		Disjunction dis = Restrictions.disjunction();
		if (entity.getModuleCode() != null
				&& !entity.getModuleCode().equals("")) {
			dis.add(Restrictions.eq("moduleCode", entity.getModuleCode()));
		}
		detachedCriteria.add(dis);
		List list = super.findByDetachedCriteria(detachedCriteria);
		if (list.size() > 0)
			throw new BizException("模块代号:" + entity.getModuleCode() + "已经存在!");

		int count = 0;
		for (ModuleFunctionBO moduleFunctionBO : entity.getModuleFunctions()) {
			count = 0;
			for (ModuleFunctionBO moduleFunctionBONext : entity
					.getModuleFunctions()) {
				if (moduleFunctionBO.getFunctionCode().equals(
						moduleFunctionBONext.getFunctionCode())) {
					count++;
					if (count>1)
						throw new BizException("模块功能代号:" + moduleFunctionBO.getFunctionCode() + "已经存在!");
				}
			}
		}
//		if (entity.getId()==null || entity.getId().equals(""))
			return super.save(entity);
//		else{
//			super.update(entity);
//			return "";
//		}
	}
	
	@Override
	public void updateNotExist(ModuleBO entity) throws BizException {
		Map<String, String> mfMap = new HashMap<String, String>();
		for (ModuleFunctionBO entDtlBO : entity.getModuleFunctions()) {
			 if(mfMap.containsKey(entDtlBO.getFunctionCode())){
				  throw new BizException("模块功能代号:"+ entDtlBO.getFunctionCode() +"已经存在!");
			 }else{
				 mfMap.put(entDtlBO.getFunctionCode(),
						 entDtlBO.getFunctionCode());
			 }
		}
		moduleDAO.update(entity);
	}
	

//	/**
//	 * @param entity
//	 */
	private void casadeSaveModuleOperate(ModuleBO entity) {
		for(ModuleFunctionBO tempBO:entity.getModuleFunctions()){
			for(ModuleFunctionOperateBO bo:tempBO.getModuleFunctionOperates()){
				if(CommonUtil.isEmpty(tempBO.getId())){
					this.moduleFunctionDAO.save(tempBO);
				}
				bo.setModuleFunctionID(tempBO.getId());
				if(CommonUtil.isEmpty(bo.getId())){
					this.moduleFunctionOperateDAO.save(bo);
				}else{
					this.moduleFunctionOperateDAO.update(bo);
				}
			}
		}
	}
    public void SaveList(List<ModuleBO> moduleBOlist){
    	if(moduleBOlist!=null&&!moduleBOlist.isEmpty()){
    		
    	DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ModuleBO.class);
    	List<ModuleBO> list = super.findByDetachedCriteria(detachedCriteria);
    	if(list!=null&&list.size()>0){
    		for (ModuleBO moduleBO : list) {
    			this.moduleDAO.delete(moduleBO);
			}
    	}
    	for(ModuleBO entity:moduleBOlist){
               super.save(entity);
               casadeSaveModuleOperate(entity);
    	}
    }

}


}
