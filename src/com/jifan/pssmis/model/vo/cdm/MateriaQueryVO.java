package com.jifan.pssmis.model.vo.cdm;
import com.jifan.pssmis.model.vo.base.BaseVO;
import java.util.Date;
public class MateriaQueryVO extends BaseVO  {
        private String id; //主键
        
        private String code; //编码
        
        private String name; //名称
        
        private String unit; //单位
        
        private double price; //单价
        
        private Integer classify=0; //分类
        private Integer subclass=0; //分类
        
        private String size; //规格
        
        private String keyCode; //关键字
        

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

        public double getPrice() {
                return  price;
        }

        public void setPrice(double price) {
                this.price = price;
        }

		public Integer getClassify() {
			return classify;
		}

		public void setClassify(Integer classify) {
			this.classify = classify;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public Integer getSubclass() {
			return subclass;
		}

		public void setSubclass(Integer subclass) {
			this.subclass = subclass;
		}

		public String getKeyCode() {
			return keyCode;
		}

		public void setKeyCode(String keyCode) {
			this.keyCode = keyCode;
		}

}
