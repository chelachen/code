package com.jifan.pssmis.dao.cdm.impl;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import com.jifan.pssmis.dao.base.impl.BaseDAOImpl;
import com.jifan.pssmis.dao.cdm.IFileDAO;
import com.jifan.pssmis.foundation.util.DAOUtil;
import com.jifan.pssmis.model.bo.cdm.FileBO;
import com.jifan.pssmis.model.vo.cdm.FileQueryVO;
@Repository
public class FileDAOImpl extends BaseDAOImpl<FileBO,String> implements IFileDAO {
        public List<FileBO> findFileByParam(FileQueryVO param) {
                String hql = "from " + FileBO.class.getName() + " where 1=1";
                List list = new ArrayList();
                        if (param.getMemo() !=null && !param.getMemo().equals("")){
                        hql = hql + " and memo = ? ";
                        list.add(param.getMemo());
                }
                        if (param.getFileUrl() !=null && !param.getFileUrl().equals("")){
                        hql = hql + " and fileUrl = ? ";
                        list.add(param.getFileUrl());
                }
                Object[] objects = DAOUtil.getObjectsByList(list);
                List<FileBO> retultList = this.getHibernateTemplate().find(hql, objects);
                return retultList;
        }

}
