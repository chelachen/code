package com.jifan.pssmis.model.bo.wxn;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;

import com.jifan.pssmis.model.bo.base.AuditableBO;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true, selectBeforeUpdate = true, optimisticLock = OptimisticLockType.VERSION)
@Table(name = "WXN_WEIXIN_QY_MSG")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "wxn_weixin_qy_msg_entity")
public class WeixinQyMsgBO extends AuditableBO {
	private static final long serialVersionUID = -6528505879877926404L;

	@Column(name = "WX_PUBLIC_ID", length = 36, nullable = false)
	private String wxPublicId; // 微信企业号Id

	@Column(name = "SECRET", length = 200)
	private String secret; // 安全令牌

	@Column(name = "WX_USER_ID", length = 36, nullable = false)
	private String wxUserId; // 微信用户Id

	@Column(name = "WX_APPLICATION_ID", nullable = false)
	private int wxApplicationId; // 微信企业号应用id

	@Column(name = "SYS_NODE_ID", length = 36, nullable = true)
	private String sysNodeID; // 所属节点

	@Column(name = "STATUS", length = 1, nullable = false)
	private Integer status; // 状态

	@Column(name = "CONTENT", columnDefinition = "VARCHAR(500)")
	private String content; // 内容

	@Column(name = "SEND_TIME", length = 0)
	private Date sendTime; // 发送时间

	@Column(name = "RESULT", length = 100)
	private String result; // 发送结果

	@Column(name = "SEND_NUM", length = 1, columnDefinition = "INT default 0")
	private Integer sendNum; // 发送次数
	
    @Column(name = "MSG_TYPE" , length=0)
    private Integer msgType; //消息类型    1文本消息 2为模板消息 

	public WeixinQyMsgBO() {
	}
	
	public WeixinQyMsgBO(String wxPublicId, String secret, String wxUserId, int wxApplicationId, String sysNodeID, Integer status,
			String content,Integer msgType) {		
		this.wxPublicId = wxPublicId;
		this.secret = secret;
		this.wxUserId = wxUserId;
		this.wxApplicationId = wxApplicationId;
		this.sysNodeID = sysNodeID;
		this.status = status;
		this.content = content;
		this.msgType=msgType;
	}



	public WeixinQyMsgBO(Integer status, String content, Date sendTime, String result) {
		this.status = status;
		this.content = content;
		this.sendTime = sendTime;
		this.result = result;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getResult() {
		return result;
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

	public String getWxPublicId() {
		return wxPublicId;
	}

	public void setWxPublicId(String wxPublicId) {
		this.wxPublicId = wxPublicId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getWxUserId() {
		return wxUserId;
	}

	public void setWxUserId(String wxUserId) {
		this.wxUserId = wxUserId;
	}

	public int getWxApplicationId() {
		return wxApplicationId;
	}

	public void setWxApplicationId(int wxApplicationId) {
		this.wxApplicationId = wxApplicationId;
	}

	public String getSysNodeID() {
		return sysNodeID;
	}

	public void setSysNodeID(String sysNodeID) {
		this.sysNodeID = sysNodeID;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

}
