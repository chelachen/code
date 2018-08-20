package com.jifan.pssmis.model.bo.cdm;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.foundation.datadic.DataCodeUtil;
import com.jifan.pssmis.model.bo.base.AuditableBO;
import com.jifan.pssmis.model.bo.sys.Money;
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true,selectBeforeUpdate=true,optimisticLock = OptimisticLockType.VERSION)
@Table(name = "CDM_MATERIA")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cdm_materia_entity")
public class MateriaBO extends AuditableBO  {
        private static final long serialVersionUID = -6528505879877926404L;

        @Column(name = "CODE" , length=100)
        private String code; //编码
        
        @Column(name = "NAME" , length=100)
        private String name; //名称
        
        @Column(name = "UNIT" , length=100)
        private String unit; //单位
        
        @Column(name = "AMOUNT" , columnDefinition = "int default 0")
        private Integer amount; //库存数量
        
    	@Embedded
    	@AttributeOverrides( { @AttributeOverride(name = "value", column = @Column(name = "PRICE", precision = 15, scale = 5)) })
        private Money price; //单价
    	
    	@Column(name = "SIZE" , length=100)
        private String size; //规格
    	
    	@Column(name = "BRAND" , length=100)
        private String brand; //品牌
    	
    	@Column(name = "CLASSIFY" , length=100)
        private Integer classify=0; //分类
    	
    	@Column(name = "SUBCLASS" , length=11)
        private Integer subclass=0; //小类
    	
    	@Column(name = "TEXTURE" , length=100)
        private String texture; //材质
    	
    	@Transient
    	private Integer tackStockNum=0;//
        

        public MateriaBO(){
        	this.price=new Money(BigDecimal.ZERO);
        }

        public MateriaBO(String code,String name,Money price,String unit,String size,String brand,String texture,Integer classify,Integer subclass,Integer amount){
                this.code=code;
                this.name=name;
                this.unit=unit;
                this.price=price;
                this.size=size;
                this.brand=brand;
                this.classify=classify;
                this.subclass=subclass;
                this.texture=texture;
                this.amount=amount;
        }
        
        public void copy(MateriaBO from){
              this.name=from.name;
              this.unit=from.unit;
              this.price=from.price;
              this.size=from.size;
              this.brand=from.brand;
              if(from.classify!=null && from.classify.intValue()!=0)
            	  this.classify=from.classify;
              if(from.subclass!=null && from.subclass.intValue()!=0)
            	  this.subclass=from.subclass;
//              this.classify=classify;
              this.texture=from.texture;
              this.amount=from.amount;
        }
        
        public void ChangeAmountByTackStock(){
        	this.amount=this.tackStockNum;
        }

        public String getId() {
                return  id;
        }

        public void setId(String id) {
                this.id = id;
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

        public String getUnit() {
                return  unit;
        }

        public void setUnit(String unit) {
                this.unit = unit;
        }

		public Money getPrice() {
			return price;
		}

		public void setPrice(Money price) {
			this.price = price;
		}

		public Integer getAmount() {
			return amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
		}

		public Integer getTackStockNum() {
			return tackStockNum;
		}

		public void setTackStockNum(Integer tackStockNum) {
			this.tackStockNum = tackStockNum;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public Integer getClassify() {
			return classify;
		}
		
		public String getClassifyName() {
			return DataCodeUtil.getClassifyName(this.classify);
		}

		public void setClassify(Integer classify) {
			this.classify = classify;
		}

		public String getTexture() {
			return texture;
		}

		public void setTexture(String texture) {
			this.texture = texture;
		}

		public Integer getSubclass() {
			return subclass;
		}

		public void setSubclass(Integer subclass) {
			this.subclass = subclass;
		}

		public String getSubclassName() {
			return DataCodeUtil.getSubclassName(this.subclass);
		}

}
