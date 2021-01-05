package sessions;

import entities.Mois;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MoisFacade
        extends AbstractFacade<Mois>
        implements MoisFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 27 */ return this.em;
    }

    public MoisFacade() {
        /* 31 */ super(Mois.class);
    }

    @Override
    public List<Mois> findAllRange() {
        /* 36 */ Query query = this.em.createQuery("SELECT m FROM Mois m ORDER BY m.numero");
        /* 37 */ return query.getResultList();
    }
}
