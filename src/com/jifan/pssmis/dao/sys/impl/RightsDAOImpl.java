package com.jifan.pssmis.dao.sys.impl;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.sys.IRightsDAO;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.foundation.util.StringUtil;
import com.jifan.pssmis.model.bo.sys.RightsBO;
import com.jifan.pssmis.model.vo.sys.RightsQueryVO;
import com.jifan.pssmis.model.vo.sys.RightsVO;
@Repository
@SuppressWarnings({ "unchecked", "serial" })
public class RightsDAOImpl extends BaseDAOImpl<RightsBO,String> implements IRightsDAO {
		public List<RightsBO> findRightsByParam(RightsQueryVO param) {
                String hql = "from " + RightsBO.class.getName() + " where 1=1";
                List list = new ArrayList();
                        if (param.getId() !=null && !param.getId().equals("")){
                        hql = hql + " and id = ? ";
                        list.add(param.getId());
                }
                        if (param.getRightsCode() !=null && !param.getRightsCode().equals("")){
                        hql = hql + " and rightsCode = ? ";
                        list.add(param.getRightsCode());
                }
                        if (param.getRightsName() !=null && !param.getRightsName().equals("")){
                        hql = hql + " and rightsName = ? ";
                        list.add(param.getRightsName());
                }
                        if (param.getRightsType() !=0){
                        hql = hql + " and rightsType = ? ";
                        list.add(param.getRightsType());
                }
                        if (param.getSourceInfo() !=null && !param.getSourceInfo().equals("")){
                        hql = hql + " and sourceInfo = ? ";
                        list.add(param.getSourceInfo());
                }
                        if (param.getSysNodeID() !=null && !param.getSysNodeID().equals("")){
                        hql = hql + " and sysNodeID = ? ";
                        list.add(param.getSysNodeID());
                }
                Object[] objects = DAOUtil.getObjectsByList(list);
                List<RightsBO> retultList = this.getHibernateTemplate().find(hql, objects);
                return retultList;
        }
        public List findByParam(List<String> param) {
        	StringBuffer sql=new StringBuffer();
    		sql.append("SELECT SUBSTR(rights_code FROM 18) as rightsCode FROM sys_rights WHERE 1=1 ");
    			 sql.append(" and source_info in ( "+StringUtil.getSqlString(param)+ ") ");
    			return super.findBySql(sql.toString(),RightsVO.class,true);	
        }

}
