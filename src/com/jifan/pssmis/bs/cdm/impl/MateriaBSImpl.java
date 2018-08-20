package com.jifan.pssmis.bs.cdm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.jifan.pssmis.bs.base.impl.BaseBSImpl;
import com.jifan.pssmis.bs.cdm.IMateriaBS;
import com.jifan.pssmis.dao.base.IBaseJdbcDAO;
import com.jifan.pssmis.dao.cdm.IMateriaDAO;
import com.jifan.pssmis.dao.cdm.IStockLogDAO;
import com.jifan.pssmis.foundation.contants.DataClassCodeContants;
import com.jifan.pssmis.foundation.contants.DataCodeContants;
import com.jifan.pssmis.foundation.datadic.DataCodeUtil;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.model.bo.cdm.DataDicDtlBO;
import com.jifan.pssmis.model.bo.cdm.MateriaBO;
import com.jifan.pssmis.model.bo.cdm.StockLogBO;
import com.jifan.pssmis.model.vo.cdm.MateriaQueryVO;
import com.jifan.pssmis.model.vo.cdm.StockLogVO;

@Service("materiaBS")
public class MateriaBSImpl extends BaseBSImpl<MateriaBO, String> implements IMateriaBS {
	@Resource
	private IMateriaDAO materiaDAO;
	@Resource
	private IStockLogDAO stockLogDAO;
	@Resource
	private IBaseJdbcDAO baseJdbcDAO;

	@Resource
	public void setBaseDAO(IMateriaDAO materiaDAO) {
		super.setBaseDAO(materiaDAO);
	}

	public List<MateriaBO> findMateriaByParam(MateriaQueryVO param) {
		return this.materiaDAO.findMateriaByParam(param);
	}
	
	 public List<MateriaBO> findMateriaByParamEqual(MateriaQueryVO param){
		 return this.materiaDAO.findMateriaByParamEqual(param);
	 }
	
	public void saveAndCheckExist(MateriaBO entity) throws BizException{
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MateriaBO.class);
		if (entity.getName() != null && !entity.getName().equals("")) {
			detachedCriteria.add(Restrictions.eq("name", entity.getName()));
		}else
			throw new BizException("材料名称不能为空！");
		
