package com.jifan.pssmis.model.vo.cdm;
import com.jifan.pssmis.model.vo.base.BaseVO;
import java.util.Date;

import javax.persistence.Column;
public class CustomerQueryVO extends BaseVO  {
        private String code; //编号
        
        private String name; //名称
        
        private String linkman; //联系人
        
        private String mobile; //联系电话
        
        private String address; //地址
        
        private Integer type; //类型，1-客户，2-供应商
        

        public String getCode() {
                return  code;
        }

        public void setCode(String code) {
                this.code = code;
        }

        public String getName() {
                return  name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getLinkman() {
                return  linkman;
        }

        public void setLinkman(String linkman) {
                this.linkman = linkman;
        }

        public String getMobile() {
                return  mobile;
        }

        public void setMobile(String mobile) {
                this.mobile = mobile;
        }

        public String getAddress() {
                return  address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

}
