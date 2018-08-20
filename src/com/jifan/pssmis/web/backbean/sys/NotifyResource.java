package com.jifan.pssmis.web.backbean.sys;
 
import java.net.URLDecoder;

import javax.faces.application.FacesMessage;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;
 
@PushEndpoint("/notify")
public class NotifyResource {
         
    @OnMessage(encoders = {JSONEncoder.class})
    public FacesMessage onMessage(FacesMessage message) {
//    	try{
//    	message.setSummary(URLDecoder.decode(message.getSummary()));
//    	message.setDetail(URLDecoder.decode(message.getDetail()));
//    	message.setSummary("哈哈");
//    	}catch (Exception e){
//    		
//    	}
        return message;

    }
 
}