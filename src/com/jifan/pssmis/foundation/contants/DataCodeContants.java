package com.jifan.pssmis.foundation.contants;

import org.antlr.grammar.v3.ANTLRv3Parser.finallyClause_return;

/**
 * 数据字典中的数据代码常量
 * 
 * @author linjy
 * 
 */
public class DataCodeContants {
	/**
	 * 区域中国代码
	 */
	public static final Integer CHINA_DATA_CODE = 100000000;

	/**
	 * 业务类型:预收
	 */
	public static final Integer BUSINESS_TYPE_ADVANCE_RECEIVE = 1;

	/**
	 * 业务类型:应收
	 */
	public static final Integer BUSINESS_TYPE_RECEIVABLE = 2;

	/**
	 * 业务类型:预付
	 */
	public static final Integer BUSINESS_TYPE_ADVANCE_PAY = 3;

	/**
	 * 业务类型:应付
	 */
	public static final Integer BUSINESS_TYPE_PAYABLE = 4;

	/**
	 * 收入/费用类型:销售
	 */
	public static final String CHARGE_TYPE_SALE = "XS_SR";

	/**
	 * 收入/费用类型:补货支出
	 */
	public static final String CHARGE_TYPE_RETRIEVAL = "BHZC_ZC";

	/**
	 * 收入/费用类型:供货收入
	 */
	public static final String CHARGE_TYPE_ALLOCATE = "GHSR_SR";

	/**
	 * 收入/费用类型:退货款(收入)
	 */
	public static final String CHARGE_TYPE_INCOME_RETURN = "THK_SR";

	/**
	 * 收入/费用类型:退货款(支出)
	 */
	public static final String CHARGE_TYPE_PAY_RETURN = "THK_ZC";

	/**
	 * 收入/费用类型:导购宝(收入)
	 */
	public static final String CHARGE_TYPE_INCOME_DGB = "DGB_SR";
	/**
	 * 收入/费用类型:导购宝(支出)
	 */
	public static final String CHARGE_TYPE_PAY_DGB = "DGB_ZC";
	/**
	 * 收入/费用类型:代付运费
	 */
	public static final String CHARGE_TYPE_YUFEI = "DFYF_SR";

	/**
	 * 收入/费用类型:道具/货架款
	 */
	public static final String CHARGE_TYPE_DAOJU = "DJHJK_SR";

	/**
	 * 收入/费用类型:公积金支出
	 */
	public static final String CHARGE_TYPE_JFC_ZC = "JFC_ZC";

	/**
	 * 收入/费用类型:公积金支出
	 */
	public static final String CHARGE_TYPE_JFC_SR = "JFC_SR";

	/**
	 * 挂账审核状态：未审核
	 */
	public static final Integer FINV_HEADER_STATUS_NOT = 0;

	/**
	 * 挂账审核状态：已审核
	 */
	public static final Integer FINV_HEADER_STATUS_ALREADY = 1;

	/**
	 * 挂账审核状态：已退回
	 */
	public static final Integer FINV_HEADER_STATUS_RETURN = 2;

	/**
	 * 挂账审核状态：已催款
	 */
	public static final Integer FINV_HEADER_STATUS_REMINDER = 3;
/**
	 * 挂账审核状态：已锁定
	 */
	public static final Integer FINV_HEADER_STATUS_LOCK = 4;
	/**
	 * 币种：人民币
	 */
	public static final Integer CURRENCY_RMB = 1;

	/**
	 * 单据类型：补货单
	 */
	public static final Integer INVOICETYPE_BB = 1;

	/**
	 * 单据类型：客户订货单
	 */
	public static final Integer INVOICETYPE_SB = 2;

	/**
	 * 单据类型：零售单
	 */
	public static final Integer INVOICETYPE_OS = 3;

	/**
	 * 单据类型：经销商销售反馈单
	 */
	public static final Integer INVOICETYPE_SF = 4;

	/**
	 * 单据类型：预配货单
	 */
	public static final Integer INVOICETYPE_OH = 5;

	/**
	 * 单据类型：入库单
	 */
	public static final Integer INVOICETYPE_LB = 7;

	/**
	 * 单据类型：出库单
	 */
	public static final Integer INVOICETYPE_OQ = 8;

	/**
	 * 单据类型：退货单
	 */
	public static final Integer INVOICETYPE_OP = 9;

	/**
	 * 单据类型：客户退货单
	 */
	public static final Integer INVOICETYPE_CP = 10;

	/**
	 * 单据类型：会员销售单
	 */
	public static final Integer INVOICETYPE_Hs = 13;

	/**
	 * 单据类型：客户盘点单
	 */
	public static final Integer INVOICETYPE_PD = 14;

	/**
	 * 单据类型：客户差异单
	 */
	public static final Integer INVOICETYPE_CY = 15;

	/**
	 * 单据类型：调出单
	 */
	public static final Integer INVOICETYPE_DC = 11;

	/**
	 * 单据类型：调入单
	 */
	public static final Integer INVOICETYPE_DR = 12;

	/**
	 * 单据类型：共享零售单
	 */
	public static final Integer INVOICETYPE_SS = 16;

	/**
	 * 单据类型：共享调出单
	 */
	public static final Integer INVOICETYPE_SC = 17;

	/**
	 * 单据类型：退货计划单
	 */
	public static final Integer INVOICETYPE_RP = 18;

	/**
	 * 单据类型：积分换购单
	 */
	public static final Integer INVOICETYPE_HS = 13;

	/**
	 * 单据类型：生产订单
	 */

	public static final Integer INVOICETYPE_PO = 24;

	/**
	 * 单据类型：生产计划单
	 */
	public static final Integer INVOICETYPE_PP = 25;

	/**
	 * 单据类型：交货退货单
	 */
	public static final Integer INVOICETYPE_PDR = 26;

	/**
	 * 单据类型：退货确认单
	 */
	public static final Integer INVOICETYPE_PRC = 27;

	/**
	 * 单据类型：交货单
	 */
	public static final Integer INVOICETYPE_JH = 28;

	/**
	 * 单据类型：流转单
	 */
	public static final Integer INVOICETYPE_LZ = 44;

	/**
	 * 超交业务类型
	 */
	public static final Integer OVERDELIVERY = 26;

	/**
	 * 单据类型：生产确认单
	 */
	public static final Integer INVOICETYPE_PC = 45;

	/**
	 * 单据类型：生产差异单
	 */
	public static final Integer INVOICETYPE_PCY = 46;

	/**
	 * 单据类型：交货确认单
	 */
	public static final Integer INVOICETYPE_JHQR = 29;

	/**
	 * 单据类型：生产需求单
	 */
	public static final Integer INVOICETYPE_SO = 23;

	/**
	 * 单据类型：发货单
	 */
	public static final Integer INVOICETYPE_FH = 31;

	/**
	 * 单据类型：收货单
	 */
	public static final Integer INVOICETYPE_SH = 32;

	/**
	 * 单据类型：转入单
	 */
	public static final Integer INVOICETYPE_Zr = 33;

	/**
	 * 单据类型：转出单
	 */
	public static final Integer INVOICETYPE_Zc = 34;

	/**
	 * 单据类型：预补货单
	 */
	public static final Integer INVOICETYPE_Yb = 38;

	/**
	 * 单据类型：店铺差异单
	 */
	public static final Integer INVOICETYPE_SY = 21;

	/**
	 * 单据类型：拒收单
	 */
	public static final Integer INVOICETYPE_JS = 42;

	/**
	 * 单据类型：拒收确认单
	 */
	public static final Integer INVOICETYPE_JSQR = 43;

	/**
	 * 单据类型：超交单
	 */
	public static final Integer INVOICETYPE_CJ = 47;

	/**
	 * 单据类型：超交确认单
	 */
	public static final Integer INVOICETYPE_CJQR = 48;

	/**
	 * 单据类型：自建商城订单
	 */
	public static final Integer BCM_INVOICETYPE_OO = 0;

	/**
	 * 单据类型：o2o订单
	 */
	public static final Integer BCM_INVOICETYPE_O2O = 88;

	/**
	 * 单据类型：京东订单
	 */
	public static final Integer BCM_INVOICETYPE_JD = 11;

	/**
	 * 单据类型：微购订单
	 */
	public static final Integer BCM_INVOICETYPE_WG = 50;

	/**
	 * 核销方式：现金
	 */
	public static final Integer WRITE_OFF_TYPE_CASH = 1;

	/**
	 * 核销方式：银行
	 */
	public static final Integer WRITE_OFF_TYPE_BANK = 2;

	/**
	 * 核销方式：预收转应收核销
	 */
	public static final Integer WRITE_OFF_TYPE_RECEIVE = 3;

	/**
	 * 核销方式：预付转应付核销
	 */
	public static final Integer WRITE_OFF_TYPE_PAY = 4;

	/**
	 * 价格类型:成本价
	 */
	public static final Integer PRICE_TYPE_COST = 1;

	/**
	 * 价格类型:供货价
	 */
	public static final Integer PRICE_TYPE_SUPPLY = 2;

	/**
	 * 价格类型：销售价
	 */
	public static final Integer PRICE_TYPE_OS = 3;

	/**
	 * 价格类型：共享价
	 */
	public static final Integer PRICE_TYPE_SHARE = 4;

	/**
	 * 价格类型：铺货价
	 */
	public static final Integer PRICE_TYPE_SHOP = 5;
	
	/**
	 * 价格类型：吊牌价
	 */
	public static final Integer PRICE_TYPE_TAG = 6;
	/**
	 * 价格类型：砍价价
	 */
	public static final Integer PRICE_TYPE_BRAGAIN = 7;
	/**
	 * 价格类型：砍价期望价格
	 */
	public static final Integer PRICE_TYPE_EXPECT =8;
	
	/**
	 * 价格类型：微商抽成一级抽成
	 */
	public static final Integer PRICE_TYPE_PORTION_ONE =9;
	
	/**
	 * 价格类型：微商抽成二级抽成
	 */
	public static final Integer PRICE_TYPE_PORTION_TWO=10;
	
	
	/**
	 * 价格类型：红包抵扣价格
	 */
	public static final Integer PRICE_TYPE_RED_DEDUCTION=12;

	/**
	 * 店铺销售流程环节：销售退回
	 */
	public static final String LINK_DEF_XSTH = "L_RBR";

	/**
	 * 电销单到货确认
	 */
	public static final String LINK_DEF_L_DC = "L_DC";

	/**
	 * 店铺销售流程环节：共享销售退回
	 */
	public static final String LINK_DEF_GXXSTH = "L_SSRM";

	/**
	 *电商销售单审核:审核
	 */
	public static final String OPERATION_O_EBSALEINVOICE_CHECK = "O_EBSALEINVOICE_CHECK";

	/**
	 * 线上支付销售操作接口:付款
	 */
	public static final String OPERATION_CODE_SALE_OP = "O_SALE_OP";

	/**
	 * 线上支付销售操作接口:废弃
	 */
	public static final String OPERATION_CODE_SALE_OP_OUT = "O_SALE_OP_OUT";

	/**
	 * 线上支付销售操作接口:自提
	 */
	public static final String OPERATION_CODE_SALE_OP_AUDIT = "O_SALE_OP_AUDIT";

	/**
	 * 线上支付销售操作接口:共享发货
	 */
	public static final String OPERATION_CODE_SALE_OP_SHARE = "O_SALE_OP_SHARE";

	/**
	 * 线上支付销售操作接口:发货
	 */
	public static final String OPERATION_CODE_SALE_OP_SEND = "O_SALE_OP_SEND";

	/**
	 * 线上礼品兑换接口:审核
	 */
	public static final String OPERATION_CODE_HS_AUDIT = "O_HS_AUDIT";

	/**
	 * 线上礼品兑换接口:作废
	 */
	public static final String OPERATION_CODE_HS_ABANDOND = "O_HS_ABANDOND";

	/**
	 * 销售退回操作接口:退回修改
	 */
	public static final String OPERATION_CODE_XSTH = "O_SALERETURN";

	/**
	 * 销售退回操作代码:废弃
	 */
	public static final String OPERATION_CODE_XSFQ = "O_SALEOUT";

	/**
	 * 共享销售退回操作代码:废弃
	 */
	public static final String OPERATION_CODE_GXXSFQ = "O_SHARE_SALE_ABANDONED";

	/**
	 * 流程：发货
	 **/
	public static final String FB_SHIPMENTS = "FB_SHIPMENTS";

	/**
	 * 流程：收货
	 **/
	public static final String FB_TD = "FB_TD";

	

	/**
	 * 计算方式:枚举
	 */
	public static final Integer CALC_TYPE_ENUM = 0;

	/**
	 * 计算方式:系数
	 */
	public static final Integer CALC_TYPE_COEF = 1;

