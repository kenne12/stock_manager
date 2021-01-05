package sessions;

import entities.Personnel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PersonnelFacade
        extends AbstractFacade<Personnel>
        implements PersonnelFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 27 */ return this.em;
    }

    public PersonnelFacade() {
        /* 31 */ super(Personnel.class);
    }

    @Override
    public Integer nextVal() {
        /* 36 */ Query query = this.em.createQuery("SELECT MAX(p.idpersonnel) FROM Personnel p");
        /* 37 */ Integer result = (Integer) query.getSingleResult();
        /* 38 */ if (result == null) {
            /* 39 */ result = Integer.valueOf(1);
        } else {
            /* 41 */ result = Integer.valueOf(result.intValue() + 1);
        }
        /* 43 */ return result;
    }

    @Override
    public List<Personnel> findByEtat(boolean etat) throws Exception {
        /* 48 */ Query query = this.em.createQuery("SELECT p FROM Personnel p WHERE p.etat=:etat ORDER BY p.nom,p.prenom").setParameter("etat", Boolean.valueOf(etat));
        /* 49 */ return query.getResultList();
    }

    @Override
    public List<Personnel> findAllRange() {
        /* 54 */ Query query = this.em.createQuery("SELECT p FROM Personnel p ORDER BY p.nom,p.prenom");
        /* 55 */ return query.getResultList();
    }
}
