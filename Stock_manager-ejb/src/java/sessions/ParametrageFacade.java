package sessions;

import entities.Parametrage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ParametrageFacade
        extends AbstractFacade<Parametrage>
        implements ParametrageFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        /* 24 */ return this.em;
    }

    public ParametrageFacade() {
        /* 28 */ super(Parametrage.class);
    }
}
