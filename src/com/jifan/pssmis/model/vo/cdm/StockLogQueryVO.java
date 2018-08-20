package com.jifan.pssmis.model.vo.cdm;
import com.jifan.pssmis.model.vo.base.BaseVO;
import java.util.Date;
public class StockLogQueryVO extends BaseVO  {
        private String materiaID; //物料ID
        

        public String getMateriaID() {
                return  materiaID;
        }

        public void setMateriaID(String materiaID) {
                this.materiaID = materiaID;
        }

}