		if (entity.getSize() != null && !entity.getSize().equals("")) {
			detachedCriteria.add(Restrictions.eq("size", entity.getSize()));
		}else
			throw new BizException("材料尺码不能为空！");
		List list = super.findByDetachedCriteria(detachedCriteria);
		if (list.size() > 0){
			MateriaBO entityForUp = (MateriaBO)list.get(0);
			entityForUp.copy(entity);
			this.update(entityForUp);
		}else
			this.save(entity);
	}


	public String saveNotExist(MateriaBO entity) throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MateriaBO.class);
		if (entity.getName() != null && !entity.getName().equals("")) {
			detachedCriteria.add(Restrictions.eq("name", entity.getName()));
		}else
			throw new BizException("材料名称不能为空！");
		
		if (entity.getSize() != null && !entity.getSize().equals("")) {
			detachedCriteria.add(Restrictions.eq("size", entity.getSize()));
		}else
			throw new BizException("材料尺码不能为空！");
		
		List list = super.findByDetachedCriteria(detachedCriteria);
		if (list.size() > 0)
			throw new BizException("材料名称:" + entity.getName() + "，尺码:" + entity.getSize() + "不能重复");
		return super.save(entity);
	}

	public void updateNotExist(MateriaBO entity) throws BizException {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(MateriaBO.class);
		if (entity.getName() != null && !entity.getName().equals("")) {
			detachedCriteria.add(Restrictions.eq("name", entity.getName()));
		}else
			throw new BizException("材料名称不能为空！");
		
		if (entity.getSize() != null && !entity.getSize().equals("")) {
			detachedCriteria.add(Restrictions.eq("size", entity.getSize()));
		}else
			throw new BizException("材料尺码不能为空！");
		
		detachedCriteria.add(Restrictions.ne("id", entity.getId()));
		List list = super.findByDetachedCriteria(detachedCriteria);
		if (list.size() > 0)
			throw new BizException("材料名称:" + entity.getName() + "，尺码:" + entity.getSize() + "不能重复");
		super.update(entity);
	}

	/**
	 * 修改 材料库存统一接口
	 * 
	 * @param entity
	 *            材料BO
	 * @param changeAmount
	 *            改变数量
	 * @param changeType
	 *            改变类型
	 *            DataCodeContants.STOCK_CHANGE_TYPE_ADD(增加),DataCodeContants
	 *            .STOCK_CHANGE_TYPE_REDUCE
	 *            (减少),DataCodeContants.STOCK_CHANGE_TYPE_COVER(覆盖)
	 * @param memo
	 *            备注信息
	 */
	public void saveChangeStockAmount(MateriaBO entity, Integer changeAmount, String changeType, String memo)
			throws BizException {
		if (CommonUtil.isEmpty(changeType))
			throw new BizException("changeType改变类型不能传入空值！");
		if (changeAmount != null && !changeAmount.equals(0)) {
			Integer bfchange = entity.getAmount();
			Integer afchange = 0;
			// 增加
			if (changeType.equals(DataCodeContants.STOCK_CHANGE_TYPE_ADD))
				afchange = bfchange + changeAmount;
			// 减少
			else if (changeType.equals(DataCodeContants.STOCK_CHANGE_TYPE_REDUCE))
				afchange = bfchange - changeAmount;
			// 覆盖
			else if (changeType.equals(DataCodeContants.STOCK_CHANGE_TYPE_COVER))
				afchange = changeAmount;
			else
				throw new BizException("changeType改变类型传入的值符合规则！传入值为：" + changeType);
			// 修改库存数据
			entity.setAmount(afchange);
			if (CommonUtil.isEmpty(entity.getId())) {
				this.saveNotExist(entity);
			} else {
				this.update(entity);
			}
			// 记录变动日志
			StockLogBO slb = new StockLogBO(entity.getId(), changeAmount, bfchange, afchange, memo, entity
				.getUpdateUser());
			this.stockLogDAO.save(slb);
		} else
			throw new BizException("changeAmount改变数量不能传入空或0！");

	}

	/**
	 * 获取库存变动明细列表
	 * 
	 * @param materiaID
	 */
	public List<StockLogVO> findStockLogList(String materiaID) {
		if (CommonUtil.isNotEmpty(materiaID)) {
			String sql = "SELECT BEFORE_AMOUNT as beforeAmount,CHANGE_AMOUNT as changeAmount,AFTER_AMOUNT as afterAmount,CREATE_USER as opUser,CREATE_TIME as opDate,MEMO as memo  from cdm_stock_log where MATERIA_ID ='" + materiaID + "' order by CREATE_TIME desc";
			return this.baseJdbcDAO.runSQLReturnList(sql, null, StockLogVO.class);
		}
		return new ArrayList<StockLogVO>();
	}
	
	/**
	 * 自动分类,把所有未分小类的标准件，根据物料名称与分类名称、关键词的匹配度进行分类
	 */
	public void saveAutoSubClassAll(){
		 String sql ="update cdm_materia a "
				 +" set a.SUBCLASS=(SELECT b.data_code from cdm_data_dic_dtl b where b.DATA_CLASS_CODE='CDM_MATERIA_SUBCLASS' "
				 +" and (length(a.name)- length(replace(a.name,b.data_name,''))) !=0 "
				 +" ORDER BY (length(a.name)- length(replace(a.name,b.data_name,'')) ) desc limit 1)  "
				 +" where  CLASSIFY=1 and  SUBCLASS is null or SUBCLASS =0";
		 this.baseJdbcDAO.executeSQL(sql);
	}
	


	public IMateriaDAO getMateriaDAO() {
		return materiaDAO;
	}

	public void setMateriaDAO(IMateriaDAO materiaDAO) {
		this.materiaDAO = materiaDAO;
	}

	public IStockLogDAO getStockLogDAO() {
		return stockLogDAO;
	}

	public void setStockLogDAO(IStockLogDAO stockLogDAO) {
		this.stockLogDAO = stockLogDAO;
	}

	public IBaseJdbcDAO getBaseJdbcDAO() {
		return baseJdbcDAO;
	}

	public void setBaseJdbcDAO(IBaseJdbcDAO baseJdbcDAO) {
		this.baseJdbcDAO = baseJdbcDAO;
	}

}