	/**
	 * 计算方式:公式
	 */
	public static final Integer CALC_TYPE_FORMULA = 2;

	/**
	 * 流程状态:运行
	 */
	public static final Integer FLOW_STATUS_RUN = 1;

	/**
	 * 流程状态:结束
	 */
	public static final Integer FLOW_STATUS_FINISHED = 2;

	/**
	 * 流程状态:终止
	 */
	public static final Integer FLOW_STATUS_TERMINATION = 3;

	/**
	 * 事务状态:运行
	 */
	public static final Integer WORK_STATUS_RUN = 1;

	/**
	 * 事务状态:结束
	 */
	public static final Integer WORK_STATUS_FINISHED = 2;

	/**
	 * 事务状态:终止
	 */
	public static final Integer WORK_STATUS_TERMINATION = 3;

	public static final Integer DATA_RIGHTS_RULE_TREE = 4;

	/**
	 * 挂账生成方式:手动
	 */
	public static final Integer GENERATE_TYPE_MANUAL = 0;

	/**
	 * 挂账生成方式:自动
	 */
	// liupg 2011-11-17:把所有财务单据都统一放在一个页面处理
	// liupg 2011-12-14:区分手动与自动查询
	public static final Integer GENERATE_TYPE_AUTO = 1;

	/**
	 * 物流
	 */
	public static final Integer FLOW_CLASS_OBJECT = 0;

	/**
	 * 钱流
	 */
	public static final Integer FLOW_CLASS_MONEY = 1;

	/**
	 * 现金
	 */
	public static final Integer PAYMENT_CASH = 1001;

	/**
	 * 刷卡
	 */
	public static final Integer PAYMENT_CARD = 1002;

	/**
	 * 部分刷卡
	 */
	public static final Integer PAYMENT_CASH_CARD = 1003;

	/**
	 * 线上支付
	 */
	public static final Integer PAYMENT_ONLLINE = 4;
	
	/**
	 * 微支付
	 */
	//public static final Integer PAYMENT_MINI = 888;

	/**
	 * 白班
	 */
	public static final Integer SHIFT_DAY = 1;

	/**
	 * 晚班
	 */
	public static final Integer SHIFT_NIGHT = 2;

	/**
	 * 库存类型 0:表示系统库存
	 */

	public static final Integer STOCK_TYPE_SYS = 0;

	// /**
	// * 库存类型 1:表示BG库存
	// */
	// public static final Integer STOCK_TYPE_BG = 1;
	/**
	 * 库存类型 1:锁定库存
	 */
	public static final Integer STOCK_TYPE_LOCKOUT = 1;

	/**
	 * 库存类型 2:表示已配库存
	 */
	public static final Integer STOCK_TYPE_ALLOCATED = 2;

	/**
	 * 库存类型 3:表示预留库存
	 */
	public static final Integer STOCK_TYPE_OBLIGATE = 3;

	/**
	 * 表类型：TABLE_TYPE 0:表示销售表 1:表示其它单据表 2:表示财务单据表 3:表示网上商城单据表
	 */
	public static final Integer TABLE_TYPE_SALE = 0;

	public static final Integer TABLE_TYPE_OTHER = 1;

	public static final Integer TABLE_TYPE_FINANCE = 2;

	public static final Integer TABLE_TYPE_ESHOP = 3;

	public static final Integer NODE_INIT_MODULE = 0;

	public static final Integer NODE_INIT_ADMIN = 1;

	public static final Integer NODE_INIT_BUSGROUP = 2;

	public static final Integer NODE_INIT_WORKFLOW = 3;

	public static final Integer NODE_INIT_WAREHOUSE = 4;

	public static final Integer NODE_INIT_CHARGETYPE = 5;

	public static final Integer NODE_INIT_DEFAULTACCOUNT = 6;

	public static final Integer NODE_INIT_TASKCONFIG = 7;

	public static final Integer NODE_INIT_RIGHSTS = 8;

	public static final Integer SEX_FMALES = 0;

	/**
	 * 直营
	 */
	public static final Integer SHOP_TYPE_RETAIL = 1;

	/**
	 * 联营
	 */
	public static final Integer SHOP_TYPE_UNION = 3;

	/**
	 * 加盟
	 */
	public static final Integer SHOP_TYPE_JOIN = 2;

	/**
	 * 铺货
	 */
	public static final Integer BUSINESS_CLASS_SHOP = 10;

	/**
	 * 半成品
	 */
	public static final Integer BUSINESS_CLASS_FINISH_PRODUCT = 27;

	/**
	 * 成品
	 */
	public static final Integer BUSINESS_CLASS_SEMIFINISH_PRODUCT = 28;

	/**
	 * 补货
	 */
	public static final Integer BUSINESS_CLASS_REP = 1;

	/**
	 * 代金券
	 */
	public static final Integer RULE_TYPE_DJ = 0;

	/**
	 * 折扣券
	 */
	public static final Integer RULE_TYPE_ZK = 1;

	/**
	 * 赠送现金
	 */
	public static final Integer RULE_TYPE_ZSXJ = 2;

	/**
	 * 赠送礼品
	 */
	public static final Integer RULE_TYPE_ZSLP = 3;

	/**
	 * 限时折扣
	 */
	public static final Integer RULE_TYPE_XSZK = 4;
	/**
	 * 红包活动
	 */
	public static final Integer RULE_TYPE_HB = 8;

	/**
	 * 礼券使用状态 使用
	 */
	public static final Integer GIFT_STATUS_USED = 2;

	/**
	 * 礼券使用状态 已发送
	 */
	public static final Integer GIFT_STATUS_SEND = 1;

	/**
	 * 礼券使用状态 未使用
	 */
	public static final Integer GIFT_STATUS_UNUSE = 0;

	/**
	 * 是否影响库存 不影响
	 */
	public static final Integer CHANGE_STOCK_FLAG_UN_EFFECT = 1;

	/**
	 * 是否影响库存 正影响
	 */
	public static final Integer CHANGE_STOCK_FLAG_POSITIVE_EFFECT = 2;

	/**
	 * 是否影响库存 负影响
	 */
	public static final Integer CHANGE_STOCK_FLAG_NEGATIVE_EFFECT = 3;

	/**
	 * 短信模板类型-生日提醒
	 */
	public static final Integer MSG_TEMPLATE_TYPE_BIRTHDAY = 2;

	/**
	 * 短信模板类型-微信会员卡绑定
	 */
	public static final Integer MSG_TEMPLATE_TYPE_BLIND = 20;

	/**
	 * 短信模板类型-注册提醒
	 */
	public static final Integer MSG_TEMPLATE_TYPE_REGISTER = 1;

	/**
	 * 短信模板类型-优惠券提示
	 */
	public static final Integer MSG_TEMPLATE_TYPE_COUPON = 3;

	/**
	 * 短信模板类型-会员密码提示
	 */
	public static final Integer MSG_TEMPLATE_TYPE_PASSWORD = 4;

	/**
	 * 短信模板类型-VIP到期提示
	 */
	public static final Integer MSG_TEMPLATE_TYPE_EXPIRE = 5;

	/**
	 * 短信模板类型-普通会员晋升VIP提示
	 */
	public static final Integer MSG_TEMPLATE_TYPE_PROMOTE = 6;

	/**
	 * 短信平台-意美
	 */
	public static final Integer MCM_SMS_CENTER_YIEMI = 1;

	/**
	 * 短信平台-意美
	 */
	public static final Integer MCM_SMS_CENTER_SHANGYI = 2;

	/**
	 * 促销类型-网上商城
	 */
	public static final Integer USABLE_RANGE_BCM = 2;

	/**
	 * 促销类型-线下店铺
	 */
	public static final Integer USABLE_RANGE_SHOP = 1;

	/**
	 * 促销类型-适合商城和店铺
	 */
	public static final Integer USABLE_RANGE_BOTH = 3;

	/**
	 * 盘点方式-部分盘点
	 */
	public static final Integer INVENTORY_TYPE_PART = 1;

	/**
	 * 盘点方式-全部盘点
	 */
	public static final Integer INVENTORY_TYPE_ALL = 2;

	/**
	 * 单据预警-未处理
	 */
	public static final Integer INVOICE_CHECK_UNTREATED = 1;

	/**
	 * 单据预警-已处理
	 */
	public static final Integer INVOICE_CHECK_PROCESS = 2;

	/**
	 * 款式联想方式-组件方式
	 */
	public static final Integer STYLE_ASSOCIATION_COMPONENT = 1;

	/**
	 * 款式联想方式-输入框方式
	 */
	public static final Integer STYLE_ASSOCIATION_INPUTTEXT = 2;

	/**
	 * 流程代码 -付款确认
	 */
	public static final String FLOW_CODE_PAYMENT = "FF_PC";

	/**
	 * 短信开关 - 会员
	 */
	public static final Integer isMemberFlag = 1;

	/**
	 * 短信开关 - 卡
	 */
	public static final Integer isCardFlag = 2;

	/**
	 * 配货单是否显示自动配货数据 1显示 默认不显示0
	 */
	public static final Integer IS_AUTO_DISTRIBUTION = 1;

	/**
	 * 节点属性36-买单金额格式化设置,四舍五入方式
	 */
	public static final Integer BGN_ATTR_36_UP = 1;

	/**
	 * 节点属性36-买单金额格式化设置,向上取舍
	 */
	public static final Integer BGN_ATTR_36_ALL_UP = 3;

	/**
	 * 节点属性36-买单金额格式化设置，直接舍去方式
	 */
	public static final Integer BGN_ATTR_36_DOWN = 2;

	/**
	 * 仓库所属业务类型 供货
	 */
	public static final Integer WAREHOUSE_BIZ_TYPE_GH = 1;

	/**
	 * 仓库所属业务类型 退货
	 */
	public static final Integer WAREHOUSE_BIZ_TYPE_TH = 2;

	/**
	 * 仓库所属业务类型 入库
	 */
	public static final Integer WAREHOUSE_BIZ_TYPE_RK = 3;

	/**
	 * 模块功能功能类型：0(普通功能)
	 */
	public static final Integer SYS_FUNCTION_TYPE_0 = 0;

	/**
	 * 模块功能功能类型：1(模板功能)
	 */
	public static final Integer SYS_FUNCTION_TYPE_1 = 1;

	/**
	 * 特殊操作无
	 */
	public static final Integer SPECIAL_OPERATION_NONE = 0;

	/**
	 * 补配货建议 业务类型 1补货 ，2为配货
	 */
	public static final Integer REPLENISHMENT_TYPE = 1;

	public static final Integer DISTRIBUTION_TYPE = 2;

	public static final Integer COMPANYRETURN_TYPE = 3;

	/**
	 * 补常量定义
	 */
	/**
	 * //当前库存(CS)、
	 */
	public static final String CS = "S1";

	/**
	 * //近N天销售(NS)、
	 */
	public static final String NS = "S2";

	/**
	 * //近N天入库(SI)
	 */
	public static final String SI = "S3";

	/**
	 * //供货在途(ST)
	 */
	public static final String ST = "S4";

	// 配货
	/**
	 * //店铺近N天销售(SNS)
	 */
	public static final String SNS = "P4";

	/**
	 * //打包未发货(PNS)
	 */
	public static final String PNS = "P5";

	/**
	 * //自动配货(ADS) 、
	 */
	public static final String ADS = "P6";

	/**
	 * //可配库存(AS)、
	 */
	public static final String AS = "P1";

	/**
	 * //店铺库存(SS)
	 */
	public static final String SS = "P2";

	/**
	 * //近N天供货(NNS))、
	 */
	public static final String NNS = "P3";

	/**
	 * 退货率
	 */
	public static final String RR = "G1";

	/***
	 * 活动状态
	 */
	/**
	 * 活动未发布
	 */
	public static final Integer ACTIVE_STATE_0 = 0;

	/**
	 * 活动已经发布
	 */
	public static final Integer ACTIVE_STATE_1 = 1;

	/**
	 * 活动已经结束
	 */
	public static final Integer ACTIVE_STATE_2 = 2;

	/**
	 * 优惠内容-减现金
	 */
	public static final Integer PREFERENTIAL_CONTENT_1 = 1;

	/**
	 * 优惠内容-送礼品
	 */
	public static final Integer PREFERENTIAL_CONTENT_2 = 2;

	/**
	 * 优惠内容-送积分
	 */
	public static final Integer PREFERENTIAL_CONTENT_3 = 3;

	/**
	 * 优惠内容-送优惠券
	 */
	public static final Integer PREFERENTIAL_CONTENT_4 = 4;

	/**
	 * 优惠内容-送折扣
	 */
	public static final Integer PREFERENTIAL_CONTENT_5 = 5;

