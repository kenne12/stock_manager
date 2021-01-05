package sessions;

import entities.Boutique;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BoutiqueFacade
        extends AbstractFacade<Boutique>
        implements BoutiqueFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        /* 24 */ return this.em;
    }

    public BoutiqueFacade() {
        /* 28 */ super(Boutique.class);
    }
}
