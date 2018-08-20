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
@Table(name = "CDM_FILE")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "cdm_file_entity")
public class FileBO extends AuditableBO  {
        private static final long serialVersionUID = -6528505879877926404L;
        @Column(name = "NAME" , length=255)
        private String name; //文件名
        
        @Column(name = "FILE_URL" , length=255)
        private String fileUrl; //文件地址
        
        @Column(name = "MEMO" , length=255)
        private String memo; //文件名
        

        public FileBO(){
        }

        public FileBO(String name,String memo,String fileUrl){
        		this.name=name;
                this.memo=memo;
                this.fileUrl=fileUrl;
        }

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
