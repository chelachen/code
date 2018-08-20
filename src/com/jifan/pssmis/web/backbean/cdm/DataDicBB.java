package com.jifan.pssmis.web.backbean.cdm;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.jifan.pssmis.bs.cdm.IDataDicBS;
import com.jifan.pssmis.bs.cdm.IDataDicDtlBS;
import com.jifan.pssmis.foundation.datadic.DataCodeUtil;
import com.jifan.pssmis.foundation.datadic.DataDicDtlTreeNode;
import com.jifan.pssmis.foundation.exception.PubShowMessage;
import com.jifan.pssmis.foundation.paging.PagedListDataModel;
import com.jifan.pssmis.foundation.paging.Paginator;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.model.bo.cdm.DataDicBO;
import com.jifan.pssmis.model.bo.cdm.DataDicDtlBO;
import com.jifan.pssmis.model.vo.cdm.DataDicQueryVO;
import com.jifan.pssmis.web.backbean.base.BaseBean;

@ManagedBean(name = "dataDicBB")
public class DataDicBB extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1054820662761921269L;

	private DataDicQueryVO param = new DataDicQueryVO();

	private DataDicBO currentBO = new DataDicBO();

	private DataDicDtlBO currentItemBO = new DataDicDtlBO();

	private List<DataDicDtlBO> itemRemoveList = new ArrayList<DataDicDtlBO>();

	private List<SelectItem> parentList = new ArrayList<SelectItem>();// 单据状态列表

	private List<SelectItem> dataCodeTypeList = new ArrayList<SelectItem>();// 数据编码规则

	private boolean dataCodeTypeFlag;

	private boolean treeEditFlag;

	private DataDicDtlTreeNode choseenTreeNode = new DataDicDtlTreeNode();

	private int page = 10;

	private int pageSize = 5;

	private int pageCount;

	private int totalCount;

	private DataDicDtlTreeNode productNodes = new DataDicDtlTreeNode();

	@ManagedProperty(name = "dataDicBS", value = "#{dataDicBS}")
	private IDataDicBS dataDicBS;

	@ManagedProperty(name = "dataDicDtlBS", value = "#{dataDicDtlBS}")
	private IDataDicDtlBS dataDicDtlBS;

	private TreeNode dataTree = new DefaultTreeNode("Root", null);
	private TreeNode selectedData;
	private DataDicDtlBO dataDicDtlBO = new DataDicDtlBO();
	private DataDicDtlBO selectedDataDicDtlBO = new DataDicDtlBO();

	private List<DataDicBO> resultList = new ArrayList<DataDicBO>();
	
	private String dataClassCode;
	
	public void initCurrentDic(){
		if(CommonUtil.isNotEmpty(dataClassCode)){
			param = new DataDicQueryVO();
			param.setDataClassCode(dataClassCode);
			this.resultList = this.dataDicBS.findDataDicByParam(param);
			if(resultList !=null && resultList.size()>0){
				this.currentBO=resultList.get(0);
				this.dataDicDtlBO = new DataDicDtlBO();
				Integer seq = DataCodeUtil.getDataDicDtlCount(dataClassCode)+1;
				this.dataDicDtlBO.initData(seq, 0, "", this.currentBO);
			}
		}
	}

	public DataDicBB() {
		pager.setPageNumber(1);
		pager.setPageSize(20);
		currentBO = new DataDicBO();
		dataCodeTypeList = DataCodeUtil.getDataCodeType();
	}

	public void save() {
		try {
			this.setUserAndDate(currentBO);
			if (CommonUtil.isEmpty(this.currentBO.getId())) {
				this.dataDicBS.saveNotExist(this.currentBO);
			} else {
				this.dataDicBS.updateNotExist(this.currentBO);
			}
			this.editDtl();
			this.findByPager();
			// currentBO.beforeSaveDatadic(this.getSysUserCode());
			PubShowMessage.showInfo(PubShowMessage.ADD);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMainMsg(e);
		}
	}

	public void createRootDataDtl() {
		DataDicDtlBO dataDicDtlBO = new DataDicDtlBO();
	}

	/**
	 * 编辑数据字典子表
	 */
	public void editDtl() {
		try {
			this.dataDicDtlBO = new DataDicDtlBO();
			this.selectedData = null;
			dataTree = new DefaultTreeNode("Root", null);
			dataTree.setExpanded(true);
			if (this.currentBO.isLeaveTree() || this.currentBO.isTraditionTree()) {
				dataTree = DataCodeUtil.getDataTree(this.currentBO.getDataClassCode());
				dataTree.setExpanded(true);
			} else {
				currentBO.setDatadtlList(this.dataDicBS.getDtlListByDataClassCode(currentBO.getDataClassCode()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMainMsg(e);
		}
	}

	public void delete() {
		try {
			currentBO.setDatadtlList(this.dataDicBS.getDtlListByDataClassCode(currentBO.getDataClassCode()));
			this.dataDicBS.deleteAll(currentBO);
			this.findByPager();
			PubShowMessage.showInfo(PubShowMessage.DELETE);
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public void itemdelete() {
		try {
			/** begin */
			this.currentBO.removeDatadicdtl(currentItemBO);
			/** end */
			itemRemoveList.add(currentItemBO);
			setpageCount();
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public void add() {
		this.currentBO = new DataDicBO();
	}

	public void itemDtladd() {
		// choseenTreeNode=new DataDicDtlTreeNode();
		currentItemBO = new DataDicDtlBO();
		treeEditFlag = true;
	}
	


	public void saveDtl() {
		try {
			DataDicDtlBO maxData = this.dataDicBS.getMaxDataCode(this.currentBO.getDataClassCode());
			if (this.currentBO.isTree()) {
				if (this.selectedData != null) {
					DataDicDtlBO parentData = null;
					parentData = (DataDicDtlBO) this.selectedData.getData();
					Integer dataCode = null;
					if (this.currentBO.isLeaveTree())
						dataCode = 0;
					else
						dataCode = maxData.getDataCode() + 1;

					// 新增
					if (CommonUtil.isEmpty(this.dataDicDtlBO.getId())) {
						this.dataDicDtlBO.initData(maxData.getDataCode() + 1, parentData.getDataCode(),
							this.dataDicDtlBO.getDataName(), this.currentBO);
						this.dataDicDtlBS.saveNotExist(this.dataDicDtlBO);
					} else {
						this.dataDicDtlBS.updateNotExist(this.dataDicDtlBO);
					}

				} else {
					PubShowMessage.showInfo("请选中一个父节点！以便添加。");
					return;
				}
			} else {

			}
			// 重新加载数据
			this.editDtl();
			PubShowMessage.showInfo(PubShowMessage.ADD);
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMainMsg(e);
		}
	}

	public void saveRootDtl() {
		try {
			DataDicDtlBO dataDicDtlBO = DataCodeUtil.getDataDicDtlBO(0, this.currentBO.getDataClassCode());
			if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getId())) {
				PubShowMessage
					.showInfo(this.currentBO.getDataClassCode() + "-" + this.currentBO.getDataClassName() + "根数据已存在！无需创建了！");
				return;
			} else {
				dataDicDtlBO = new DataDicDtlBO();
				dataDicDtlBO.initData(0, 0, this.currentBO.getDataClassName(), this.currentBO);
				this.dataDicDtlBS.saveNotExist(dataDicDtlBO);
			}
			this.editDtl();
		} catch (Exception e) {
			e.printStackTrace();
			msg.setMainMsg(e);
		}
	}

	public void deletDtl() {
		try {
			if (this.selectedData != null) {
				this.dataDicDtlBO = (DataDicDtlBO) this.selectedData.getData();
				this.dataDicDtlBS.deleteAll(this.dataDicDtlBO);
				this.editDtl();
				PubShowMessage.showInfo(PubShowMessage.DELETE);
			} else {
				PubShowMessage.showInfo("请选中一个节点！以便删除。");
				return;
			}
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public void deletDtlEnum() {
		try {
			if (this.dataDicDtlBO != null) {
				this.dataDicDtlBS.deleteAll(this.dataDicDtlBO);
				this.currentBO.getDatadtlList().remove(this.dataDicDtlBO);
				PubShowMessage.showInfo(PubShowMessage.DELETE);
			} else {
				PubShowMessage.showInfo("请选中一条记录！");
				return;
			}
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public void addDtlEnum() {
		try {
			this.dataDicDtlBO = new DataDicDtlBO();
//			DataDicDtlBO maxData = this.dataDicBS.getMaxDataCode(this.currentBO.getDataClassCode());
//			if (maxData != null)
				this.dataDicDtlBO.initData(this.currentBO.getDatadtlList().size()+1, 0, "", this.currentBO);
//			else
//				this.dataDicDtlBO.initData(1, 0, "", this.currentBO);
			this.currentBO.getDatadtlList().add(this.dataDicDtlBO);
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public void saveDtlEnum() {
		try {
			for (DataDicDtlBO dtl : this.currentBO.getDatadtlList()) {
				if (CommonUtil.isEmpty(dtl.getId()))
					this.dataDicDtlBS.saveNotExist(dtl);
				else
					this.dataDicDtlBS.updateNotExist(dtl);
			}
			PubShowMessage.showInfo(PubShowMessage.ADD);
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	/**
	 * 编辑选中的分类树节点
	 */
	public void updateDtl() {
		try {
			if (this.selectedData != null)
				this.dataDicDtlBO = (DataDicDtlBO) this.selectedData.getData();

			else {
				PubShowMessage.showInfo("请选中一个父节点！以便添加。");
				return;
			}
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	/**
	 * 添加选中的树子节点
	 */
	public void addDtl() {
		try {
			if (this.selectedData != null)
				this.dataDicDtlBO = new DataDicDtlBO();
			else {
				PubShowMessage.showInfo("请选中一个父节点！以便添加。");
				return;
			}
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public void findByPager() {
		try {
			this.resultList = this.dataDicBS.findDataDicByParam(param);
		} catch (Exception e) {
			msg.setMainMsg(e);
		}
	}

	public String next() {
		return "DataDic";
	}

	public DataDicQueryVO getParam() {
		return param;
	}

	public void setParam(DataDicQueryVO param) {
		this.param = param;
	}

	public DataDicBO getCurrentBO() {
		return currentBO;
	}

	public void setCurrentBO(DataDicBO currentBO) {
		this.currentBO = currentBO;
	}

	private void setpageCount() {
		this.totalCount = this.getCurrentBO().getDatadtlList().size();
		this.pageCount = (totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1);
	}

	public PagedListDataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(PagedListDataModel dataModel) {
		this.dataModel = dataModel;
	}

	public Paginator getPager() {
		return pager;
	}

	public void setPager(Paginator pager) {
		this.pager = pager;
	}

	public DataDicDtlBO getCurrentItemBO() {
		return currentItemBO;
	}

	public void setCurrentItemBO(DataDicDtlBO currentItemBO) {
		this.currentItemBO = currentItemBO;
	}

	public List<DataDicDtlBO> getItemRemoveList() {
		return itemRemoveList;
	}

	public void setItemRemoveList(List<DataDicDtlBO> itemRemoveList) {
		this.itemRemoveList = itemRemoveList;
	}

	public List<SelectItem> getParentList() {
		return parentList;
	}

	public void setParentList(List<SelectItem> parentList) {
		this.parentList = parentList;
	}

	public boolean isDataCodeTypeFlag() {
		dataCodeTypeFlag = (currentBO.getDataCodeType() != 0 && currentBO.getDataCodeType() != 10);
		return dataCodeTypeFlag;

	}

	public void setDataCodeTypeFlag(boolean dataCodeTypeFlag) {
		this.dataCodeTypeFlag = dataCodeTypeFlag;
	}

	public DataDicDtlTreeNode getProductNodes() {
		return productNodes;
	}

	public void setProductNodes(DataDicDtlTreeNode productNodes) {
		this.productNodes = productNodes;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<SelectItem> getDataCodeTypeList() {
		return dataCodeTypeList;
	}

	public boolean isTreeEditFlag() {
		return treeEditFlag;
	}

	public void setTreeEditFlag(boolean treeEditFlag) {
		this.treeEditFlag = treeEditFlag;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public DataDicDtlTreeNode getChoseenTreeNode() {
		return choseenTreeNode;
	}

	public void setChoseenTreeNode(DataDicDtlTreeNode choseenTreeNode) {
		this.choseenTreeNode = choseenTreeNode;
	}

	public IDataDicBS getDataDicBS() {
		return dataDicBS;
	}

	public void setDataDicBS(IDataDicBS dataDicBS) {
		this.dataDicBS = dataDicBS;
	}

	public IDataDicDtlBS getDataDicDtlBS() {
		return dataDicDtlBS;
	}

	public void setDataDicDtlBS(IDataDicDtlBS dataDicDtlBS) {
		this.dataDicDtlBS = dataDicDtlBS;
	}

	public void setDataCodeTypeList(List<SelectItem> dataCodeTypeList) {
		this.dataCodeTypeList = dataCodeTypeList;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<DataDicBO> getResultList() {
		return resultList;
	}

	public void setResultList(List<DataDicBO> resultList) {
		this.resultList = resultList;
	}

	public TreeNode getDataTree() {
		return dataTree;
	}

	public void setDataTree(TreeNode dataTree) {
		this.dataTree = dataTree;
	}

	public DataDicDtlBO getDataDicDtlBO() {
		return dataDicDtlBO;
	}

	public void setDataDicDtlBO(DataDicDtlBO dataDicDtlBO) {
		this.dataDicDtlBO = dataDicDtlBO;
	}

	public TreeNode getSelectedData() {
		return selectedData;
	}

	public void setSelectedData(TreeNode selectedData) {
		this.selectedData = selectedData;
	}

	public DataDicDtlBO getSelectedDataDicDtlBO() {
		return selectedDataDicDtlBO;
	}

	public void setSelectedDataDicDtlBO(DataDicDtlBO selectedDataDicDtlBO) {
		this.selectedDataDicDtlBO = selectedDataDicDtlBO;
	}

	public String getDataClassCode() {
		return dataClassCode;
	}

	public void setDataClassCode(String dataClassCode) {
		this.dataClassCode = dataClassCode;
	}

}
