package com.jifan.pssmis.foundation.datadic;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jifan.pssmis.bs.base.IBaseJdbcBS;
import com.jifan.pssmis.bs.cdm.ICustomerBS;
import com.jifan.pssmis.bs.cdm.IDataDicDtlBS;
import com.jifan.pssmis.bs.cdm.IMachineBS;
import com.jifan.pssmis.bs.cdm.IMateriaBS;
import com.jifan.pssmis.bs.sys.IUserBS;
import com.jifan.pssmis.bs.sys.IUserGroupBS;
import com.jifan.pssmis.foundation.contants.DataClassCodeContants;
import com.jifan.pssmis.foundation.contants.DataCodeContants;
import com.jifan.pssmis.foundation.exception.BizException;
import com.jifan.pssmis.foundation.log.SysLogger;
import com.jifan.pssmis.foundation.session.SysSession;
import com.jifan.pssmis.foundation.util.CommonUtil;
import com.jifan.pssmis.foundation.util.MathUtil;
import com.jifan.pssmis.model.bo.cdm.CustomerBO;
import com.jifan.pssmis.model.bo.cdm.DataDicDtlBO;
import com.jifan.pssmis.model.bo.cdm.MachineBO;
import com.jifan.pssmis.model.bo.cdm.MateriaBO;
import com.jifan.pssmis.model.bo.sys.UserBO;
import com.jifan.pssmis.model.bo.sys.UserGroupBO;
import com.jifan.pssmis.model.vo.cdm.CustomerQueryVO;
import com.jifan.pssmis.model.vo.cdm.DataDicDtlQueryVO;
import com.jifan.pssmis.model.vo.cdm.MachineQueryVO;
import com.jifan.pssmis.model.vo.cdm.MateriaQueryVO;

@Component
public class DataCodeUtil {

	private static IDataDicDtlBS dataDicDtlBS;

	private static IUserGroupBS userGroupBS;

	private static IUserBS userBS;

	private static IMateriaBS materiaBS;
	private static IMachineBS machineBS;
	private static ICustomerBS customerBS;
	private static IBaseJdbcBS baseJdbcBS;


	private static String DEFAULT = "未维护";

