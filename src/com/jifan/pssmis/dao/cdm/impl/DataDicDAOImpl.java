package com.jifan.pssmis.dao.cdm.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.cdm.IDataDicDAO;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.cdm.DataDicBO;
import com.jifan.pssmis.model.bo.cdm.DataDicDtlBO;
import com.jifan.pssmis.model.vo.cdm.DataDicQueryVO;

@Repository
public class DataDicDAOImpl  extends BaseDAOImpl<DataDicBO, String> implements IDataDicDAO {
	public List<DataDicBO> findDataDicByParam(DataDicQueryVO param) {
		String hql = "from " + DataDicBO.class.getName() + " where 1=1";
		List list = new ArrayList();
		if (param.getDataClassCode() != null && !param.getDataClassCode().equals("")) {
			hql = hql + " and dataClassCode like '%' || ? || '%'  ";
			list.add(param.getDataClassCode());
		}
		if (param.getDataClassName() != null && !param.getDataClassName().equals("")) {
			hql = hql + " and dataClassName like '%' || ? || '%'  ";
			list.add(param.getDataClassName());
		}
		if (param.getDataCodeType() != -1) {
			hql = hql + " and dataCodeType = ? ";
			list.add(param.getDataCodeType());
		}

		if (param.getMemo() != null && !param.getMemo().equals("")) {
			hql = hql + " and memo = ? ";
			list.add(param.getMemo());
		}
		hql = hql + " order by dataClassCode ";
		Object[] objects = DAOUtil.getObjectsByList(list);
		List<DataDicBO> retultList = this.getHibernateTemplate().find(hql, objects);
		return retultList;
	}

	public List<DataDicDtlBO> getDtlListByDataClassCode(String dataClassCode){
		if(CommonUtil.isEmpty(dataClassCode)){
			throw new RuntimeException("getDataClassCode，传入的dataClassCode不能为空");
		}
		List<DataDicDtlBO> dataList = new ArrayList<DataDicDtlBO>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DataDicDtlBO.class);
		if(CommonUtil.isNotEmpty(dataClassCode))
			detachedCriteria.add(Restrictions.eq("dataClassCode", dataClassCode));
		detachedCriteria.addOrder(Order.asc("dataCode"));
		dataList = super.findByDetachedCriteria(detachedCriteria);
		return dataList;
	}
	
	public DataDicDtlBO getMaxDataCode(String  dataClassCode){
		if(CommonUtil.isEmpty(dataClassCode)){
			throw new RuntimeException("getDataClassCode，传入的dataClassCode不能为空");
		}
		List<DataDicDtlBO> dataList = new ArrayList<DataDicDtlBO>();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DataDicDtlBO.class);
		if(CommonUtil.isNotEmpty(dataClassCode))
			detachedCriteria.add(Restrictions.eq("dataClassCode", dataClassCode));
		detachedCriteria.add(Restrictions.sqlRestriction(" 1=1 order by Data_code desc limit 1 "));
		dataList = super.findByDetachedCriteria(detachedCriteria);
		if(dataList !=null && dataList.size()>0)
			return dataList.get(0);
		return null;
	}

}
