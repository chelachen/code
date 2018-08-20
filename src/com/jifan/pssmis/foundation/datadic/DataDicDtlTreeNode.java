package com.jifan.pssmis.foundation.datadic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.swing.tree.TreeNode;

import com.google.common.collect.Iterators;
import com.jifan.pssmis.foundation.util.ClassifyCodingUtil;
import com.jifan.pssmis.model.bo.cdm.DataDicBO;
import com.jifan.pssmis.model.bo.cdm.DataDicDtlBO;
import com.jifan.pssmis.model.bo.sys.RightsBO;

public class DataDicDtlTreeNode implements TreeNode,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DataDicDtlTreeNode> childrenList = new ArrayList<DataDicDtlTreeNode>();
	private List<DataDicDtlTreeNode> allSubChildList = new ArrayList<DataDicDtlTreeNode>();
	private DataDicDtlTreeNode dataDicDtlTreeNode;
	private DataDicDtlBO dataDicDtlBO;
	private RightsBO rightsBO; 
	private String nodeName;
	private int dataCode;
	private boolean choice;
	private boolean assignable;
	private boolean chosen;
	private boolean disChosen;
	private boolean treeOpen=true;//默认展开
	private boolean rootOpen;//根节点展开 子节点也会展开


	private Integer msgNumber ;//短信数量
	private void getAllSubTreeNode(DataDicDtlTreeNode treeNode) {
		for (DataDicDtlTreeNode dataticTreeNode : treeNode.getChildrenList()) {
			this.allSubChildList.add(dataticTreeNode);
			getAllSubTreeNode(dataticTreeNode);
		}
	}

	private void getAllSubTreeNode(DataDicDtlTreeNode treeNode, Map<String, RightsBO> rightMap) {
		if (treeNode.getDataDicDtlBO() != null)
			treeNode.setRightsBO(rightMap.get(treeNode.getDataDicDtlBO().getId()));
		for (DataDicDtlTreeNode dataticTreeNode : treeNode.getChildrenList()) {
			dataticTreeNode.setRightsBO(rightMap.get(dataticTreeNode.getDataDicDtlBO().getId()));
			this.allSubChildList.add(dataticTreeNode);
			getAllSubTreeNode(dataticTreeNode, rightMap);
		}
	}
	/**
	 * 选中根节点 递归选中 其父节点
	 */
	public void setParentChoice(boolean choice){
		if(this.getDataDicDtlTreeNode()!=null){
			if(!choice){
				this.getDataDicDtlTreeNode().setChoice(choice);
				this.getDataDicDtlTreeNode().setParentChoice(choice);
			}else{
				boolean choiceAll=true;
				for(DataDicDtlTreeNode treeNode:this.getDataDicDtlTreeNode().getChildrenList()){
					if(!treeNode.isChoice()&&!this.equals(treeNode)){
						choiceAll=false;
						break;
					}
				}
				if(choiceAll){
					this.getDataDicDtlTreeNode().setChoice(choice);
					this.getDataDicDtlTreeNode().setParentChoice(choice);
				}
			}
		}
	}

	public void setParentChoice2(DataDicDtlTreeNode treeNode, boolean value) {
		if (treeNode.getDataDicDtlTreeNode() != null) {
			if(!value){
				treeNode.getDataDicDtlTreeNode().setChoice(value);
				treeNode.getDataDicDtlTreeNode().setChosen(value);
				setParentChoice2(treeNode.getDataDicDtlTreeNode(), value);
			}else {
				boolean isAllChoice = true;
				for (DataDicDtlTreeNode dtlTreeNode : treeNode.getDataDicDtlTreeNode().getChildrenList()) {
					if(treeNode.equals(dtlTreeNode))
						continue;
					if (!dtlTreeNode.isChoice()) {
						isAllChoice = false;
						break;
					}
				}
				treeNode.getDataDicDtlTreeNode().setChoice(isAllChoice);
				treeNode.getDataDicDtlTreeNode().setChosen(isAllChoice);
			}
		
		}
	}

	public void setParentAssignable(DataDicDtlTreeNode treeNode) {
		if (treeNode.getParent() != null) {
			treeNode.getDataDicDtlTreeNode().setAssignable(treeNode.isAssignable());
			setParentAssignable(treeNode.getDataDicDtlTreeNode());
		}
	}

	@Override
	public Enumeration<DataDicDtlTreeNode> children() {
		return Iterators.asEnumeration(getChildrenList().iterator());
	}

	public DataDicDtlTreeNode() {
	}

	/**
	 * 构造方�?
	 * 
	 * @param dataDicDtlBO
	 * @param dataDicDtlTreeNode
	 * @param nodeName
	 */
	public DataDicDtlTreeNode(DataDicDtlBO dataDicDtlBO, DataDicDtlTreeNode dataDicDtlTreeNode, String nodeName) {

		this.dataDicDtlBO = dataDicDtlBO;
		this.dataDicDtlTreeNode = dataDicDtlTreeNode;
		if ("".equals(nodeName) || null != nodeName) {
			this.nodeName = nodeName;
		}

		if (null != dataDicDtlBO) {
			this.nodeName = dataDicDtlBO.getDataName();
			this.choice = dataDicDtlBO.isChoiced();
		}
		if (this.dataDicDtlBO != null && this.getSubDataDicDtlBOList(dataDicDtlBO) != null) {
			for (DataDicDtlBO bo : this.getSubDataDicDtlBOList(dataDicDtlBO)) {
				childrenList.add(new DataDicDtlTreeNode(bo, this, null));
			}
		}

	}


	/**
	 * 获取子树列表
	 * @param dataDicDtlBO
	 * @return
	 */
	public List<DataDicDtlBO> getSubDataDicDtlBOList(DataDicDtlBO dataDicDtlBO) {
		DataDicBO dataDicBO=dataDicDtlBO.getDataDicBO();
		if (dataDicBO != null && (dataDicBO.isLeaveTree())
				&&dataDicDtlBO.getDataLevel()<ClassifyCodingUtil.getLeavel(dataDicBO.getDataCodeType())) {
			return DataCodeUtil.getSubDataDicDtlBOListByLevel(dataDicDtlBO);
		} else {
			return DataCodeUtil.getSubDataDicDtlBOListByParent(dataDicDtlBO);
		}
	}


	public DataDicDtlTreeNode contains(DataDicDtlBO dataDicDtlBO) {
		for (DataDicDtlTreeNode dataDicDtlTreeNode : childrenList) {
			if (dataDicDtlTreeNode.getDataDicDtlBO().equals(dataDicDtlBO)) {
				return dataDicDtlTreeNode;
			}
		}
		return null;
	}

	public void setSubDisChosed(boolean chosed) {
		setAllSubDisTreeNodeChosed(this, chosed);
	}

	public void setSubChosed(boolean chosed) {
		setAllSubTreeNodeChosed(this, chosed);
	}

	public void setSubChosed2(boolean chosed) {
		setAllSubTreeNodeChosed2(this, chosed);
		setParentChoice2(this, chosed);
	}

	private void setAllSubDisTreeNodeChosed(DataDicDtlTreeNode treeNode, boolean chosed) {
		treeNode.setAssignable(chosed);
		for (DataDicDtlTreeNode rightsTreeNode : treeNode.getChildrenList()) {
			rightsTreeNode.setAssignable(chosed);
			setAllSubDisTreeNodeChosed(rightsTreeNode, chosed);
		}
	}

	private void setAllSubTreeNodeChosed(DataDicDtlTreeNode treeNode, boolean chosed) {
		treeNode.setChoice(chosed);
		for (DataDicDtlTreeNode rightsTreeNode : treeNode.getChildrenList()) {
			rightsTreeNode.setChoice(chosed);
			setAllSubTreeNodeChosed(rightsTreeNode, chosed);
		}
	}

	private void setAllSubTreeNodeChosed2(DataDicDtlTreeNode treeNode, boolean chosed) {
		treeNode.setChoice(chosed);
		for (DataDicDtlTreeNode rightsTreeNode : treeNode.getChildrenList()) {
			rightsTreeNode.setChoice(chosed);
			rightsTreeNode.setChosen(chosed);
			setAllSubTreeNodeChosed2(rightsTreeNode, chosed);
		}
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return childrenList.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return childrenList.size();
	}

	@Override
	public int getIndex(TreeNode node) {
		return childrenList.indexOf(node);
	}

	@Override
	public TreeNode getParent() {
		return dataDicDtlTreeNode;
	}

	@Override
	public boolean isLeaf() {
		return childrenList.isEmpty();
	}

	public DataDicDtlBO getDataDicDtlBO() {
		return dataDicDtlBO;
	}

	public void setDataDicDtlBO(DataDicDtlBO dataDicDtlBO) {
		this.dataDicDtlBO = dataDicDtlBO;
	}

	public DataDicDtlTreeNode getDataDicDtlTreeNode() {
		return dataDicDtlTreeNode;
	}

	public void setDataDicDtlTreeNode(DataDicDtlTreeNode dataDicDtlTreeNode) {
		this.dataDicDtlTreeNode = dataDicDtlTreeNode;
	}

	public List<DataDicDtlTreeNode> getChildrenList() {

		return childrenList;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public boolean isChoice() {
		return choice;
	}

	public void setChoice(boolean choice) {
		if (dataDicDtlBO != null) {
			dataDicDtlBO.setChoiced(choice);
		}
		this.choice = choice;
	}

	public List<DataDicDtlTreeNode> getAllSubChildList() {
		this.allSubChildList.clear();
		getAllSubTreeNode(this);
		return allSubChildList;
	}

	public List<DataDicDtlTreeNode> getAllSubChildList(Map<String, RightsBO> rightMap) {
		this.allSubChildList.clear();
		getAllSubTreeNode(this, rightMap);
		return allSubChildList;
	}

	public void setAllSubChildList(List<DataDicDtlTreeNode> allSubChildList) {
		this.allSubChildList = allSubChildList;
	}

	public boolean isAssignable() {
		return assignable;
	}

	public void setAssignable(boolean assignable) {
		this.assignable = assignable;
	}

	public boolean isChosen() {
		return chosen;
	}

	public void setChosen(boolean chosen) {
		this.chosen = chosen;
	}

	public boolean isDisChosen() {
		return disChosen;
	}

	public void setDisChosen(boolean disChosen) {
		this.disChosen = disChosen;
	}

	public RightsBO getRightsBO() {
		return rightsBO;
	}

	public void setRightsBO(RightsBO rightsBO) {
		this.rightsBO = rightsBO;
	}

	public boolean isTreeOpen() {
		return treeOpen;
	}

	public void setTreeOpen(boolean treeOpen) {
		this.treeOpen = treeOpen;
	}

	public boolean isRootOpen() {
		return rootOpen;
	}

	public void setRootOpen(boolean rootOpen) {
		for(DataDicDtlTreeNode dtlTreeNode:this.getAllSubChildList()){
			dtlTreeNode.setTreeOpen(rootOpen);
		}
		this.rootOpen = rootOpen;
	}
	
	public int getDataCode() {
		return dataCode;
	}

	public void setDataCode(int dataCode) {
		this.dataCode = dataCode;
	}

	public Integer getMsgNumber() {
	    return msgNumber;
	}

	public void setMsgNumber(Integer msgNumber) {
	    this.msgNumber = msgNumber;
	}
	
}
