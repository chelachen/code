package com.jifan.pssmis.dao.cdm;
import java.util.List;
import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.cdm.DataDicDtlBO;
import com.jifan.pssmis.model.vo.cdm.DataDicDtlQueryVO;
public interface IDataDicDtlDAO extends IBaseDAO<DataDicDtlBO,String>{
        public List<DataDicDtlBO> findDataDicDtlByParam(DataDicDtlQueryVO param);

        public List<DataDicDtlBO> findParendDataDtl(String dataclassCode,
				String sysNodeID) throws BizException;

        public Integer findMaxSequenceNo(String dataclassCode, String sysNodeID)
				throws BizException;

        public Integer findMaxDataCode(String dataclassCode, String sysNodeID) throws BizException;

}
