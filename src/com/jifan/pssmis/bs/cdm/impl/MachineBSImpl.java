package com.jifan.pssmis.bs.cdm.impl;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.cdm.IMachineBS;
import com.jifan.pssmis.dao.cdm.IMachineDAO;
import com.jifan.pssmis.dao.cdm.impl.MachineDAOImpl;
import com.jifan.pssmis.model.bo.cdm.MachineBO;
import com.jifan.pssmis.model.vo.cdm.MachineQueryVO;
@Service("machineBS")
public class MachineBSImpl extends BaseBSImpl<MachineBO,String> implements IMachineBS{
        @Resource
        private IMachineDAO machineDAO;
        
        @Resource
        public void setBaseDAO(IMachineDAO machineDAO) {
                super.setBaseDAO(machineDAO);
        }
        public List<MachineBO> findMachineByParam(MachineQueryVO param) {
                return this.machineDAO.findMachineByParam(param);
        }

        public String saveNotExist(MachineBO entity) throws BizException{
                DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MachineBO.class);
                Disjunction dis = Restrictions.disjunction();
                if(entity.getCode() != null && !entity.getCode().equals("")){
                        dis.add(Restrictions.eq("code",entity.getCode()));
                }else
        			throw new BizException("机台编码不能为空！");
                detachedCriteria.add(dis);
                List list = super.findByDetachedCriteria(detachedCriteria);
                if (list.size()>0)
                        throw new BizException("机台代码:"+ entity.getCode() +"不能重复");
                return super.save(entity);
        }
        
        public void updateNotExist(MachineBO entity) throws BizException{
        	 DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MachineBO.class);
             Disjunction dis = Restrictions.disjunction();
             if(entity.getCode() != null && !entity.getCode().equals("")){
                     dis.add(Restrictions.eq("code",entity.getCode()));
             }else
     			throw new BizException("机台编码不能为空！");
            detachedCriteria.add(dis);
    		detachedCriteria.add(Restrictions.ne("id", entity.getId()));
            List list = super.findByDetachedCriteria(detachedCriteria);
            if (list.size()>0)
                    throw new BizException("机台代码:"+ entity.getCode() +"不能重复");
           super.update(entity);
        }

        public IMachineDAO getMachineDAO() {
                return machineDAO;
        }

        public void setMachineDAO(IMachineDAO machineDAO) {
                this.machineDAO = machineDAO;
        }

}
