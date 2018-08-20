package com.jifan.pssmis.bs.sys;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.RightsBO;
import com.jifan.pssmis.model.vo.sys.RightsQueryVO;
import com.jifan.pssmis.model.vo.sys.RightsVO;

public interface IRightsBS extends IBaseBS<RightsBO, String> {
	public List<RightsBO> findRightsByParam(RightsQueryVO param);

	public String saveNotExist(RightsBO entity) throws BizException;

	public void updateNotExist(RightsBO entity) throws BizException;

	/**
	 * 权限控制接口，判断一个具体的权限 chencl 2011-06-28
	 * 
	 * @param userID 用户ID
	 * @param sourceInfo 资源信息
	 * @param rightsType 权限类型
	 * @return true:有权限，false:没有权限
	 */
	public boolean rightsCheck(String userID, String sourceInfo, int rightsType);

	/**
	 * 权限控制接口，取得某用户的某权限类型的所有权限信息Map(资源信息,权限BO) chencl 2011-06-28
	 * 
	 * @param userID 用户ID
	 * @param sourceInfo 资源信息
	 * @param rightsType 权限类型
	 * @return true:有权限，false:没有权限
	 */
	public Map getRightsByUserAndType(String userID, int rightsType);


	/**
	 * 批量保存功能权限
	 * 
	 * @param sysNodeID
	 * @param existsValidate是否需要存在性校验
	 * @throws BizException
	 * @throws Exception
	 */
	void CreateFuncRights(String sysNodeID, boolean existsValidate)
			throws BizException, Exception;

	public List findByParam(List<String> param);
	
	/**
	 * 获取用户组的权限信息
	 * @param userGroupID 用户组ID
	 * @param exists true：获取已关联的权限，false：获取未关联的权限
	 * @return
	 */
	public List<RightsVO> findAllFuncRights(String userGroupID,boolean exists);


}
