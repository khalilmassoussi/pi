package converter;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import persistence.Articles;
import services.BasicOpsLocal;

@ManagedBean
@RequestScoped
@FacesConverter(forClass = Articles.class)

public class AConverter implements Converter {

	@EJB
	private BasicOpsLocal basicOps;

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
		if (modelValue == null) {
			return "";
		}

		if (modelValue instanceof Articles) {
			return String.valueOf(((Articles) modelValue).getId());
		} else {
			throw new ConverterException(new FacesMessage(modelValue + "is not a valid User entity"));
		}
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		if (submittedValue == null || submittedValue.isEmpty()) {
			return null;
		}

		try {
			return basicOps.findArticlesById(Integer.valueOf(submittedValue));
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(submittedValue + " is not a valid User ID"), e);
		}
	}

}