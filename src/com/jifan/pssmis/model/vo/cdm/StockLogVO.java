package com.jifan.pssmis.model.vo.cdm;
import com.jifan.pssmis.foundation.util.CalendarUtil;
import com.jifan.pssmis.foundation.util.DateUtil;
import com.jifan.pssmis.model.vo.base.BaseVO;
import java.util.Date;
public class StockLogVO extends BaseVO  {
        private String materiaID; //物料ID
        private Integer changeAmount; //改变数量
        private Integer beforeAmount; //改变前数量
        private Integer afterAmount; //改变后数量
        private String opUser;//操作用户
        private Date opDate;//操作时间
        private String memo; // 备注
        

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

		public String getOpUser() {
			return opUser;
		}

		public void setOpUser(String opUser) {
			this.opUser = opUser;
		}

		public Date getOpDate() {
			return opDate;
		}
		
		public String getOpDateStr() {
			return CalendarUtil.getDateToStrMill(this.opDate);
		}

		public void setOpDate(Date opDate) {
			this.opDate = opDate;
		}

		public String getMemo() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}

}
