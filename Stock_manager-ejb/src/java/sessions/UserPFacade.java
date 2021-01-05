package sessions;

import entities.UserP;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserPFacade
        extends AbstractFacade<UserP>
        implements UserPFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 26 */ return this.em;
    }

    public UserPFacade() {
        /* 30 */ super(UserP.class);
    }

    @Override
    public Integer nextVal() {
        /* 35 */ Query query = this.em.createQuery("SELECT MAX(u.iduserP) FROM UserP u");
        /* 36 */ Integer result = (Integer) query.getSingleResult();
        /* 37 */ if (result == null) {
            /* 38 */ result = Integer.valueOf(1);
        } else {
            /* 40 */ result = Integer.valueOf(result.intValue() + 1);
        }
        /* 42 */ return result;
    }

    @Override
    public List<UserP> findAllRange() {
        /* 47 */ Query query = this.em.createQuery("SELECT u FROM UserP u ORDER BY u.nom");
        /* 48 */ return query.getResultList();
    }
}
