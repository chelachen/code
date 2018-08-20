package com.jifan.pssmis.bs.sys.impl;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.sys.ICodeDtlBS;
import com.jifan.pssmis.dao.sys.ICodeDtlDAO;
import com.jifan.pssmis.dao.sys.impl.CodeDtlDAOImpl;
import com.jifan.pssmis.model.bo.sys.CodeDtlBO;
import com.jifan.pssmis.model.vo.sys.CodeDtlQueryVO;
@Service("codeDtlBS")
public class CodeDtlBSImpl extends BaseBSImpl<CodeDtlBO,String> implements ICodeDtlBS{
        @Resource
        private ICodeDtlDAO codeDtlDAO;
        
        @Resource
        public void setBaseDAO(ICodeDtlDAO codeDtlDAO) {
                super.setBaseDAO(codeDtlDAO);
        }
        public List<CodeDtlBO> findCodeDtlByParam(CodeDtlQueryVO param) {
                return this.codeDtlDAO.findCodeDtlByParam(param);
        }
        public String saveNotExist(CodeDtlBO entity) throws BizException{
             if(CommonUtil.isEmpty(entity.getId()))
                return super.save(entity);
             else
            	 super.update(entity);
             
             return entity.getId();
        }

        public ICodeDtlDAO getCodeDtlDAO() {
                return codeDtlDAO;
        }

        public void setCodeDtlDAO(ICodeDtlDAO codeDtlDAO) {
                this.codeDtlDAO = codeDtlDAO;
        }

}