	/**
	 * 优惠内容-送折扣券
	 */
	public static final Integer PREFERENTIAL_CONTENT_6 = 6;

	/**
	 * 优惠内容-免单
	 */
	public static final Integer PREFERENTIAL_CONTENT_7 = 7;

	/**
	 * 优惠内容-免单券
	 */
	public static final Integer PREFERENTIAL_CONTENT_8 = 8;

	/**
	 * 优惠内容-免单套券
	 */
	public static final Integer PREFERENTIAL_CONTENT_9 = 9;
	
	/**
	 * 优惠内容-半价套券
	 */
	public static final Integer PREFERENTIAL_CONTENT_10 = 10;
	
	/**
	 * 优惠内容-赠送红包
	 */
	public static final Integer PREFERENTIAL_CONTENT_11 = 11;

	/****** 商城订单线下状态start ******/
	/**
	 * 等待买家付款
	 */
	public static int WAIT_BUYER_PAY = 1;

	/**
	 * 等待卖家发货
	 */
	public static int WAIT_SELLER_SEND_GOODS = 2;

	/**
	 * 等待买家确认收货
	 */
	public static int WAIT_BUYER_CONFIRM_GOODS = 3;

	/**
	 * 买家已签收
	 */
	public static int TRADE_BUYER_SIGNED = 4;

	/**
	 * 交易成功
	 */
	public static int TRADE_FINISHED = 5;

	/**
	 * 没有创建支付宝交易
	 */
	public static int TRADE_NO_CREATE_PAY = 6;

	/**
	 * 付款以后用户退款成功
	 */
	public static int TRADE_CLOSED = 7;

	/**
	 * 卖家或买家主动关闭交易
	 */
	public static int TRADE_CLOSED_BY_TAOBAO = 8;

	/****** 商城订单线下状态end ******/

	/****** 订单导入进销存时处理标记start ******/
	/**
	 * 待导入
	 */
	public static int WAIT_IMPORT = 1;

	/**
	 * 导入成功
	 */
	public static int IMPORT_SUCCESS = 2;

	/**
	 * 导入失败
	 */
	public static int IMPORT_FAILURE = 3;

	/**
	 * 待上传
	 */
	public static final int WAIT_UPLOAD = 4;

	/**
	 * 上传成功
	 */
	public static final int UPLOAD_SUCCESS = 5;

	/**
	 * 上传失败
	 */
	public static final int UPLOAD_FAILURE = 6;

	/****** 订单导入进销存时处理标记end ******/

	/**
	 * 单据类型：淘宝订单
	 */
	public static final Integer INVOICETYPE_TOP = 35;

	/**
	 * 单据类型：电商销售单
	 */
	public static final Integer INVOICETYPE_DSXS = 37;

	/**** 商城退款状态 start ****/
	/**
	 * 没有退款
	 */
	public static final String NO_REFUND = "NO_REFUND";

	/**
	 * 买家已经申请退款，等待卖家同意
	 */
	public static final String WAIT_SELLER_AGREE = "WAIT_SELLER_AGREE";

	/**
	 * 卖家已经同意退款，等待买家退货
	 */
	public static final String WAIT_BUYER_RETURN_GOODS = "WAIT_BUYER_RETURN_GOODS";

	/**
	 * 买家已经退货，等待卖家确认收货
	 */
	public static final String WAIT_SELLER_CONFIRM_GOODS = "WAIT_SELLER_CONFIRM_GOODS";

	/**
	 * 卖家拒绝退款
	 */
	public static final String SELLER_REFUSE_BUYER = "SELLER_REFUSE_BUYER";

	/**
	 * 退款关闭
	 */
	public static final String CLOSED = "CLOSED";

	/**
	 * 退款成功
	 */
	public static final String SUCCESS = "SUCCESS";

	/**** 商城退款状态 end ****/
	/**** 商城订单数据来源start ****/
	// /**
	// * 自建商城
	// */
	// public static final Integer PERSONAL = 1;

	//
	// /**
	// * 拍拍
	// */
	// public static final Integer PAIPAI = 3;

	/**** 商城订单数据来源end ****/
	/**
	 * 初始化对方事务类型：创建新事务
	 */
	public static final Integer NEW_TRANSACTION = 1;

	/**
	 * 初始化对方事务类型：触发对方事务实例并往下进行
	 */
	public static final Integer TRANSACTION_NEXT_OPERATION = 2;

	/**
	 * 店铺共享销售流程环节：等待中
	 */
	public static final String LINK_DEF_DDZ = "L_SSW";

	/**
	 * 店铺共享销售流程环节：到货确认
	 */
	public static final String LINK_DEF_DHQR = "L_SSAG";

	/**
	 * 共享销售操作代码:到货确认
	 */
	public static final String OPERATION_CODE_DHQR = "O_SHARE_SALE_CONFIRM";

	/**
	 * 共享销售操作代码:等待中-退回修改
	 */
	public static final String OPERATION_CODE_DDZXG = "O_SHARE_SALE_WG_RETURN_MODIFY";

	/**
	 * 共享销售操作代码:废弃-等待中
	 */
	public static final String OPERATION_CODE_DDZFQ = "O_SHARE_SALE_WG_ABANDONED";

	/**
	 * 共享调货操作代码:审核发货
	 */
	public static final String OPERATION_CODE_SHFH = "O_SHARE_CALLOUT_AUDIT";

	/**
	 * 共享调货操作代码:退回修改
	 */
	public static final String OPERATION_CODE_GTTHXG = "O_SHARE_CALL_RETURN";

	/**
	 * 普通会员默认密码：123
	 */
	public static final String MEMBER_PASSWORD = "202cb962ac59075b964b07152d234b70";

	/**
	 * 普通会员类型
	 */
	public static final Integer MEMBER_TYPE = 3;

	/**
	 * 赠送积分配置-对应资料项 基本信息
	 */
	public static final Integer MEMBER_INTEGRITY_ITEM_BASE = 1;

	/**
	 * 赠送积分配置-对应资料项 显示信息
	 */
	public static final Integer MEMBER_INTEGRITY_ITEM_SHOW = 2;

	/**
	 * 赠送积分配置-对应资料项 身材资料
	 */
	public static final Integer MEMBER_INTEGRITY_ITEM_BODY = 3;

	/**
	 * 赠送积分配置-对应资料项 其他资料
	 */
	public static final Integer MEMBER_INTEGRITY_ITEM_OTHER = 4;

	/**
	 * 赠送积分配置-商品评论
	 */
	public static final Integer MEMBER_INTEGRITY_PRODUCT_COMMENT = 5;

	/**
	 * 赠送积分配置-店铺评论
	 */
	public static final Integer MEMBER_INTEGRITY_NODE_COMMENT = 6;

	/**
	 * 赠送积分配置-店铺签到-送积分
	 */
	public static final Integer MEMBER_INTEGRITY_CHECK_IN_I = 7;

	/**
	 * 赠送积分配置-店铺签到-送礼券
	 */
	public static final Integer MEMBER_INTEGRITY_CHECK_IN_G = 8;

	/**
	 * 赠送积分配置-推荐朋友-送积分
	 */
	public static final Integer MEMBER_INTEGRITY_RECOMMEND = 9;

	/**
	 * 赠送积分配置-注册-送积分
	 */
	public static final Integer MEMBER_INTEGRITY_REGISTER = 10;

	/**
	 * 赠送积分配置-认证-送积分
	 */
	public static final Integer MEMBER_INTEGRITY_CHECK = 11;

	/**
	 * 赠送积分配置-微信每日签到-送积分
	 */
	public static final Integer MEMBER_INTEGRITY_WEIXIN_SING = 22;

	/**
	 * 赠送积分配置-微信抽奖-送积分
	 */
	public static final Integer MEMBER_INTEGRITY_WEIXIN_CHIC = 13;
	
	/**
	 * 赠送积分配置-商城点赞
	 */
	public static final Integer MEMBER_INTEGRITY_MALL_LIKE = 14;
	
	/**
	 * 赠送积分配置-商城分享
	 */
	public static final Integer MEMBER_INTEGRITY_MALL_SHARE = 15;

	/**
	 * 商品评分-好评
	 */
	public static final Integer MMM_RATING_PRO_LEVEL_H = 1;

	/**
	 * 商品评分-中评
	 */
	public static final Integer MMM_RATING_PRO_LEVEL_Z = 2;

	/**
	 * 商品评分-差评
	 */
	public static final Integer MMM_RATING_PRO_LEVEL_C = 3;

	/**
	 * 店铺评分-一颗星
	 */
	public static final Integer MMM_RATING_NODE_LEVEL_ONE = 1;

	/**
	 * 店铺评分-二颗星
	 */
	public static final Integer MMM_RATING_NODE_LEVEL_TWO = 2;

	/**
	 * 店铺评分-三颗星
	 */
	public static final Integer MMM_RATING_NODE_LEVEL_THREE = 3;

	/**
	 * 店铺评分-四颗星
	 */
	public static final Integer MMM_RATING_NODE_LEVEL_FOUR = 4;

	/**
	 * 店铺评分-五颗星
	 */
	public static final Integer MMM_RATING_NODE_LEVEL_FIVE = 5;

	/**
	 * 评论标记-待审核
	 */
	public static final Integer MMM_COMMENT_FLAG_D = 1;

	/**
	 * 评论标记-审核通过
	 */
	public static final Integer MMM_COMMENT_FLAG_P = 2;

	/**
	 * 评论标记-审核不通过
	 */
	public static final Integer MMM_COMMENT_FLAG_N = 3;
	
	
	/**
	 * 咨询状态 未处理
	 */
	public static final Integer CONSULTATION_STATUS_D = 1;

	/**
	 * 咨询状态 已解决
	 */
	public static final Integer CONSULTATION_STATUS_P = 2;

	/**
	 * 咨询状态 未解决
	 */
	public static final Integer CONSULTATION_STATUS_N = 3;

	/**
	 * vip会员类型
	 */
	public static final Integer MEMBER_VIP = 1;

	/**
	 * 铺货状态
	 */
	public static final String DISTRIBUTION_STATE = "DISTRIBUTION_STATE";

	/**
	 * 单据类型：退货申请单（电商）
	 */
	public static final Integer INVOICETYPE_DSRR = 39;

	/**
	 * 单据类型：退货单（电商）
	 */
	public static final Integer INVOICETYPE_DSR = 40;

	/**
	 * 单据类型：退货作业单（电商）
	 */
	public static final Integer INVOICETYPE_DSRJ = 41;

	/**
	 * 退货到货确认操作代码: 确认收货
	 */
	public static final String O_RETURNGOODS_CONFIRM = "O_RETURNGOODS_CONFIRM";

	/**
	 * 铺货状态:未开始
	 */
	public static final Integer DISTRIBUTION_STATE_NOT = 0;

	/**
	 * 铺货状态:已开始
	 */
	public static final Integer DISTRIBUTION_STATE_RUN = 1;

	/**
	 * 铺货状态:已结束
	 */
	public static final Integer DISTRIBUTION_STATE_FINISHED = 2;

	/**
	 * 
	 * 节点类型
	 * 
	 */

	/**
	 * 直营
	 */
	public static final Integer NODETYPE_CHAIN_STORE = 1;

	/**
	 * 加盟
	 */
	public static final Integer NODETYPE_FRANCHISEE = 2;

	/**
	 * 联营
	 */
	public static final Integer NODETYPE_JOINT_VENTURE = 3;

	/**
	 * 公司
	 */
	public static final Integer NODETYPE_COMPANY = 4;

	/**
	 * 优惠券获取方式-下载
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_DOWNLOAD = 1;

	/**
	 * 优惠券获取方式-赠送
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_GIVE = 2;

	/**
	 * 优惠券获取方式-兑换
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_EXCHANGE = 3;

	/**
	 * 优惠券获取方式-推荐有礼
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_RECOMMEND = 4;

	/**
	 * 优惠券获取方式-积分商城
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_INTEGRAL = 5;

	/**
	 * 优惠券获取方式-签到送10元代金券
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_CHECK_FRIST = 50;

	/**
	 * 优惠券获取方式-签到送20元代金券
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_CHECK_SECOND = 51;

	/**
	 * 优惠券获取方式-签到送30元代金券
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_CHECK_THRID = 52;

	/**
	 * 优惠券获取方式-会员换卡送优惠券
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_CHANGE_CARD = 60;

	/**
	 * 促销获取方式 登录抽奖
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_LOGIN_LOTTERY = 88;

	/**
	 * 促销获取方式 首页抽奖
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_HEADER_LOTTERY = 102;

	/**
	 * 优惠券获取方式 O2OMID
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_O2OMID = 100;

	/**
	 * 优惠券获取方式- 手动派发券
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_MANUAL = 101;

	/**
	 * 优惠券获取方式- 买单送套券
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_SET_CERT = 105;
	/**
	 * 优惠券获取方式- 买单半价券
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_ORDER_HALF = 106;
	
	/**
	 * 优惠券获取方式- 员工内购券
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_USER_PROMOTION = 107;
	
	/**
	 * 优惠券获取方式- 砍价活动
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_STYLE_BRAGAIN = 111;
	
	/**
	 * 优惠券获取方式- 转发二维码
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_SNS_QRCODE = 90026;
	/**
	 * 微信活动投票
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_VOTES_QRCODE = 91026;

	/**
	 * 会员类型-VIP
	 */
	public static final Integer VIP_MEMBER_TYPE = 1;

