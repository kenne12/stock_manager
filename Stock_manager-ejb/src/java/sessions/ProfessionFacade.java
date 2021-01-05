package sessions;

import entities.Profession;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProfessionFacade
        extends AbstractFacade<Profession>
        implements ProfessionFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 24 */ return this.em;
    }

    public ProfessionFacade() {
        /* 28 */ super(Profession.class);
    }
}
