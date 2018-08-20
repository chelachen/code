package com.jifan.pssmis.model.vo.sys;
import com.jifan.pssmis.model.vo.base.BaseVO;
import java.util.Date;
public class CodeDtlQueryVO extends BaseVO  {
        private String fieldName; //字段名
        
        private String fieldType; //类型
        
        private String codeID; //类ID
        
        private int num; //序号
        
        private int length; //长度
        
        private int canNull; //是否可空
        

        public String getFieldName() {
                return  fieldName;
        }

        public void setFieldName(String fieldName) {
                this.fieldName = fieldName;
        }

        public String getFieldType() {
                return  fieldType;
        }

        public void setFieldType(String fieldType) {
                this.fieldType = fieldType;
        }

        public String getCodeID() {
                return  codeID;
        }

        public void setCodeID(String codeID) {
                this.codeID = codeID;
        }

        public int getNum() {
                return  num;
        }

        public void setNum(int num) {
                this.num = num;
        }

        public int getLength() {
                return  length;
        }

        public void setLength(int length) {
                this.length = length;
        }

        public int getCanNull() {
                return  canNull;
        }

        public void setCanNull(int canNull) {
                this.canNull = canNull;
        }

}
