package com.jifan.pssmis.web.backbean.commen;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

public class ExtendTreeNode extends DefaultTreeNode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TreeNodeValue value=new TreeNodeValue();
	
	
	public  ExtendTreeNode(String name,Object obj,ExtendTreeNode treeNode){
		super(obj, treeNode);
		value.setValue(obj);
		value.setName(name);
		value.setTreeNode(this);
	}
	public TreeNodeValue getData(){
		return value;
	}
	/**
	 * 获取所有子节点
	 * @return
	 */
	public List<TreeNode> getLeafChilds() {
		List<TreeNode> childAlls=new ArrayList<TreeNode>();
		for(TreeNode treeNode:this.getChildren()){
			if(treeNode.isLeaf())
			childAlls.add(treeNode);
			childAlls.addAll(((ExtendTreeNode)treeNode).getLeafChilds());
		}
		return childAlls;
	}

}
