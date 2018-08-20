package com.jifan.pssmis.model.vo.sys;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.model.vo.base.BaseVO;

public class NodeVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8204656130787097335L;

	private String nodeId;

	private String nodeCode;

	private String nodeName;

	private String address;

	private String mobile;

	private String email;

	private String tel;

	private String faxes;

	private String qq;

	private String zipcode;

	private String linkman;

	private String enabled;

	private boolean enabledAdd;

	private BigDecimal balance; // 余额

	private Integer nodeType; // 节点类型

	private boolean choiced;
	
	private boolean flag;

	private Integer bgn_attr2;

	private Integer bgn_attr11;

	private Integer bgn_attr12;

	private Integer bgn_attr13;

	private Integer bgn_attr16;

	private Integer bgn_attr50;

	private Integer bgn_attr52;// 配货方式

	private Integer bgn_attr53; // 区域类型

	private String outId;

	private Integer defaultFlag;

	private String tpassNodeLinkId;

	private String picName;

	private String showNodeName;

	private Integer province;// 省份

	private Integer city;// 城市

	private String nodeTypeName;
	
	private double distance;
	
	private String remark;

	private String bgnAttr2 ;
	
	private String bgnAttr1 ;
	
	private String isEnableParam="";
	
	private boolean nodeRightFlag;
	
	private List<String> rightNodes = new ArrayList<String>();
	
	private String sysUserCode;
	
	private String sysNodeId;
	
	private String bgNodeId;
	
	private Integer bgnAttr11;
	
	private String bgId;
	
	private boolean hasRights;
	
	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public NodeVO() {
		super();
	}

	public NodeVO(String nodeCode, String nodeName) {
		super();
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
	}

	public NodeVO(String nodeId, String nodeCode, String nodeName, Integer nodeType) {
		super();
		this.nodeId = nodeId;
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.nodeType = nodeType;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFaxes() {
		return faxes;
	}

	public void setFaxes(String faxes) {
		this.faxes = faxes;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Integer getNodeType() {
		return nodeType;
	}

	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}



	public Integer getBgn_attr12() {
		return bgn_attr12;
	}

	public void setBgn_attr12(Integer bgnAttr12) {
		bgn_attr12 = bgnAttr12;
	}



	public Integer getBgn_attr13() {
		return bgn_attr13;
	}

	public void setBgn_attr13(Integer bgnAttr13) {
		bgn_attr13 = bgnAttr13;
	}


	public Integer getBgn_attr16() {
		return bgn_attr16;
	}

	public void setBgn_attr16(Integer bgnAttr16) {
		bgn_attr16 = bgnAttr16;
	}

	public String getOutId() {
		return outId;
	}

	public void setOutId(String outId) {
		this.outId = outId;
	}

	public boolean isChoiced() {
		return choiced;
	}

	public void setChoiced(boolean choiced) {
		this.choiced = choiced;
	}

	public Integer getBgn_attr2() {
		return bgn_attr2;
	}

	public void setBgn_attr2(Integer bgnAttr2) {
		bgn_attr2 = bgnAttr2;
	}

	public Integer getBgn_attr11() {
		return bgn_attr11;
	}

	public void setBgn_attr11(Integer bgnAttr11) {
		bgn_attr11 = bgnAttr11;
	}

	public Integer getBgn_attr52() {
		return bgn_attr52;
	}

	public void setBgn_attr52(Integer bgn_attr52) {
		this.bgn_attr52 = bgn_attr52;
	}

	public Integer getBgn_attr53() {
		return bgn_attr53;
	}

	public void setBgn_attr53(Integer bgn_attr53) {
		this.bgn_attr53 = bgn_attr53;
	}

	public Integer getBgn_attr50() {
		return bgn_attr50;
	}

	public void setBgn_attr50(Integer bgn_attr50) {
		this.bgn_attr50 = bgn_attr50;
	}

	public boolean isEnabledAdd() {
		return enabledAdd;
	}

	public void setEnabledAdd(boolean enabledAdd) {
		this.enabledAdd = enabledAdd;
	}

	public Integer getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public String getTpassNodeLinkId() {
		return tpassNodeLinkId;
	}

	public void setTpassNodeLinkId(String tpassNodeLinkId) {
		this.tpassNodeLinkId = tpassNodeLinkId;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getShowNodeName() {
		if (CommonUtil.isNotEmpty(this.nodeName)) {
			if (this.nodeName.length() <= 8)
				return this.nodeName;
			else
				return this.nodeName.substring(0, 8);
		}
		return showNodeName;
	}

	public void setShowNodeName(String showNodeName) {
		this.showNodeName = showNodeName;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nodeId == null) ? 0 : nodeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeVO other = (NodeVO) obj;
		if (nodeId == null) {
			if (other.nodeId != null)
				return false;
		} else if (!nodeId.equals(other.nodeId))
			return false;
		return true;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setNodeTypeName(String nodeTypeName) {
		this.nodeTypeName = nodeTypeName;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getBgnAttr2() {
		return bgnAttr2;
	}

	public void setBgnAttr2(String bgnAttr2) {
		this.bgnAttr2 = bgnAttr2;
	}

	public String getBgnAttr1() {
		return bgnAttr1;
	}

	public void setBgnAttr1(String bgnAttr1) {
		this.bgnAttr1 = bgnAttr1;
	}

	public String getIsEnableParam() {
		return isEnableParam;
	}

	public void setIsEnableParam(String isEnableParam) {
		this.isEnableParam = isEnableParam;
	}

	public boolean isNodeRightFlag() {
		return nodeRightFlag;
	}

	public void setNodeRightFlag(boolean nodeRightFlag) {
		this.nodeRightFlag = nodeRightFlag;
	}

	public List<String> getRightNodes() {
		return rightNodes;
	}

	public void setRightNodes(List<String> rightNodes) {
		this.rightNodes = rightNodes;
	}

	public String getSysUserCode() {
		return sysUserCode;
	}

	public void setSysUserCode(String sysUserCode) {
		this.sysUserCode = sysUserCode;
	}

	public String getSysNodeId() {
		return sysNodeId;
	}

	public void setSysNodeId(String sysNodeId) {
		this.sysNodeId = sysNodeId;
	}

	public String getBgNodeId() {
		return bgNodeId;
	}

	public void setBgNodeId(String bgNodeId) {
		this.bgNodeId = bgNodeId;
	}

	public Integer getBgnAttr11() {
		return bgnAttr11;
	}

	public void setBgnAttr11(Integer bgnAttr11) {
		this.bgnAttr11 = bgnAttr11;
	}

	public String getBgId() {
		return bgId;
	}

	public void setBgId(String bgId) {
		this.bgId = bgId;
	}

	public boolean isHasRights() {
		return hasRights;
	}

	public void setHasRights(boolean hasRights) {
		this.hasRights = hasRights;
	}
	
	
	
	

	
}
