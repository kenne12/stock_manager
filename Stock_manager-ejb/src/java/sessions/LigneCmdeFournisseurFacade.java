package sessions;

import entities.LigneCmdeFournisseur;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LigneCmdeFournisseurFacade
        extends AbstractFacade<LigneCmdeFournisseur>
        implements LigneCmdeFournisseurFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 29 */ return this.em;
    }

    public LigneCmdeFournisseurFacade() {
        /* 33 */ super(LigneCmdeFournisseur.class);
    }

    @Override
    public Long nextVal() {
        /* 38 */ Query query = this.em.createQuery("SELECT MAX(l.idLigneCmdeFournisseur) FROM LigneCmdeFournisseur l");
        /* 39 */ Long result = (Long) query.getSingleResult();
        /* 40 */ if (result == null) {
            /* 41 */ result = Long.valueOf(1L);
        } else {
            /* 43 */ result = Long.valueOf(result.longValue() + 1L);
        }
        /* 45 */ return result;
    }

    @Override
    public List<LigneCmdeFournisseur> findByCommande(Long idCmdeFournisseur) throws Exception {
        /* 50 */ List<LigneCmdeFournisseur> ligneCmdeFournisseurs = null;
        /* 51 */ Query query = this.em.createQuery("SELECT l FROM LigneCmdeFournisseur l WHERE l.idCmdeFournisseur.idCmdeFournisseur=:idcommande ORDER BY l.idproduit.nom");
        /* 52 */ query.setParameter("idcommande", idCmdeFournisseur);
        /* 53 */ ligneCmdeFournisseurs = query.getResultList();
        /* 54 */ return ligneCmdeFournisseurs;
    }

    @Override
    public List<LigneCmdeFournisseur> findByDate(Date date) throws Exception {
        /* 60 */ List<LigneCmdeFournisseur> ligneCmdeFournisseurs = null;
        /* 61 */ Query query = this.em.createQuery("SELECT l FROM LigneCmdeFournisseur l WHERE l.idCmdeFournisseur.dateCmde=:date ORDER BY l.idproduit.nom");
        /* 62 */ query.setParameter("date", date);
        /* 63 */ ligneCmdeFournisseurs = query.getResultList();
        /* 64 */ return ligneCmdeFournisseurs;
    }
}