	/**
	 * 审批流
	 */
	public static final Integer FLOW_CLASS_AUDIT = 2;

	/**
	 * 买一送一
	 */
	public static final Integer RULE_TYPE_MYSY = 5;
	
	/**
	 * 积分兑换
	 */
	public static final Integer RULE_TYPE_JFDH = 7;

	/**
	 * 赠送礼品方式
	 */
	public static final String GIFTS_WAY = "GIFTS_WAY";

	/**
	 * 单据状态:等待审批
	 */
	public static final Integer INVOICE_STATUS_DA = 13;

	/**
	 * 
	 * 条件规则: (数据权限)用于检索数据的条件定义，1-节点、2-仓库、3-款式、4-金额
	 */
	public static final Integer SYS_CONDITION_TYPE_NODE = 1;

	/**
	 * 
	 * 条件规则: (数据权限)用于检索数据的条件定义，1-节点、2-仓库、3-款式、4-金额
	 */
	public static final Integer SYS_CONDITION_TYPE_WAREHOSE = 2;

	/**
	 * 
	 * 条件规则: (数据权限)用于检索数据的条件定义，1-节点、2-仓库、3-款式、4-金额
	 */
	public static final Integer SYS_CONDITION_TYPE_STYLE = 3;

	/**
	 * 
	 * 条件规则: (数据权限)用于检索数据的条件定义，1-节点、2-仓库、3-款式、4-金额
	 */
	public static final Integer SYS_CONDITION_TYPE_MONEY = 4;

	/**
	 * 数据权限规则枚举类
	 */
	public static final Integer DATA_RIGHTS_RULE_ENUM = 1;

	/**
	 * 是否提交差异单到公司 1：表示要提交
	 */
	public static final Integer BGN_ATTR_19_YES = 1;

	/**
	 * 是否提交差异单到公司 0：表示不提交
	 */
	public static final Integer BGN_ATTR_19_NO = 0;

	/**
	 * 调出拒收退回确认
	 */
	public static final String CALLOUT_REFUSE_CONFIRM = "callout_refuse_confirm";

	/**
	 * 商城收藏 商品收藏
	 */
	public static final Integer BCM_COLLECT_TYPE_STYLE = 1;

	/**
	 * 商城收藏 店铺收藏
	 */
	public static final Integer BCM_COLLECT_TYPE_SHOP = 2;

	/**
	 * 商城单据状态-购物车
	 */
	public static final Integer BCM_INVOICESTATUS_BC = 0;

	/**
	 * 商城单据状态-未付款
	 */
	public static final Integer BCM_INVOICESTATUS_UP = 1;

	/**
	 * 商城单据状态-已付款
	 */
	public static final Integer BCM_INVOICESTATUS_AP = 2;

	/**
	 * 商城单据状态-退款
	 */
	public static final Integer BCM_INVOICESTATUS_UR = 3;

	/**
	 * 商城单据状态-已退款
	 */
	public static final Integer BCM_INVOICESTATUS_AR = 4;

	/**
	 * 商城单据状态-已发货
	 */
	public static final Integer BCM_INVOICESTATUS_AS = 5;

	/**
	 * 商城单据状态-退货
	 */
	public static final Integer BCM_INVOICESTATUS_UG = 6;

	/**
	 * 商城单据状态-已退货
	 */
	public static final Integer BCM_INVOICESTATUS_AG = 7;

	/**
	 * 商城单据状态-取消
	 */
	public static final Integer BCM_INVOICESTATUS_CL = 8;

	/**
	 * 商城单据状态-关闭
	 */
	public static final Integer BCM_INVOICESTATUS_CO = 9;

	/**
	 * 商城单据状态-成功
	 */
	public static final Integer BCM_INVOICESTATUS_SC = 10;

	/**
	 * 商城单据状态-已付现
	 */
	public static final Integer BCM_INVOICESTATUS_PC = 11;

	/**
	 * 商城单据状态-已付现，已提现
	 */
	public static final Integer BCM_INVOICESTATUS_PG = 12;

	/**
	 * 会员来源-APP
	 */
	public static final Integer MMM_MEMBER_SOURCE_APP = 1;

	/**
	 * 会员来源-线下分销
	 */
	public static final Integer MMM_MEMBER_SOURCE_IEAP = 2;

	/**
	 * 会员来源-pc网上商城
	 */
	public static final Integer MMM_MEMBER_SOURCE_OL = 3;

	/**
	 * 会员来源-wap网上商城
	 */
	public static final Integer MMM_MEMBER_SOURCE_WOL = 4;

	/**
	 * 会员来源-微信
	 */
	public static final Integer MMM_MEMBER_SOURCE_WEIXIN = 5;

	/**
	 * 会员来源-淘宝
	 */
	public static final Integer MMM_MEMBER_SOURCE_TAOBAO = 6;

	/**
	 * 会员来源-淘宝o2o
	 */
	public static final Integer MMM_MEMBER_SOURCE_TAOBAO_O2O = 7;

	/**
	 * 会员来源-京东
	 */
	public static final Integer MMM_MEMBER_SOURCE_JD = 8;
	/**
	 * 会员来源-开单
	 */
	public static final Integer MMM_MEMBER_SOURCE_KORDER = 9;
	
	/**
	 * 会员来源-开单
	 */
	public static final Integer MMM_MEMBER_SOURCE_WEIXIN_H5= 10;
	
	/**
	 * 会员来源-开单
	 */
	public static final Integer MMM_MEMBER_SOURCE_WEIXIN_MALL_H5= 11;

	/**
	 * 网上商城品类-新品上市
	 */
	public static final Integer BCM_NEW_PRODUCT = 800000000;

	/**
	 * 网上商城品类-限时折扣
	 */
	public static final Integer BCM_X_S_Z_K = 900000000;

	/**
	 * 金苑鞋类
	 */
	public static final Integer JINYUAN_SHOES = 1200000000;

	/**
	 * 流程：供货工作流
	 **/
	public static final String FB_DIST = "FB_DIST";

	/**
	 * 金苑鞋类补货
	 */
	public static final Integer JINYUAN_SHOES_BUSINESSCLASS = 7;

	/**
	 * 款式唯一码属性：使用唯一码：1
	 */
	public static final Integer BGN_ATTR_7_1 = 1;

	/**
	 * app推荐好友的短信模板类型
	 */
	public static final Integer MCM_TEMPLATE_APP_RECOMMAND_MSG = 7;

	/**
	 * 积分兑换礼品的验证码短信类型
	 */
	public static final Integer MCM_TEMPLATE_MEMBER_EXCHANGE_GIFT = 8;

	/**
	 * 商城支付方式-货到付款
	 */
	public static final Integer BCM_PAYMENT_CONFIG_COD = 4;

	/**
	 * 商城支付方式-邮局汇款
	 */
	public static final Integer BCM_PAYMENT_CONFIG_POST = 5;

	/**
	 * 商城支付方式-自提
	 */
	public static final Integer BCM_PAYMENT_CONFIG_TAKE = 6;

	/**
	 * 商城支付方式-在线支付
	 */
	public static final Integer BCM_PAYMENT_CONFIG_ONLINE = 7;

	/**
	 * 商城支付方式-公司转帐
	 */
	public static final Integer BCM_PAYMENT_CONFIG_COMPANY = 8;
	/**
	 * 商城支付方式-银行转帐
	 */
	public static final Integer BCM_PAYMENT_CONFIG_VISA = 9;
	/**
	 * 商城支付方式-积分支付
	 */
	public static final Integer BCM_PAYMENT_CONFIG_INTEGRAL = 10;

	/**
	 * 商城支付方式-红包支付
	 */
	public static final Integer BCM_PAYMENT_CONFIG_RED = 11;

	/**
	 * 商城支付方式-他人代付
	 */
	public static final Integer BCM_PAYMENT_CONFIG_OTHER = 12;
	
	/**
	 * 商城支付方式-抽奖礼品
	 */
	public static final Integer BCM_PAYMENT_CONFIG_LOTTERY = 13;

	public static final String PATH = "";

	/**
	 * 积分类型-app评论产品送积分
	 */
	public static final Integer INTEGRAL_TYPE_1 = 1;

	/**
	 * 积分类型-app评论店铺送积分
	 */
	public static final Integer INTEGRAL_TYPE_2 = 2;

	/**
	 * 积分类型-开通会员送积分
	 */
	public static final Integer INTEGRAL_TYPE_3 = 3;

	/**
	 * 积分类型-线下零售
	 */
	public static final Integer INTEGRAL_TYPE_4 = 4;

	/**
	 * 积分类型-WEB手机商城销售
	 */
	public static final Integer INTEGRAL_TYPE_5 = 5;

	/**
	 * 积分类型-WEBPC销售
	 */
	public static final Integer INTEGRAL_TYPE_6 = 6;

	/**
	 * 积分类型-App签到活动
	 */
	public static final Integer INTEGRAL_TYPE_7 = 7;

	/**
	 * 积分类型-促销活动送积分（满送）
	 */
	public static final Integer INTEGRAL_TYPE_8 = 8;

	/**
	 * 积分类型-退货
	 */
	public static final Integer INTEGRAL_TYPE_9 = 9;

	/**
	 * 积分类型-积分兑换礼品 (积分兑换礼品和优惠券兑换不退换货)
	 */
	public static final Integer INTEGRAL_TYPE_10 = 10;

	/**
	 * 积分类型-积分兑换优惠券 (积分兑换礼品和优惠券兑换不退换货)
	 */
	public static final Integer INTEGRAL_TYPE_11 = 11;

	/**
	 * 积分类型-积分手工录入
	 */
	public static final Integer INTEGRAL_TYPE_12 = 12;

	/**
	 * 积分类型-积分消费手工录入
	 */
	public static final Integer INTEGRAL_TYPE_13 = 13;

	/**
	 * 积分类型-会员生日送积分
	 */
	public static final Integer INTEGRAL_TYPE_14 = 14;

	/**
	 * 积分类型-推荐会员送积分
	 */
	public static final Integer INTEGRAL_TYPE_15 = 15;

	/**
	 * 积分类型-微信认证会员送积分
	 */
	public static final Integer INTEGRAL_TYPE_16 = 16;

	/**
	 * 积分类型-促销活动送积分（抽奖）
	 */
	public static final Integer INTEGRAL_TYPE_17 = 17;

	/**
	 * 积分类型-淘宝商城销售
	 */
	public static final Integer INTEGRAL_TYPE_18 = 18;

	/**
	 * 积分类型-APP商城消费积分
	 */
	public static final Integer INTEGRAL_TYPE_19 = 19;
	
	
	/**
	 * 积分类型-会员资料完善
	 */
	public static final Integer INTEGRAL_TYPE_20 = 20;
	
	/**
	 * 积分类型-码分享
	 */
	public static final Integer INTEGRAL_TYPE_21 = 21;
	
	/**
	 * 积分类型-微信签到送积分
	 */
	public static final Integer INTEGRAL_TYPE_22 = 22;

	

	/**
	 * 积分类型-其他
	 */
	public static final Integer INTEGRAL_TYPE_99 = 99;

	/**
	 * 消息等级1-重要
	 */
	public static final Integer MSG_GRADE_ONE = 1;

	/**
	 * 消息等级2-需要
	 */
	public static final Integer MSG_GRADE_TWO = 2;

	/**
	 * 消息等级3-一般
	 */
	public static final Integer MSG_GRADE_THREE = 3;

	/**
	 * 消息等级4-系统消息，不针对会员，如app推送
	 */
	public static final Integer MSG_GRADE_FOUR = 4;

	/**
	 * 消息类型 货品变更
	 */
	public final static Integer MSG_TYPE_GOODS_CHANGE = 1;

	/**
	 * 消息类型 积分变更
	 */
	public final static Integer MSG_TYPE_INTEGER_CHANGE = 2;

	/**
	 * 消息类型 优惠券
	 */
	public final static Integer MSG_TYPE_GIFTCERTIFICATE = 3;

