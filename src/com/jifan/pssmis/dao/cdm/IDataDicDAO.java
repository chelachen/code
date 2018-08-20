package com.jifan.pssmis.dao.cdm;
import java.util.List;
import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.cdm.DataDicBO;
import com.jifan.pssmis.model.bo.cdm.DataDicDtlBO;
import com.jifan.pssmis.model.vo.cdm.DataDicQueryVO;
public interface IDataDicDAO extends IBaseDAO<DataDicBO,String>{
	
        public List<DataDicBO> findDataDicByParam(DataDicQueryVO param);
        
        public List<DataDicDtlBO> getDtlListByDataClassCode(String dataClassCode);
        
        public DataDicDtlBO getMaxDataCode(String  dataClassCode);

}
