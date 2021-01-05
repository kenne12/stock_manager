package sessions;

import entities.Unite;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UniteFacade
        extends AbstractFacade<Unite>
        implements UniteFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 24 */ return this.em;
    }

    public UniteFacade() {
        /* 28 */ super(Unite.class);
    }
}
