package com.jifan.pssmis.foundation.contants;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义系统级别的常�?
 * 
 * @author jifan
 */
public class SysContants {
	/**
     * 
     */
	public static String MORNING = "morning";

	/**
	 * Session中USER_SESSION_VO的键值
	 */
	public static String SYS_USER_SESSION_VO = "SYS_USER_SESSION_VO";

	/**
	 * 网上商城的Session中USER_SESSION_VO的键值
	 */
	public static String BCM_SYS_USER_SESSION_VO = "BCM_SYS_USER_SESSION_VO";

	/**
	 * Session中LAST_OPEN_PAGE的键值,最后打开的tab页面
	 */
	public static String LAST_OPEN_PAGE = "LAST_OPEN_PAGE";

	public static String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd-HH-mm-ss";

	/*********************
	 * 权限相关star********************************* /** 功能权限类型
	 */
	public static int RIGHTS_TYPE_FUNC = 1;

	/**
	 * 流程权限类型
	 */
	public static int RIGHTS_TYPE_FLOW = 2;

	/**
	 * 数据权限类型
	 */
	public static int RIGHTS_TYPE_DATA = 3;

	/**
	 * 编辑的权限后缀
	 */
	public static String EDIT = "_edit";

	/**
	 * 添加的权限后缀
	 */
	public static String ADD = "_add";

	/**
	 * 删除的权限后缀
	 */
	public static String DELETE = "_delete";

	/**
	 * 查询的权限后缀
	 */
	public static String QUERY = "_query";

	/**
	 * 审核的权限后缀
	 */
	public static String AUDIT = "_auditable";

	/**
	 * 修改数据的权限后缀
	 */
	public static String ORGINAL = "_orginal";

	/**
	 * 取消审核的权限后缀
	 */
	public static String UNEXAMINE = "_enExamine";
	/**
	 * 发送激活码的权限后缀
	 */
	public static String SENDCODE = "_sendCode";

	/**
	 * 节点初始化权限后缀
	 */
	public static String NODE_INIT = "_nodeInit";

	/**
	 * 缓存刷新权限后缀
	 */
	public static String CACHE_REFLESH = "_cacheReflesh";

	/**
	 * 出库单修改折扣的权限后缀
	 */
	public static String CHANGE_DIS = "_changedis";

	/**
	 * 模块管理的权限后缀 数组装的时候会用到 manager 来区分功能模块权限和功能权限
	 */
	public static String MANAGER = "_manager";

	/**
	 * 编辑的权限后缀
	 */
	public static String EDIT_NAME = "_编辑";

	/**
	 * 添加的权限后缀
	 */
	public static String ADD_NAME = "_添加";

	/**
	 * 删除的权限后缀
	 */
	public static String DELETE_NAME = "_删除";

	/**
	 * 查询的权限后缀
	 */
	public static String QUERY_NAME = "_查询";

	/*********************
	 * 权限相关end********************************* /** 事务定义编码
	 */

	/**
	 * 进货管理事务代码
	 */
	public static final String WORK_STOCK_MANAGE_CODE = "W_GI";

	/**
	 * 供货管理事务
	 */
	public static final String WORK_SUPPLY_MANAGE_CODE = "W_SC";

	/**
	 * 线上支付销售事务
	 */
	public static final String WORK_ONLINE_PAY_SALE_MANAGE_CODE = "W_RETAIL_OP";

	/**
	 * 销售事务
	 */
	public static final String WORK_SALE_MANAGE_CODE = "W_RETAIL";

	/**
	 * 会员销售销售事务
	 */

	public static final String WORK_MEMBER_SALE_MANAGE_CODE = "W_EG";

	/**
	 * 线上会员销售销售事务
	 */

	public static final String WORK_MEMBER_OL_SALE_MANAGE_CODE = "W_OL_EG";

	/**
	 * 公司成品入库事务
	 */
	public static final String WORK_ENDPRODUCT_STOCKIN = "W_FPS";

	/**
	 * 退货管理店铺端
	 */
	public static final String WORK_SHOP_RETURN = "W_RGC";

	/**
	 * 退货管理公司端
	 */
	public static final String WORK_COMPANY_RETURN = "W_RGS";

	/**
	 * 调入管理事务
	 */
	public static final String WORK_CALL_IN = "W_CTI";

	/**
	 * 生产管理事务-进货方
	 */
	public static final String WORK_PRODUCTION_CONTROL_PURCHASE = "W_PC_P";

	/**
	 * 生产管理事务-供货方
	 */
	public static final String WORK_PRODUCTION_CONTROL_SUPPLIER = "W_PCS";

	/**
	 * 生产管理事务-供货方
	 */
	public static final String WORK_PRODUCTION_CONTROL_FLOW = "W_PCF";

	/**
	 * 生产管理事务-生产中心
	 */
	public static final String WORK_PRODUCTION_CONTROL_PRODUCTION_CENTER = "W_PC_PC";

