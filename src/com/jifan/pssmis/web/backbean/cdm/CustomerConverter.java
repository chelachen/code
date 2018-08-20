package com.jifan.pssmis.web.backbean.cdm;
 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.jifan.pssmis.model.bo.cdm.CustomerBO;
 
@FacesConverter("customerConverter")
public class CustomerConverter implements Converter {
 
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
            	CustomerBB customerBB = (CustomerBB) fc.getExternalContext().getSessionMap().get("customerBB");
                return customerBB.getCustomerBOByID(value);
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "选择失败", "没有对应的客户。"));
            }
        }
        else {
            return new CustomerBO();
        }
    }
 
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null && object !="") {
            return String.valueOf(((CustomerBO) object).getId());
        }
        else {
            return "";
        }
    }   
}         