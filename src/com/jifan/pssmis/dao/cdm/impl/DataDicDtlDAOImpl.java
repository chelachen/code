package com.jifan.pssmis.dao.cdm.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.cdm.IDataDicDtlDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.cdm.DataDicDtlBO;
import com.jifan.pssmis.model.vo.cdm.DataDicDtlQueryVO;

@Repository
public class DataDicDtlDAOImpl extends BaseDAOImpl<DataDicDtlBO, String> implements IDataDicDtlDAO {
	
	public List<DataDicDtlBO> findDataDicDtlByParam(DataDicDtlQueryVO param) {
		String hql = "from " + DataDicDtlBO.class.getName() + " where 1=1";
		List<Object> list = new ArrayList<Object>();
		if (param.getDataClassCode() != null && !param.getDataClassCode().equals("")) {
			hql = hql + " and dataClassCode = ? ";
			list.add(param.getDataClassCode());
		}
		if (param.getParentDataCode() != null && !param.getParentDataCode().equals("")) {
			hql = hql + " and parentDataCode = ? ";
			list.add(param.getParentDataCode());
		}
		if (param.getDataCode() != null && !param.getDataCode().equals("")) {
			hql = hql + " and dataCode = ? ";
			list.add(param.getDataCode());
		}
		if (param.getCorDataCode() != null && !param.getCorDataCode().equals("")) {
			hql = hql + " and corDataCode = ? ";
			list.add(param.getCorDataCode());
		}
		if (param.getDataName() != null && !param.getDataName().equals("")) {
			hql = hql + " and dataName = ? ";
			list.add(param.getDataName());
		}
		if (param.getIsEnabled() != 0) {
			hql = hql + " and isEnabled = ? ";
			list.add(param.getIsEnabled());
		}
		if (param.getSequenceNo() != 0) {
			hql = hql + " and sequenceNo = ? ";
			list.add(param.getSequenceNo());
		}
		if (param.getMemo() != null && !param.getMemo().equals("")) {
			hql = hql + " and memo = ? ";
			list.add(param.getMemo());
		}
		if (null != param.getDataLevel()) {
			hql = hql + " and dataLevel = ? ";
			list.add(param.getDataLevel());
		}
		if (CommonUtil.isNotEmpty(param.getSysNodeID())) {
			hql = hql + " and sysNodeID = ? ";
			list.add(param.getSysNodeID());
		}
		hql = hql + " order by sequenceNo asc";
		Object[] objects = DAOUtil.getObjectsByList(list);
		this.getHibernateTemplate().setCacheQueries(true);
		List<DataDicDtlBO> retultList = this.getHibernateTemplate().find(hql, objects);
		return retultList;
	}
	public List<DataDicDtlBO> findParendDataDtl(String dataclassCode,String sysNodeID) throws BizException{
		StringBuffer sbf=new StringBuffer("SELECT t.DATA_CLASS_CODE as dataClassCode,t.DATA_CODE as dataCode ,t.DATA_NAME as dataName from cdm_data_dic_dtl t where t.DATA_CLASS_CODE='"+dataclassCode+"' ");
		sbf.append(" and SYS_NODE_ID ='"+sysNodeID+"' and EXISTS(SELECT DISTINCT PARENT_DATA_CODE   FROM `cdm_data_dic_dtl` b ");
		sbf.append("where DATA_CLASS_CODE='"+dataclassCode+"' and b.SYS_NODE_ID ='"+sysNodeID+"' and t.DATA_CODE=b.PARENT_DATA_CODE)");
		return super.findBySql(sbf.toString(), DataDicDtlBO.class, true);
	}
	public Integer findMaxSequenceNo(String dataclassCode,String sysNodeID) throws BizException{
		StringBuffer sbf=new StringBuffer("SELECT IFNULL(max(SEQUENCE_NO),-1) FROM `cdm_data_dic_dtl` WHERE DATA_CLASS_CODE='"+dataclassCode+"' and SYS_NODE_ID ='"+sysNodeID+"'");
		List objList=super.findBySql(sbf.toString(), null,true);
		return Integer.valueOf(objList.get(0).toString());
	}
	public Integer findMaxDataCode(String dataclassCode,String sysNodeID) throws BizException{
		StringBuffer sbf=new StringBuffer("SELECT IFNULL(max(DATA_CODE),-1) FROM `cdm_data_dic_dtl` WHERE DATA_CLASS_CODE='"+dataclassCode+"' and SYS_NODE_ID ='"+sysNodeID+"'");
		List objList=super.findBySql(sbf.toString(), null,true);
		return Integer.valueOf(objList.get(0).toString());
	}

}
