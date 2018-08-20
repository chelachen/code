package com.jifan.pssmis.web.backbean.commen;

public class TreeNodeValue {

	private Object value;
	
	private String name;
	
	private ExtendTreeNode treeNode;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isLeaf(){
		return treeNode.isLeaf();
	}

	public ExtendTreeNode getTreeNode() {
		return treeNode;
	}

	public void setTreeNode(ExtendTreeNode treeNode) {
		this.treeNode = treeNode;
	}
}
