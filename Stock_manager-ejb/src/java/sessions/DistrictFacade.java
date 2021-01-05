package sessions;

import entities.District;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DistrictFacade
        extends AbstractFacade<District>
        implements DistrictFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        /* 27 */ return this.em;
    }

    public DistrictFacade() {
        /* 31 */ super(District.class);
    }

    @Override
    public Integer nextVal() {
        /* 36 */ Query query = this.em.createQuery("SELECT MAX(d.iddistrict) FROM District d");
        /* 37 */ Integer result = (Integer) query.getSingleResult();
        /* 38 */ if (result == null) {
            /* 39 */ result = Integer.valueOf(1);
        } else {
            /* 41 */ result = Integer.valueOf(result.intValue() + 1);
        }
        /* 43 */ return result;
    }

    @Override
    public List<District> findAllRange() {
        /* 48 */ Query query = this.em.createQuery("SELECT d FROM District d ORDER BY d.nom");
        /* 49 */ return query.getResultList();
    }
}