	/**
	 * 交货拒收事务-进货方
	 */
	public static final String WORK_PRODUCTION_REJECTION_PURCHASE = "W_PRP";

	/**
	 * 交货拒收事务-供货方
	 */
	public static final String WORK_PRODUCTION_REJECTION_SUPPLIER = "W_PRS";

	/**
	 * 交货管理事务-进货方
	 */
	public static final String WORK_DELIVERY_CONTROL_PURCHASE = "W_DC_P";

	/**
	 * 交货管理事务-供货方
	 */
	public static final String WORK_DELIVERY_CONTROL_SUPPLIER = "W_DC_S";

	/**
	 * 超交管理事务-进货方
	 */
	public static final String WORK_OVER_DELIVERY_CONTROL_PURCHASE = "W_ODC_P";

	/**
	 * 超交管理事务-供货方
	 */
	public static final String WORK_OVER_DELIVERY_CONTROL_SUPPLIER = "W_ODC_S";

	/**
	 * 交货退货事务_进货方
	 */
	public static final String WORK_PRODUCTION_RETURNDELIVERY = "W_DR";

	/**
	 * 生产退货事务-供货方
	 */
	public static final String WORK_PRODUCTION_RETURNCONFIRMATION = "W_RC";

	/**
	 * 调出管理事务
	 */
	public static final String WORK_CALL_OUT = "W_CTO";

	/**
	 * 铺货工作流-供方
	 */
	public static final String WORK_CASUAL_SUPPLIER = "W_DS";

	/**
	 * 铺货工作流-进方
	 */
	public static final String WORK_CASUAL_CUSTOMER = "W_DC";

	/**
	 * 共享销售事务-销售端
	 */
	public static final String WORK_SHARE_SALE = "W_SS";

	/**
	 * 共享销售事务-共享端
	 */
	public static final String WORK_SHARE_CALLOUT = "W_SSC";

	/**
	 * FB_TD
	 */
	public static final String WORK_ = "FB_TD";

	/**
	 * 流程定义代码
	 */

	/**
	 * 补货流程
	 */
	public static final String FLOW_REPLENISH_CODE = "FB_GR";

	/**
	 * 入库流程
	 */
	public static final String FLOW_PUTIN_STORAGE_CODE = "FB_STORAGE";

	/**
	 * 出库流程
	 */
	public static final String FLOW_TAKEOUT_STORAGE_CODE = "FB_OS";

	/**
	 * 配货流程
	 */
	public static final String FLOW_ALLOCATE_CARGO_CODE = "FB_DIST";

	/**
	 * 店铺销售流�?
	 */
	public static final String FLOW_SALE_MANAGE_CODE = "FB_RETAIL";

	/**
	 * 生成客户订单
	 */
	public static final String FLOW_GENRATE_CUSTOMR_ORDER_CODE = "sckhdd";

	/**
	 * 客户订单处理
	 */
	public static final String FLOW_CUSTOMER_ORDER_PROCESS_CODE = "FB_CBD";

	/**
	 * 收款确认
	 */
	public static final String FLOW_RECEIPTS_CONFIRMED = "FF_RC";

	/**
	 * 付款确认
	 */
	public static final String FLOW_PAY_CONFIRMED = "FF_PC";

	/**
	 * 调出
	 */
	public static final String FLOW_CALL_OUT_CODE = "FB_TO";

	/**
	 * 调入
	 */
	public static final String FLOW_CALL_IN_CODE = "FF_CI";

	/**
	 * 环节定义代码
	 */

	/**
	 * 提交补货单
	 */
	public static final String LINK_SUBMIT_REPLENISHORDER_CODE = "L_SRB";

	/**
	 * 生成入库单
	 */
	public static final String LINK_GENRATE_STORAGE_INVOICE_CODE = "L_GSB";

	/**
	 * 入库
	 */
	public static final String LINK_PUTIN_STORAGE_CODE = "L_STORAGE";

	/**
	 * 配货审核
	 */
	public static final String LINK_ALLOCATION_AUDIT_CODE = "L_DBA";

	/**
	 * 出库
	 */
	public static final String LINK_TAKEOUT_STORAGE_CODE = "L_OS";

	/**
	 * 生成出库单
	 */
	public static final String LINK_GENRATE_TAKEOUT_INVOICE_CODE = "L_GOSB";

	/**
	 * 生成客户订单
	 */
	public static final String LINK_GENRATE_CUSTOMER_ORDER_CODE = "sckhdd";

	/**
	 * 客户订单处理:审核环节
	 */
	public static final String LINK_CUSTOMER_ORDER_AUDIT_CODE = "L_CBA";

	/**
	 * 销售单录入确认
	 */
	public static final String LINK_SALE_MANAGE_CODE = "L_RA";

	/**
	 * 提交调出单
	 */
	public static final String LINK_CALL_OUT_CODE = "L_STOB";

	/**
	 * 调入单审核
	 */
	public static final String LINK_CALL_IN_CODE = "L_CIA";

