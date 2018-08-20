package com.jifan.pssmis.bs.sys;
import java.util.List;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.model.bo.sys.CodeDtlBO;
import com.jifan.pssmis.model.vo.sys.CodeDtlQueryVO;
public interface ICodeDtlBS extends IBaseBS<CodeDtlBO,String>  {
        public List<CodeDtlBO> findCodeDtlByParam(CodeDtlQueryVO param);
        public String saveNotExist(CodeDtlBO entity) throws BizException;

}
