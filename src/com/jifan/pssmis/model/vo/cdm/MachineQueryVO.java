package com.jifan.pssmis.model.vo.cdm;
import com.jifan.pssmis.model.vo.base.BaseVO;
import java.util.Date;
public class MachineQueryVO extends BaseVO  {
        private String code; //机台名称
        private Integer type; //工艺类型 
        private String name; //名称
        
        

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

		public void setType(Integer type) {
			this.type = type;
		}

}
