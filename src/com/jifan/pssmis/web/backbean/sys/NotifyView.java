package com.jifan.pssmis.web.backbean.sys;
 
import java.net.URLEncoder;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.protocol.HTTP;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
 
@ManagedBean
@RequestScoped
public class NotifyView {
     
    private final static String CHANNEL = "/notify";
     
    private String summary;
     
    private String detail;
     
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
     
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
     
    public void send() {
    	try{
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
//        summary = URLEncoder.encode(summary, HTTP.UTF_8);
//        detail = URLEncoder.encode(detail, HTTP.UTF_8);
        eventBus.publish(CHANNEL, new FacesMessage(StringEscapeUtils.escapeHtml(summary), StringEscapeUtils.escapeHtml(detail)));
    	}
        catch (Exception e){
        	
        }
    }
}