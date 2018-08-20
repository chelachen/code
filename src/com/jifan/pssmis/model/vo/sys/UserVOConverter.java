package com.jifan.pssmis.model.vo.sys;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("userConverter")
public class UserVOConverter implements Converter {
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if ((value != null) && (value.trim().length() > 0)) {
			try {
				System.out.println(value);
			} catch (NumberFormatException e) {
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error",
					"Not a valid theme."));
			}
		}

		return null;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((UserVO) object).getId());
		}

		return null;
	}
}