	/**
	 * 消息类型 会员变更
	 */
	public final static Integer MSG_TYPE_MEMBER_CHANGE = 4;

	/**
	 * 消息类型 营销
	 */
	public final static Integer MSG_TYPE_MARKETING = 5;

	/**
	 * 消息类型 好友消息
	 */
	public final static Integer MSG_TYPE_FRIEND_MSG = 6;

	/**
	 * 消息类型 推广
	 */
	public final static Integer MSG_TYPE_POPULARIZE = 7;

	/**
	 * 消息类型 催款
	 */
	public final static Integer MSG_TYPE_REMINDE = 9;

	/**
	 * 消息类型 OA任务提醒
	 */
	public final static Integer MSG_OA_TASK_REMIND = 10;

	/**
	 * 消息类型 微信活动
	 */
	public final static Integer MSG_TYPE_WEXIN_ACTIVITY = 11;
	/**
	 * 消息类型 短信验证码
	 */
	public final static Integer MSG_TYPE_DUAN_CHECK = 12;

	/**
	 * 商城标签类型 - 导航
	 */
	public static final Integer BCM_LINK_TAG_NAV = 1;

	/**
	 * 商城标签类型 - 分类
	 */
	public static final Integer BCM_LINK_TAG_CLASSFY = 2;

	/**
	 * 商城标签类型 - 分栏目
	 */
	public static final Integer BCM_LINK_TAG_CLOUMN = 3;

	/**
	 * 商城标签类型 - banner
	 */
	public static final Integer BCM_LINK_TAG_BANNER = 4;

	/**
	 * 商城标签类型 - 风格推荐
	 */
	public static final Integer BCM_LINK_TAG_RECOMMEND = 5;

	/**
	 * 商城标签类型 - 手机导航
	 */
	public static final Integer BCM_LINK_TAG_APP_NAV = 6;

	/**
	 * 商城标签类型 - 手机导航
	 */
	public static final Integer BCM_LINK_TAG_APP_CLOUMN = 7;

	/**
	 * 商城标签试用范围- pc范围
	 */
	public static final Integer BCM_LINK_TAG_RANGE_PC = 1;

	/**
	 * 商城标签试用范围- app
	 */
	public static final Integer BCM_LINK_TAG_RANGE_APP = 2;

	/**
	 * 分成模式-按会员归属
	 */
	public static final Integer PROFIT_MODE_MEMBER = 1;

	/**
	 * 分成模式-按引流者
	 */
	public static final Integer PROFIT_MODE_DRAIN = 2;

	/**
	 * 扫描类别 nid
	 */
	public static final String SCAN_TYPE_NID = "nid";

	/**
	 * 扫描类别 eid
	 */
	public static final String SCAN_TYPE_EID = "eid";

	/**
	 * 扫描类别 pid
	 */
	public static final String SCAN_TYPE_PID = "pid";

	/**
	 * 扫描类别 mid
	 */
	public static final String SCAN_TYPE_MID = "mid";

	/**
	 * 扫描类别 nid
	 */
	public static final String SCAN_TYPE_NID_NAME = "节点";

	/**
	 * 扫描类别 eid
	 */
	public static final String SCAN_TYPE_EID_NAME = "员工";

	/**
	 * 扫描类别 pid
	 */
	public static final String SCAN_TYPE_PID_NAME = "产品";

	/**
	 * 扫描类别 mid
	 */
	public static final String SCAN_TYPE_MID_NAME = "会员";

	/**
	 * 二维码地址
	 */
	public static final String QUICK_MARK_URL = "http://qr.liantu.com/api.php?text=";

	/**
	 * 任务类别 ： 自动审核任务
	 */
	public static final Integer TASKTYPE_AUTOAUDIT = 2;

	/**
	 * 任务类别 ： 待办任务
	 */
	public static final Integer TASKTYPE_BACKLOG = 1;

	/**
	 * 金苑X类
	 */
	public static final Integer JINYUAN_X = 1400000000;

	/**
	 * 会员抽奖状态-未发奖
	 */
	public static final Integer MMM_MEMBER_LOTTERY_STATUS_NO = 1;

	/**
	 * 会员抽奖状态-已发奖
	 */
	public static final Integer MMM_MEMBER_LOTTERY_STATUS_YES = 2;

	/**
	 * 会员抽奖状态-过期
	 */
	public static final Integer MMM_MEMBER_LOTTERY_STATUS_OUTDATE = 3;

	/**
	 * 会员抽奖发货状态-未发
	 */
	public static final Integer MMM_LOTTERY_SEND_STATUS_NO = 1;

	/**
	 * 会员抽奖发货状态-已发
	 */
	public static final Integer MMM_LOTTERY_SEND_STATUS_YES = 2;

	/**
	 * 抽奖
	 */
	public static final Integer RULE_TYPE_CJ = 6;

	/**
	 * 微信会员绑定
	 */
	public static final Integer WX_MEMEBER_BIND = 1;

	/**
	 * 微信会员同步
	 */
	public static final Integer WX_MEMEBER_SYN = 2;

	/**
	 * 会员登录类型
	 */
	public static final Integer MEMBER_LOG_TYPE = 3;

	/**
	 * 单据状态:未打印
	 */
	public static final Integer INVOICE_STATUS_Pr = 16;

	/**
	 * app推送目标 -广播
	 */
	public static final Integer APP_PUSH_TARGET_TYPE_PUB = 1;

	/**
	 * app推送目标 -标签
	 */
	public static final Integer APP_PUSH_TARGET_TYPE_TAG = 2;

	/**
	 * app推送目标 -别名
	 */
	public static final Integer APP_PUSH_TARGET_TYPE_ALIAS = 3;

	/**
	 * app推送目标 -纯文本
	 */
	public static final Integer APP_PUSH_TYPE_TEXT = 1;

	/**
	 * app推送目标 -带链接
	 */
	public static final Integer APP_PUSH_TYPE_HTML = 2;

	/**
	 * 配送方式 -自提
	 */
	public static final Integer SHIPPING_TYPE_TAKE = 2;

	/**
	 * 配送方式 -配送
	 */
	public static final Integer SHIPPING_TYPE_SEND = 1;

	/**
	 * 支付方式：支付宝
	 */
	public static final Integer BCM_PAYMENT_CONFIG_ALIPAY = 1;

	/**
	 * 支付方式：财付通
	 */
	public static final Integer BCM_PAYMENT_CONFIG_TENPAY = 2;

	/**
	 * 支付方式：快钱
	 */
	public static final Integer BCM_PAYMENT_CONFIG_KUAIPAY = 3;

	/**
	 * 支付方式：微信支付
	 */
	public static final Integer BCM_PAYMENT_CONFIG_WEIPAY = 888;

	/**
	 * 会员标签 -会员区域
	 */
	public static final Integer MMM_TAG_TYPE_AREA = 1;

	/**
	 * 会员标签 -会员年龄段
	 */
	public static final Integer MMM_TAG_TYPE_AGE = 2;

	/**
	 * 会员标签 -会员类型
	 */
	public static final Integer MMM_TAG_TYPE_LEVEL = 3;

	/**
	 * 会员标签 -会员所属节点
	 */
	public static final Integer MMM_TAG_TYPE_NODE = 4;

	/**
	 * 会员标签 -其他
	 */
	public static final Integer MMM_TAG_TYPE_OTHER = 5;

	/**
	 * 金苑M类
	 */
	public static final Integer JINYUAN_M = 1600000000;

	/**
	 * 微购在线支付订单状态,微购订单等待买家付款
	 */
	public static final int STATE_WG_WAIT_PAY = 60;

	/**
	 * 微购订单买家已付款，等待卖家发货
	 */
	public static final int STATE_WG_PAY_OK = 61;

	/**
	 * 微购订单卖家已发货，等待买家确认收货
	 */
	public static final int STATE_WG_SHIPPING_OK = 62;

	/**
	 * 微购订单交易成功
	 */
	public static final int STATE_WG_END = 63;

	/**
	 * 微购订单交易关闭
	 */
	public static final int STATE_WG_CANCLE = 64;

	/**
	 * 微购COD订单状态.(COD，这里的含义是货到付款)微购COD订单等待卖家发货
	 */
	public static final int STATE_WG_COD_WAIT_SHIP = 70;

	/**
	 * 微购COD订单卖家已发货,等待确认收货
	 */
	public static final int STATE_WG_COD_SHIPPING_OK = 71;

	/**
	 * 微购COD订单交易成功
	 */
	public static final int STATE_WG_COD_END = 72;

	/**
	 * 微购COD订单交易关闭(订单关闭 or 发货后拒签)
	 */
	public static final int STATE_WG_COD_CANCLE = 73;

	/**
	 * 订单复合状态常量定义,待付款(60/)
	 */
	public static final int STATE_WG_COMPLEX_WAIT_PAY = 801;

	/**
	 * 订单复合状态常量定义,待发货 (61/70)
	 */
	public static final int STATE_WG_COMPLEX_WAIT_SHIP = 802;

	/**
	 * 订单复合状态常量定义,待收货(62/71)
	 */
	public static final int STATE_WG_COMPLEX_WAIT_RECV = 803;

	/**
	 * 订单复合状态常量定义,已结束(63/64/72/73)
	 */
	public static final int STATE_WG_COMPLEX_WAIT_END = 804;

	/**
	 * 微购物订单详情：1=待付款
	 */
	public static final int WGW_DETAIL_STATUS_DFK = 1;

	/**
	 * 微购物订单详情：2=待发货
	 */
	public static final int WGW_DETAIL_STATUS_DFH = 2;

	/**
	 * 微购物订单详情：3=待收货
	 */
	public static final int WGW_DETAIL_STATUS_DSH = 3;

	/**
	 * 微购物订单详情：4=已关闭
	 */
	public static final int WGW_DETAIL_STATUS_YGB = 4;

	/**
	 * 微购物订单详情：5=已拒签
	 */
	public static final int WGW_DETAIL_STATUS_YJQ = 5;

	/**
	 * 微购物订单详情：6=交易成功
	 */
	public static final int WGW_DETAIL_STATUS_JYCG = 6;

	/**
	 * 微购物订单详情：7=退款中
	 */
	public static final int WGW_DETAIL_STATUS_TKZ = 7;

	/**
	 * 微购物订单详情：8=退款完成
	 */
	public static final int WGW_DETAIL_STATUS_TKWC = 8;

	/**
	 * 微购物订单详情：10=待发货(退货待发货)
	 */
	public static final int WGW_DETAIL_STATUS_TDDFH = 10;

	/**
	 * 微购物订单详情：11=待确认收货(退货待发货)
	 */
	public static final int WGW_DETAIL_STATUS_DQRSH = 11;

	/**
	 * 微购物订单详情：12=交易成功(退货成功)
	 */
	public static final int WGW_DETAIL_STATUS_THJYCG = 12;

	/**
	 * 微购物订单详情： 13=已关闭(退货关闭)
	 */
	public static final int WGW_DETAIL_STATUS_THYGB = 13;

	/**
	 * 微购物订单详情：14=待店员确认
	 */
	public static final int WGW_DETAIL_STATUS_DDYQR = 14;

	/**
	 * 微购物订单详情： 15=门店取货成功
	 */
	public static final int WGW_DETAIL_STATUS_MDQHCG = 15;

	/**
	 * 微购物订单详情： 16=卖家已发货
	 */
	public static final int WGW_DETAIL_STATUS_MJYHF = 16;

	/**
	 * 微购物订单详情：17=待商家同意退货
	 */
	public static final int WGW_DETAIL_STATUS_DSJTYTH = 17;

	/**
	 * 微购物订单详情： 18=待买家发货
	 */
	public static final int WGW_DETAIL_STATUS_DMJHF = 18;

	/**
	 * 微购物订单详情：19=待商家审核退货
	 */
	public static final int WGW_DETAIL_STATUS_DSJSHFH = 19;

	/**
	 * 微购物订单详情：20=取消退货
	 */
	public static final int WGW_DETAIL_STATUS_QXTH = 20;

	/**
	 * 微购物订单详情： 21=退货完成
	 */
	public static final int WGW_DETAIL_STATUS_THWC = 21;

	/**
	 * 微购物订单详情：22=商家审核退货通过
	 */
	public static final int WGW_DETAIL_STATUS_SJSHTHTG = 22;

	/**
	 * 微购物订单详情：23=商家审核退货不通过
	 */
	public static final int WGW_DETAIL_STATUS_SJSHTHBTG = 23;

	/**
	 * 微购物订单详情：24=退货中
	 */
	public static final int WGW_DETAIL_STATUS_THZ = 23;

