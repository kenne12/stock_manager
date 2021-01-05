package sessions;

import entities.Annee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AnneeFacade
        extends AbstractFacade<Annee>
        implements AnneeFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 27 */ return this.em;
    }

    public AnneeFacade() {
        /* 31 */ super(Annee.class);
    }

    @Override
    public Integer nextVal() {
        /* 36 */ Query query = this.em.createQuery("SELECT MAX(a.idannee) FROM Annee a");
        /* 37 */ Integer result = (Integer) query.getSingleResult();
        /* 38 */ if (result == null) {
            /* 39 */ result = (1);
        } else {
            /* 41 */ result = (result.intValue() + 1);
        }
        /* 43 */ return result;
    }

    @Override
    public List<Annee> findByEtat(boolean etat) throws Exception {
        /* 48 */ List<Annee> annees = null;
        /* 49 */ Query query = this.em.createQuery("SELECT a FROM Annee a WHERE a.etat=:etat ORDER BY a.nom");
        /* 50 */ query.setParameter("etat", (etat));
        /* 51 */ annees = query.getResultList();
        /* 52 */ return annees;
    }

    @Override
    public List<Annee> findByRange() {
        /* 57 */ List<Annee> annees = null;
        /* 58 */ Query query = this.em.createQuery("SELECT a FROM Annee a  ORDER BY a.nom");
        /* 59 */ annees = query.getResultList();
        /* 60 */ return annees;
    }

    @Override
    public Annee findByCode(String nom) {
        List<Annee> annees = null;
        Query query = this.em.createQuery("SELECT a FROM Annee a WHERE a.nom=:nom");
        query.setParameter("nom", nom);
        annees = query.getResultList();
        if (annees.isEmpty()) {
            return null;
        }
        return annees.get(0);
    }
}
