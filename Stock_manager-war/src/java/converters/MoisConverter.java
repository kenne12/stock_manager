package converters;

import entities.Mois;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sessions.MoisFacadeLocal;
import utils.JsfUtil;

@FacesConverter("moisConverter")
public class MoisConverter
        implements Converter {

    @EJB
    private MoisFacadeLocal ejbFacade;

    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        /* 24 */ if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            /* 25 */ return null;
        }
        /* 27 */ return this.ejbFacade.find(getKey(value));
    }

    Integer getKey(String value) {
        /* 32 */ Integer key = Integer.valueOf(value);
        /* 33 */ return key;
    }

    String getStringKey(Integer value) {
        /* 37 */ return "" + value;
    }

    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        /* 42 */ if (object == null || (object instanceof String && ((String) object)
                /* 43 */.length() == 0)) {
            /* 44 */ return null;
        }
        /* 46 */ if (object instanceof Mois) {
            /* 47 */ Mois m = (Mois) object;
            /* 48 */ return getStringKey(m.getIdmois());
        }
        /* 50 */ Logger.getLogger(getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Mois.class.getName()});
        /* 51 */ return null;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\converters\MoisConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
