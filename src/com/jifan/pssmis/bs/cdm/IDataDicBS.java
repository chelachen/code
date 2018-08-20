package com.jifan.pssmis.bs.cdm;

import java.util.List;

import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.cdm.DataDicBO;
import com.jifan.pssmis.model.bo.cdm.DataDicDtlBO;
import com.jifan.pssmis.model.vo.cdm.DataDicQueryVO;

public interface IDataDicBS extends IBaseBS<DataDicBO, String> {
	public List<DataDicBO> findDataDicByParam(DataDicQueryVO param);

	public String saveNotExist(DataDicBO entity) throws BizException;
	
	public void updateNotExist(DataDicBO entity) throws BizException;
	
	public void deleteAll(DataDicBO entity) throws BizException;

	public List<DataDicDtlBO> getDtlListByDataClassCode(String dataClassCode);
	
	public DataDicDtlBO getMaxDataCode(String  dataClassCode);
}
