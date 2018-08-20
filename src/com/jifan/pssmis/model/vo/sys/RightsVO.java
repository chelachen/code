package com.jifan.pssmis.model.vo.sys;
import com.jifan.pssmis.model.vo.base.BaseVO;
public class RightsVO extends BaseVO  {
	
		private String id;
		private String rightsName;
        
        private String rightsCode; //权限代码

		public String getRightsCode() {
			return rightsCode;
		}

		public void setRightsCode(String rightsCode) {
			this.rightsCode = rightsCode;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getRightsName() {
			return rightsName;
		}

		public void setRightsName(String rightsName) {
			this.rightsName = rightsName;
		}


}
