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
@Table(name = "CDM_STOCK")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cdm_stock_entity")
public class StockBO extends AuditableBO  {
        private static final long serialVersionUID = -6528505879877926404L;
        @Column(name = "MATERIA_ID" , length=36)
        private String materiaID; //物料ID
        
        @Column(name = "WAREHOUSE_ID" , length=36)
        private String warehouseID; //仓库ID
        
        @Column(name = "AMOUNT" , length=11)
        private Integer amount; //数量
        

        public StockBO(){
        }

        public StockBO(String materiaID,String warehouseID,int amount){
                this.materiaID=materiaID;
                this.warehouseID=warehouseID;
                this.amount=amount;
        }

        public String getMateriaID() {
                return  materiaID;
        }

        public void setMateriaID(String materiaID) {
                this.materiaID = materiaID;
        }

        public String getWarehouseID() {
                return  warehouseID;
        }

        public void setWarehouseID(String warehouseID) {
                this.warehouseID = warehouseID;
        }

		public Integer getAmount() {
			return amount;
		}

		public void setAmount(Integer amount) {
			this.amount = amount;
		}



}
