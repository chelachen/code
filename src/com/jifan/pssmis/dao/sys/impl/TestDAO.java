package com.jifan.pssmis.dao.sys.impl;
import java.util.List;
import java.util.ArrayList;
import com.jifan.pssmis.foundation.util.CommonUtil;
import org.springframework.stereotype.Repository;
import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.dao.sys.ITestDAO;
import com.jifan.pssmis.model.bo.sys.TestBO;
import com.jifan.pssmis.model.vo.sys.TestQueryVO;
@Repository
public class TestDAO extends BaseDAOImpl<TestBO,String> implements ITestDAO{
		public List<TestBO> findTestByParam(TestQueryVO param){
			String hql = "from " + TestBO.class.getName() + " where 1=1";
			List list = new ArrayList();
			if (CommonUtil.isNotEmpty(param.getTestOne())) {
				hql = hql + " and testOne = ? ";
				list.add(param.getTestOne());
			}
			if (param.getTestTwo() != null && param.getTestTwo().intValue()!=0) {
				hql = hql + " and testTwo = ? ";
				list.add(param.getTestTwo());
			}
			Object[] objects = DAOUtil.getObjectsByList(list);
			List<TestBO> retultList = this.getHibernateTemplate()
					.find(hql, objects);
			return retultList;
		}
}