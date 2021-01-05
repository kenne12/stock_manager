package converters;

import entities.Famille;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessions.FamilleFacadeLocal;
import utils.JsfUtil;

@FacesConverter("familleConverter")
public class FamilleConverter
        implements Converter {

    @EJB
    private FamilleFacadeLocal ejbFacade;

    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        /* 22 */ if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            /* 23 */ return null;
        }
        /* 25 */ return this.ejbFacade.find(getKey(value));
    }

    Integer getKey(String value) {
        /* 30 */ Integer key = Integer.valueOf(value);
        /* 31 */ return key;
    }

    String getStringKey(Integer value) {
        /* 35 */ return "" + value;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        /* 40 */ if (object == null || (object instanceof String && ((String) object)
                /* 41 */.length() == 0)) {
            /* 42 */ return null;
        }
        /* 44 */ if (object instanceof Famille) {
            /* 45 */ Famille o = (Famille) object;
            /* 46 */ return getStringKey(o.getIdfamille());
        }
        /* 48 */ Logger.getLogger(getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Famille.class.getName()});
        /* 49 */ return null;
    }
}
