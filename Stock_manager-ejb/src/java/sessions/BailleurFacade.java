package sessions;

import entities.Bailleur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class BailleurFacade
        extends AbstractFacade<Bailleur>
        implements BailleurFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        /* 28 */ return this.em;
    }

    public BailleurFacade() {
        /* 32 */ super(Bailleur.class);
    }

    @Override
    public Integer nextVal() {
        /* 37 */ Query query = this.em.createQuery("SELECT MAX(b.idbailleur) FROM Bailleur b");
        /* 38 */ Integer result = (Integer) query.getSingleResult();
        /* 39 */ if (result == null) {
            /* 40 */ result = Integer.valueOf(1);
        } else {
            /* 42 */ result = Integer.valueOf(result.intValue() + 1);
        }
        /* 44 */ return result;
    }

    @Override
    public List<Bailleur> findAllRange() {
        /* 49 */ Query query = this.em.createQuery("SELECT b FROM Bailleur b ORDER BY b.nom");
        /* 50 */ return query.getResultList();
    }
}
