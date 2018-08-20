package com.jifan.pssmis.bs.sys.impl;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.sys.ITestBS;
import com.jifan.pssmis.dao.sys.ITestDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.model.bo.sys.TestBO;
import com.jifan.pssmis.model.vo.sys.TestQueryVO;
@Service("testBS")
public class TestBSImpl extends BaseBSImpl<TestBO,String> implements ITestBS{
        @Resource
        private ITestDAO testDAO;

        @Resource
        public void setBaseDAO(ITestDAO testDAO) {
                super.setBaseDAO(testDAO);
        }
        public List<TestBO> findTestByParam(TestQueryVO param) {
                return this.testDAO.findTestByParam(param);
        }
        public String saveNotExist(TestBO entity) throws BizException{
                DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TestBO.class);
//                Disjunction dis = Restrictions.disjunction();
//                if(CommonUtil.isNotEmpty(entity.getCode())){
//                        dis.add(Restrictions.eq("code",entity.getCode()));
//                }
//                detachedCriteria.add(dis);
                if(CommonUtil.isNotEmpty(entity.getId()))
                	detachedCriteria.add(Restrictions.ne("id", entity.getId()));
//                List list = super.findByDetachedCriteria(detachedCriteria);
//                if (list.size()>0)
//                        throw new BizException("编码:"+ entity.getCode() +"不能重复");
                if(CommonUtil.isEmpty(entity.getId()))
                	 super.save(entity);
                else
                	 super.update(entity);
                return entity.getId();
        }

        public ITestDAO getTestDAO() {
                return testDAO;
       }

       public void setTestDAO(ITestDAO testDAO) {
                this.testDAO = testDAO;
        }
}