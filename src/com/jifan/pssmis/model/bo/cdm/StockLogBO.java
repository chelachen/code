package com.jifan.pssmis.model.bo.cdm;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;
import com.jifan.pssmis.model.bo.base.AuditableBO;
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true,selectBeforeUpdate=true,optimisticLock = OptimisticLockType.VERSION)
@Table(name = "CDM_STOCK_LOG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cdm_stock_entity")
public class StockLogBO extends AuditableBO  {
        private static final long serialVersionUID = -6528505879877926404L;
        @Column(name = "MATERIA_ID" , length=36)
        private String materiaID; //物料ID
        
        @Column(name = "CHANGE_AMOUNT" , length=11)
        private Integer changeAmount; //改变数量
        
        @Column(name = "BEFORE_AMOUNT" , length=11)
        private Integer beforeAmount; //改变前数量
        
        @Column(name = "AFTER_AMOUNT" , length=11)
        private Integer afterAmount; //改变后数量
        
    	@Column(name = "MEMO", length = 255)
    	private String memo; // 备注
        
        public StockLogBO(String materiaID,Integer changeAmount,Integer beforeAmount,Integer afterAmount,String memo,String changeUser){
        	this.materiaID=materiaID;
        	this.changeAmount=changeAmount;
        	this.beforeAmount=beforeAmount;
        	this.afterAmount=afterAmount;
        	this.memo=memo;
        	this.createUser=changeUser;
        	this.updateUser=changeUser;
        	this.createTime=new Date();
        	this.updateTime=new Date();
        }
        
        public StockLogBO(){
        }

        public String getMateriaID() {
                return  materiaID;
        }

        public void setMateriaID(String materiaID) {
                this.materiaID = materiaID;
        }

		public Integer getChangeAmount() {
			return changeAmount;
		}

		public void setChangeAmount(Integer changeAmount) {
			this.changeAmount = changeAmount;
		}

		public Integer getBeforeAmount() {
			return beforeAmount;
		}

		public void setBeforeAmount(Integer beforeAmount) {
			this.beforeAmount = beforeAmount;
		}

		public Integer getAfterAmount() {
			return afterAmount;
		}

		public void setAfterAmount(Integer afterAmount) {
			this.afterAmount = afterAmount;
		}

		public String getMemo() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}




}
