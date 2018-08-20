package com.jifan.pssmis.model.bo.cdm;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;

import com.jd.open.api.sdk.internal.util.StringUtil;
import com.jifan.pssmis.foundation.util.PingYinUtil;
import com.jifan.pssmis.model.bo.base.AuditableBO;
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true,selectBeforeUpdate=true,optimisticLock = OptimisticLockType.VERSION)
@Table(name = "CDM_CUSTOMER")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cdm_customer_entity")
public class CustomerBO extends AuditableBO  {
        private static final long serialVersionUID = -6528505879877926404L;
        @Column(name = "CODE" , length=100)
        private String code; //编号
        
        @Column(name = "SHORT_CODE" , length=100)
        private String shortCode; //短编号
        
        @Column(name = "NAME" , length=100)
        private String name; //名称
        
        @Column(name = "LINKMAN" , length=100)
        private String linkman; //联系人
        
        @Column(name = "MOBILE" , length=100)
        private String mobile; //联系电话
        
        @Column(name = "ADDRESS" , length=100)
        private String address; //地址
        
        @Column(name = "FAX" , length=100)
        private String fax; //传真
        @Column(name = "EMAIL" , length=100)
        private String email; //电  邮
        
        @Column(name = "TYPE" , length=11)
        private Integer type; //类型，1-客户，2-供应商
        
       
        public CustomerBO(){
        }

        public CustomerBO(Integer type,String code,String name,String linkman,String mobile,String address,String fax,String email){
                this.type=type;
                this.code=code;
                this.name=name;
                this.linkman=linkman;
                this.mobile=mobile;
                this.address=address;
                this.fax=fax;
                this.email=email;
        }

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

		public String getFax() {
			return fax;
		}

		public void setFax(String fax) {
			this.fax = fax;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
			if(!StringUtil.isEmpty(this.name)){
				this.shortCode=PingYinUtil.getFirstSpellOne(this.name);
			}
		}

		public String getShortCode() {
			return shortCode;
		}

		public void setShortCode(String shortCode) {
			this.shortCode = shortCode;
		}



}
