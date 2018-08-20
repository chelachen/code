package com.jifan.pssmis.model.vo.cdm;
import com.jifan.pssmis.model.vo.base.BaseVO;
import java.util.Date;
public class FileQueryVO extends BaseVO  {
        private String name; //文件名
        private String memo; //备注        
        private String fileUrl; //文件地址
        

        public String getMemo() {
                return  memo;
        }

        public void setMemo(String memo) {
                this.memo = memo;
        }

        public String getFileUrl() {
                return  fileUrl;
        }

        public void setFileUrl(String fileUrl) {
                this.fileUrl = fileUrl;
        }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

}