	/**
	 * 退货申请单 买家退货操作代码
	 */
	public static final String O_EBSALINVOICE_WAITRETURN = "O_EBSALINVOICE_WAITRETURN";

	/**
	 * 单据状态:待退款
	 */
	public static final Integer INVOICE_STATUS_WP = 15;

	/**
	 * 节状态：正常
	 */
	public static final Integer SYS_NODE_STATUS_NORMAL = 1;

	/**
	 * 节状态：申请结业
	 */
	public static final Integer SYS_NODE_STATUS_APPLY = 2;

	/**
	 * 节状态：已结业
	 */
	public static final Integer SYS_NODE_STATUS_COMPLETION = 3;

	/**
	 * 金苑包包补货
	 */
	public static final Integer JINYUAN_BAOBAO_BUSINESSCLASS = 25;

	/**
	 * PDCA时序化类型：计划时序
	 */
	public static final Integer PDCA_TEMPORAL_TYPE_PLAN = 1;

	/**
	 * PDCA时序化类型：实际时序
	 */
	public static final Integer PDCA_TEMPORAL_TYPE_REAL = 2;

	/**
	 * PDCA时序化方法：其他
	 */
	public static final Integer PDCA_TEMPORAL_METHOD_OTHER = -1;

	/**
	 * PDCA时序化方法：平摊法
	 */
	public static final Integer PDCA_TEMPORAL_METHOD_AVERAGE = 1;

	/**
	 * PDCA时序化方法：线性
	 */
	public static final Integer PDCA_TEMPORAL_METHOD_LINEAR = 2;

	/**
	 * PDCA时序化方法：抛物线
	 */
	public static final Integer PDCA_TEMPORAL_METHOD_PARABOLA = 3;

	/**
	 * PDCA计划状态：进行中
	 */
	public static final Integer PDCA_PLAN_TYPE_J = 3;

	/**
	 * PDCA计划引用属性类型:款式
	 */
	public static final Integer PDCA_LINK_ATTR_TYPE_STYLE = 1;

	/**
	 * PDCA计划引用属性类型:节点
	 */
	public static final Integer PDCA_LINK_ATTR_TYPE_NODE = 2;

	/**
	 * PDCA计划目标类型:数量
	 */
	public static final Integer PDCA_TARGET_TYPE_AMOUNT = 1;

	/**
	 * PDCA计划目标类型:金额
	 */
	public static final Integer PDCA_TARGET_TYPE_MONEY = 2;

	/**
	 * PDCA计划目标类型:数量 + 金额
	 */
	public static final Integer PDCA_TARGET_TYPE_AM = 3;

	/**
	 * PDCA计划时序标记:数量
	 */
	public static final Integer PDCA_TEMPORAL_FLAG_AMOUNT = 1;

	/**
	 * PDCA计划时序标记:金额
	 */
	public static final Integer PDCA_TEMPORAL_FLAG_MONEY = 2;

	/**
	 * PDCA计划状态:作废
	 */
	public static final Integer PDCA_PLAN_STATUS_Z = 5;

	/**
	 * PDCA计划状态:制定中
	 */
	public static final Integer PDCA_PLAN_STATUS_M = 1;

	/**
	 * PDCA计划状态:待执行
	 */
	public static final Integer PDCA_PLAN_STATUS_W = 2;

	/**
	 * PDCA计划状态:执行中
	 */
	public static final Integer PDCA_PLAN_STATUS_D = 3;

	/**
	 * PDCA计划状态:已完成
	 */
	public static final Integer PDCA_PLAN_STATUS_Y = 4;

	/**
	 * PDCA计划 报警级别 :正常
	 */
	public static final Integer PDCA_ALARM_LEVEL_NORMAL = 1;

	/**
	 * PDCA计划 报警级别 :提醒
	 */
	public static final Integer PDCA_ALARM_LEVEL_REMIND = 2;

	/**
	 * PDCA计划 报警级别 :报警
	 */
	public static final Integer PDCA_ALARM_LEVEL_POLICE = 3;

	/**
	 * 多流程标识
	 */
	public static final Integer MULTIPATH_PATH_TWO = 2;

	/**
	 * 多流程标识
	 */
	public static final Integer MULTIPATH_PATH_THREE = 3;

	/**
	 * 流程复制子单据:不复制
	 */
	public static final Integer PSS_COPY_DTL_NO = 1;

	/**
	 * 流程复制子单据:复制(数量为0的明细也复制)
	 */
	public static final Integer PSS_COPY_DTL_CONTAIN_ZERO = 2;

	/**
	 * 流程复制子单据:复制(数量为0的明细不复制)
	 */
	public static final Integer PSS_COPY_DTL_NO_CONTAIN_ZERO = 3;

	/**
	 * 已配库存影响类型:不影响
	 */
	public static final Integer STOCK_ALLOCATED_FLAG_ONE = 1;

	/**
	 * 已配库存影响类型:正影响（即清除后写入）
	 */
	public static final Integer STOCK_ALLOCATED_FLAG_TWO = 2;

	/**
	 * 已配库存影响类型:负影响（即直接清除）
	 */
	public static final Integer STOCK_ALLOCATED_FLAG_THREE = 3;

	/**
	 * 已配库存影响类型:本单正关联单负（清除本单和关联单并写入本单）
	 */
	public static final Integer STOCK_ALLOCATED_FLAG_FOUR = 4;

	/**
	 * 已配库存影响类型:本单负关联单正（清除本单，写入关联单）
	 */
	public static final Integer STOCK_ALLOCATED_FLAG_FIVE = 5;

	/**
	 * 已配库存影响类型:本单负关联单负（清除本单和关联单）
	 */
	public static final Integer STOCK_ALLOCATED_FLAG_SIX = 6;

	/**
	 * 财务余额控制:1-不控制
	 */
	public static final Integer PSS_BALANCE_CONTROL_ONE = 1;

	/**
	 * 财务余额控制:2-控制本节点
	 */
	public static final Integer PSS_BALANCE_CONTROL_TWO = 2;

	/**
	 * 财务余额控制:3-控制交易对象
	 */
	public static final Integer PSS_BALANCE_CONTROL_THREE = 3;

	/**
	 * 本地退换
	 */
	public static final Integer LOCAL_EXCHANGE = 31;

	/**
	 * 异地退换
	 */
	public static final Integer FOREIGN_EXCHANGE = 32;

	/**
	 * 生产管理计划
	 */
	public static final Integer PRODUCTION_MANAGER_PLAN = 1;

	/**
	 * 退货率配制表控制粒度 1）、控制到SKU
	 */
	public static final Integer CONTROL_SKU_FlAG_ONE = 1;

	/**
	 * 退货率配制表控制粒度 2）、控制到款式
	 */
	public static final Integer CONTROL_SKU_FlAG_TWO = 2;

	/**
	 * 退货率配制表控制粒度 3）、控制到总金额
	 */
	public static final Integer CONTROL_SKU_FlAG_THREE = 3;

	/**
	 * app版面元素位置 顶部
	 */
	public static final Integer APP_LAYOUT_ELEMENT_POSITION_TOP = 1;

	/**
	 * app版面元素位置 中部
	 */
	public static final Integer APP_LAYOUT_ELEMENT_POSITION_CENTER = 2;

	/**
	 * app版面元素位置 底部
	 */
	public static final Integer APP_LAYOUT_ELEMENT_POSITION_BOTTOM = 3;

	/**
	 * app版面元素位置 分类
	 */
	public static final Integer APP_LAYOUT_ELEMENT_TYPE_CLASS = 1;

	/**
	 * app版面元素位置 时尚咨询
	 */
	public static final Integer APP_LAYOUT_ELEMENT_TYPE_FASHION = 2;

	/**
	 * app版面元素位置 登录网址
	 */
	public static final Integer APP_LAYOUT_ELEMENT_TYPE_LOGIN = 3;

	/**
	 * app版面元素位置 积分商城
	 */
	public static final Integer APP_LAYOUT_ELEMENT_TYPE_INTEGRAL = 4;

	/**
	 * 验证码发送方式 1 – 短信发送
	 */
	public static final Integer MMM_SECURITY_CODE_SEND_TYPE_ONE = 1;

	/**
	 * 验证码发送方式 2 – 邮箱发送
	 */
	public static final Integer MMM_SECURITY_CODE_SEND_TYPE_TWO = 2;

	/**
	 * 验证码类型 2 – 会员生日验证码
	 */
	public static final Integer MMM_SECURITY_CODE_TYPE_TWO = 2;

	/**
	 * 生产管理
	 */
	public final static String PRODUCTION_MANAGER = "生产管理";

	/**
	 * 业务类型：正常买单
	 */
	public static final Integer BUSINESS_CLASS_SALE_NORMAL = 31;
	/**
	 * 业务类型：买单本地退换货
	 */
	public static final Integer BUSINESS_CLASS_SALE_LOCAL = 32;
	/**
	 * 业务类型：买单异地退换货
	 */
	public static final Integer BUSINESS_CLASS_SALE_ALLOPATRY = 33;
	/**
	 * 业务类型：导购宝买单自提
	 */
	public static final Integer BUSINESS_CLASS_SALE_TAKEN = 34;
	/**
	 * 业务类型：导购宝买单发货
	 */
	public static final Integer BUSINESS_CLASS_SALE_SEND = 35;

	/**
	 * 业务类型：服装配货
	 */
	public static final Integer BUSINESS_CLASS_ALLOCATE = 38;
	
	/**
	 * 业务类型：砍价活动
	 */
	public static final Integer BUSINESS_CLASS_BRAGAIN = 39;
	
	/**
	 * 横调
	 */
	public static final Integer BUSINESS_CLASS_HENGDIAO = 44;
	
	/**
	 * 淘宝线上买单支付门店发货
	 */
	public static final Integer BUSINESS_CLASS_TAOBAO_O2O = 51;
	
	public static final Integer BUSINESS_CLASS_ALIPAY = 45;
	
	public static final Integer BUSINESS_CLASS_WEIPAY = 46;

	/**
	 * 业务关账状态:1-未生效
	 */
	public static final Integer FMM_CLOSING_BIZ_STATUS_ONE = 1;

	/**
	 * 业务关账状态:2-生效
	 */
	public static final Integer FMM_CLOSING_BIZ_STATUS_TWO = 2;
	/**
	 * 可退件数为负时是否设置为0 不控制
	 */
	public static final Integer RETURNABLE_AMOUNT_SET_TO_ZERO_ONE = 1;

	/**
	 * 可退件数为负时是否设置为0 控制到商品
	 */
	public static final Integer RETURNABLE_AMOUNT_SET_TO_ZERO_TWO = 2;

	/**
	 * 可退件数为负时是否设置为0 控制到款式
	 */
	public static final Integer RETURNABLE_AMOUNT_SET_TO_ZERO_THREE = 3;

	/**
	 * 京东订单状态-等待出库
	 */
	public static final int JD_WAIT_SELLER_STOCK_OUT = 1;

	/**
	 * 退货计划核算控制 1-不控制
	 */
	public static final Integer PSS_RETURNED_CONTROL_ONE = 1;

	/**
	 * 退货计划核算控制 2-控制所属节点
	 */
	public static final Integer PSS_RETURNED_CONTROL_TWO = 2;

	/**
	 * 退货计划核算控制 3-控制交易对象
	 */
	public static final Integer PSS_RETURNED_CONTROL_THREE = 3;

	/**
	 * 管理区域配置code
	 */
	public static final String DATA_RIGHTS_AREA_MAN = "DATA_RIGHTS_AREA_MAN";

	/**
	 * 裁剪
	 */
	public static final Integer TAILOR = 40;

	/**
	 * 缝制
	 */
	public static final Integer SEW = 41;

	/**
	 * 水洗
	 */
	public static final Integer WASH = 42;

	/**
	 * 包装
	 */
	public static final Integer PACK = 43;

	/**
	 * 周期类型-周次
	 */
	public static final Integer CYCLE_TYPE_WEEK_TIMES = 1;

