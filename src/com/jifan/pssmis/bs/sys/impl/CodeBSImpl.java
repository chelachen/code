package com.jifan.pssmis.bs.sys.impl;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.sys.ICodeBS;
import com.jifan.pssmis.dao.sys.ICodeDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.model.bo.sys.CodeBO;
import com.jifan.pssmis.model.vo.sys.CodeQueryVO;
@Service("codeBS")
public class CodeBSImpl extends BaseBSImpl<CodeBO,String> implements ICodeBS{
        @Resource
        private ICodeDAO codeDAO;
        
        @Resource
        public void setBaseDAO(ICodeDAO codeDAO) {
                super.setBaseDAO(codeDAO);
        }
        public List<CodeBO> findCodeByParam(CodeQueryVO param) {
                return this.codeDAO.findCodeByParam(param);
        }
        public String saveNotExist(CodeBO entity) throws BizException{
                DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CodeBO.class);
                Disjunction dis = Restrictions.disjunction();
                if(CommonUtil.isNotEmpty(entity.getClassName())){
                        dis.add(Restrictions.eq("className",entity.getClassName()));
                }
                detachedCriteria.add(dis);
                if(CommonUtil.isNotEmpty(entity.getId()))
                	detachedCriteria.add(Restrictions.ne("id", entity.getId()));
                List list = super.findByDetachedCriteria(detachedCriteria);
                if (list.size()>0)
                        throw new BizException("类名:"+ entity.getClassName() +"不能重复");
                if(CommonUtil.isEmpty(entity.getId()))
                	 super.save(entity);
                else
                	 super.update(entity);
                return entity.getId();
        }

        public ICodeDAO getCodeDAO() {
                return codeDAO;
        }

        public void setCodeDAO(ICodeDAO codeDAO) {
                this.codeDAO = codeDAO;
        }

}