	/**
	 * 数据代码编码规则
	 */
	public static List<SelectItem> getDataCodeType() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(0, "树状"));
		// list.add(new SelectItem(1, "10层"));
		// list.add(new SelectItem(2, "5层"));
		// list.add(new SelectItem(3, "4层"));
		// list.add(new SelectItem(4, "3层"));
		list.add(new SelectItem(10, "枚举"));
		return list;
	}
	
	public static int getDataDicDtlCount(String dataClassCode){
		try{
			String sql = "SELECT  max(DATA_CODE)  from cdm_data_dic_dtl where DATA_CLASS_CODE='"+dataClassCode+"'";
			int count=baseJdbcBS.getIntValue(sql);
			return count;
		}catch (Exception e){
			SysLogger.error(DataCodeUtil.class, e.getMessage());
		}
		return 0;
	}
	
	public static int getComBuyCounts(Integer invoiceType,String projectID,String comId){
		int count=0;
		try{
			String sql ="select count(1) from pss_project_com_order a left join PSS_PROJECT_COM_ORDER_DTL b on a.ID=b.INVOICE_ID where a.INVOICE_TYPE="+invoiceType
				+" and a.invoice_status !=6 and a.PROJECT_ID='"+projectID+"' and b.PROJECT_COMPONENTS_ID='"+comId+"'";
			count=baseJdbcBS.getIntValue(sql);
		}catch(Exception e){
			 count=0;
		}
		return count;
	}
	

	/**
	 * 获取物料单据状态
	 * @param projectId
	 * @param comId
	 * @return
	 * int
	 *
	 */
	public static String getPicking(Integer invoiceType,String projectId,String comId){
		String str ="";
		try{
			String sql = "SELECT (SELECT b.DATA_NAME from cdm_data_dic_dtl b where b.DATA_CLASS_CODE='PSS_INVOICE_STATUS' and b.DATA_CODE=a.INVOICE_STATUS) from pss_project_com_order a left join pss_project_com_order_dtl c on a.id =c.INVOICE_ID" +
					" where a.INVOICE_TYPE="+invoiceType+" and a.PROJECT_ID='"+projectId+"' and c.PROJECT_COMPONENTS_ID='"+comId+"' ORDER BY a.CREATE_TIME desc limit 1";
			str=baseJdbcBS.getStringValue(sql);
			if(CommonUtil.isEmpty(str)){
				if(invoiceType.intValue()==1)
					str="未领料";
				else
					str="未请购";
			} else{
				if(invoiceType.intValue()==1)
					str="领料"+str;
				else
					str="请购"+str;
			}
		}catch (Exception e){
			SysLogger.error(DataCodeUtil.class, e.getMessage());
		}
		return str;
	}
	
	/**
	 * 获取加工进度
	 * @param projectID
	 * @return
	 * int
	 *
	 */
	public static String getScheduleOfCraft(String projectID){
		try{
			if(CommonUtil.isNotEmpty(projectID)){
			String testSql ="SELECT code from pss_project_test where PROJECT_ID='"+projectID+"' ORDER BY CREATE_TIME desc limit 1 ";	
			String testCode="";
			try{
				testCode=baseJdbcBS.getStringValue(testSql);
			}catch(Exception e){
				SysLogger.error(DataCodeUtil.class, testSql+"出错"+e.getMessage());
			}
				
			String sql = "SELECT ROUND((SELECT (SELECT IFNULL(sum(IFNULL(PLAN_HOURS,0)),0) from pss_project_com_craft a  "
					+"where a.PROJECT_ID='"+projectID+"' " ;
					if(CommonUtil.isNotEmpty(testCode))
						sql=sql+" and tn_code ='"+testCode+"' ";
					sql=sql+" and REAL_DATE is not null ) / "
					+"(SELECT IFNULL(sum(IFNULL(PLAN_HOURS,0)),0) from pss_project_com_craft a   where a.PROJECT_ID='"+projectID+"'" ;
					if(CommonUtil.isNotEmpty(testCode))
						sql=sql+" and tn_code ='"+testCode+"' ";
					sql =sql+"))  , 5)";
			Float count=baseJdbcBS.getFloatValue(sql);
			if(count==null)
				count=0.0f;
			String  str =String.valueOf(MathUtil.round(count*100, 3)) +"%";
			return str;
			}
		}catch (Exception e){
			SysLogger.error(DataCodeUtil.class, e.getMessage());
		}
		return "0%";
	}
	
	public static String getProjectPic(String projectID){
		String sql ="";
		try{
			sql="SELECT PIC_PATH from pss_project where id ='"+projectID+"'";
			return baseJdbcBS.getStringValue(sql);
		}catch (Exception e){
			SysLogger.error(DataCodeUtil.class, "getProjectPic出错！sql:"+sql+";"+e.getMessage());
		}
		return "";
	}
	

	public static String getCostTypeName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.COST_TYPE);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}

	public static String getCraftTypeName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.PSS_CRAFT_TYPE);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}

	public static String getCraftContentName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.PSS_CRAFT_CONTENT);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}

	public static String getNozzleTypeName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.PSS_NOZZLE_TYPE);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}
	
	/**
	 * 获取单据类型 编码
	 * @param dataCode
	 * @return
	 * String
	 *
	 */
	public static String getInvoiceTypeCor(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.PSS_INVOICE_TYPE);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getCorDataCode();
		return "";
	}
	
	public static String getModelNumName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.PSS_MODEL_NUM);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}

	public static String getTextureName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.PSS_TEXTURE);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}
	
	public static String getClassifyName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.CDM_MATERIA_CLASS);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}
	
	public static String getClassifyShortName(Integer dataCode){
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.CDM_MATERIA_CLASS);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getCorDataCode();
		return "";
	}
	
	public static String getSubclassName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.CDM_MATERIA_SUBCLASS);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}
	
	
	public static Integer getClassifyCodeByName(String dataName) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataName, DataClassCodeContants.CDM_MATERIA_CLASS);
		if(dataDicDtlBO==null)
			dataDicDtlBO=getDataDicDtlBOByCorCode(dataName, DataClassCodeContants.CDM_MATERIA_CLASS);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataCode();
		return 0;
	}
	
	public static Integer getSubclassCodeByName(String dataName){
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataName, DataClassCodeContants.CDM_MATERIA_SUBCLASS);
		if(dataDicDtlBO==null)
			dataDicDtlBO=getDataDicDtlBOByCorCode(dataName, DataClassCodeContants.CDM_MATERIA_SUBCLASS);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataCode();
		return 0;
	}

	public static String getBusinessTypeName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.PSS_BUSINESS_TYPE);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}

	public static String getWorkstageName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.PSS_WORKSTAGE);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}

	public static String getStatusName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.PSS_PROJECT_STATUS);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}
	
	public static String getInvoiceTypeName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.PSS_INVOICE_TYPE);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}
	
	public static String getInvoiceStatusName(Integer dataCode,Integer invoiceType) {
		String status;
		//领料单
		if(invoiceType !=null && invoiceType.equals(DataCodeContants.INVOICE_TYPE_ONE))
			status=DataClassCodeContants.PSS_LL_STATUS;
		//请购单
		else if (invoiceType !=null && invoiceType.equals(DataCodeContants.INVOICE_TYPE_TWO))
			status=DataClassCodeContants.PSS_QG_STATUS;
		else //采购单
			status=DataClassCodeContants.PSS_CG_STATUS;
		
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode,status);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}
	
	public static String getDtlStatusName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode,DataClassCodeContants.PSS_DTL_STATUS);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}

	public static String getUserGroupTypeName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.USER_GROUP_TYPE);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}
	
	public static String getUserTypeName(Integer dataCode) {
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(dataCode, DataClassCodeContants.USER_TYPE);
		if (dataDicDtlBO != null && CommonUtil.isNotEmpty(dataDicDtlBO.getDataName()))
			return dataDicDtlBO.getDataName();
		return dataCode + DEFAULT;
	}


	public static String getDepartmentName(String id) {
		UserGroupBO userGroupBO = userGroupBS.get(id);
		if (userGroupBO != null)
			return userGroupBO.getGroupName();
		return "";
	}

	public static String getUserName(String id) {
		UserBO userBO = userBS.get(id);
		if (userBO != null)
			return userBO.getUserName();
		return "";
	}

	public static boolean isCanNotify(String proId) {
		String sql ="SELECT count(1) from pss_project_logs where PROJECT_ID='"+proId+"' and USER_ID='"+SysSession.getUserId()+"' and OPERATION='"+DataCodeContants.PROJECT_STATUS_FOURNAME+"'";
		try{
			int count = baseJdbcBS.getIntValue(sql);
			if (count>0)
				return false;
		}catch (Exception e){
			return false;
		}
		return true;
	}

	/**
	 * 获取物料名称
	 * @param materiaID
	 * @return
	 */
	public static String getMateriaName(String materiaID) {
		if (CommonUtil.isNotEmpty(materiaID)) {
			MateriaBO materia = materiaBS.get(materiaID);
			if (materia != null)
				return materia.getName();
		}
		return "";
	}
	
	/**
	 * 获取物料BO
	 * @param materiaID
	 * @return
	 */
	public static MateriaBO getMateriaBO(String materiaID) {
		if (CommonUtil.isNotEmpty(materiaID)) {
			MateriaBO materia = materiaBS.get(materiaID);
			return materia;
		}
		return new MateriaBO();
	}
	
	/**
	 * 根据名称 获取物料ID，查找不到则直接添加一个
	 * @param materiaID
	 * @return
	 */
	public static String getMateriaBOWithCodeAndName(String materiaCode,String materiaName,Integer classify,String size) {
		String id ="";
		if (CommonUtil.isNotEmpty(materiaName)) {
			// 根据名称和规格找
			MateriaQueryVO param = new MateriaQueryVO();
//			param.setCode(materiaCode);
			param.setName(materiaName);
			param.setSize(size);
			List<MateriaBO>  materias = materiaBS.findMateriaByParamEqual(param);
			if(materias.size()>0)
				return materias.get(0).getId();
			else{
				MateriaBO mat =new MateriaBO();
				mat.setCode(materiaCode);
				mat.setName(materiaName);
				mat.setClassify(classify);
				mat.setSize(size);
				return materiaBS.save(mat);
			}
		}
		return id;
	}
	
	/**
	 * 获取客户BO
	 * @param materiaID
	 * @return
	 */
	public static CustomerBO getCustimerBO(String id) {
		if (CommonUtil.isNotEmpty(id)) {
			return  customerBS.get(id);
		}
		return new CustomerBO();
	}
	
	/**
	 * 获取客户名称
	 * @param materiaID
	 * @return
	 */
	public static String getCustomerName(String id) {
		if (CommonUtil.isNotEmpty(id)) {
			CustomerBO cus=  customerBS.get(id);
			if(cus!=null && CommonUtil.isNotEmpty(cus.getName()))
				return cus.getName();
		}
		return "";
	}
	
	/**
	 * 获取物料列表
	 * 
	 * @param dataCode
	 * @param dataClassCode
	 * @return
	 */
	public static List<MateriaBO> getMateriaBOList() {
		return  materiaBS.findMateriaByParam(new MateriaQueryVO());
	}
	
	/**
	 * 获取客户列表
	 * 
	 * @param dataCode
	 * @param dataClassCode
	 * @return
	 */
	public static List<CustomerBO> getCustomerBOList() {
		CustomerQueryVO param =new CustomerQueryVO();
		param.setType(1);
		return customerBS.findCustomerByParam(param);
	}
	
	/**
	 * 获取供应商列表
	 * 
	 * @param dataCode
	 * @param dataClassCode
	 * @return
	 */
	public static List<CustomerBO> getProviderBOList() {
		CustomerQueryVO param =new CustomerQueryVO();
		param.setType(2);
		return customerBS.findCustomerByParam(param);
	}
	/**
	 * 获取机台列表
	 * 
	 * @param dataCode
	 * @param dataClassCode
	 * @return
	 */
	public static List<MachineBO> getMachineBOList() {
		MachineQueryVO param =new MachineQueryVO();
		return machineBS.findMachineByParam(param);
	}
	
	
	/**
	 * 获取供应商名称
	 * 
	 * @param dataCode
	 * @param dataClassCode
	 * @return
	 */
	public static String getProviderName(String id) {
		CustomerBO provider = customerBS.get(id);
		if(provider!=null)
			return provider.getName();
		return "";
	}

	public static List<SelectItem> getStatusSelectList() {
		return getDataSelectList(DataClassCodeContants.PSS_PROJECT_STATUS);
	}
	
	public static List<SelectItem> getInoviceStatusSelectList(Integer invoiceType) {
		//领料单
		if(invoiceType !=null && invoiceType.equals(DataCodeContants.INVOICE_TYPE_ONE))
			return getDataSelectList(DataClassCodeContants.PSS_LL_STATUS);
		//请购单
		else if (invoiceType !=null && invoiceType.equals(DataCodeContants.INVOICE_TYPE_TWO))
			return getDataSelectList(DataClassCodeContants.PSS_QG_STATUS);
		else //采购单
			return getDataSelectList(DataClassCodeContants.PSS_CG_STATUS);

	}

	public static List<SelectItem> getNozzleSelectList() {
		return getDataSelectList(DataClassCodeContants.PSS_NOZZLE_TYPE);
	}
	
	public static List<SelectItem> getModelNumSelectList() {
		return getDataSelectList(DataClassCodeContants.PSS_MODEL_NUM);
	}

	public static List<SelectItem> getTextureSelectList() {
		return getDataSelectList(DataClassCodeContants.PSS_TEXTURE);
	}
	
	public static List<SelectItem> getClassifySelectList() {
		return getDataSelectList(DataClassCodeContants.CDM_MATERIA_CLASS);
	}
	
	public static List<SelectItem> getSubclassSelectList(){
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<DataDicDtlBO> dtls = getDataDicDtlBOList(DataClassCodeContants.CDM_MATERIA_SUBCLASS);
		for (DataDicDtlBO dtl : dtls) {
			if(dtl.getDataCode() !=0)
				list.add(new SelectItem(dtl.getDataCode(), dtl.getDataName()));
		}
		return list;
	}


	public static List<SelectItem> getBusinessTypeSelectList() {
		return getDataSelectList(DataClassCodeContants.PSS_BUSINESS_TYPE);
	}

	public static List<SelectItem> getUserGroupTypeList() {
		return getDataSelectList(DataClassCodeContants.USER_GROUP_TYPE);
	}
	
	public static List<SelectItem> getUserTypeList() {
		return getDataSelectList(DataClassCodeContants.USER_TYPE);
	}

	public static List<SelectItem> getCraftTypeSelectList() {
		return getDataSelectList(DataClassCodeContants.PSS_CRAFT_TYPE);
	}

