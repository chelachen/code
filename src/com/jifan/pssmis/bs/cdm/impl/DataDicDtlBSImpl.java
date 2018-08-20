package com.jifan.pssmis.bs.cdm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.cdm.IDataDicDtlBS;
import com.jifan.pssmis.dao.cdm.IDataDicDtlDAO;
import com.jifan.pssmis.foundation.datadic.DataCodeUtil;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.util.ClassifyCodingUtil;
import com.jifan.pssmis.model.bo.cdm.DataDicBO;
import com.jifan.pssmis.model.bo.cdm.DataDicDtlBO;
import com.jifan.pssmis.model.vo.cdm.DataDicDtlQueryVO;

@Service("dataDicDtlBS")
public class DataDicDtlBSImpl extends BaseBSImpl<DataDicDtlBO, String> implements IDataDicDtlBS {
	@Resource
	private IDataDicDtlDAO dataDicDtlDAO;

	@Resource
	public void setBaseDAO(IDataDicDtlDAO dataDicDtlDAO) {
		super.setBaseDAO(dataDicDtlDAO);
	}
	public List<DataDicDtlBO> findDataDicDtlByDetachedCriteria(DetachedCriteria detachedCriteria) {
		return this.dataDicDtlDAO.findByDetachedCriteria(detachedCriteria);
	}
	public List<DataDicDtlBO> findDataDicDtlByParam(DataDicDtlQueryVO param) {
		return this.dataDicDtlDAO.findDataDicDtlByParam(param);
	}
	

	public IDataDicDtlDAO getDataDicDtlDAO() {
		return dataDicDtlDAO;
	}

	public void setDataDicDtlDAO(IDataDicDtlDAO dataDicDtlDAO) {
		this.dataDicDtlDAO = dataDicDtlDAO;
	}

