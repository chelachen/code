package com.jifan.pssmis.bs.sys;
import java.util.List;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.model.bo.sys.CodeBO;
import com.jifan.pssmis.model.vo.sys.CodeQueryVO;
public interface ICodeBS extends IBaseBS<CodeBO,String>  {
        public List<CodeBO> findCodeByParam(CodeQueryVO param);
        public String saveNotExist(CodeBO entity) throws BizException;

}
