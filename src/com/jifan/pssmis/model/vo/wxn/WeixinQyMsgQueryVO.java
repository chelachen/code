package com.jifan.pssmis.model.vo.wxn;
import com.jifan.pssmis.model.vo.base.BaseVO;
import java.util.Date;
public class WeixinQyMsgQueryVO extends BaseVO  {
        private String userId; //用户id
        
        private String enterpriseID; //微信用户id
        
        private Integer status; //状态 
        
        private String content; //内容
        
        private Date sendTime; //发送时间
        
        private String result; //发送结果
        
        private Integer sendNum;//发送次数
        

        public String getUserId() {
                return  userId;
        }

        public void setUserId(String userId) {
                this.userId = userId;
        }


        public String getEnterpriseID() {
	    return enterpriseID;
	}

	public void setEnterpriseID(String enterpriseID) {
	    this.enterpriseID = enterpriseID;
	}

	public Integer getStatus() {
                return  status;
        }

        public void setStatus(Integer status) {
                this.status = status;
        }

        public String getContent() {
                return  content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public Date getSendTime() {
                return  sendTime;
        }

        public void setSendTime(Date sendTime) {
                this.sendTime = sendTime;
        }

        public String getResult() {
                return  result;
        }

        public void setResult(String result) {
                this.result = result;
        }

	public Integer getSendNum() {
	    return sendNum;
	}

	public void setSendNum(Integer sendNum) {
	    this.sendNum = sendNum;
	}
        

}
