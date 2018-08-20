package com.jifan.pssmis.bs.cdm.impl;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.cdm.IDataDicBS;
import com.jifan.pssmis.bs.cdm.IDataDicDtlBS;
import com.jifan.pssmis.dao.cdm.IDataDicDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.cdm.DataDicBO;
import com.jifan.pssmis.model.bo.cdm.DataDicDtlBO;
import com.jifan.pssmis.model.vo.cdm.DataDicQueryVO;
@Service("dataDicBS")
public class DataDicBSImpl extends BaseBSImpl<DataDicBO,String> implements IDataDicBS{
        @Resource
        private IDataDicDAO dataDicDAO;
        
        @Resource
        private IDataDicDtlBS dataDicDtlBS ;
        
        @Resource
        public void setBaseDAO(IDataDicDAO dataDicDAO) {
                super.setBaseDAO(dataDicDAO);
        }
        public List<DataDicBO> findDataDicByParam(DataDicQueryVO param) {
                return this.dataDicDAO.findDataDicByParam(param);
        }

        public IDataDicDAO getDataDicDAO() {
                return dataDicDAO;
        }

        public void setDataDicDAO(IDataDicDAO dataDicDAO) {
                this.dataDicDAO = dataDicDAO;
        }
		@Override
		public String saveNotExist(DataDicBO entity) throws BizException {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DataDicBO.class);
            Disjunction dis = Restrictions.disjunction();
            if(entity.getDataClassCode() != null && !entity.getDataClassCode().equals("")){
                    dis.add(Restrictions.eq("dataClassCode",entity.getDataClassCode()));
            }
            detachedCriteria.add(dis);
            List list = super.findByDetachedCriteria(detachedCriteria);
            if (list.size()>0)
                    throw new BizException("数据类别代码:"+ entity.getDataClassCode() +"名称:"+entity.getDataClassName()+"已经存在！");
            return super.save(entity);
		}
		
		public void updateNotExist(DataDicBO entity) throws BizException{
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DataDicBO.class);
            Disjunction dis = Restrictions.disjunction();
            if(entity.getDataClassCode() != null && !entity.getDataClassCode().equals("")){
                    dis.add(Restrictions.eq("dataClassCode",entity.getDataClassCode()));
            }
            detachedCriteria.add(dis);
            detachedCriteria.add(Restrictions.ne("id",entity.getId()));
            List list = super.findByDetachedCriteria(detachedCriteria);
            if (list.size()>0)
                    throw new BizException("数据类别代码:"+ entity.getDataClassCode() +"名称:"+entity.getDataClassName()+"已经存在！");
             super.update(entity);
		}
		
		/**
		 * 删除数据字典主子表
		 */
		public void deleteAll(DataDicBO entity) throws BizException{
			//删除子表
			for(DataDicDtlBO dtl :entity.getDatadtlList()){
				dataDicDtlBS.delete(dtl);
			}
			this.dataDicDAO.delete(entity);
		}
		
		public List<DataDicDtlBO> getDtlListByDataClassCode(String  dataClassCode){
			return this.dataDicDAO.getDtlListByDataClassCode(dataClassCode);
		}
		
		public DataDicDtlBO getMaxDataCode(String  dataClassCode){
			return this.dataDicDAO.getMaxDataCode(dataClassCode);
		}
		
		public IDataDicDtlBS getDataDicDtlBS() {
			return dataDicDtlBS;
		}
		public void setDataDicDtlBS(IDataDicDtlBS dataDicDtlBS) {
			this.dataDicDtlBS = dataDicDtlBS;
		}

}
