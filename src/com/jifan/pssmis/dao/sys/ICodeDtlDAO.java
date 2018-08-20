package com.jifan.pssmis.dao.sys;
import java.util.List;
import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.sys.CodeDtlBO;
import com.jifan.pssmis.model.vo.sys.CodeDtlQueryVO;
public interface ICodeDtlDAO extends IBaseDAO<CodeDtlBO,String>{
        public List<CodeDtlBO> findCodeDtlByParam(CodeDtlQueryVO param);

}
