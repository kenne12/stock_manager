package sessions;

import entities.Annee;
import entities.AnneeMois;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AnneeMoisFacade
        extends AbstractFacade<AnneeMois>
        implements AnneeMoisFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 29 */ return this.em;
    }

    public AnneeMoisFacade() {
        /* 33 */ super(AnneeMois.class);
    }

    @Override
    public Integer nextVal() {
        /* 38 */ Query query = this.em.createQuery("SELECT MAX(a.idAnneeMois) FROM AnneeMois a");
        /* 39 */ Integer result = (Integer) query.getSingleResult();
        /* 40 */ if (result == null) {
            /* 41 */ result = (1);
        } else {
            /* 43 */ result = (result + 1);
        }
        /* 45 */ return result;
    }

    @Override
    public List<AnneeMois> findByAnneeEtat(Annee annee, boolean etat) throws Exception {
        /* 50 */ Query query = this.em.createQuery("SELECT a FROM AnneeMois a WHERE a.idannee.idannee=:annee AND a.etat=:etat ORDER BY a.idmois.idmois");
        /* 51 */ query.setParameter("annee", annee.getIdannee()).setParameter("etat", (etat));
        /* 52 */ return query.getResultList();
    }

    @Override
    public List<AnneeMois> findByAnnee(Annee annee) throws Exception {
        /* 57 */ Query query = this.em.createQuery("SELECT a FROM AnneeMois a WHERE a.idannee.idannee=:annee ORDER BY a.idmois.idmois");
        /* 58 */ query.setParameter("annee", annee.getIdannee());
        /* 59 */ return query.getResultList();
    }

    @Override
    public List<AnneeMois> findByEtat(Boolean etat) throws Exception {
        /* 64 */ List<AnneeMois> anneeMoises = null;
        /* 65 */ Query query = this.em.createQuery("SELECT a FROM AnneeMois a WHERE a.etat=:etat ORDER BY a.idmois.idmois");
        /* 66 */ query.setParameter("etat", etat);
        /* 67 */ anneeMoises = query.getResultList();
        /* 68 */ return anneeMoises;
    }

    @Override
    public List<AnneeMois> findByRange() throws Exception {
        /* 73 */ Query query = this.em.createQuery("SELECT a FROM AnneeMois a ORDER BY a.idannee.nom DESC , a.idmois.numero ASC");
        /* 74 */ return query.getResultList();
    }

    @Override
    public AnneeMois findByDate(Date date) throws Exception {
        /* 79 */ Query query = this.em.createQuery("SELECT a FROM AnneeMois a WHERE a.dateDebut>=:date AND a.dateFin<=:date");
        /* 80 */ query.setParameter("date", date);
        /* 81 */ List<AnneeMois> list = query.getResultList();
        /* 82 */ if (!list.isEmpty()) {
            /* 83 */ return list.get(0);
        }
        /* 85 */ return null;
    }
}
