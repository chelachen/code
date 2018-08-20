package com.jifan.pssmis.bs.sys;
import java.util.List;
import java.util.Map;

import com.jifan.pssmis.bs.base.IBaseBS;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.model.bo.sys.UserGroupBO;
import com.jifan.pssmis.model.bo.sys.UserGroupRights;
import com.jifan.pssmis.model.vo.sys.RightsVO;
import com.jifan.pssmis.model.vo.sys.UserGroupQueryVO;
import com.jifan.pssmis.model.vo.sys.UserVO;
public interface IUserGroupBS extends IBaseBS<UserGroupBO,String>  {
        public List<UserGroupBO> findUserGroupByParam(UserGroupQueryVO param);
        public String saveNotExist(UserGroupBO entity) throws BizException;
		public void updateNotExist(UserGroupBO entity) throws BizException;
		
		public List<UserGroupBO> getSubUserGroups(String id);
		
		public void deleteAll(UserGroupBO entity) throws BizException;
		
		/**
		 * 获取用户组对应的用户列表
		 * @param entity
		 * @param exists true 获取用户组关联的用户列表，false 获取不再用户组里的用户列表
		 * @return
		 */
		public List<UserVO>  loadUsers(UserGroupBO entity,boolean exists) ;
		
		/**
		 * 用户组组员保存
		 * @param entity 
		 * @param userList
		 */
		public void saveGroupUsers(UserGroupBO entity,List<UserVO> userList);
		
		/**
		 * 用户组权限保存
		 * @param entity 
		 * @param userList
		 */
		public void saveGroupRights(UserGroupBO entity,List<RightsVO> rightsList);
		
		/**
		 * 用户角色保存
		 * @param entity 
		 * @param userList
		 */
		public void saveUserRoles(String userId,List<UserGroupBO> roles);
}
