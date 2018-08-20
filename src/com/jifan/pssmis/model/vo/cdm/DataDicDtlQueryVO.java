package com.jifan.pssmis.model.vo.cdm;
import java.io.Serializable;

import com.jifan.pssmis.model.vo.base.BaseVO;
public class DataDicDtlQueryVO extends BaseVO implements Serializable{
        private String dataClassCode; //数据类别代码
        
        private Integer parentDataCode; //父商代码
        
        private Integer dataCode; //属性代码
        
        private String corDataCode; //属性公司编码
        
        private String dataName; //属性名称
        
        private int isEnabled; //是否可用
        
        private int sequenceNo; //序号
        
        private String memo; //备注
        
        private Integer dataLevel; //当前层级
        
        private String sysNodeID;
        

        public DataDicDtlQueryVO() {
			super();
		}

        
		public DataDicDtlQueryVO(String dataClassCode, Integer dataCode,
				String sysNodeID) {
			super();
			this.dataClassCode = dataClassCode;
			this.dataCode = dataCode;
			this.sysNodeID = sysNodeID;
		}


		public String getDataClassCode() {
                return  dataClassCode;
        }

        public void setDataClassCode(String dataClassCode) {
                this.dataClassCode = dataClassCode;
        }

        public Integer getParentDataCode() {
                return  parentDataCode;
        }

        public void setParentDataCode(Integer parentDataCode) {
                this.parentDataCode = parentDataCode;
        }

        public Integer getDataCode() {
                return  dataCode;
        }

        public void setDataCode(Integer dataCode) {
                this.dataCode = dataCode;
        }

        public String getCorDataCode() {
                return  corDataCode;
        }

        public void setCorDataCode(String corDataCode) {
                this.corDataCode = corDataCode;
        }

        public String getDataName() {
                return  dataName;
        }

        public void setDataName(String dataName) {
                this.dataName = dataName;
        }

        public int getIsEnabled() {
                return  isEnabled;
        }

        public void setIsEnabled(int isEnabled) {
                this.isEnabled = isEnabled;
        }

        public int getSequenceNo() {
                return  sequenceNo;
        }

        public void setSequenceNo(int sequenceNo) {
                this.sequenceNo = sequenceNo;
        }

        public String getMemo() {
                return  memo;
        }

        public void setMemo(String memo) {
                this.memo = memo;
        }

		public String getSysNodeID() {
			return sysNodeID;
		}

		public void setSysNodeID(String sysNodeID) {
			this.sysNodeID = sysNodeID;
		}

		public Integer getDataLevel() {
			return dataLevel;
		}

		public void setDataLevel(Integer dataLevel) {
			this.dataLevel = dataLevel;
		}

}
