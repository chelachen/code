package com.jifan.pssmis.bs.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.jifan.pssmis.bs.base.IBaseJdbcBS;
import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.sys.IBusinessGroupBS;
import com.jifan.pssmis.dao.sys.IBusinessGroupDAO;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.BusinessGroupBO;
import com.jifan.pssmis.model.vo.sys.BusinessGroupQueryVO;

@Service("businessGroupBS")
public class BusinessGroupBSImpl extends BaseBSImpl<BusinessGroupBO, String>
		implements IBusinessGroupBS {
	@Resource
	private IBusinessGroupDAO businessGroupDAO;

	@Resource
	private IBaseJdbcBS baseJdbcBS;

	@Resource
	public void setBaseDAO(IBusinessGroupDAO businessGroupDAO) {
		super.setBaseDAO(businessGroupDAO);
	}


	public List<BusinessGroupBO> findBusinessGroupByParam(
			BusinessGroupQueryVO param) {
		return this.businessGroupDAO.findBusinessGroupByParam(param);
	}

	public String saveNotExist(BusinessGroupBO entity) throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(BusinessGroupBO.class);
		Disjunction dis = Restrictions.disjunction();
		if (entity.getBgCode() != null && !entity.getBgCode().equals("")) {
			dis.add(Restrictions.eq("bgCode", entity.getBgCode()));
		}
		if (entity.getBgName() != null && !entity.getBgName().equals("")) {
			dis.add(Restrictions.eq("bgName", entity.getBgName()));
		}
		detachedCriteria.add(dis);

		List list = super.findByDetachedCriteria(detachedCriteria);
		if (list.size() > 0)
			throw new BizException("商业组代码:" + entity.getBgCode() + "名称:"
					+ entity.getBgName() + "不能重复");
		return super.save(entity);
	}



	public IBusinessGroupDAO getBusinessGroupDAO() {
		return businessGroupDAO;
	}

	public void setBusinessGroupDAO(IBusinessGroupDAO businessGroupDAO) {
		this.businessGroupDAO = businessGroupDAO;
	}


	public IBaseJdbcBS getBaseJdbcBS() {
		return baseJdbcBS;
	}

	public void setBaseJdbcBS(IBaseJdbcBS baseJdbcBS) {
		this.baseJdbcBS = baseJdbcBS;
	}

}