	@Override
	public String saveNotExist(DataDicDtlBO entity) throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DataDicDtlBO.class);
		Disjunction dis = Restrictions.disjunction();
		if (entity.getDataCode() != null && !entity.getDataCode().equals("")) {
			dis.add(Restrictions.eq("dataCode", entity.getDataCode()));
		}
		if (entity.getDataClassCode() != null && !entity.getDataClassCode().equals("")) {
			detachedCriteria.add(Restrictions.eq("dataClassCode", entity.getDataClassCode()));
		}
		detachedCriteria.add(dis);
		List list = super.findByDetachedCriteria(detachedCriteria);
		if (list.size() > 0)
			throw new BizException("属性代码:" + entity.getDataCode() + "名称:" + entity.getDataName() + "已经存在！");

		return super.save(entity);
	}
	
	public void updateNotExist(DataDicDtlBO entity) throws BizException{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DataDicDtlBO.class);
		Disjunction dis = Restrictions.disjunction();
		if (entity.getDataCode() != null && !entity.getDataCode().equals("")) {
			dis.add(Restrictions.eq("dataCode", entity.getDataCode()));
		}
		if (entity.getDataClassCode() != null && !entity.getDataClassCode().equals("")) {
			detachedCriteria.add(Restrictions.eq("dataClassCode", entity.getDataClassCode()));
		}
		if (entity.getId()!=null){
			detachedCriteria.add(Restrictions.ne("id", entity.getId()));
		}
		detachedCriteria.add(dis);
		List list = super.findByDetachedCriteria(detachedCriteria);
		if (list.size() > 0)
			throw new BizException("属性代码:" + entity.getDataCode() + "名称:" + entity.getDataName() + "已经存在！");
		 super.update(entity);
	}
	
	/**
	 * 删除自己和所有子数据
	 */
	public void deleteAll(DataDicDtlBO entity) throws BizException{
		DataDicBO datadic=entity.getDataDicBO();
		if(datadic !=null && datadic.isTree()){
			//树形结构删除，需递归删除子数据
			for(DataDicDtlBO dtl :DataCodeUtil.getSubDataDicDtlBOList(entity)){
				this.deleteAll(dtl);
			}
		}
		this.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataDicDtlBO getDataDicDtlByDataCode(String dataClassCode, Integer dataCode) {
		DetachedCriteria dc = DetachedCriteria.forClass(DataDicDtlBO.class);
		dc.add(Restrictions.eq("dataClassCode", dataClassCode));
		dc.add(Restrictions.eq("dataCode", dataCode));
		List<DataDicDtlBO> resultList = this.findByDetachedCriteria(dc);
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}

	}
	@SuppressWarnings("unchecked")
	public List<DataDicDtlBO> getSubDataDicDtlBOListByParent(DataDicDtlBO dataDicDtlBO) {

		DetachedCriteria dc = DetachedCriteria.forClass(DataDicDtlBO.class);
		dc.add(Restrictions.eq("dataClassCode", dataDicDtlBO.getDataClassCode()));
		dc.add(Restrictions.eq("parentDataCode", dataDicDtlBO.getDataCode()));
		dc.add(Restrictions.ne("dataCode", dataDicDtlBO.getDataCode()));
		dc.addOrder(Order.asc("sequenceNo"));
		List<DataDicDtlBO> temp = this.findByDetachedCriteria(dc);
		return temp;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataDicDtlBO> getSubDataDicDtlBOListByLevel(DataDicDtlBO dataDicDtlBO) {

		DetachedCriteria dc = DetachedCriteria.forClass(DataDicDtlBO.class);
		dc.add(Restrictions.eq("dataClassCode", dataDicDtlBO.getDataClassCode()));
		Integer tolerance = ClassifyCodingUtil.levelCodingInfo.get(dataDicDtlBO.getDataDicBO().getDataCodeType()).get(
				dataDicDtlBO.getDataLevel() + 1).get(ClassifyCodingUtil.TOLERANCE);
		Integer bitlen = ClassifyCodingUtil.levelCodingInfo.get(dataDicDtlBO.getDataDicBO().getDataCodeType()).get(
				dataDicDtlBO.getDataLevel() + 1).get(ClassifyCodingUtil.BITLEN);
		if (dataDicDtlBO.getDataCode() == 0) {
			dc.add(Restrictions.ge("dataCode", tolerance));
			dc.add(Restrictions.le("dataCode", tolerance * bitlen));

		} else {
			dc.add(Restrictions.ge("dataCode", dataDicDtlBO.getDataCode()));
			dc.add(Restrictions.le("dataCode", dataDicDtlBO.getDataCode() + tolerance * bitlen));
		}
		dc.add(Restrictions.eq("dataLevel", dataDicDtlBO.getDataLevel() + 1));
		List<DataDicDtlBO> temp = this.findByDetachedCriteria(dc);
		return temp;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataDicDtlBO> getSubDataDicDtlBOList(DataDicDtlQueryVO param) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlByDataCode(param.getDataClassCode(), param.getDataCode());
		if (null != dataDicDtlBO) {
			DetachedCriteria dc = DetachedCriteria.forClass(DataDicDtlBO.class, "dataDicDtl");
			dc.add(Restrictions.eq("dataDicDtl.dataClassCode", dataDicDtlBO.getDataClassCode()));
			Integer tolerance = ClassifyCodingUtil.levelCodingInfo.get(dataDicDtlBO.getDataDicBO().getDataCodeType()).get(dataDicDtlBO.getDataLevel() + 1).get(ClassifyCodingUtil.TOLERANCE);
			Integer bitlen = ClassifyCodingUtil.levelCodingInfo.get(dataDicDtlBO.getDataDicBO().getDataCodeType()).get(dataDicDtlBO.getDataLevel() + 1).get(ClassifyCodingUtil.BITLEN);
			dc.add(Restrictions.ge("dataDicDtl.dataCode", dataDicDtlBO.getDataCode()));
			dc.add(Restrictions.le("dataDicDtl.dataCode", dataDicDtlBO.getDataCode() + tolerance*bitlen));
			if(param.getDataLevel()!=null){
				dc.add(Restrictions.le("dataLevel", param.getDataLevel()));
			}
			dc.addOrder(Order.asc("dataCode"));
			List<DataDicDtlBO> temp = this.findByDetachedCriteria(dc);
			return temp;
		} else {
			return null;
		}

	}
	@Override
	public List<DataDicDtlBO> findParendDataDtl(String dataclassCode,
			String sysNodeID) throws BizException {
		
		return dataDicDtlDAO.findParendDataDtl(dataclassCode, sysNodeID);
	}
	@Override
	public Integer findMaxSequenceNo(String dataclassCode, String sysNodeID)
			throws BizException {
		return dataDicDtlDAO.findMaxSequenceNo(dataclassCode, sysNodeID);
	}
	@Override
	public Integer findMaxDataCode(String dataclassCode, String sysNodeID) throws BizException {
		return dataDicDtlDAO.findMaxDataCode(dataclassCode, sysNodeID);
	}
}
