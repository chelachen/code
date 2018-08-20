package com.jifan.pssmis.model.bo.sys;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.model.bo.base.AuditableBO;
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true,selectBeforeUpdate=true,optimisticLock = OptimisticLockType.VERSION)
@Table(name = "SYS_RIGHTS")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "sys_rights_entity")
public class RightsBO extends AuditableBO {
        private static final long serialVersionUID = -6528505879877926404L;
        @Column(name = "RIGHTS_CODE" , length=100 ,nullable = false )
        private String rightsCode; //权限代码
        
        @Column(name = "RIGHTS_NAME" , length=200)
        private String rightsName; //权限名称
        
        @Column(name = "RIGHTS_TYPE" , length=0 ,nullable = false )
        private int rightsType; //权限类型
        
        @Column(name = "SOURCE_INFO" , length=100)
        private String sourceInfo; //资源信息
        
        @Column(name = "FUNCTION_INFO" , length=100)
        private String functionInfo; //功能点信息
        
        @Column(name = "FUNCTION_ID" , length=36)
        private String functionID; //功能点ID
        
        @Transient
    	private boolean choiced; // 用于款式添加颜色时判断是否选择 20110627 xiebs
        @Transient
        private String refRight;//权限来源
        @Transient
    	private boolean assignable; // 用于款式添加颜色时判断是否选择 20110627 xiebs

        public boolean isChoiced() {
			return choiced;
		}

		public void setChoiced(boolean choiced) {
			this.choiced = choiced;
		}
        public RightsBO(){
        }

        public RightsBO(String rightsCode,String rightsName,int rightsType,String sourceInfo,String functionInfo){
                this.rightsCode=rightsCode;
                this.rightsName=rightsName;
                this.rightsType=rightsType;
                this.sourceInfo=sourceInfo;
                this.functionInfo=functionInfo;
        }
        public RightsBO copyRightsBO(){
        	RightsBO rightsBO2=new RightsBO(this.getRightsCode(),this.getRightsName(),this.getRightsType(),this.getSourceInfo(),this.functionInfo);
            return rightsBO2;
        }
        public RightsBO copyRightsBO(String sysNodeID){
        	RightsBO rightsBO2=new RightsBO(this.getRightsCode(),this.getRightsName(),this.getRightsType(),this.getSourceInfo(),this.functionInfo);
            return rightsBO2;
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


		public boolean isAssignable() {
			return assignable;
		}

		public void setAssignable(boolean assignable) {
			this.assignable = assignable;
		}
		public String getRefRight() {
			return refRight;
		}

		public void setRefRight(String refRight) {
			this.refRight = refRight;
		}

		public String getFunctionInfo() {
			return functionInfo;
		}

		public void setFunctionInfo(String functionInfo) {
			this.functionInfo = functionInfo;
		}

		public String getFunctionID() {
			return functionID;
		}

		public void setFunctionID(String functionID) {
			this.functionID = functionID;
		}
	

}