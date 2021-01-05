package sessions;

import entities.Fournisseur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FournisseurFacade
        extends AbstractFacade<Fournisseur>
        implements FournisseurFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 27 */ return this.em;
    }

    public FournisseurFacade() {
        /* 31 */ super(Fournisseur.class);
    }

    @Override
    public Integer nextVal() {
        /* 36 */ Query query = this.em.createQuery("SELECT MAX(f.idfournisseur) FROM Fournisseur f");
        /* 37 */ Integer result = (Integer) query.getSingleResult();
        /* 38 */ if (result == null) {
            /* 39 */ result = (1);
        } else {
            /* 41 */ result = (result + 1);
        }
        /* 43 */ return result;
    }

    @Override
    public List<Fournisseur> findAllRange() {
        List<Fournisseur> fournisseurs = null;
        Query query = this.em.createQuery("SELECT f FROM Fournisseur f ORDER BY f.raisonSociale");
        fournisseurs = query.getResultList();
        return fournisseurs;
    }
}
