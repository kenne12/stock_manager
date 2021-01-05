package sessions;

import entities.LigneFactBailleur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LigneFactBailleurFacade
        extends AbstractFacade<LigneFactBailleur>
        implements LigneFactBailleurFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        /* 27 */ return this.em;
    }

    public LigneFactBailleurFacade() {
        /* 31 */ super(LigneFactBailleur.class);
    }

    @Override
    public Long nextVal() {
        /* 36 */ Query query = this.em.createQuery("SELECT MAX(l.idLigneFactBailleur) FROM LigneFactBailleur l");
        /* 37 */ Long result = (Long) query.getSingleResult();
        /* 38 */ if (result == null) {
            /* 39 */ result = Long.valueOf(1L);
        } else {
            /* 41 */ result = Long.valueOf(result.longValue() + 1L);
        }
        /* 43 */ return result;
    }

    @Override
    public List<LigneFactBailleur> findByFacture(Long facturebailleur) throws Exception {
        /* 48 */ Query query = this.em.createQuery("SELECT l FROM LigneFactBailleur l WHERE l.idfactureBailleur.idFactureBailleur=:facture");
        /* 49 */ return query.setParameter("facture", facturebailleur).getResultList();
    }
}
