package com.jifan.pssmis.dao.cdm.impl;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.cdm.IMateriaDAO;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.cdm.MateriaBO;
import com.jifan.pssmis.model.vo.cdm.MateriaQueryVO;
@Repository
public class MateriaDAOImpl extends BaseDAOImpl<MateriaBO,String> implements IMateriaDAO {
        public List<MateriaBO> findMateriaByParam(MateriaQueryVO param) {
                String hql = "from " + MateriaBO.class.getName() + " where 1=1";
                List list = new ArrayList();
                        if (param.getId() !=null && !param.getId().equals("")){
                        hql = hql + " and id = ? ";
                        list.add(param.getId());
                }
                  if (param.getKeyCode() !=null && !param.getKeyCode().equals("")){
                            hql = hql + " and (code like '%' || ? || '%' or  name like '%' || ? || '%' or  size like '%' || ? || '%' ) ";
                            list.add(param.getKeyCode());
                            list.add(param.getKeyCode());
                            list.add(param.getKeyCode());
                 }         
                        
              if (param.getCode() !=null && !param.getCode().equals("")){
                        hql = hql + " and code like '%' || ? || '%'  ";
                        list.add(param.getCode());
                }
                if (param.getName() !=null && !param.getName().equals("")){
                        hql = hql + " and name like '%' || ? || '%'  ";
                        list.add(param.getName());
                }
                if (param.getSize() !=null && !param.getSize().equals("")){
                    hql = hql + " and size like '%' || ? || '%'  ";
                    list.add(param.getSize());
            }
                if (param.getClassify() !=null && param.getClassify().intValue()!=0){
                            hql = hql + " and classify = ? ";
                            list.add(param.getClassify());
                }
                if (param.getSubclass() !=null && param.getSubclass().intValue()!=0){
                    hql = hql + " and subclass = ? ";
                    list.add(param.getSubclass());
        }
               if (param.getUnit() !=null && !param.getUnit().equals("")){
                        hql = hql + " and unit = ? ";
                        list.add(param.getUnit());
                }
                Object[] objects = DAOUtil.getObjectsByList(list);
                List<MateriaBO> retultList = this.getHibernateTemplate().find(hql, objects);
                return retultList;
        }
        
        public List<MateriaBO> findMateriaByParamEqual(MateriaQueryVO param) {
            String hql = "from " + MateriaBO.class.getName() + " where 1=1";
            List list = new ArrayList();

              if (param.getCode() !=null && !param.getCode().equals("")){
                    hql = hql + " and code =? ";
                    list.add(param.getCode());
            }
            if (param.getName() !=null && !param.getName().equals("")){
                    hql = hql + " and name=?  ";
                    list.add(param.getName());
            }
            if (param.getClassify() !=null && param.getClassify().intValue()!=0){
                        hql = hql + " and classify = ? ";
                        list.add(param.getClassify());
            }
           if (param.getSize()!=null && !param.getSize().equals("")){
                    hql = hql + " and size = ? ";
                    list.add(param.getSize());
            }
            Object[] objects = DAOUtil.getObjectsByList(list);
            List<MateriaBO> retultList = this.getHibernateTemplate().find(hql, objects);
            return retultList;
    }


}
