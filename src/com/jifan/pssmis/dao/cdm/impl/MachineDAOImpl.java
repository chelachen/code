package com.jifan.pssmis.dao.cdm.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.cdm.IMachineDAO;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.cdm.MachineBO;
import com.jifan.pssmis.model.vo.cdm.MachineQueryVO;
@Repository
public class MachineDAOImpl extends BaseDAOImpl<MachineBO,String> implements IMachineDAO {
        public List<MachineBO> findMachineByParam(MachineQueryVO param) {
                String hql = "from " + MachineBO.class.getName() + " where 1=1";
                List list = new ArrayList();
                if (param.getCode() !=null && !param.getCode().equals("")){
                        hql = hql + " and code like '%' || ? || '%'  ";
                        list.add(param.getCode());
                }
                 if (param.getType()!=null && param.getType().intValue()!=0){
                        hql = hql + " and type = ? ";
                        list.add(param.getType());
                }
                if (param.getName() !=null && !param.getName().equals("")){
                	  	hql = hql + " and name like '%' || ? || '%'  ";
                        list.add(param.getName());
                }
                Object[] objects = DAOUtil.getObjectsByList(list);
                List<MachineBO> retultList = this.getHibernateTemplate().find(hql, objects);
                return retultList;
        }

}
