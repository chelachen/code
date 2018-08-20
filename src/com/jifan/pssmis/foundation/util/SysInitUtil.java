package com.jifan.pssmis.foundation.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import com.jifan.pssmis.bs.sys.ISystemConfigBS;
import com.jifan.pssmis.foundation.contants.SystemConfigContants;
import com.jifan.pssmis.foundation.log.SysLogger;
import com.jifan.pssmis.model.bo.sys.SystemConfigBO;
import com.jifan.pssmis.model.vo.sys.SystemConfigVO;

@Component("sysInitUtil")
public class SysInitUtil
{
    /**
     * 分割符
     */
    private static final String sperator = ";";
    
    /**
     * 系统配置信息
     */
    private static ISystemConfigBS systemConfigBS;
    
    /**
     * 缓存策略,首次查询后最为静态数据，避免重复从数据库中
     */
    public static String SYS_CACHE_POLICY = "";
    
    /**
     * 获取系统配置,从静态缓存取
     * 
     * @return
     */
    public static String getSystemConfigValue(String confKey)
    {
    	
//        String confValue = getCachePolicy().getSystemConfigValue(confKey);
//        if (confValue == null || confValue.equals(""))
//        {
    	String  confValue = getSystemConfigValueAct(confKey);
//        }
        return confValue;
    }
    
    /**
     * 实时获取系统配置
     */
    private static String getSystemConfigValueAct(String confKey)
    {
        if (systemConfigBS != null)
        {
//        	String bgId= SysSession.getBgId();
            List<SystemConfigBO> list = systemConfigBS.findSystemConfigByParam(new SystemConfigVO(confKey,null));
            if (list != null && list.size() > 0)
            {
                SystemConfigBO systemConfigBO = list.get(0);
                return systemConfigBO.getConfigValue();
            }
            else
            {
                SysLogger.error(SysInitUtil.class, "找不到系统配置信息，请添加配置！配置键值：" + confKey);
                // 默认配置
                return "";
            }
        }
        else
        {
            SysLogger.error(SysInitUtil.class, "getSystemConfigBO中systemConfigBS未注入，采用默认配置！");
            // 默认配置
            return "";
        }
    }
    
    /**
     * 获取系统配置信息中的缓存策略字符串
     * 
     * @return
     */
    public static String getCachePolicyString()
    {
        String policy = "";
//        if (SYS_CACHE_POLICY == null || SYS_CACHE_POLICY.equals(""))
//        {
//            policy = getSystemConfigValueAct(SystemConfigContants.SYS_CACHE_POLICY);
//            // 配置信息为空或者非=memcached,非=null,则一律默认为ehcache
//            if (null == policy || "".equals(policy) || (!policy.equals(CacheKeyContants.CACHE_MEM_CACHE_POLICY)
//            		&& !policy.equals(CacheKeyContants.CACHE_NULL_CACHE_POLICY)))
//                policy = CacheKeyContants.CACHE_EH_CACHE_POLICY;
//            SYS_CACHE_POLICY = policy;
//            // 此日志只打印一次，即初始化时提示，若出现多次则说明缓存存在问题
//            SysLogger.forceInfo(CachePolicyControler.class, "缓存策略：" + SYS_CACHE_POLICY);
//        }
//        else
//            policy = SYS_CACHE_POLICY;
        return policy;
    }
    
    /**
     * 获取系统配置信息中的缓存前缀字符
     * 
     * @return
     */
    public static String getCachePrefix()
    {
        String value = getSystemConfigValueAct(SystemConfigContants.SYS_CACHE_PREFIX);
        return value;
    }
    
    /**
     * 获取系统配置信息中的缓存前缀字符
     * 
     * @return
     */
    public static String getBcmSysNodeID()
    {
        
        String value = getSystemConfigValue(SystemConfigContants.BCM_SYS_NODE_ID);
        return value;
    }
    
    /**
     * 获取系统配置信息中的商城运费商品
     * 
     * @return
     */
    public static String getBcmPostFeeProductID()
    {
        
        String value = getSystemConfigValue(SystemConfigContants.BCM_POST_FEE_PRODUCT_ID);
        return value;
    }
    
    /**
     * 获取缓存策略
     * 
     * @return
     */
//    public static ICachePolicy getCachePolicy()
//    {
//        return CachePolicyControler.getCachePolicyFromConfig();
//    }
    
    /**
     * 获取系统配置信息中的-缓存加载开关
     * 
     * @return
     */
    public static Boolean isInitCache()
    {
        // 默认为加载
        Boolean initCachce = true;
//        // 配置中的开关-弃用 chencl 2013-09-03
//        // String value=
//        // getSystemConfigValue(SystemConfigContants.SYS_INIT_CACHE);
//        // if (value !=null && value.equals("0"))
//        // initCachce= true;
//        // 判断缓存策略ehcache则直接根据数据库配置，memcached则进一步根据配置文件的配置
//        String cachePolocyStr = getCachePolicyString();
//        if (!cachePolocyStr.equals(CacheKeyContants.CACHE_MEM_CACHE_POLICY))
//            return initCachce;
//        // 根据配置文件判断是否初始化
//        initCachce = SysInitUtil.isInitCacheFromConfig();
        return initCachce;
    }
    
