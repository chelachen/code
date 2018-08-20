package com.jifan.pssmis.model.vo.sys;
import com.jifan.pssmis.model.vo.base.BaseVO;
public class RightsQueryVO extends BaseVO  {
        private String id; //ID
        
        private String rightsCode; //权限代码
        
        private String rightsName; //权限名称
        
        private int rightsType; //权限类型
        
        private String sourceInfo; //资源信息
        
        private String sysNodeID; //所属节点
        
        private String refRights;//权限来源
        
        public RightsQueryVO(){}
        public RightsQueryVO(String rightsCode,String rightsName,int rightsTpe,String refRights){
        	this.rightsCode=rightsCode;
        	this.rightsName=rightsName;
        	this.rightsType=rightsTpe;
        	this.refRights=refRights;
        	
        }
        public String getId() {
                return  id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getRightsCode() {
                return  rightsCode;
        }

        public void setRightsCode(String rightsCode) {
                this.rightsCode = rightsCode;
        }

        public String getRightsName() {
                return  rightsName;
        }

        public void setRightsName(String rightsName) {
                this.rightsName = rightsName;
        }

        public int getRightsType() {
                return  rightsType;
        }

        public void setRightsType(int rightsType) {
                this.rightsType = rightsType;
        }

        public String getSourceInfo() {
                return  sourceInfo;
        }

        public void setSourceInfo(String sourceInfo) {
                this.sourceInfo = sourceInfo;
        }

        public String getSysNodeID() {
                return  sysNodeID;
        }

        public void setSysNodeID(String sysNodeID) {
                this.sysNodeID = sysNodeID;
        }

		public String getRefRights() {
			return refRights;
		}

		public void setRefRights(String refRights) {
			this.refRights = refRights;
		}

}
