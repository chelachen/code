package com.jifan.pssmis.dao.cdm.impl;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.cdm.ICustomerDAO;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.cdm.CustomerBO;
import com.jifan.pssmis.model.vo.cdm.CustomerQueryVO;
@Repository
public class CustomerDAOImpl extends BaseDAOImpl<CustomerBO,String> implements ICustomerDAO {
        public List<CustomerBO> findcustomerByParam(CustomerQueryVO param) {
                String hql = "from " + CustomerBO.class.getName() + " where 1=1";
                List list = new ArrayList();
                        if (param.getCode() !=null && !param.getCode().equals("")){
                        hql = hql + " and code = ? ";
                        list.add(param.getCode());
                }
                 if (param.getType() !=null && param.getType().intValue() !=0){
                            hql = hql + " and type=?  ";
                            list.add(param.getType());
                   }
                        if (param.getName() !=null && !param.getName().equals("")){
                        hql = hql + " and name like '%' || ? || '%'  ";
                        list.add(param.getName());
                }
                        if (param.getLinkman() !=null && !param.getLinkman().equals("")){
                        hql = hql + " and linkman  like '%' || ? || '%'   ";
                        list.add(param.getLinkman());
                }
                        if (param.getMobile() !=null && !param.getMobile().equals("")){
                        hql = hql + " and mobile = ? ";
                        list.add(param.getMobile());
                }
                       if (param.getAddress() !=null && !param.getAddress().equals("")){
                        hql = hql + " and address like '%' || ? || '%'   ";
                        list.add(param.getAddress());
                }
                hql = hql + " order by shortCode";
                Object[] objects = DAOUtil.getObjectsByList(list);
                List<CustomerBO> retultList = this.getHibernateTemplate().find(hql, objects);
                return retultList;
        }

}
