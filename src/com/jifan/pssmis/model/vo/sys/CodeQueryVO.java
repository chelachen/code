package com.jifan.pssmis.model.vo.sys;
import com.jifan.pssmis.model.vo.base.BaseVO;
import java.util.Date;
public class CodeQueryVO extends BaseVO  {
        private String className; //类名
        
        private String bagName; //包名
        
        private Integer num; //序号
        
        private String memo; //说明
        

        public String getClassName() {
                return  className;
        }

        public void setClassName(String className) {
                this.className = className;
        }

        public String getBagName() {
                return  bagName;
        }

        public void setBagName(String bagName) {
                this.bagName = bagName;
        }

        public Integer getNum() {
                return  num;
        }

        public void setNum(Integer num) {
                this.num = num;
        }

		public String getMemo() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}



}
