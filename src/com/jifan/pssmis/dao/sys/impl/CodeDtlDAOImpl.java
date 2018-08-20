package com.jifan.pssmis.dao.sys.impl;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.sys.ICodeDtlDAO;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.sys.CodeDtlBO;
import com.jifan.pssmis.model.vo.sys.CodeDtlQueryVO;
@Repository
public class CodeDtlDAOImpl extends BaseDAOImpl<CodeDtlBO,String> implements ICodeDtlDAO {
        public List<CodeDtlBO> findCodeDtlByParam(CodeDtlQueryVO param) {
                String hql = "from " + CodeDtlBO.class.getName() + " where 1=1";
                List list = new ArrayList();
                        if (param.getFieldName() !=null && !param.getFieldName().equals("")){
                        hql = hql + " and fieldName = ? ";
                        list.add(param.getFieldName());
                }
                        if (param.getFieldType() !=null && !param.getFieldType().equals("")){
                        hql = hql + " and fieldType = ? ";
                        list.add(param.getFieldType());
                }
                        if (param.getCodeID() !=null && !param.getCodeID().equals("")){
                        hql = hql + " and codeID = ? ";
                        list.add(param.getCodeID());
                }
                      
                Object[] objects = DAOUtil.getObjectsByList(list);
                List<CodeDtlBO> retultList = this.getHibernateTemplate().find(hql, objects);
                return retultList;
        }

}
