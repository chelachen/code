package com.jifan.pssmis.model.bo.rpt;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OptimisticLockType;
import org.primefaces.model.TreeNode;

import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.model.bo.base.BaseBO;
import com.jifan.pssmis.web.backbean.commen.ExtendTreeNode;
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true,selectBeforeUpdate=true,optimisticLock = OptimisticLockType.VERSION)
@Table(name = "RPT_CUSTOM_QUERY_BUILDER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "rpt_custom_query_builder_entity")
public class CustomQueryBuilderBO extends BaseBO  {
        private static final long serialVersionUID = -6528505879877926404L;
        @Column(name = "SUBJECT_ID" , length=36)
        private String subjectId; //主题ID
        
        @Column(name = "QUERY_FIELD" , length=50)
        private String queryField; //查询条件字段
        
        @Column(name = "QUERY_SHOW_NAME" , length=50)
        private String queryShowName; //查询条件字段
        
        @Column(name = "QUERY_CLASS_CODE" , length=50)
        private String queryClasscode; //对应的数据字典类别代码
        
        @Column(name = "QUERY_ATTR" , length=50)
        private String queryAttr; //对应的数据字典类别代码
        
        @Column(name = "DATA_RIGHTS" )
        private boolean dataRights; //数据权限控制
        
        @Column(name = "DATA_RIGHTS_CONFIG" , length=50)
        private String dataRightsConfig; //数据权限配置代码
        @Column(name = "QUERY_TREE_LEVEL")
        private Integer  queryTreeLevel;
        
        @Enumerated(EnumType.ORDINAL)
        @Column(name = "QUERY_TYPE" )
        private QueryType queryType; //查询条件类别
        
        @Enumerated(EnumType.ORDINAL)
        @Column(name = "QUERY_SHOW" )
        private QueryShow queryShow; //查询条件展示方式
        
        @Column(name = "MEMO" , length=500)
        private String memo; //备注
        
        @Column(name = "IS_GENERAL" )
        private boolean isGeneral; //是否常规
        
        @Column(name = "IS_HANG_LEAVES" )
        private boolean isHangLeaves; //是否挂载叶子
        
        @Column(name = "IS_EXCLUDE" )
        private boolean isExclude; //是否挂载叶子
        
        @Transient
        private Date beginDate=new Date();
        @Transient
        private Date endDate=new Date();
        
    	/**
    	 * 下拉列表结果集
    	 */
        @Transient
    	private List<SelectItem> selectItems=new ArrayList<SelectItem>();
        /**
    	 * 复选框多选对应的值
    	 */
        @Transient
        private List selectedValues;
        /**
         * 下拉，单选对应的值
         */
        @Transient
        private Object singleValue;
    	/**
    	 * 树行展示
    	 */
        @Transient
    	private ExtendTreeNode rootNode;
        
        @Transient
        private TreeNode[] selectedNodes;  
        
        
        /**
    	 * 树型展示方式
    	 */
        @Transient
    	private  boolean trees;
    	/**
    	 * 多选展示方式
    	 */
        @Transient
    	private  boolean checkboxs;
    	/**
    	 * 单选展示方式
    	 */
        @Transient
    	private  boolean radios;
    	/**
    	 * 下拉展示方式
    	 */
        @Transient
    	private  boolean selects;
    	/**
    	 * 时间展示方式
    	 */
        @Transient
    	private  boolean times;
        
        @Transient
        private boolean inputs;
        
        public boolean nullvalue(){
        	boolean temp=true;
        	if(this.isRadios()||this.isSelects()){
				if(CommonUtil.isNotEmpty(this.getSingleValue())){
					temp=false;
				}
			}else if(this.isCheckboxs()){
				if(this.getSelectedValues() !=null&&!this.getSelectedValues().isEmpty())
				{
					temp=false;
				}
			}else if(this.isTrees()){
				if(this.getSelectedNodes() !=null&&this.getSelectedNodes().length>0)
				{
					temp=false;
				}
			}else if(this.isTimes()){
				if(this.getBeginDate()!=null||this.getEndDate()!=null)
				{
					temp=false;
				}
			}else if(this.isInputs()){
				if(CommonUtil.isNotEmpty(this.getSingleValue())){
					temp=false;
				}
			}
        	return temp;
        }
        
    	
        public boolean isTrees() {
			return trees;
		}


		public boolean isCheckboxs() {
			return checkboxs;
		}


		public boolean isRadios() {
			return radios;
		}


		public boolean isSelects() {
			return selects;
		}


		public boolean isTimes() {
			return times;
		}


		public void setTrees(boolean trees) {
			this.trees = trees;
		}


		public void setCheckboxs(boolean checkboxs) {
			this.checkboxs = checkboxs;
		}


		public void setRadios(boolean radios) {
			this.radios = radios;
		}


		public void setSelects(boolean selects) {
			this.selects = selects;
		}


		public void setTimes(boolean times) {
			this.times = times;
		}


		public Object getSingleValue() {
			return singleValue;
		}


		public void setSingleValue(Object singleValue) {
			this.singleValue = singleValue;
		}


		public CustomQueryBuilderBO(){
			endDate=new Date();
			endDate.setHours(23);
			endDate.setMinutes(59);
			endDate.setSeconds(59);
			
			beginDate=new Date();
			beginDate.setHours(0);
			beginDate.setMinutes(0);
			beginDate.setSeconds(0);
						
        }

        public CustomQueryBuilderBO(String subjectId,String queryField,String queryClasscode,boolean dataRights,String dataRightsConfig,QueryType queryType,QueryShow queryShow){
                this.subjectId=subjectId;
                this.queryField=queryField;
                this.queryClasscode=queryClasscode;
                this.dataRights=dataRights;
                this.dataRightsConfig=dataRightsConfig;
                this.queryType=queryType;
                this.queryShow=queryShow;
        }

        public String getSubjectId() {
                return  subjectId;
        }

        public void setSubjectId(String subjectId) {
                this.subjectId = subjectId;
        }

        public String getQueryField() {
                return  queryField;
        }

        public void setQueryField(String queryField) {
                this.queryField = queryField;
        }

        public String getQueryClasscode() {
                return  queryClasscode;
        }

        public void setQueryClasscode(String queryClasscode) {
                this.queryClasscode = queryClasscode;
        }


		public boolean isDataRights() {
			return dataRights;
		}

		public void setDataRights(boolean dataRights) {
			this.dataRights = dataRights;
		}

		public String getDataRightsConfig() {
                return  dataRightsConfig;
        }

        public void setDataRightsConfig(String dataRightsConfig) {
                this.dataRightsConfig = dataRightsConfig;
        }

		public QueryType getQueryType() {
			return queryType;
		}

		public QueryShow getQueryShow() {
			return queryShow;
		}

		public void setQueryType(QueryType queryType) {
			this.queryType = queryType;
		}

		public void setQueryShow(QueryShow queryShow) {
			this.queryShow = queryShow;
		}
		public void initQueryShow() {
			switch (queryShow) {
			case tree:
				trees=true;
				break;
			case radio:
				radios=true;
				break;
			case checkbox:
				checkboxs=true;
				break;
			case select:
				selects=true;
				break;
			case time:
				times=true;
				break;
			case input:
				inputs=true;
				break;	

			default:
				break;
			}
		}

		public List<SelectItem> getSelectItems() {
			return selectItems;
		}

		public ExtendTreeNode getRootNode() {
			return rootNode;
		}

		public TreeNode[] getSelectedNodes() {
			return selectedNodes;
		}

		public void setSelectItems(List<SelectItem> selectItems) {
			this.selectItems = selectItems;
		}

		public void setRootNode(ExtendTreeNode rootNode) {
			this.rootNode = rootNode;
		}

		public void setSelectedNodes(TreeNode[] selectedNodes) {
			this.selectedNodes = selectedNodes;
		}

		public List getSelectedValues() {
			return selectedValues;
		}

		public void setSelectedValues(List selectedValues) {
			this.selectedValues = selectedValues;
		}


		public String getQueryShowName() {
			return queryShowName;
		}


		public void setQueryShowName(String queryShowName) {
			this.queryShowName = queryShowName;
		}


		public Date getBeginDate() {
			return beginDate;
		}


		public void setBeginDate(Date beginDate) {
			this.beginDate = beginDate;
		}


		public Date getEndDate() {
			return endDate;
		}


		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}


		public boolean isInputs() {
			return inputs;
		}


		public void setInputs(boolean inputs) {
			this.inputs = inputs;
		}


		public Integer getQueryTreeLevel() {
			if(queryTreeLevel==null)
				return 0;
			return queryTreeLevel;
		}


		public void setQueryTreeLevel(Integer queryTreeLevel) {
			this.queryTreeLevel = queryTreeLevel;
		}


		public String getMemo() {
			return memo;
		}


		public void setMemo(String memo) {
			this.memo = memo;
		}


		public String getQueryAttr() {
			return queryAttr;
		}


		public void setQueryAttr(String queryAttr) {
			this.queryAttr = queryAttr;
		}


		public boolean isGeneral() {
			return isGeneral;
		}


		public void setGeneral(boolean isGeneral) {
			this.isGeneral = isGeneral;
		}


		public boolean isHangLeaves() {
			return isHangLeaves;
		}


		public void setHangLeaves(boolean isHangLeaves) {
			this.isHangLeaves = isHangLeaves;
		}



		public boolean isExclude() {
			return isExclude;
		}


		public void setExclude(boolean isExclude) {
			this.isExclude = isExclude;
		}






}