	/**
	 * 周期类型-周天
	 */
	public static final Integer CYCLE_TYPE_WEEK_DAY = 2;
	/**
	 * 周期类型-指定日期
	 */
	public static final Integer CYCLE_TYPE_POINT_DATE = 3;
		/**
	 * 日期类型：单双号
	 */
	public static final Integer CYCLE_TYPE_DATE_TYPE = 4;
	/**
	 * 周次-一周一次
	 */
	public static final Integer CYCLE_TYPE_WEEKS_TIMES_ONE = 1;
	/**
	 * 周次-两周一次
	 */
	public static final Integer CYCLE_TYPE_WEEKS_TIMES_TWO = 2;
	/**
	 * 周次-三周一次
	 */
	public static final Integer CYCLE_TYPE_WEEKS_TIMES_THREE = 3;
	/**
	 * 周天-周一
	 */
	public static final Integer CYCLE_TYPE_WEEKS_DAY_ONE = 1;
	/**
	 * 周天-周二
	 */
	public static final Integer CYCLE_TYPE_WEEKS_DAY_TWO = 2;
	/**
	 * 周天-周三
	 */
	public static final Integer CYCLE_TYPE_WEEKS_DAY_THREE = 3;
	/**
	 * 周天-周四
	 */
	public static final Integer CYCLE_TYPE_WEEKS_DAY_FOUR = 4;
	/**
	 * 周天-周五
	 */
	public static final Integer CYCLE_TYPE_WEEKS_DAY_FIVE = 5;
	/**
	 * 周天-周六
	 */
	public static final Integer CYCLE_TYPE_WEEKS_DAY_SIX = 6;
	/**
	 * 周天-周日
	 */
	public static final Integer CYCLE_TYPE_WEEKS_DAY_SERVEN = 7;
	/**
	 * user 是否可用 1-可用，2-待审核，3-不可用
	 */
	public static final Integer SYS_USER_STATUS_ONE = 1;
	/**
	 * user 是否可用 1-可用，2-待审核，3-不可用
	 */
	public static final Integer SYS_USER_STATUS_TWO = 2;
	/**
	 * user 是否可用 1-可用，2-待审核，3-不可用
	 */
	public static final Integer SYS_USER_STATUS_THREE = 3;
	/**
	 * app请求状态 是否可用 1-请求失败，2-请求完成
	 */
	public static final Integer APP_REQUEST_STATUS_FAILED = 1;
	/**
	 * app请求状态 是否可用 1-请求失败，2-请求完成
	 */
	public static final Integer APP_REQUEST_STATUS_SUCCESS = 2;
	/**
	 * 消息编码 12 :店铺二维码被扫描提醒管理员角色的所有用户
	 */
	public static final Integer MCM_MSG_DEF_TWELVE = 12;
	/**
	 * 消息编码 13 :店员二维码被扫描提醒该店员
	 */
	public static final Integer MCM_MSG_DEF_THIRTEEN = 13;
	/**
	 * 消息编码 14 :店员二维码被扫描提醒所在店铺管理员角色的所有用户
	 */
	public static final Integer MCM_MSG_DEF_FOURTEEN = 14;
	/**
	 * 消息编码 15 :会员二维码被扫描提醒该会员
	 */
	public static final Integer MCM_MSG_DEF_FIFTEEN = 15;
	/**
	 * 消息编码 16 :给店员利润分成提醒该店员
	 */
	public static final Integer MCM_MSG_DEF_SIXTEEN = 16;
	/**
	 * 消息编码 17 :给店员利润分成提醒所在店铺管理员角色的所有用户
	 */
	public static final Integer MCM_MSG_DEF_SEVENTEEN = 17;
	/**
	 * 消息编码 18 :给店铺利润分成提醒所在店铺管理员角色的所有用户
	 */
	public static final Integer MCM_MSG_DEF_EIGHTEEN = 18;
	/**
	 * 消息编码 19 :给会员利润分成提醒会员
	 */
	public static final Integer MCM_MSG_DEF_NINETEEN = 19;
	/**
	 * 消息编码 20 :商品二维码被扫描提醒商品所在店铺（根据扫描者位置匹配1公里内最近的一家店铺）管理员角色的所有用户
	 */
	public static final Integer MCM_MSG_DEF_TWENTY = 20;
	/**
	 * 消息编码 21 :销售业绩提醒，店铺每销售一单提醒管理员角色的所有用户
	 */
	public static final Integer MCM_MSG_DEF_TWENTYONE = 21;
	/**
	 * 消息编码 25 :企业号吐槽中心点赞消息提醒
	 */
	public static final Integer MCM_MSG_DEF_TCZX_DZ = 25;
	
	/**
	 * 消息编码 26 :企业号吐槽中心评论消息提醒
	 */
	public static final Integer MCM_MSG_DEF_TCZX_PR = 26;
	
	/**
	 * 消息编码 27 :企业号情报中心点赞消息提醒
	 */
	public static final Integer MCM_MSG_DEF_QBZX_DZ = 27;
	
	/**
	 * 消息编码 28 :企业号情报中心评论消息提醒
	 */
	public static final Integer MCM_MSG_DEF_QBZX_PR = 28;
	
	/**
	 * 消息编码 29 :企业号设计中心点赞消息提醒
	 */
	public static final Integer MCM_MSG_DEF_SJZX_DZ = 29;
	
	/**
	 * 消息编码 30 :企业号设计中心评论消息提醒
	 */
	public static final Integer MCM_MSG_DEF_SJZX_PR = 30;
	
	
	/**
	 * 消息编码 45 :we社区点赞消息提醒
	 */
	public static final Integer MCM_MSG_DEF_WSQ_DZ = 45;
	
	/**
	 * 消息编码 46 :we社区评论消息提醒
	 */
	public static final Integer MCM_MSG_DEF_WSQ_PR = 46;
	
	
	/**
	 * 消息编码 31 :会员归属节点未获得利润的消息发送
	 */
	public static final Integer MCM_MSG_DEF_THIRTYONE = 31;
	
	/**
	 * 消息编码 43 :绑定成功提醒归属节点	【会员绑定】 
	 */
	public static final Integer MCM_MSG_DEF_FORTYTHREE = 43;
	/**
	 * 消息编码 33 :店铺二维码被未绑定手机号的扫描提醒管理员角色
	 */
	public static final Integer MCM_MSG_DEF_THIRTYTHREE = 33;
	/**
	 * 消息编码 34 :店员二维码被未绑定手机号的扫描提醒该店员
	 */
	public static final Integer MCM_MSG_DEF_THIRTYFOUR = 34;
	/**
	 * 消息编码 35 :店员二维码被未绑定手机号的扫描提醒所在店铺管理员角色
	 */
	public static final Integer MCM_MSG_DEF_THIRTYFIVE = 35;
	/**
	 * 消息编码 36 :会员二维码被未绑定手机号的扫描提醒该会员
	 */
	public static final Integer MCM_MSG_DEF_THIRTYSIX = 36;
	/**
	 * 消息编码 37 :会员扫了非归属节点的店铺或店员二维码，提醒归属店铺管理员角色
	 */
	public static final Integer MCM_MSG_DEF_THIRTYSEVEN = 37;
	/**
	 * 消息编码 39 :支付成功预计店员利润分成提醒该店员
	 */
	public static final Integer MCM_MSG_DEF_THIRTYNINE = 39;
	/**
	 * 消息编码40 :支付成功预计店员利润分成提醒所在店铺管理员角色的所有用户
	 */
	public static final Integer MCM_MSG_DEF_FORTY = 40;
	/**
	 * 消息编码41 :支付成功预计店铺利润分成提醒所在店铺管理员角色的所有用户；
	 */
	public static final Integer MCM_MSG_DEF_FORTYONE = 41;
	/**
	 * 消息编码42 :支付成功预计会员利润分成提醒会员；
	 */
	public static final Integer MCM_MSG_DEF_FORTYTWO = 42;
	
	/**
	 * 消息编码47 :微信提醒积分到期清0；
	 */
	public static final Integer MCM_MSG_DEF_FORTYSEVEN = 47;
	/**
	 * 消息编码48 :微信会员生日提醒；
	 */
	public static final Integer MCM_MSG_DEF_FORTYEIGHT = 48;
	
	
	/**
	 * 消息编码 49 :下线店员二维码被扫描提醒该店员
	 */
	public static final Integer MCM_MSG_DEF_FOURTEEN_NIGHT = 49;
	/**
	 * 消息编码 50 :下线店员给店员利润分成提醒该店员
	 */
	public static final Integer MCM_MSG_DEF_FIFTY = 50;
	/**
	 * 消息编码 51 :下线店员二维码被未绑定手机号的扫描提醒该店员
	 */
	public static final Integer MCM_MSG_DEF_FIFTY_ONE = 51;
	/**
	 * 消息编码 52 :下线引流会员支付成功预计店员利润分成提醒该店员
	 */
	public static final Integer MCM_MSG_DEF_FIFTY_TWO = 52;
	
	/**
	 * 消息编码 53 :代理申请审核通过
	 */
	public static final Integer MCM_MSG_DEF_FIFTY_THREE = 53;
	
	/**
	 * 消息编码 54 :代理申请审核不通过
	 */
	public static final Integer MCM_MSG_DEF_FIFTY_FOUR = 54;
	
	/**
	 * 消息编码 55 :代理申请通知
	 */
	public static final Integer MCM_MSG_DEF_FIFTY_FIVE = 55;
	
	/**
	 * 消息编码 56 :代理申请通知 提交成功通知上级
	 */
	public static final Integer MCM_MSG_DEF_FIFTY_SIX = 56;
	
	/**
	 * 消息编码 57 :代理申请通知 绑定手机号通知上级
	 */
	public static final Integer MCM_MSG_DEF_FIFTY_SEVEN= 57;
	
	/**
	 * 消息编码 58 :代理申请通知 审核通过通知上级
	 */
	public static final Integer MCM_MSG_DEF_FIFTY_EIGHT= 58;
	
	/**
	 * 消息编码 59 :代理申请通知 审核不通过通知上级
	 */
	public static final Integer MCM_MSG_DEF_FIFTY_NIGHT= 59;
	/**
	 * 消息编码 60 :提现成功
	 */
	public static final Integer MCM_MSG_DEF_SIXTY= 60;
	/**
	 * 消息编码 61 :提现失败
	 */
	public static final Integer MCM_MSG_DEF_SIXTY_ONE= 61;
	
	/**
	 * 消息编码 62 :发放销售激励通知
	 */
	public static final Integer MCM_MSG_DEF_SIXTY_TWO= 62;
	
	/**
	 * 消息编码 63 :取消销售激励通知
	 */
	public static final Integer MCM_MSG_DEF_SIXTY_THREE= 63;
	
	/**
	 * 消息编码 64 :快递发货通知
	 */
	public static final Integer MCM_MSG_DEF_SIXTY_FOUR= 64;
	
	/**
	 * 消息编码 65 :线上支付发放销售激励通知
	 */
	public static final Integer MCM_MSG_DEF_SIXTY_FIVE= 65;
	/**
	 * 消息编码 66 :终身利润分层预估通知
	 */
	public static final Integer MCM_MSG_DEF_SIXTY_SIX= 66;
	/**
	 * 消息编码 67 :终身利润分层通知
	 */
	public static final Integer MCM_MSG_DEF_SIXTY_SEVEN= 67;
	/**
	 * 消息编码 68 :任务完成奖励通知
	 */
	public static final Integer MCM_MSG_DEF_SIXTY_EIGHT= 68;
	
	/**
	 * 消息编码 69 :付款成功通知
	 */
	public static final Integer MCM_MSG_DEF_SIXTY_NIGHT= 69;
	/**
	 * 消息编码 7 :购买成功通知
	 */
	public static final Integer MCM_MSG_DEF_SEVEN =7;
	/**
	 * 消息编码 77 :积分兑换成功通知 
	 */
	public static final Integer MCM_MSG_DEF_SEVENTY_SEVEN =77;
	/**
	 * 部门
	 */
	public static final Integer USER_GROUP_TYPE_ONE = 1;
	
	/**
	 * 职位
	 */
	public static final Integer USER_GROUP_TYPE_TWO=2;

	public static final Integer MSG_STATUS_ZERO = 0;
	/**
	 * 消息发送设置 :1-不发送，2-发送所属节点，3-发送交易对象
	 */
	public static final Integer PSS_MSG_SEND_CONF_ONE = 1;
	/**
	 * 消息发送设置 :1-不发送，2-发送所属节点，3-发送交易对象
	 */
	public static final Integer PSS_MSG_SEND_CONF_TWO = 2;
	/**
	 * 消息发送设置 :1-不发送，2-发送所属节点，3-发送交易对象
	 */
	public static final Integer PSS_MSG_SEND_CONF_THREE = 3;

	/**
	 * 社交类型 ：1:表示微信
	 */
	public static final Integer SNS_TYPE = 1;
	
	
	/**
	 * app 消息类型
	 */
	public static final Integer APP_MSG_TYPE_JOIN = 2;
	
	/**
	 * app 消息类型--加盟
	 */
	public static final Integer APP_MSG_TYPE_JOIN_UNION = 4;
	
	/**
	 * app 消息处理状态 1 未处理
	 */
	public static final Integer APP_MSG_RECEIVED_STATUS_ONE = 1;
	
