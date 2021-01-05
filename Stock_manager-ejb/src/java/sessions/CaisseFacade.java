package sessions;

import entities.Caisse;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CaisseFacade
        extends AbstractFacade<Caisse>
        implements CaisseFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        /* 26 */ return this.em;
    }

    public CaisseFacade() {
        /* 30 */ super(Caisse.class);
    }

    @Override
    public Integer nextVal() {
        /* 35 */ Query query = this.em.createQuery("SELECT MAX(c.idcaisse) FROM Caisse c");
        /* 36 */ Integer result = (Integer) query.getSingleResult();
        /* 37 */ if (result == null) {
            /* 38 */ result = Integer.valueOf(1);
        } else {
            /* 40 */ result = Integer.valueOf(result.intValue() + 1);
        }
        /* 42 */ return result;
    }
}