//	public static List<SelectItem> getCraftContentSelectList() {
//		return getDataSelectList(DataClassCodeContants.PSS_CRAFT_CONTENT);
//	}
	
	public static List<SelectItem> getCraftContentSelectListAllName(String memo) {
		List<SelectItem> list = new ArrayList<SelectItem>();
		DataDicDtlQueryVO DataDicDtlQueryVO = new DataDicDtlQueryVO();
		DataDicDtlQueryVO.setDataClassCode(DataClassCodeContants.PSS_CRAFT_CONTENT);
		DataDicDtlQueryVO.setMemo(memo);
		List<DataDicDtlBO> dtls = dataDicDtlBS.findDataDicDtlByParam(DataDicDtlQueryVO);
		for (DataDicDtlBO dtl : dtls) {
			list.add(new SelectItem(dtl.getDataName(), dtl.getDataName()));
		}
		return list;
	}

	private static List<SelectItem> getDataSelectList(String dataClassCode) {
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<DataDicDtlBO> dtls = getDataDicDtlBOList(dataClassCode);
		for (DataDicDtlBO dtl : dtls) {
			list.add(new SelectItem(dtl.getDataCode(), dtl.getDataName()));
		}
		return list;
	}
	
	/**
	 * 获取物料小类
	 * 
	 * @return
	 * @throws BizException
	 */
	public static TreeNode getMateriaSubClassTree() throws BizException {
		return getDataTree(DataClassCodeContants.CDM_MATERIA_SUBCLASS);
	}

	/**
	 * 获取成本分析类型 树
	 * 
	 * @return
	 * @throws BizException
	 */
	public static TreeNode getCheckboxCostTypeTree() throws BizException {
		return getDataTree(DataClassCodeContants.COST_TYPE);
	}

	/**
	 * 获取成本分析类型 树
	 * 
	 * @return
	 * @throws BizException
	 */
	public static TreeNode getCheckboxWorkstageTree() throws BizException {
		return getDataTree(DataClassCodeContants.PSS_WORKSTAGE);
	}
	
	/**
	 * 获取成本分析类型 树
	 * 
	 * @return
	 * @throws BizException
	 */
	public static TreeNode getMateriaSubclassTree() throws BizException {
		return getDataTree(DataClassCodeContants.CDM_MATERIA_SUBCLASS);
	}

	/**
	 * 通用 -获取数据字典树状结构
	 * 
	 * @param dataClassCode
	 * @return
	 * @throws BizException
	 */
	public static TreeNode getDataTree(String dataClassCode) throws BizException {
		// 获取根节点 代码为0
		DataDicDtlBO dataDicDtlBO = getDataDicDtlBO(0, dataClassCode);
		TreeNode root = new CheckboxTreeNode(dataDicDtlBO);
		root.setExpanded(true);
		TreeNode treeNode = new CheckboxTreeNode(dataDicDtlBO, root);
		treeNode.setExpanded(true);
		if (dataDicDtlBO != null) {
			getSubDataTree(dataDicDtlBO, treeNode);
		} else
			throw new BizException("未找到代码为0的根，请先创建根数据，数据类别代码：" + dataClassCode);

		return root;
	}

	/**
	 * 获取子树
	 */
	private static void getSubDataTree(DataDicDtlBO dtl, TreeNode parentTreeNode) {
		List<DataDicDtlBO> subDatas = getSubDataDicDtlBOList(dtl);
		TreeNode subTreeNode = null;
		for (DataDicDtlBO subdtl : subDatas) {
			subTreeNode = new CheckboxTreeNode(subdtl, parentTreeNode);
			subTreeNode.setExpanded(true);
			// 递归获取子树
			getSubDataTree(subdtl, subTreeNode);
		}
	}
	
	/**
	 * 通用 -获取用户组树状结构
	 * 
	 * @param dataClassCode
	 * @return
	 * @throws BizException
	 */
	public static TreeNode getUserGroupTree(List<UserGroupBO> selectNodes) {
		TreeNode groupTree = new DefaultTreeNode("Root", null);
		// 获取根节点 代码为0
		UserGroupBO userGroupBO = new UserGroupBO("0", "组织架构");
		groupTree = new CheckboxTreeNode(userGroupBO);
		TreeNode treeNode = new CheckboxTreeNode(userGroupBO, groupTree);
		getSubDataTree(userGroupBO, treeNode,selectNodes);
		treeNode.setExpanded(true);
		groupTree.setExpanded(true);
		//选中 之前已分配的
		if(selectNodes !=null && selectNodes.size()>0)
			checkSelectRole(groupTree,selectNodes);
		return groupTree;
	}
	
	private  static void checkSelectRole(TreeNode treeNode,List<UserGroupBO> selectNodes){
		UserGroupBO ug=null;
		for(TreeNode tn:treeNode.getChildren()){
		    ug= (UserGroupBO)tn.getData();
			if(ug!=null && selectNodes.size()>0){
				for(UserGroupBO sl :selectNodes ){
					if(sl.getId().equals(ug.getId())){
						tn.setSelected(true);
						break;
					}
				}
			}
			if(!tn.isLeaf())
				checkSelectRole(tn,selectNodes);
		}
	}

	/**
	 * 获取子树
	 */
	private static void getSubDataTree(UserGroupBO group, TreeNode parentTreeNode,List<UserGroupBO> selectNodes) {
		List<UserGroupBO> subDatas = userGroupBS.getSubUserGroups(group.getId());
		TreeNode subTreeNode = null;
		for (UserGroupBO subdtl : subDatas) {
			subTreeNode = new CheckboxTreeNode(subdtl, parentTreeNode);
			subTreeNode.setExpanded(true);
//			if(selectNodes!=null && selectNodes.size()>0 ){
//				if(selectNodes.contains(subdtl))
//					subTreeNode.setSelected(true);
//				for(UserGroupBO sl :selectNodes ){
//					if(sl.getId().equals(subdtl.getId())){
//						subTreeNode.setSelected(true);
//						break;
//					}
//				}
//			}
			// 递归获取子树
			getSubDataTree(subdtl, subTreeNode,selectNodes);
		}
	}

	/**
	 * 根据 数据字典代码，类别代码获取数据字典BO
	 * 
	 * @param dataCode
	 * @param dataClassCode
	 * @return
	 */
	public static DataDicDtlBO getDataDicDtlBO(Integer dataCode, String dataClassCode) {
		DataDicDtlQueryVO DataDicDtlQueryVO = new DataDicDtlQueryVO();
		DataDicDtlQueryVO.setDataCode(dataCode);
		DataDicDtlQueryVO.setDataClassCode(dataClassCode);
		List<DataDicDtlBO> dataDicDtlBOs = dataDicDtlBS.findDataDicDtlByParam(DataDicDtlQueryVO);
		if (dataDicDtlBOs != null && dataDicDtlBOs.size() > 0) {
			DataDicDtlBO dataDicDtlBO = dataDicDtlBOs.get(0);
			return dataDicDtlBO;
		}
		return null;
	}
	
	/**
	 * 根据 数据字典名称，类别代码获取数据字典BO
	 * 
	 * @param dataName
	 * @param dataClassCode
	 * @return
	 */
	public static DataDicDtlBO getDataDicDtlBO(String dataName, String dataClassCode) {
		DataDicDtlQueryVO DataDicDtlQueryVO = new DataDicDtlQueryVO();
		DataDicDtlQueryVO.setDataName(dataName.trim());
		DataDicDtlQueryVO.setDataClassCode(dataClassCode);
		List<DataDicDtlBO> dataDicDtlBOs = dataDicDtlBS.findDataDicDtlByParam(DataDicDtlQueryVO);
		if (dataDicDtlBOs != null && dataDicDtlBOs.size() > 0) {
			DataDicDtlBO dataDicDtlBO = dataDicDtlBOs.get(0);
			return dataDicDtlBO;
		}
		return null;
	}
	
	/**
	 * 根据 数据字典公司代码，类别代码获取数据字典BO
	 * 
	 * @param dataName
	 * @param dataClassCode
	 * @return
	 */
	public static DataDicDtlBO getDataDicDtlBOByCorCode(String corCode, String dataClassCode) {
		DataDicDtlQueryVO DataDicDtlQueryVO = new DataDicDtlQueryVO();
		DataDicDtlQueryVO.setCorDataCode(corCode.trim());
		DataDicDtlQueryVO.setDataClassCode(dataClassCode);
		List<DataDicDtlBO> dataDicDtlBOs = dataDicDtlBS.findDataDicDtlByParam(DataDicDtlQueryVO);
		if (dataDicDtlBOs != null && dataDicDtlBOs.size() > 0) {
			DataDicDtlBO dataDicDtlBO = dataDicDtlBOs.get(0);
			return dataDicDtlBO;
		}
		return null;
	}

	/**
	 * 根据类别代码获取数据字典BO列表
	 * 
	 * @param dataCode
	 * @param dataClassCode
	 * @return
	 */
	public static List<DataDicDtlBO> getDataDicDtlBOList(String dataClassCode) {
		if (CommonUtil.isNotEmpty(dataClassCode)) {
			DataDicDtlQueryVO DataDicDtlQueryVO = new DataDicDtlQueryVO();
			DataDicDtlQueryVO.setDataClassCode(dataClassCode);
			List<DataDicDtlBO> dataDicDtlBOs = dataDicDtlBS.findDataDicDtlByParam(DataDicDtlQueryVO);
			return dataDicDtlBOs;
		} else
			return null;
	}

	/**
	 * 构造数据字典下拉框
	 * 
	 * @param dataDicDtlList
	 *            数据字典集合
	 * @return 下拉框
	 */
	public static List<SelectItem> createSelector(List<DataDicDtlBO> dataDicDtlList) {
		List<SelectItem> selectList = new ArrayList<SelectItem>();
		if (dataDicDtlList != null) {
			for (DataDicDtlBO dataDicDtlBO : dataDicDtlList) {
				selectList.add(new SelectItem(dataDicDtlBO.getDataCode(), dataDicDtlBO.getDataName()));
			}
		}
		return selectList;
	}

	public static List<DataDicDtlBO> getSubDataDicDtlBOList(DataDicDtlBO dataDicDtlBO) {
		if (dataDicDtlBO.getDataDicBO() != null) {
			if (dataDicDtlBO.getDataDicBO().isLeaveTree())
				return getSubDataDicDtlBOListByLevel(dataDicDtlBO);
			else if (dataDicDtlBO.getDataDicBO().isTraditionTree())
				return getSubDataDicDtlBOListByParent(dataDicDtlBO);
			else {
				SysLogger
					.error(DataCodeUtil.class, dataDicDtlBO.getDataDicBO().getDataClassCode() + "不是树状结构！无法获取子数据集!");
				return null;
			}
		} else {
			SysLogger.error(DataCodeUtil.class, dataDicDtlBO.getId() + "数据字典主表信息为空!");
			return null;
		}
	}

	/**
	 * 获取树形数据字典子节点
	 * 
	 * @param dataClassCode
	 * @return
	 */
	public static List<DataDicDtlBO> getSubDataDicDtlBOListByLevel(DataDicDtlBO dataDicDtlBO) {
		List<DataDicDtlBO> dataDicList = dataDicDtlBS.getSubDataDicDtlBOListByLevel(dataDicDtlBO);
		return dataDicList;
	}

	/**
	 * 获取数据字典子节点
	 * 
	 * @param dataClassCode
	 * @return
	 */
	public static List<DataDicDtlBO> getSubDataDicDtlBOListByParent(DataDicDtlBO dataDicDtlBO) {
		List<DataDicDtlBO> dataDicList = dataDicDtlBS.getSubDataDicDtlBOListByParent(dataDicDtlBO);
		return dataDicList;
	}
	
	/**
	 * 获取成本分析中的材质名称
	 * @param projectId 模具id
	 * @param name 成本项中文名
	 * @return
	 * String
	 *
	 */
	public static String getCostTexttureName(String projectId,String name){
		String result="";
		try{
		String sql = "SELECT t3.NAME from "
		+" (SELECT * from cdm_data_dic_dtl where DATA_CLASS_CODE='"+DataClassCodeContants.COST_TYPE+"' and DATA_NAME like '%"+name+"%') t1"
		+" left join pss_project_cost_materia t2 on t1.DATA_CODE=t2.COST_ITEM"
		+" left join cdm_materia t3 on t2.TEXTURE=t3.ID"
		+"  where t2.PROJECT_ID='"+projectId+"' limit 1";
		result=baseJdbcBS.getStringValue(sql);
		if(result==null)
			result="";
		}catch(Exception e){
			result="";
		}
		return result;
	}
	
	public static String getPlanBuyDate(String projectID,String comID){
		try{
			String sql="SELECT DATE_FORMAT(b.PLAN_DATE,'%Y%m%d') from  pss_project_com_order_dtl b "+
				"where  b.PROJECT_ID='"+projectID+"' and b.PROJECT_COMPONENTS_ID='"+comID+"' "+
				"ORDER BY CREATE_TIME desc LIMIT 1 ";
			String str =baseJdbcBS.getStringValue(sql);
			return str;
		}catch (Exception e){
			
		}
		return "";
	}
	
	public static String getRealBuyDate(String projectID,String comID){
		try{
			String sql="SELECT DATE_FORMAT(b.REAL_DATE,'%Y%m%d') from pss_project_com_order a left join  pss_project_com_order_dtl b on a.id=b.INVOICE_ID  "+
				"where a.INVOICE_TYPE=3 and (a.INVOICE_STATUS=5 or a.INVOICE_STATUS=7) and  b.PROJECT_ID='"+projectID+"' and b.PROJECT_COMPONENTS_ID='"+comID+"' "+
				"ORDER BY b.CREATE_TIME desc LIMIT 1 ";
			String str =baseJdbcBS.getStringValue(sql);
			return str;
		}catch (Exception e){
			
		}
		return "";
	}
	

	public static IDataDicDtlBS getDataDicDtlBS() {
		return dataDicDtlBS;
	}

	@Autowired(required = true)
	public void setDataDicDtlBS(IDataDicDtlBS dataDicDtlBS) {
		DataCodeUtil.dataDicDtlBS = dataDicDtlBS;
	}

	public static IUserGroupBS getUserGroupBS() {
		return userGroupBS;
	}

	@Autowired(required = true)
	public void setUserGroupBS(IUserGroupBS userGroupBS) {
		DataCodeUtil.userGroupBS = userGroupBS;
	}

	public static IUserBS getUserBS() {
		return userBS;
	}

	@Autowired(required = true)
	public void setUserBS(IUserBS userBS) {
		DataCodeUtil.userBS = userBS;
	}

	public static IMateriaBS getMateriaBS() {
		return materiaBS;
	}

	@Autowired(required = true)
	public  void setMateriaBS(IMateriaBS materiaBS) {
		DataCodeUtil.materiaBS = materiaBS;
	}

	public static ICustomerBS getCustomerBS() {
		return customerBS;
	}

	@Autowired(required = true)
	public  void setCustomerBS(ICustomerBS customerBS) {
		DataCodeUtil.customerBS = customerBS;
	}

	public static IBaseJdbcBS getBaseJdbcBS() {
		return baseJdbcBS;
	}

	@Autowired(required = true)
	public  void setBaseJdbcBS(IBaseJdbcBS baseJdbcBS) {
		DataCodeUtil.baseJdbcBS = baseJdbcBS;
	}

	public static IMachineBS getMachineBS() {
		return machineBS;
	}
	@Autowired(required = true)
	public  void setMachineBS(IMachineBS machineBS) {
		DataCodeUtil.machineBS = machineBS;
	}


}