	/**
	 * app 消息处理状态 2 未处理
	 */
	public static final Integer APP_MSG_RECEIVED_STATUS_TWO = 2;
	/**
	 * 收入/费用类型:利润分成支出
	 */
	public static final String CHARGE_TYPE_LRFC_ZC = "LRFC_ZC";
	/**
	 * 收入/费用类型:利润分成收入
	 */
	public static final String CHARGE_TYPE_LRFC_SR = "LRFC_SR";
	
	
	/**
	 * 收入/费用类型:销售奖励支出
	 */
	public static final String CHARGE_TYPE_XSJL_ZC = "XSJL_ZC";
	/**
	 * 收入/费用类型:销售奖励收入
	 */
	public static final String CHARGE_TYPE_XSJL_SR = "XSJL_SR";
	
	/**
	 * 收入/费用类型:利润分成提现收入
	 */
	public static final String CHARGE_TYPE_LRFCTX_SR = "LRFCTX_SR";
	/**
	 * 收入/费用类型:利润分成提现支出
	 */
	public static final String CHARGE_TYPE_LRFCTX_ZC = "LRFCTX_ZC";
/**
	 *  会员利润分成状态（MMM_PROFIT_STATUS）1-激活，2-未激活
	 */
	public static final Integer MMM_PROFIT_STATUS_ONE = 1;
	/**
	 *  会员利润分成状态（MMM_PROFIT_STATUS）1-激活，2-未激活
	 */
	public static final Integer MMM_PROFIT_STATUS_TWO = 2;
	
	/**
	 * 红包类型-正红包
	 */
	public final static Integer RED_PLUS = 1;
	/**
	 * 红包类型-负红包
	 */
	public final static Integer RED_MINUS = 2;
	/**
	 * 红包类型-金元宝
	 */
	public final static Integer RED_BOMB =3;
	/**
	 * 红包类型-炸弹
	 */
	public final static Integer RED_GOLDEN = 4;
	/**
	 * 红包类型-实物5
	 */
	public final static Integer RED_GIFT_5 = 5;
	/**
	 * 红包类型-实物6
	 */
	public final static Integer RED_GIFT_6 = 6;
	/**
	 * 红包类型-砍价
	 */
	public final static Integer RED_BARGAIN = 7;
	/**
	 * 红包类型-投票
	 */
	public final static Integer RED_VOTES = 8;
	
	/**
	 * 红包收取方式 别人赠送
	 */
	public final static Integer RED_RECIVED_SEND = 1;
	
	/**
	 * 红包收取方式 别人回赠
	 */
	public final static Integer RED_RECIVED_BACK = 2;
	/**
	 * 验证码类型8 – 会员激活验证码
	 */
	public static final Integer MMM_SECURITY_CODE_TYPE_EIGHT = 8;
	
	/**
	 * 红包活动分组
	 */
	public final static Integer MEMBER_LINK_GROUP_A = 1;
	
	/**
	 * 红包活动分组
	 */
	public final static Integer MEMBER_LINK_GROUP_B = 2;
	
	/**
	 * 红包活动分组
	 */
	public final static Integer MEMBER_LINK_GROUP_C = 3;
	
	
	/**
	 * 红包活动分组
	 */
	public final static Integer MEMBER_LINK_TYPE_ONE = 1;
	
	/**
	 * 红包活动分组
	 */
	public final static Integer MEMBER_LINK_TYPE_TWO = 2;
	/**
	 * 红包活动分组
	 */
	public final static Integer MEMBER_LINK_TYPE_THREE = 3;
	/**
	 * 红包活动分组
	 */
	public final static Integer MEMBER_LINK_TYPE_FOUR = 4;

	
	/**
	 * 消息编码 24 :吐槽中心点赞消息
	 */
	public static final Integer MCM_MSG_DEF_TCZX = 25;
	/**
	 * 消息编码 15 :批处理业绩通知1
	 */
	public static final Integer MCM_MSG_DEF_YZTZ_1 = 22;
	
	/**
	 * 消息编码 15 :批处理业绩通知2
	 */
	public static final Integer MCM_MSG_DEF_YZTZ_2 = 23;
	/**
	 * 消息编码 15 :批处理业绩通知3
	 */
	public static final Integer MCM_MSG_DEF_YZTZ_3 = 24;
	
	/**
	 * 促销活动titleCode 限时折扣
	 */
	public static final Integer SMM_TITLE_CODE_XSZK=4;
	
	/**
	 * 促销活动titleCode 代金券
	 */
	public static final Integer SMM_TITLE_CODE_DJ=0;
	
	/**
	 * 促销活动titleCode 折扣券
	 */
	public static final Integer SMM_TITLE_CODE_ZK=1;
	
	/**
	 * 促销活动titleCode 满就送
	 */
	public static final Integer SMM_TITLE_CODE_MS=3;
	
	/**
	 * 促销活动titleCode 买一送一
	 */
	public static final Integer SMM_TITLE_CODE_MYSY=5;
	/**
	 * 促销活动titleCode 抽奖
	 */
	public static final Integer SMM_TITLE_CODE_CJ=6;
	/**
	 * 促销活动titleCode 积分兑换
	 */
	public static final Integer SMM_TITLE_CODE_JFDH=7;
	/**
	 * 促销活动titleCode 红包活动
	 */
	public static final Integer SMM_TITLE_CODE_HB=8;

	
	/**
	 *OA 任务提醒
	 */
	public static final Integer MCM_MSG_OA_WARNING = 44;
	
	/**
	 * 签到方式周期性
	 */
	public static final Integer WXN_SIGN_TYPE_ONE = 1;
	/**
	 * 签到方式固定
	 */
	public static final Integer WXN_SIGN_TYPE_TWO = 2;
	
	/**
	 * 连续签到，到最后保持在最大值
	 */
	public static final Integer WXN_SIGN_TYPE_THREE = 3;
	
	/**
	 * 签到推送图文方式-不推
	 */
	public static final Integer WXN_CONTENT_SEND_TYPE_ZERO=0;
	
	/**
	 * 签到推送图文方式-随机图文
	 */
	public static final Integer WXN_CONTENT_SEND_TYPE_ONE=1;
	
	/**
	 * 签到推送图文方式-固定图文
	 */
	public static final Integer WXN_CONTENT_SEND_TYPE_TWO=2;
	/**
	 * 积分类型-线下消费
	 */
	public static final Integer INTEGRAL_TYPE_23 = 23;
	/**
	 * 开单URL地址
	 */
	public static final String KORDER_URL="http://www.korder.cn/phpepos/eposServer/ipConfig.html";
	
	public static final String server_korder_host = "http://www.korder.cn/korder";
	
	public static final String server_default_epos_host = "http://www.korder.cn/eposdf";
	
	/**
	 *  会员编码
	 */
	public static final Integer MEMBER_CODE_SEQUENCE = 1;
	/**
	 *  会员卡编码
	 */
	public static final Integer CARD_CODE_SEQUENCE = 2;
	
/**
	 * 日期类型：单号；
	 */
	public static final Integer WEEK_TYPE_SINGLE = 1;
		/**
	 * 日期类型：双号；
	 */
	public static final Integer WEEK_TYPE_DOUBLE = 2;
	
	/**
	 * 补货星期双
	 */
	public static final Integer BGN_ATTR9_EVEN = 4;
	
	/**
	 * 补货星期单
	 */
	public static final Integer BGN_ATTR9_ODD = 5;
	
	/**
	 *  	微商角色: 1-员工、2-店铺、3-会员三种角色
	 */
	public static final Integer PORTION_ROLE_ONE=1;
	/**
	 *  	微商角色: 1-员工、2-店铺、3-会员三种角色
	 */
	public static final Integer PORTION_ROLE_TWO=2;
	/**
	 *  	微商角色: 1-员工、2-店铺、3-会员三种角色
	 */
	public static final Integer PORTION_ROLE_THREE=3;
	
	/**
	 *  	微商抽成级别: 
	 */
	public static final Integer PORTION_RANK_ONE=1;
	/**
	 *  	抽成状态:1-预估 
	 */
	public static final Integer PROFIT_STATUS_ONE=1;
	/**
	 *  	抽成状态:2-结案 
	 */
	public static final Integer PROFIT_STATUS_TWO=2;
	/**
	 *  	抽成状态:3-处理中
	 */
	public static final Integer PROFIT_STATUS_THREE=3;
	/**
	 *  	抽成状态:4-失败
	 */
	public static final Integer PROFIT_STATUS_FOUR=4;
	
	/**
	 *  	抽成流水类型-抽成收入
	 */
	public static final Integer PROFIT_TYPE_ONE=1;
	/**
	 *  	抽成流水类型-抽成支出
	 */
	public static final Integer PROFIT_TYPE_TWO=2;	
	/**
	 * app 消息类型
	 */
	public static final Integer APP_MSG_TYPE_REVIEW = 3;
	
	/**
	 * 红包分组类别 :端午棕子活动
	 */
	public static final Integer GROUP_TYPE_DRAGON_BOAT_FESTIVAL = 515;
	/**
	 * 红包分组类别 :年中大促
	 */
	public static final Integer PROMOTION_ACHIEVE_TYPE_YEAR = 90028;
	
	/**
	 * 消息编码 78 :购买优惠券到账通知
	 */
	public static final Integer MCM_MSG_DEF_SEVENTY_EIGHT =78;
	/**
	 * 消息编码 79 :抽奖优惠券到账通知
	 */
	public static final Integer MCM_MSG_DEF_SEVENTY_NINE =79;
	
	/**
	 * 消息编码 80 :VIP生日优惠券通知
	 */
	public static final Integer MCM_MSG_DEF_EAGHTY =80;
	
	/**
	 * 消息编码 8001 :评论回复通知
	 */
	public static final Integer MCM_MSG_DEF_EAGHTY_AND_ONE =8001;

	/**  
	 * @Fields LOGISTICS_COMPANY_CAI_NIAO : 菜鸟物流编号
	 */  
	public static final Integer LOGISTICS_COMPANY_CAI_NIAO = 60;
	
	/**  
	 * @Fields 零售单退回环节
	 */  
	public static final String LINK_DEF_RBR = "L_RBR";
	/**
	 * 消息编码 90 :每天发消息给店员
	 */
	public static final Integer MCM_MSG_DEF_NINETY =90;
	/**
	 * 消息编码 91 :每天发消息给店张
	 */
	public static final Integer MCM_MSG_DEF_NINETY_ONE =91;
	/**
	 * 消息编码 92 :每天发消息给店张 伙伴完成
	 */
	public static final Integer MCM_MSG_DEF_NINETY_TWO =92;
	/**
	 * 消息编码 93 :每天发消息给店张 月销售top5
	 */
	public static final Integer MCM_MSG_DEF_NINETY_THREE =93;
	/**
	 * 消息编码 94 :每天发消息给店张 店铺库存最多的top3
	 */
	public static final Integer MCM_MSG_DEF_NINETY_FOUR =94;
	/**
	 * 消息编码 95 :买单后发消息给导购
	 */
	public static final Integer MCM_MSG_DEF_NINETY_FIVE =95;

	public static final Integer INVOICE_TYPE_ONE=1;//领料单
	public static final Integer INVOICE_TYPE_TWO=2;//请购单
	public static final Integer INVOICE_TYPE_THREE=3;//采购单
	
	public static final Integer INVOICE_STATUS_ONE=1;//保存
	public static final Integer INVOICE_STATUS_TWO=2;//待提交
	public static final Integer INVOICE_STATUS_THREE=3;//待审核
	public static final Integer INVOICE_STATUS_FIVE=5;//结案
	
	public static final Integer PROJECT_STATUS_ONE=1;
	public static final Integer PROJECT_STATUS_TWO=2;
	public static final Integer PROJECT_STATUS_THREE=3;
	public static final Integer PROJECT_STATUS_FOUR=4;
	public static final Integer PROJECT_STATUS_FIVE=5;
	public static final Integer PROJECT_STATUS_SIX=6;//已废弃
	public static final Integer PROJECT_STATUS_SEVEN=7;//已入库
	public static final Integer PROJECT_STATUS_EIGHT=8;//已完成
	
	public static final String PROJECT_STATUS_ONENAME="创建";
	public static final String PROJECT_STATUS_TWONAME="下单";
	public static final String PROJECT_STATUS_THREENAME="审核";
	public static final String PROJECT_STATUS_FOURNAME="知会";
	public static final String PROJECT_STATUS_FIVENAME="完成";
	public static final String PROJECT_STATUS_THREENAME_CANCEL="取消审核";
	
	public static final String STOCK_CHANGE_TYPE_ADD="增加";
	public static final String STOCK_CHANGE_TYPE_REDUCE="减少";
	public static final String STOCK_CHANGE_TYPE_COVER="覆盖";
	
	public static final Integer MATERIA_CLASS_ONE=1;//标准件
	
}