	/**
	 * 系统超级用户 chencl 2011-07-06
	 */
	public static final String ADMIN = "admin";

	/**
	 * 退货组用的店铺用户 liuj 2012-02-16
	 */
	public static final String SYSZZ = "zz";

	/**
	 * 金额显示的数据权限的资源信息
	 */
	public static final String SHOWRIHGT = "402887283a63616d013a9243c087052b";

	/**
	 * 共享库存查询数量数据权限的资源信息
	 */
	public static final String SHSTOCK = "402887e73b829f60013b82b623c70003";

	/**
	 * 库存转出
	 */
	public static final String W_ROI = "W_ROI";

	/**
	 * 库存转入
	 */
	public static final String W_II = "W_II";

	/**
	 * 电商销售单审单事务-电商端
	 */
	public static final String WORK_ELECTRONICBUSINESS_SALEINVOICE_CHECK = "W_EBSC";

	/**
	 * 退货申请单退货事务-电商端
	 */
	public static final String WORK_ELECTRONICBUSINESS_SALEINVOICE_RETURN = "W_EBSR";

	/**
	 * 退货申请单退货事务-共享端/电商端
	 */
	public static final String WORK_SHARE_CALLOUT_RETURNGOODS = "W_SCRG";

	/**
	 * 配货流程环节定义：退回修改(配)
	 */
	public static final String LINK_ALLOCATE_CARGO_RETURN = "L_RMA";

	/**
	 * 配货流程环节定义：配货单审核
	 */
	public static final String LINK_ALLOCATE_CARGO_AUDIT = "L_DBA";

	/**
	 * 配货流程环节定义：生成出库单
	 */
	public static final String LINK_ALLOCATE_CARGO_StOCKOUT = "L_GOSB";

	public final static Integer SUCCESS = 1001;// 请求成功

	public final static Integer FAILE = 1002;// 请求失败

	// public final static Integer ITEM_CODE_LENGTH=19;//唯一码长度

	/**
	 * 计划退货流-供方
	 */
	public static final String WORK_PLAN_RETURN_SUPPLIER = "W_PRGS";

	/**
	 * 计划退货流-供方流程定义 :制作退货计划与通知
	 */
	public static final String FLOW_PLAN_RETURN_NOTICE = "FB_RPN";

	/**
	 * 计划退货流-供方环节定义 :提交退货计划
	 */
	public static final String LINK_SUBMIT_RETURN_PLAN = "L_SRP";

	/**
	 * 计划退货流-退方
	 */
	public static final String WORK_PLAN_RETURN_CUSTOMER = "W_PRGC";

	/**
	 * 计划退货流-供方环节定义 :发退货通知
	 */
	public static final String LINK_RETURN_NOTICE = "L_PRN";

	/**
	 * 调货钱流是否经过公司 0：表示不通过第三方结算，1：表示通过第三方结算，2：
	 * 表示只有节点类型不是加盟和加盟调货时才通过第三方结算,加盟与加盟调货店铺双方结算 默认为0
	 */
	public static final Integer CALLINOUTBYCOMPANYFLAG_0 = 0;

	public static final Integer CALLINOUTBYCOMPANYFLAG_1 = 1;

	public static final Integer CALLINOUTBYCOMPANYFLAG_2 = 2;

	/**
	 *共享销售钱流是否经过公司 0：表示不通过第三方结算，1：表示通过第三方结算，2：
	 * 表示只有节点类型不是加盟和加盟调货时才通过第三方结算,加盟与加盟调货店铺双方结算 默认为0
	 */
	public static final Integer SHARESTOCKBYCOMPANYFLAG_0 = 0;

	public static final Integer SHARESTOCKBYCOMPANYFLAG_1 = 1;

	public static final Integer SHARESTOCKBYCOMPANYFLAG_2 = 2;

	/**
	 * epos平台节点
	 */
	public static final String EPOS_NODE_CODE = "8888";

	/**
	 * epos平台
	 */
	public static final String EPOS = "epos";

	/**
	 * wewe企业号SECRET配置键
	 */
	public static final String WEIXINQY_CORPSECRET = "OA_WEIXINQY_CORPSECRET";

	/**
	 * 微信企业号消息加密Token
	 */
	public static final String S_TOKEN = "tofan";

	/**
	 * 微信企业号消息加密密钥
	 */
	public static final String S_ENCODING_AES_KEY = "ZI88JY22yADrnepNGfs9vr0SODbdX4DMfxFpptgR9cN";
    /**
     * Session中LOGIN_COUNT_SESSION的键值
     */
    public static String LOGIN_COUNT_SESSION = "LOGIN_COUNT_SESSION";
    
	/**
	 * 导入的权限后缀
	 */
	public static String IMPORT = "_import";
	
	/**
	 * 清空的权限后缀
	 */
	public static String pruge = "_pruge";
	
	public static Map<String, String> map = new HashMap<String, String>();
	
	public static Map<String, Boolean> runMap = new HashMap<String, Boolean>();

}
