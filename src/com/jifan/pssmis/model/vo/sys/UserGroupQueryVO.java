package com.jifan.pssmis.model.vo.sys;
import com.jifan.pssmis.model.vo.base.BaseVO;
import java.util.Date;
public class UserGroupQueryVO extends BaseVO  {
        private String groupCode; //组代码
        
        private String groupName; //组名称
        
        private String parentGroup; //父组
        
        private String remark; //说明
        
        private String sysNodeID; //所属节点
        
        private Integer userGroupType;//组类别
        
        private String id;
               
        
        public UserGroupQueryVO(String sysNodeID,Integer userGroupType) {			
			this.sysNodeID = sysNodeID;
			this.userGroupType = userGroupType;
		}

		public UserGroupQueryVO() {
			super();
		}

		public String getGroupCode() {
                return  groupCode;
        }

        public void setGroupCode(String groupCode) {
                this.groupCode = groupCode;
        }

        public String getGroupName() {
                return  groupName;
        }

        public void setGroupName(String groupName) {
                this.groupName = groupName;
        }

        public String getParentGroup() {
                return  parentGroup;
        }

        public void setParentGroup(String parentGroup) {
                this.parentGroup = parentGroup;
        }

        public String getRemark() {
                return  remark;
        }

        public void setRemark(String remark) {
                this.remark = remark;
        }

        public String getSysNodeID() {
                return  sysNodeID;
        }

        public void setSysNodeID(String sysNodeID) {
                this.sysNodeID = sysNodeID;
        }

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Integer getUserGroupType() {
			return userGroupType;
		}

		public void setUserGroupType(Integer userGroupType) {
			this.userGroupType = userGroupType;
		}
		
}
