package com.jifan.pssmis.bs.sys;
import java.util.List;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.model.bo.sys.TestBO;
import com.jifan.pssmis.model.vo.sys.TestQueryVO;
public interface ITestBS extends IBaseBS<TestBO,String>  {
        public List<TestBO> findTestByParam(TestQueryVO param);
        public String saveNotExist(TestBO entity) throws BizException;
}
                    	