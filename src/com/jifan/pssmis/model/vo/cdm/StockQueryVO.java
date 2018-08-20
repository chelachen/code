package com.jifan.pssmis.model.vo.cdm;
import com.jifan.pssmis.model.vo.base.BaseVO;
import java.util.Date;
public class StockQueryVO extends BaseVO  {
        private String materiaID; //物料ID
        
        private String warehouseID; //仓库ID
        
        private Integer amount; //数量
        

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
