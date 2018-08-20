package com.jifan.pssmis.bs.cdm.impl;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.cdm.ICustomerBS;
import com.jifan.pssmis.dao.cdm.ICustomerDAO;
import com.jifan.pssmis.dao.cdm.impl.CustomerDAOImpl;
import com.jifan.pssmis.model.bo.cdm.CustomerBO;
import com.jifan.pssmis.model.vo.cdm.CustomerQueryVO;
@Service("customerBS")
public class CustomerBSImpl extends BaseBSImpl<CustomerBO,String> implements ICustomerBS{
        @Resource
        private ICustomerDAO customerDAO;
        
        @Resource
        public void setBaseDAO(ICustomerDAO customerDAO) {
                super.setBaseDAO(customerDAO);
        }
        public List<CustomerBO> findCustomerByParam(CustomerQueryVO param) {
                return this.customerDAO.findcustomerByParam(param);
        }
        public String saveNotExist(CustomerBO entity) throws BizException{
                DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerBO.class);
                Disjunction dis = Restrictions.disjunction();
//                if(entity.getCode() != null && !entity.getCode().equals("")){
//                        dis.add(Restrictions.eq("customerCode",entity.getCode()));
//                }
                if(entity.getName() != null && !entity.getName().equals("")){
                        dis.add(Restrictions.eq("name",entity.getName()));
                }
                detachedCriteria.add(dis);
                List list = super.findByDetachedCriteria(detachedCriteria);
                if (list.size()>0)
                        throw new BizException("客户名称:"+entity.getName()+"不能重复");
                return super.save(entity);
        }
        
        public void updateNotExist(CustomerBO entity) throws BizException{
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerBO.class);
            Disjunction dis = Restrictions.disjunction();
            if(entity.getName() != null && !entity.getName().equals("")){
                    dis.add(Restrictions.eq("name",entity.getName()));
            }
            detachedCriteria.add(Restrictions.ne("id", entity.getId()));
            detachedCriteria.add(dis);
            List list = super.findByDetachedCriteria(detachedCriteria);
            if (list.size()>0)
                    throw new BizException("客户名称:"+entity.getName()+"不能重复");
            this.update(entity);
    }


        public ICustomerDAO getcustomerDAO() {
                return customerDAO;
        }

        public void setcustomerDAO(ICustomerDAO customerDAO) {
                this.customerDAO = customerDAO;
        }

}
