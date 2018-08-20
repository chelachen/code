package com.jifan.pssmis.bs.cdm;
import java.util.List;

import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.cdm.MateriaBO;
import com.jifan.pssmis.model.vo.cdm.MateriaQueryVO;
import com.jifan.pssmis.model.vo.cdm.StockLogVO;
public interface IMateriaBS extends IBaseBS<MateriaBO,String>  {
        public List<MateriaBO> findMateriaByParam(MateriaQueryVO param);
        public String saveNotExist(MateriaBO entity) throws BizException;
        public void saveAndCheckExist(MateriaBO entity) throws BizException;
        public void updateNotExist(MateriaBO entity) throws BizException;
        
    	/**
    	 * 修改 材料库存统一接口
    	 * 
    	 * @param entity
    	 *            材料BO
    	 * @param changeAmount
    	 *            改变数量
    	 * @param changeType
    	 *            改变类型 DataCodeContants.STOCK_CHANGE_TYPE_ADD(增加),DataCodeContants.STOCK_CHANGE_TYPE_REDUCE(减少),DataCodeContants.STOCK_CHANGE_TYPE_COVER(覆盖)
    	 * @param memo
    	 *            备注信息
    	 */
    	public void saveChangeStockAmount(MateriaBO entity, Integer changeAmount, String changeType, String memo) throws BizException;
    	
    	/**
    	 * 获取库存变动明细列表
    	 * @param materiaID
    	 */
    	public List<StockLogVO> findStockLogList(String materiaID);
    	
    	public List<MateriaBO> findMateriaByParamEqual(MateriaQueryVO param);
    	
    	/**
    	 * 自动分类,把所有未分小类的标准件，
    	 * 根据物料名称与分类名称、关键词的匹配度进行分类
    	 */
    	public void saveAutoSubClassAll();
}
