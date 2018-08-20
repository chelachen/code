package com.jifan.pssmis.dao.sys;
import java.util.List;
import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.sys.CodeBO;
import com.jifan.pssmis.model.vo.sys.CodeQueryVO;
public interface ICodeDAO extends IBaseDAO<CodeBO,String>{
        public List<CodeBO> findCodeByParam(CodeQueryVO param);

}
