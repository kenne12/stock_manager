package sessions;

import entities.Famille;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FamilleFacade extends AbstractFacade<Famille> implements FamilleFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 27 */ return this.em;
    }

    public FamilleFacade() {
        /* 31 */ super(Famille.class);
    }

    @Override
    public Integer nextVal() {
        /* 36 */ Query query = this.em.createQuery("SELECT MAX(f.idfamille) FROM Famille f");
        /* 37 */ Integer result = (Integer) query.getSingleResult();
        /* 38 */ if (result == null) {
            /* 39 */ result = (1);
        } else {
            /* 41 */ result = (result + 1);
        }
        /* 43 */ return result;
    }

    @Override
    public List<Famille> findAllRange() {
        /* 48 */ List<Famille> familles = null;
        /* 49 */ Query query = this.em.createQuery("SELECT f FROM Famille f ORDER BY f.nom");
        /* 50 */ familles = query.getResultList();
        /* 51 */ return familles;
    }
}
