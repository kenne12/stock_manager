package sessions;

import entities.AnneeMois;
import entities.Inventaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class InventaireFacade
        extends AbstractFacade<Inventaire>
        implements InventaireFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 28 */ return this.em;
    }

    public InventaireFacade() {
        /* 32 */ super(Inventaire.class);
    }

    @Override
    public Long nextVal() {
        /* 37 */ Query query = this.em.createQuery("SELECT MAX(i.idinventaire) FROM Inventaire i");
        /* 38 */ Long result = (Long) query.getSingleResult();
        /* 39 */ if (result == null) {
            /* 40 */ result = (1L);
        } else {
            /* 42 */ result = (result + 1L);
        }
        /* 44 */ return result;
    }

    @Override
    public Long nextVal(AnneeMois anneeMois) {
        /* 49 */ Query query = this.em.createQuery("SELECT COUNT(i.idinventaire) FROM Inventaire i WHERE i.idmois.idAnneeMois=:mois");
        /* 50 */ query.setParameter("mois", anneeMois.getIdAnneeMois());
        /* 51 */ Long result = (Long) query.getSingleResult();
        /* 52 */ if (result == null) {
            /* 53 */ result = (1L);
        } else {
            /* 55 */ result = (result + 1L);
        }
        /* 57 */ return result;
    }

    @Override
    public List<Inventaire> findAllRange() throws Exception {
        /* 62 */ List<Inventaire> inventaires = null;
        /* 63 */ Query query = this.em.createQuery("SELECT i FROM Inventaire i ORDER BY i.dateInventaire DESC");
        /* 64 */ inventaires = query.getResultList();
        /* 65 */ return inventaires;
    }
}
