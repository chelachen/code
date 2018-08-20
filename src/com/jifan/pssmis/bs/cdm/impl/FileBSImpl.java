package com.jifan.pssmis.bs.cdm.impl;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.cdm.IFileBS;
import com.jifan.pssmis.dao.cdm.IFileDAO;
import com.jifan.pssmis.dao.cdm.impl.FileDAOImpl;
import com.jifan.pssmis.model.bo.cdm.FileBO;
import com.jifan.pssmis.model.vo.cdm.FileQueryVO;
@Service("fileBS")
public class FileBSImpl extends BaseBSImpl<FileBO,String> implements IFileBS{
        @Resource
        private IFileDAO fileDAO;
        
        @Resource
        public void setBaseDAO(IFileDAO fileDAO) {
                super.setBaseDAO(fileDAO);
        }
        public List<FileBO> findFileByParam(FileQueryVO param) {
                return this.fileDAO.findFileByParam(param);
        }
        public String saveNotExist(FileBO entity) throws BizException{
             if(CommonUtil.isEmpty(entity.getId()))
            	 super.save(entity);
             else
            	 super.update(entity);
             
             return entity.getId();
        }

        public IFileDAO getFileDAO() {
                return fileDAO;
        }

        public void setFileDAO(IFileDAO fileDAO) {
                this.fileDAO = fileDAO;
        }

}
