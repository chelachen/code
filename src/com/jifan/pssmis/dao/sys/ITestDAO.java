package com.jifan.pssmis.dao.sys;
import java.util.List;
import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.sys.TestBO;
import com.jifan.pssmis.model.vo.sys.TestQueryVO;
public interface ITestDAO extends IBaseDAO<TestBO,String> {
		public List<TestBO> findTestByParam(TestQueryVO param);
}