    /**
     * 根据配置文件判断是否加载缓存 只有使用memcached缓存策略才有不加载缓存的情况
     * 
     * @创建者 jifan
     * @创建时间Jun 19, 2013
     * @return
     */
    private static Boolean isInitCacheFromConfig()
    {
        ResourceBundle bundle = null;
        try
        {
            bundle = PropertyResourceBundle.getBundle("cache_config");
        }
        catch (Exception ex)
        {
            SysLogger.error(SysInitUtil.class, "加载资源文件cache_config.properties失败！");
        }
        String initCache = "";
        if (bundle != null)
        {
            initCache = bundle.getString("initCache");
            if (initCache == null || initCache.equals("") || initCache.equals("1"))
                return true;
            else
                return false;
        }
        return true;
    }
    
    /**
     * 获取系统配置表中的产品编码长度配置 如：10，18，19 表示产品编码长度有三种，分别为10、18、19。
     * 默认返回10、18、19;
     * 
     */
	public static String getProductCodeLength(){
		String value = getSystemConfigValue(SystemConfigContants.PRODUCT_CODE_LENGTH);
		if(CommonUtil.isEmpty(value)){
			return "10,18,19";
		}
		return value;
	}
    
    /**
     * @param propertiesMap
     * @param fileConfig
     */
    public static void properties(Map<String, String> propertiesMap, String fileConfig)
    {
        // 处理数据库配置文�?
        // String configFilePath;
        try
        {
            // configFilePath =
            // Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath()+
            // fileConfig;
            Properties properties = new Properties();
            File file = new File(fileConfig);
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            properties.load(in);
            if (propertiesMap != null)
            {
                for (String key : propertiesMap.keySet())
                {
                    properties.setProperty(key, propertiesMap.get(key));
                    // properties.put(key,propertiesMap.get(key));
                }
                OutputStream outputStream = new FileOutputStream(file);
                properties.store(outputStream, "xxxxxxxxx");
                outputStream.close();
            }
            
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
//    public static void copyXML(String backupFilePath, String filePath)
//    {
//        try
//        {
//            // String backupConfigFilePath =
//            // Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath()+
//            // backupFilePath;
//            // String configFilePath
//            // =Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath()
//            // + filePath;
//            Files.copy(new File(backupFilePath), new File(filePath));
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        
//    }
    
    // 检测是否已安装
    public static boolean isInstalled()
    {
        try
        {
            String systemConfigFilePath =
                Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath() + "shopxx.xml";
            File systemConfigFile = new File(systemConfigFilePath);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(systemConfigFile);
            Node isInstalledNode = document.selectSingleNode("/shopxx/systemConfig/isInstalled");
            if (isInstalledNode != null && StringUtils.equalsIgnoreCase(isInstalledNode.getText(), "false"))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return true;
        }
    }
    
    public static void executeSql(String sqlFilePath, String databaseHost, String databasePort, String databaseName,
        String databaseUsername, String databasePassword)
    {
        String jdbcUrl =
            "jdbc:mysql://" + databaseHost + ":" + databasePort + "/" + databaseName
                + "?useUnicode=true&characterEncoding=UTF-8";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            // 检测数据库连接
            connection = DriverManager.getConnection(jdbcUrl, databaseUsername, databasePassword);
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            String[] types = {"TABLE"};
            resultSet = databaseMetaData.getTables(null, databaseName, "%", types);
            
            // 导入数据�?
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bufferedReader = null;
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(sqlFilePath), "UTF-8"));
            String line = "";
            while (null != line)
            {
                line = bufferedReader.readLine();
                stringBuffer.append(line);
                if (null != line && line.endsWith(";"))
                {
                    System.out.println("[SHOP++安装程序]SQL: " + line);
                    preparedStatement = connection.prepareStatement(stringBuffer.toString());
                    preparedStatement.executeUpdate();
                    stringBuffer = new StringBuffer();
                }
            }
            
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                    resultSet = null;
                }
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                    preparedStatement = null;
                }
                if (connection != null)
                {
                    connection.close();
                    connection = null;
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    // -----------------获取配置参数start-----------------------------------------------------
    
    /**
     * 获取系统版本号 默认为1.0
     * 
     * @return
     */
    public static String getSysVersion()
    {
        String value = getSystemConfigValue(SystemConfigContants.SYS_VERSION);
        if (value != null && value.equals(""))
            value = "1.0";
        return value;
    }
    
    /**
     * 获取系统配置信息中的域名 默认为""
     * 
     * @return
     */
    public static String getContextUrl()
    {
        return getSystemConfigValue(SystemConfigContants.SYS_CONTEXT_URL);
    }
    
    /**
     * 获取详情图片服务器URL 默认为""
     * 
     * @return
     */
    public static String getXiangQingPhotosUrl()
    {
        return getSystemConfigValue(SystemConfigContants.SYS_PHOTOS_XIANGQING_URL);
    }
    
    /**
     * 获取图片服务器URL 默认为""
     * 
     * @return
     */
    public static String getPhotosUrl()
    {
        return getSystemConfigValue(SystemConfigContants.SYS_PHOTOS_URL);
    }
    
    /**
     * 获取文件服务器URL 默认为""
     * 
     * @return
     */
    public static String getFileUrl()
    {
        return getSystemConfigValue("SYS_FILE_URL");
    }
    
    /**
     * 获取图片服务器URL 默认为""
     * 
     * @return
     */
    public static String getNodePhotosUrl()
    {
        return getSystemConfigValue(SystemConfigContants.SYS_NODE_PHOTOS_URL);
    }
    
    /**
     * 获取图片服务器URL 默认为""
     * 
     * @return
     */
    public static String getMemberPhotosUrl()
    {
        return getSystemConfigValue(SystemConfigContants.SYS_MEMBER_PHOTOS_URL);
    }
    /**
     * 获取图片服务器URL 默认为""
     * 
     * @return
     */
    public static String getWeiShangPhotosUrl()
    {
        return getSystemConfigValue(SystemConfigContants.SYS_WEISHANG_PHOTOS_URL);
    }
    /**
     * 获取系统配置信息中的是否 新增会员时短信通知 默认为 0-不通知
     * 
     * @return
     */
    public static Boolean getIsNewMemberNotify()
    {
        String value = getSystemConfigValue(SystemConfigContants.MMM_IS_NEW_MEMBER_NOTIFY);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 获取系统配置信息中的是否 开启开关 默认为 0-不开启 1—开启
     * 
     * @return
     */
    public static Boolean getSysAutoCloseOutFlag(String configCode)
    {
        String value = getSystemConfigValue(configCode);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 节点选择管理区域权限控制 默认为，开启 0—不控制
     * 
     * @return
     */
    public static Boolean getNodeRightFlag()
    {
        String value = getSystemConfigValue(SystemConfigContants.SYS_NODE_CONTRAL_FLAG);
        if (value != null && value.equals("0"))
            return false;
        else
            return true;
    }
    
    /**
     * 获取系统自动结案提醒频率表示分钟数
     * 
     * @return
     */
    public static Integer getSysInvoiceAutoCloseOutRate()
    {
        String sysInvoiceAutoCloseOutRate = getSystemConfigValue(SystemConfigContants.SYS_INVOICE_AUTO_CLOSEOUT_RATE);
        if (CommonUtil.isEmpty(sysInvoiceAutoCloseOutRate))
        {
            return 10 * 60 * 1000;
        }
        else
        {
            return Integer.parseInt(sysInvoiceAutoCloseOutRate) * 60 * 1000;
        }
    }
    
    /**
     * 获取系统配置信息中的会员卡模式 1-无卡，2-有卡,默认为1
     * 
     * @return
     */
    public static Integer getIsMemberCardFlag()
    {
        String value = getSystemConfigValue(SystemConfigContants.MMM_IS_MEMBER_CARD_FLAG);
        if (value != null && !value.equals(""))
            try
            {
                return new Integer(value);
            }
            catch (Exception ex)
            {
                return 1;
            }
        else
            return 1;
    }
    /**
     * 在线支付是否生成账务账
     * 
     * @return
     */
    public static boolean isCreateOnlineFinvHeader() 
    {
    	String value = getSystemConfigValue(SystemConfigContants.FMM_CREATE_ONLINE_FINV_HEADER);
    	if (CommonUtil.isEmpty(value) || value.equals("1"))
    			return true;
    	else
    		return false;
    }
    
    /**
     * 工作台 二维码 用户手册是否放出来，0为放出来，1为不放出来
     * 
     * @return
     */
    public static boolean getModuleShow(){
    	 String value = getSystemConfigValue(SystemConfigContants.SYS_MODULE_SHOW);
         if (value != null && value.equals("1"))
             return true;
         else
             return false;
    }
    
    /**
     * 获取系统配置信息中的是否 已经初始化 默认为false
     * 
     * @return
     */
    public static Boolean getIsInitialized()
    {
        String value = getSystemConfigValue(SystemConfigContants.SYS_INITIALIZED);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 获取系统配置信息中的是否打开MAC 默认为false
     */
    public static Boolean getMacSwitch()
    {
        String value = getSystemConfigValue(SystemConfigContants.SYS_MAC_SWITCH);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 获取系统配置表信息中的系统标题 默认为"e服宝"
     * 
     * @return
     */
    public static String getSystemTitle()
    {
        String value = getSystemConfigValue(SystemConfigContants.SYS_SYSTEM_TITLE);
        if (value == null || value.equals(""))
            value = "e模宝";
        return value;
    }
    
    /**
     * 获取OA子系统的访问地址
     * @return
     */
    public static String getOaUrl()
    {
    	 // 当前访问的地址
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String currentUrl =
            "http://" + FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get("host")
                + request.getContextPath();
        // 配置表里面的地址 地址顺序：外网进销存放在第一位，外网报表第二位，内网进销存放在第三位，内网报表第四位
        Map<String, String> urlsMap = new LinkedHashMap<String, String>();// 存放为
        // 键值对的方式
        String OaUrl = getSystemConfigValue(SystemConfigContants.OA_URL);
        // 没有配置则直接返回
        if (OaUrl == null || OaUrl.equals(""))
            return OaUrl;
        // 如果是报表子系统 则直接返回
        if (OaUrl != null && OaUrl.equals("OA"))
            return OaUrl;
        if (CommonUtil.isNotEmpty(OaUrl))
        {
            String[] urls = OaUrl.split(sperator);
            for (int i = 0; i < urls.length; i++)
            {
                i++;
                if (i < urls.length)
                    urlsMap.put(urls[i - 1], urls[i]);
            }
        }
        
        String resultUrl = "";
        if (!urlsMap.isEmpty())
        {// 有配置
            if (urlsMap.containsKey(currentUrl))
            {
                resultUrl = urlsMap.get(currentUrl);
            }
            else
            {
                // 如果找不到 默认访问 外网地址
                resultUrl = urlsMap.values().toArray(new String[0])[0];
            }
        }
        
        return resultUrl;
    }
    
    /**
     * 获取报表子系统的访问地址 chencl 2012-10-18 默认为""
     * 
     * @return
     */
    public static String getRptUrl()
    {
        // 当前访问的地址
        HttpServletRequest request =
            (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String currentUrl =
            "http://" + FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get("host")
                + request.getContextPath();
        // 配置表里面的地址 地址顺序：外网进销存放在第一位，外网报表第二位，内网进销存放在第三位，内网报表第四位
        Map<String, String> urlsMap = new LinkedHashMap<String, String>();// 存放为
        // 键值对的方式
        String rptUrl = getSystemConfigValue(SystemConfigContants.RPT_URL);
        // 没有配置则直接返回
        if (rptUrl == null || rptUrl.equals(""))
            return rptUrl;
        // 如果是报表子系统 则直接返回
        if (rptUrl != null && rptUrl.equals("RPT"))
            return rptUrl;
        if (CommonUtil.isNotEmpty(rptUrl))
        {
            String[] urls = rptUrl.split(sperator);
            for (int i = 0; i < urls.length; i++)
            {
                i++;
                if (i < urls.length)
                    urlsMap.put(urls[i - 1], urls[i]);
            }
        }
        
        String resultUrl = "";
        if (!urlsMap.isEmpty())
        {// 有配置
            if (urlsMap.containsKey(currentUrl))
            {
                resultUrl = urlsMap.get(currentUrl);
            }
            else
            {
                // 如果找不到 默认访问 外网地址
                resultUrl = urlsMap.values().toArray(new String[0])[0];
            }
        }
        
        return resultUrl;
    }
    
    /**
     * 获取系统配置信息中的款式联想方式,实时方式 默认1 不联想
     * 
     * @return
     */
    public static Integer getStyleAssociation()
    {
        String value = getSystemConfigValue(SystemConfigContants.SYS_STYLE_ASSOCIATION);
        if (value != null && !value.equals(""))
            try
            {
                return new Integer(value);
            }
            catch (Exception ex)
            {
                return 1;
            }
        else
            return 1;
    }
    
    /**
     * 控制是否显示配货单的中的自动配货数据 默认为0
     * 
     * @return
     */
    public static Integer getIsAutoDisribution()
    {
        String value = getSystemConfigValue(SystemConfigContants.PSS_IS_AUTO_DISTRIBUTION);
        if (value != null && !value.equals(""))
            try
            {
                return new Integer(value);
            }
            catch (Exception ex)
            {
                return 0;
            }
        else
            return 0;
    }
    
    /**
     * 获取系统配置信息中的是否安装后需激活 ，用于离线版激活控制 默认为不用激活
     * 
     * @return
     */
    public static Boolean isActivate()
    {
        String value = getSystemConfigValue(SystemConfigContants.SYS_IS_ACITVATE);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 是否支持物流中心 1支持；0不支持
     * 
     * @return 默认为0
     */
    public static Integer getLogisticsCenter()
    {
        String value = getSystemConfigValue(SystemConfigContants.SYS_LOG_IS_TICS_CENTER);
        if (value != null && !value.equals(""))
            try
            {
                return new Integer(value);
            }
            catch (Exception ex)
            {
                return 0;
            }
        else
            return 0;
    }
    
    /**
     * 获取系统配置信息中的是否调货时钱流是否经过公司，0：表示不通过第三方结算，1：表示通过第三方结算，2： 表示只有节点类型不是加盟和加盟调货时才通过第三方结算,加盟与加盟调货店铺双方结算 默认为0
     */
    public static Integer getCallInOutByCompanyFlag()
    {
        String value = getSystemConfigValue(SystemConfigContants.FMM_CALLINOUT_BYCOMPANY_FLAG);
        if (value != null && !value.equals(""))
            return new Integer(value);
        else
            return 0;
        
    }
    
    /**
     * 共享库存钱流是否经过公司 0：表示不通过第三方结算，1：表示通过第三方结算，2： 表示只有节点类型不是加盟和加盟调货时才通过第三方结算,加盟与加盟调货店铺双方结算 默认为0
     */
    public static Integer getShareStockByCompanyFlag()
    {
        String value = getSystemConfigValue(SystemConfigContants.FMM_SHARESTOCK_BYCOMPANY_FLAG);
        if (value != null && !value.equals(""))
            return new Integer(value);
        else
            return 0;
    }
    
    /**
     * 获取系统配置信息中调入调出需经过公司时财务帐是否审核 默认为true
     */
    public static Boolean getFinvHeaderStatus()
    {
        String value = getSystemConfigValue(SystemConfigContants.FMM_FINV_HEADER_STATUS);
        if (value != null && value.equals("0"))
            return false;
        else
            return true;
    }
    
    /**
     * 获取系统配置信息中共享销售是否需经过公司 默认为true
     */
    public static Integer getShareSaleByCompanyFlag()
    {
        return getShareStockByCompanyFlag();
    }
    
    /**
     * 获取系统配置信息中是否使用共享价 默认为false
     */
    public static Boolean getSharePriceUsed()
    {
        String value = getSystemConfigValue(SystemConfigContants.PSS_SHARE_PRICE_USED);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 网上商城当前使用模版
     * 
     * @return
     */
    public static String getBcmTemplatePathName()
    {
        return getSystemConfigValue(SystemConfigContants.BCM_TEMPLATE_PATH_NAME);
    }
    
    /**
     * 会员卡是否先录入系统 1(true)表示已录入;0(false)表示未录入 已录入：即在创建会员的时候直接判断是否已使用 未录入：即创建会员时需额外创建对应的卡信息 默认为false
     * 
     * @return
     */
    public static Boolean getCardHaveInput()
    {
        String value = getSystemConfigValue(SystemConfigContants.MMM_CARD_HAVE_INPUT);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 会员换卡的时候是否赠送优惠券和发送短信开关 默认为false
     * 
     * @return
     */
    public static Boolean getChangeCardPromotion()
    {
        String value = getSystemConfigValue(SystemConfigContants.MMM_CHANGE_CARD_PROMOTION);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 自动配货单据类型 默认23
     * 
     * @return
     */
    public static Integer getDistributionAssociationInvoiceType()
    {
        String value = getSystemConfigValue(SystemConfigContants.PSS_DISTRIBUTION_ASSOCIATION_INVOICE_TYPE);
        if (value != null && !value.equals(""))
            try
            {
                return new Integer(value);
            }
            catch (Exception ex)
            {
                return 23;
            }
        else
            return 23;
    }
    
    /**
     * 出库单是否要监控区下拉框 默认为false
     */
    public static Boolean getShowArea()
    {
        String value = getSystemConfigValue(SystemConfigContants.PSS_SHOW_AREA);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 获取相应公司主页的访问地址
     * 
     * @return
     */
    public static String getCompangHomepage()
    {
        return getSystemConfigValue(SystemConfigContants.MMM_COMPANG_HOMEPAGE);
    }
    
    /**
     * 是否强制提醒买单付款方式 默认为false
     */
    public static Boolean getRemindPayment()
    {
        String value = getSystemConfigValue(SystemConfigContants.SMM_REMIND_PAYMENT);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 买单导购员是否必输 默认为false
     */
    public static Boolean getSalesRequired()
    {
        String value = getSystemConfigValue(SystemConfigContants.SMM_SALES_REQUIRED);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 是否确认配货仓库 默认为false
     */
    public static Boolean getConfirmWarehouse()
    {
        String value = getSystemConfigValue(SystemConfigContants.PSS_IS_CONFIRMWAREHOUSE);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 是否把鞋类单独做配货单 默认为false
     */
    public static Boolean getShoeAlone()
    {
        String value = getSystemConfigValue(SystemConfigContants.PSS_SHOE_ALONE);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 唯一码：是否启用唯一码 默认为false
     */
    public static Boolean getUseItemSwitch()
    {
        String value = getSystemConfigValue(SystemConfigContants.PSS_USE_ITEM_SWITCH);
        // 1-开启，2-开启并强校验,3-销售也开启并强校验
        if (value != null && (value.equals("1") || value.equals("2") || value.equals("3")))
            return true;
        else
            return false;
    }
    
    /**
     * 唯一码：判断扫描的条码是否符合唯一码规则
     * 
     * @param code 扫描的条码
     * @return
     */
    public static Boolean getUseItemSwitch(String code)
    {
        if (code == null || code.trim().equals(""))
            return false;
        // 系统配置有开启
        if (getUseItemSwitch())
        {
            // 符合唯一码规则
            if (code.length() == SystemConfigContants.ITEM_CODE_LENGTH)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    // /**
    // * 唯一码：其他单据是否启用唯一码强校验
    // *
    // * @return
    // */
    // public static Boolean getUseItemSwitchCheck() {
    // String value = getSystemConfigValue(SystemConfigContants.PSS_USE_ITEM_SWITCH);
    // // 2-开启并强校验
    // if (value != null && (value.equals("2") || value.equals("3")))
    // return true;
    // else
    // return false;
    // }
    //
    // /**
    // * 唯一码：买单是否启用唯一码强校验
    // *
    // * @return
    // */
    // public static Boolean getUseItemSwitchCheckForSale() {
    // String value = getSystemConfigValue(SystemConfigContants.PSS_USE_ITEM_SWITCH);
    // // 2-开启并强校验
    // if (value != null && value.equals("3"))
    // return true;
    // else
    // return false;
    // }
    
    /**
     * 获取积分（公积金）节点编号
     * 
     * @return
     */
    public static String getIntegralPoolNodeCode()
    {
        String value = getSystemConfigValue(SystemConfigContants.MMM_INTEGRAL_POOL_NODE_CODE);
        return value == null || "".equals(value) ? "" : value;
    }
    
    /**
     * 获取手机商城URL 默认为"http://www.tofan.cn/"
     * 
     * @return
     */
    public static String getMobileShopUrl()
    {
        String value = getSystemConfigValue(SystemConfigContants.SYS_MOBILE_SHOP_URL);
        if (value == null || value.equals(""))
            value = "http://www.tofan.cn/";
        return value;
    }
    
    /**
     * 商户主QQ 商户主号在微购物平台使用的QQ号码
     * 
     * @return
     */
    
    public static String getWGWUin()
    {
        String value = getSystemConfigValue(SystemConfigContants.WGW_QQ_UIN);
        return value == null ? "" : value;
    }
    
    /**
     * 在微购物平台建立应用使用的AppOAuthId
     * 
     * @return
     */
    
    public static String getWGWAppOAuthID()
    {
        String value = getSystemConfigValue(SystemConfigContants.WGW_APP_OAUTH_ID);
        return value == null ? "" : value;
    }
    
    /**
     * 在微购物平台建立应用使用的AppOAuthKey
     * 
     * @return
     */
    public static String getWGWAppOAuthKey()
    {
        String value = getSystemConfigValue(SystemConfigContants.WGW_APP_OAUTH_KEY);
        return value == null ? "" : value;
    }
    
    /**
     * 在微购物平台建立应用使用的accessToken
     * 
     * @return
     */
    public static String getWGWAccessToken()
    {
        String value = getSystemConfigValue(SystemConfigContants.WGW_ACCESS_TOKEN);
        return value == null ? "" : value;
    }
    
    /**
     * 商户主号是指商户在微购物平台创建应用推送接口使用的的签名密钥secret_key
     * 
     * @return
     */
    public static String getWGWAppSecretKey()
    {
        String value = getSystemConfigValue(SystemConfigContants.WGW_APP_SECRET_KEY);
        return value == null ? "" : value;
    }
    
    /**
     * 得到微支付的PaySignKey
     * 
     * @return
     */
    public static String getWGWPaySecretKey()
    {
        String value = getSystemConfigValue(SystemConfigContants.WGW_PAY_SECRET_KEY);
        return value == null ? "" : value;
    }
    
    /**
     * 买满如：880元，普通会员升级vip开关
     * 
     * @return
     */
    public static boolean getBuyUpgradeVIPSwitch()
    {
        String value = getSystemConfigValue(SystemConfigContants.MMM_BUY_UPGRADE_VIP_SWITCH);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
	/**
	 * 开启淘宝菜鸟落地配开关
	 * 
	 * @return
	 */
	public static boolean getQimenSwitch() {
		String value = getSystemConfigValue(SystemConfigContants.TAOBAO_QIMEN_SWITCH);
		if (value != null && value.equals("1"))
			return true;
		else
			return false;
	}
    
	/**
	 * 调货件数限制
	 * 
	 * @return
	 */
	public static int getCallInLimitNum() {
		String value = getSystemConfigValue(SystemConfigContants.LIMIT_CALL_IN_NUM);
		if (value != null && !value.equals(""))
			return Integer.parseInt(value);
		else
			return 0;
	}

    /**
     * 买满多少升级为vip
     * 
     * @return
     */
    public static int getOnceBuyUpgradeVIP()
    {
        String value = getSystemConfigValue(SystemConfigContants.MMM_ONCE_BUY_UPGRADE_VIP);
        if (value != null)
            try
            {
                return Integer.parseInt(value);
            }
            catch (Exception e)
            {
                return 880;
            }
        else
            return 880;
    }
    
    /**
     * 满多少积分可升级为vip
     * 
     * @return
     */
    public static int getIntegerUpgradeVIP()
    {
        String value = getSystemConfigValue(SystemConfigContants.MMM_INTEGER_UPGRADE_VIP);
        if (value != null)
            try
            {
                return Integer.parseInt(value);
            }
            catch (Exception e)
            {
                return 2000;
            }
        else
            return 2000;
    }
    
    /**
     * 微支付发货状态post的Url
     * 
     * @return
     */
    public static String getWeiPaySendGoodsNotifyUrl()
    {
        String value = getSystemConfigValue(SystemConfigContants.BCM_WEI_PAY_SEND_GOODS_NOTIFY_URL);
        return value == null ? "" : value;
    }
    
    /**
     * 微支付维权post的Url
     * 
     * @return
     */
    public static String getWeiPayComplaintUrl()
    {
        String value = getSystemConfigValue(SystemConfigContants.BCM_WEI_PAY_COMPLAINT_URL);
        return value == null ? "" : value;
    }
    
    /**
     * 手机商城支付的URL
     * 
     * @return
     */
    public static String getMobileMallUrl()
    {
        String value = getSystemConfigValue(SystemConfigContants.BCM_MOBILE_MALL_URL);
        return value == null ? "" : value;
    }
    
    /**
     * 获取系统配置信息中的网上商城是否支持货到付款
     */
    public static Boolean getSupportCod()
    {
        String value = getSystemConfigValue(SystemConfigContants.BCM_SUPPORT_COD);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 获取系统配置信息中的供货财务账审核是否影响库存
     */
    public static Boolean getReceiveChangeStock()
    {
        String value = getSystemConfigValue(SystemConfigContants.FMM_RECEIVE_CHANGE_STOCK);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 获取系统配置信息中的配货实时锁定可配库存 0 –false 表示不开启，1-表示开启 默认不开启；
     */
    public static Boolean getAllocateStockLock(){
        String value = getSystemConfigValue(SystemConfigContants.PSS_ALLOCATE_STOCK_LOCK);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 获取系统配置信息中的 是否开启避规调货钻空子模式 （ 销售<调入-调出,且 退货率=100%：净供货数量=供货-退货）   0 –false 表示不开启，1-表示开启 默认不开启；
     */
    public static Boolean getAvoidCallMode(){
        String value = getSystemConfigValue(SystemConfigContants.PSS_AVOID_CALL_MODE);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 买单是否开启积分抵现
     */
    public static Boolean getIntegralSale (){
        String value = getSystemConfigValue(SystemConfigContants.SMM_INTEGRAL_SALE);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 买单是否开启积分抵现
     */
    public static Boolean getRedMoneySale (){
        String value = getSystemConfigValue(SystemConfigContants.SMM_RED_MONEY_SALE);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 商城买单是否开启积分抵现
     */
    public static Boolean getBcmRedMoneySale (){
        String value = getSystemConfigValue(SystemConfigContants.BCM_RED_MONEY_SALE);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
    
    /**
     * 买单线上支付 0 –false 表示不开启，1-表示开启 默认不开启；
     */
    public static Boolean getSalesOnlinePay(){
        String value = getSystemConfigValue(SystemConfigContants.SMM_SALES_ONLINE_PAY);
        if (value != null && value.equals("1"))
            return true;
        else
            return false;
    }
   
    
    @Resource(name = "systemConfigBS")
    public synchronized void setsystemConfigBS(ISystemConfigBS systemConfigBS)
    {
        SysInitUtil.systemConfigBS = systemConfigBS;
    }
    
    public static synchronized void setsystemConfigBSOut(ISystemConfigBS systemConfigBS)
    {
        SysInitUtil.systemConfigBS = systemConfigBS;
    }
    
    /**
     * ERP数据库jdbc URL
     */
    public static String getErpJdbcUrl()
    {
        String value = getSystemConfigValue(SystemConfigContants.PMM_ERP_JDBC_URL);
        return value == null ? "" : value;
    }
    
    /**
     * 
     * @description： 系统最大节点数
     * @author fangyuan
     * @date 2015-4-27 下午02:57:34
     */
	public static String getMaxNodeNum() {
		String value = getSystemConfigValue(SystemConfigContants.SYS_MAX_NODE_NUM);
		return value == null ? "" : value;
	}
	
	 /**
     * 
     * @description： 会员积分有效期（默认12个月，负数为不开启控制，单位为月）
     * @author fangyuan
     * @date 2015-4-27 下午02:57:34
     */
	public static int getMemberIntegralValid() {
		String value = getSystemConfigValue(SystemConfigContants.MMM_MEMBER_INTEGRAL_VALID);
		if (value != null){
			try {
				return Integer.parseInt(value);
			} catch (Exception e) {
				return 12;
			}
		}else
			return 12;
	}
	
	/**
	 * 
	 * @description： 登陆界面节点输入方式 1：表示下拉选择 返回TRUE 0：表示 输入框输入 返回 FALSE 如果没有配置默认为 1,返回TRUE;
	 * @author liupg
	 * @date 2015-5-15 下午02:57:34
	 */
	public static Boolean getNodeShowIsSelect() {
		String value = getSystemConfigValue(SystemConfigContants.NODE_SHOW_IS_SSELECT);
		if (value != null) {
			if (value.equals("1")) {
				return true;
			}else if (value.equals("0")){
				return false;
			}else{
				return true;
			}
		} else{
			return true;
		}	
	}
	
	/**
	 * 
	 * @description： 开单平台通行证开关，true-开启，则需通过平台注册登录和提交创建节点的申请，fake-关闭则按原有e服宝的逻辑，默认为false
	 * @author fangyuan
	 * @date 2015-5-11 上午09:23:23
	 */
	public static boolean getTpassSwitch() {
		String value = getSystemConfigValue(SystemConfigContants.SYS_TPSSS_SWITCH);
		if (value != null && value.equals("1"))
			return true;
		else
			return false;
	}
	
	/**
	 * 销售单购买成功后通知处理,true-执行通知，默认为 false
	 * @return
	 */
	public static boolean getSaleInvoiceNotice() {
		String value = getSystemConfigValue(SystemConfigContants.SALE_INVOCIE_NOTICE);
		if (value != null && value.equals("1"))
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @description： 获取VIP购买的商品Id，多个以逗号隔开
	 * @author fangyuan
	 * @date 2015-5-11 上午09:23:23
	 */
	public static String getVipProduct() {
		String value = getSystemConfigValue(SystemConfigContants.MMM_VIP_PRODUCT);
		return value == null ? "" : value;
	}
	
	/**
	 * 校验是否购买VIP
	 * @param productId 传入的商品Id，传空则表示不做商品校验
	 * @return
	 */
	public static boolean isVipProduct(String productId){
		String vipProduct = SysInitUtil.getVipProduct();
		//购买VIP的配置不为空或0
		if(CommonUtil.isNotEmpty(vipProduct) && !vipProduct.equals("0")){
			//传入的商品Id不为空
			if(CommonUtil.isNotEmpty(productId) ){
				//传入的商品Id 在配置内
				if(vipProduct.contains(productId))
					return true;
				else
					return false;
			}else
				return true;
				
		}else
			return false;
	}
	
	/**
	 * 登陆时是否自动加载代办任务，1-加载，0-不加载，默认为加载
	 * @return
	 */
	public static boolean getTaskLoad() {
		String value = getSystemConfigValue(SystemConfigContants.SYS_TASK_LOAD);
		if (value != null && value.equals("0"))
			return false;
		else
			return true;
	}
	
	/**
	 * 打印每页多少列商品
	 */
	public static int getPrintColumnNum() {
		String value = getSystemConfigValue(SystemConfigContants.PSS_PRINT_COLUMN_NUM);
		if (value != null && !value.equals("") )
			return Integer.valueOf(value);
		else
			return 0;
	}
	
	   /**
     * 开单服务号的URL
     */
    public static String getPlatformUrl()
    {
        String value = getSystemConfigValue(SystemConfigContants.PLATFORM_RUL);
        return value == null ? "" : value;
    }
    

	   /**
  * 微信接口地址的URL
  */
 public static String getWeixinInterfaceUrl()
 {
     String value = getSystemConfigValue(SystemConfigContants.WXN_WEIXIN_INTERFACE_URL);
     return value == null ? "" : value;
 }
 /**
	 * 商城评论是立即显示
	 * @return
	 */
	public static boolean getBcmCommentShow() {
		String value = getSystemConfigValue(SystemConfigContants.BCM_COMMENT_SHOW);
		if (value != null && value.equals("1"))
			return true;
		else
			return false;
	}
	
	 /**
	 * 商城买单是否自动评论
	 * @return
	 */
	public static boolean getBcmAutoComment() {
		String value = getSystemConfigValue(SystemConfigContants.BCM_AUTO_COMMENT);
		if (value != null && value.equals("1"))
			return true;
		else
			return false;
	}
 
	/**
	 * 商城买单是否开启买单成为代理
	 * @return
	 */
	public static boolean getSaleToAgent() {
		String value = getSystemConfigValue(SystemConfigContants.BCM_SALE_AGENT);
		if (value != null && value.equals("1"))
			return true;
		else
			return false;
	}
	
	/**
	 * 是否开启利润分成超预估
	 */
	public static boolean getBcmProfitBefore(){
		String value = getSystemConfigValue(SystemConfigContants.BCM_PROFIT_BEFORE);
		if (value != null && value.equals("1"))
			return true;
			else
			return false;
	}
	
	/**
	 * 是否开启改户口
	 */
	public static boolean getMmmChangeHukou(){
		String value = getSystemConfigValue(SystemConfigContants.MMM_CHANGE_HUKOU);
		if (value != null && value.equals("1"))
			return false;
			else
			return true;
	}
	
	/**
     * 获取默认微商节点
     * 
     * @return
     */
    public static String getDefaultWeishangNode()
    {
        String value = getSystemConfigValue(SystemConfigContants.DEFAULT_WEISHANG_NODE);
        return value;
    }
    
    
    /**
     * 微商自动审核
     * @return
     */
    public static boolean getAutoAgentAudit()
    {
    	String value = getSystemConfigValue(SystemConfigContants.AUTO_AGENT_AUDIT);
		if (value != null && value.equals("1"))
			return true;
			else
			return false;
    }
    /**
     * 获取系统配置信息中的新手任务商品
     * 
     * @return
     */
    public static String getRookieTaskStyle()
    {
        String value = getSystemConfigValue(SystemConfigContants.ROOKIE_TASK_STYLE);
        return value;
    }
    /**
     * 获取完成码
     * @return
     */
    public static String getFinishCode(){
    	String value = getSystemConfigValue("SYS_FINISH_CODE");
        return value;
    }
    
}
