package com.jifan.pssmis.model.bo.cdm;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.foundation.datadic.DataCodeUtil;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.model.bo.base.AuditableBO;
import com.jifan.pssmis.model.bo.sys.Money;
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true,selectBeforeUpdate=true,optimisticLock = OptimisticLockType.VERSION)
@Table(name = "CDM_MACHINE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cdm_machine_entity")
public class MachineBO extends AuditableBO  {
        private static final long serialVersionUID = -6528505879877926404L;
        @Column(name = "CODE" , length=50)
        private String code; //机台名称
        
        @Column(name = "TYPE" , length=11)
        private Integer type; //工艺类型
        
        @Column(name = "NAME" , length=100)
        private String name; //名称
        
        @Column(name = "PROVIDER_ID" , length=36)
        private String providerID; //所属加工单位
        
    	@Embedded
    	@AttributeOverrides( { @AttributeOverride(name = "value", column = @Column(name = "PRICE", precision = 15, scale = 2)) })
        private Money price=new Money(BigDecimal.ZERO); //单价
        
        public MachineBO(){
        }

        public MachineBO(String code,Integer type,String name,String providerID,Money price){
                this.code=code;
                this.type=type;
                this.name=name;
                this.providerID=providerID;
                this.price=price;
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

		public Integer getType() {
			return type;
		}
		public String getTypeName() {
			return DataCodeUtil.getCraftTypeName(this.type);
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getProviderID() {
			return providerID;
		}
		public String getProviderName() {
			if(CommonUtil.isNotEmpty(providerID))
				return DataCodeUtil.getProviderName(providerID);
			return "";
		}

		public void setProviderID(String providerID) {
			this.providerID = providerID;
		}

		public Money getPrice() {
			return price;
		}

		public void setPrice(Money price) {
			this.price = price;
		}

}
