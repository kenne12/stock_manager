package sessions;

import entities.Menu;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MenuFacade
        extends AbstractFacade<Menu>
        implements MenuFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 24 */ return this.em;
    }

    public MenuFacade() {
        /* 28 */ super(Menu.class);
    }
}
