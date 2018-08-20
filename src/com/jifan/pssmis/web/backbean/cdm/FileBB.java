package com.jifan.pssmis.web.backbean.cdm;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.jifan.pssmis.bs.cdm.IFileBS;
import com.jifan.pssmis.foundation.exception.PubShowMessage;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.foundation.util.FtpUtil;
import com.jifan.pssmis.foundation.util.FtpUtil.FileVO;
import com.jifan.pssmis.foundation.util.SysInitUtil;
import com.jifan.pssmis.model.bo.cdm.FileBO;
import com.jifan.pssmis.model.vo.cdm.FileQueryVO;
import com.jifan.pssmis.web.backbean.base.BaseBean;

@ManagedBean(name = "fileBB")
public class FileBB extends BaseBean {
        /**
        *
        **/
        private static final long serialVersionUID = -1054820662761921269L;

        private FileQueryVO param = new FileQueryVO();

        private FileBO currentBO = new FileBO();

        private List<FileBO> resultList = new ArrayList<FileBO>();


        @ManagedProperty(name = "fileBS", value = "#{fileBS}")
        private IFileBS fileBS;



        public FileBB() {
                pager.setPageNumber(1);
                pager.setPageSize(10);
                currentBO = new FileBO();
        }

        public void save() {
                try {
                        this.setUserAndDate(currentBO);
                       this.fileBS.saveNotExist(currentBO);
                       PubShowMessage.showInfo(PubShowMessage.ADD);
                } catch (Exception e) {
                        msg.setMainMsg(e);
                }
        }


        public void add() {
                this.currentBO = new FileBO();
                
        }

        public void delete() {
                try {
                        this.fileBS.delete(this.currentBO);
                        this.resultList.remove(this.currentBO);
                        PubShowMessage.showInfo(PubShowMessage.DELETE);
                } catch (Exception e) {
                        log.error(e);
                }
        }

        public void findByPager() {
                try {
                       this.resultList= this.fileBS.findFileByParam(param);
                } catch (Exception e) {
                        log.error(e);
                }
        }
        
    	/**
    	 * 上传文件
    	 * 
    	 * @param event
    	 */
    	public void handleFileUpload(FileUploadEvent event) {
    		try {
    			UploadedFile item = event.getFile();
    			String picPath = FtpUtil.cheVoid(SysInitUtil.getFileUrl());
    			/*** FTP上传 ****/
    			FtpUtil f = new FtpUtil();
    			FileVO fileVO = f.fileUpload(item, picPath);
    			/****** FTP上传 ******/
    			if(CommonUtil.isEmpty(this.currentBO.getName()))
    				this.currentBO.setName(fileVO.getFileName());
    			 this.currentBO.setFileUrl(picPath + "/" + fileVO.getFileNewName());
    			 this.save();
    			 this.resultList.add(currentBO);
    			 this.findByPager();
    			 PubShowMessage.showInfo("上传成功！");
    		} catch (Exception e) {
    			this.msg.setMainMsg(e);
    			e.printStackTrace();
    		}
    	}

        public String next() {
                return "File";
        }

        public FileQueryVO getParam() {
                return param;
                }

        public void setParam(FileQueryVO param) {
                this.param = param;
        }

        public FileBO getCurrentBO() {
                return currentBO;
        }

        public void setCurrentBO(FileBO currentBO) {
                this.currentBO = currentBO;
        }

        public List<FileBO> getResultList() {
                return resultList;
        }

        public void setResultList(List<FileBO> resultList) {
                this.resultList = resultList;
        }

		public IFileBS getFileBS() {
			return fileBS;
		}

		public void setFileBS(IFileBS fileBS) {
			this.fileBS = fileBS;
		}




}
