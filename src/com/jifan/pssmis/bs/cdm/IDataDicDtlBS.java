package com.jifan.pssmis.bs.cdm;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.cdm.DataDicDtlBO;
import com.jifan.pssmis.model.bo.cdm.MateriaBO;
import com.jifan.pssmis.model.vo.cdm.DataDicDtlQueryVO;

public interface IDataDicDtlBS extends IBaseBS<DataDicDtlBO, String> {
	public List<DataDicDtlBO> findDataDicDtlByParam(DataDicDtlQueryVO param);

	public String saveNotExist(DataDicDtlBO entity) throws BizException;
	
	 public void updateNotExist(DataDicDtlBO entity) throws BizException;
	 
	 public void deleteAll(DataDicDtlBO entity) throws BizException;

	public DataDicDtlBO getDataDicDtlByDataCode(String dataClassCode, Integer dataCode);

	public List<DataDicDtlBO> getSubDataDicDtlBOListByParent(DataDicDtlBO dataDicDtlBO);
	
	/**
	 * 取以dataCode为根节点的树形List
	 * @param param
	 * @return
	 */
	public List<DataDicDtlBO> getSubDataDicDtlBOList(DataDicDtlQueryVO param);

	List<DataDicDtlBO> getSubDataDicDtlBOListByLevel(DataDicDtlBO dataDicDtlBO);

	List<DataDicDtlBO> findDataDicDtlByDetachedCriteria(
			DetachedCriteria detachedCriteria);
	
	 public List<DataDicDtlBO> findParendDataDtl(String dataclassCode,
				String sysNodeID) throws BizException;
	  public Integer findMaxSequenceNo(String dataclassCode, String sysNodeID)
		throws BizException;
	  
	  public Integer findMaxDataCode(String dataclassCode, String sysNodeID) throws BizException;
}
