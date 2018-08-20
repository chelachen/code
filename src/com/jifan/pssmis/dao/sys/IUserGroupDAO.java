package com.jifan.pssmis.dao.sys;
import java.util.List;
import java.util.Map;

import com.jifan.pssmis.dao.base.IBaseDAO;
import com.jifan.pssmis.model.bo.sys.UserGroupBO;
import com.jifan.pssmis.model.bo.sys.UserGroupRights;
import com.jifan.pssmis.model.vo.sys.UserGroupQueryVO;
public interface IUserGroupDAO extends IBaseDAO<UserGroupBO,String>{
        public List<UserGroupBO> findUserGroupByParam(UserGroupQueryVO param);
        
        public Map<String,String> findUserGroupRightsMap(String templateBgNodeID,String nodeID);
        
        public List<UserGroupRights> findUserGroupRightsList(String templateBgNodeID,Integer userGroupType);
        
        public Map<String,String> findUserGroupMap(String templateBgNodeID,String nodeID);
        
        public Map<String,String> findTemplateUserGroupMap(String templateBgNodeID);
        
        public List<UserGroupBO> findUserGroupByParentID(String id);
}
