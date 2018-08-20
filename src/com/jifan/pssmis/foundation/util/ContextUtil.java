package com.jifan.pssmis.foundation.util;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.PropertyResolver;
import javax.faces.el.ValueBinding;
import javax.faces.el.VariableResolver;
import javax.faces.webapp.UIComponentTag;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.jsf.FacesContextUtils;


/**
 * 
 * @author jifan
 *
 */
public class ContextUtil {
	private static final Log log = LogFactory.getLog(ContextUtil.class);
	/**
	 * 	求解JSF表达式
	 * @param expr 例如： #{beanObj.name}
	 * @return
	 */
	public static Object eval(String expr) {
		Object obj = null;
		try {
			//logger.info("************expr = " + expr);
			if(expr != null && isValueReference(expr)) {
				FacesContext context = FacesContext.getCurrentInstance();
				if(context == null) {
					log.error("----------- 没有激活FacesServlet控制器！--------------");
					return null;
				}
				ValueBinding binding = context.getApplication().createValueBinding(expr);
				if(binding == null) {
					log.error("----------- JSF表达式有误: [" + expr + "]----------");
					return null;
				}
				obj = binding.getValue(context);
			}else {
				obj = expr;
			}			
		} catch(Exception ex) {
			//ex.printStackTrace();
			if(log.isFatalEnabled()) {
				log.fatal(ex);
			}
		}		
		return obj;
	}
	
	/**
	 * 返回相对路径文件的绝对路径
	 * @return
	 */
	public static String getRealPath(String shortpath) {
		ExternalContext ec= getExternalContext();
		return ec.getRealPath(shortpath);
	}
	
	/**
	 * 根据配置文件中的配置的名称，获取BackingBean 对象
	 * @param beanName
	 * @return
	 */
	public static Object getManagedBean(String beanName) {
		/*if(beanName.indexOf("#{") < 0) {
			beanName = "#{" + beanName;
		}
		if(beanName.indexOf("}") != beanName.length() - 1) {
			beanName += "}";
		}*/
		beanName = "#{" + beanName + "}";
		return eval(beanName);
	}
	/**
	 *
	 *功能描述： 获取JSF的属性解析器
	 * @return
	 */
	public static PropertyResolver getPropertyResolver() {
		return FacesContext.getCurrentInstance().getApplication().getPropertyResolver();
	}
	/**
	 * 
	 *功能描述： 获取JSF的变量解析器
	 * @return
	 */
	public static VariableResolver getVariableResolver() {
		return FacesContext.getCurrentInstance().getApplication().getVariableResolver();
	}
	/**
	 * 
	 *功能描述： 获取JSF的视图处理器
	 * @return
	 */
	public static ViewHandler getViewHandler() {
		return FacesContext.getCurrentInstance().getApplication().getViewHandler();
	}
	/**
	 * 判断是否为值引用(值绑定)表达式
	 * @param expression
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "deprecation" })
	public static boolean isValueReference(String expression) {
		return UIComponentTag.isValueReference(expression);
	}
	/**
	 * 
	 *功能描述：获取JSF对应的外部上下文
	 * @return
	 */
	public static ExternalContext getExternalContext () {
		FacesContext fc = FacesContext.getCurrentInstance();
		return fc.getExternalContext();
	}
	/**
	 * 
	 *功能描述：获取JSF的request 对象
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest)getExternalContext().getRequest();
	}
	/**
	 * 
	 *功能描述：获取JSF的session 对象
	 * @return
	 */
	public static HttpSession getSession (boolean flag) {
		return (HttpSession)getExternalContext().getSession(flag);
	}
	/**
	 * 
	 *功能描述：获取JSF的Application 对象
	 * @return
	 */
	public static Application getApplication () {
		FacesContext fc = FacesContext.getCurrentInstance();
		return fc.getApplication();
	}
	/**
	 * 获取Spring 配置的bean
	 *功能描述：
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName) {
		FacesContext fc = FacesContext.getCurrentInstance();
		return FacesContextUtils.getWebApplicationContext(fc).getBean(beanName);
	}
	
	/**
	 * 获取访问者的IP地址
	 * @return
	 */
	public static String getVisitorIP(){
		//获取ip
		FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest)fc.getExternalContext().getRequest();
        String ip = request.getHeader("x-forwarded-for");   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
             ip = request.getHeader("Proxy-Client-IP");   
         }   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
             ip = request.getHeader("WL-Proxy-Client-IP");   
         }   
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
             ip = request.getRemoteAddr();   
         } 
        return ip;
	}
	

}
