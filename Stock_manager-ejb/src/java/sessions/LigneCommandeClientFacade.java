package sessions;

import entities.LigneCommandeClient;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class LigneCommandeClientFacade
        extends AbstractFacade<LigneCommandeClient>
        implements LigneCommandeClientFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 31 */ return this.em;
    }

    public LigneCommandeClientFacade() {
        /* 35 */ super(LigneCommandeClient.class);
    }

    @Override
    public Long nextVal() {
        /* 40 */ Query query = this.em.createQuery("SELECT MAX(l.idLigneCmdeClient) FROM LigneCommandeClient l");
        /* 41 */ Long result = (Long) query.getSingleResult();
        /* 42 */ if (result == null) {
            /* 43 */ result = Long.valueOf(1L);
        } else {
            /* 45 */ result = Long.valueOf(result.longValue() + 1L);
        }
        /* 47 */ return result;
    }

    @Override
    public List<LigneCommandeClient> findByCommande(Long idCommanceClient) throws Exception {
        /* 52 */ List<LigneCommandeClient> ligneCommandeClients = null;
        /* 53 */ Query query = this.em.createQuery("SELECT l FROM LigneCommandeClient l WHERE l.idCmdeClient.idCommandeClient=:idcommande ORDER BY l.idproduit.nom");
        /* 54 */ query.setParameter("idcommande", idCommanceClient);
        /* 55 */ ligneCommandeClients = query.getResultList();
        /* 56 */ return ligneCommandeClients;
    }

    @Override
    public List<LigneCommandeClient> findByDate(Date date) throws Exception {
        /* 62 */ List<LigneCommandeClient> ligneCommandeClients = null;
        /* 63 */ Query query = this.em.createQuery("SELECT l FROM LigneCommandeClient l WHERE l.idCmdeClient.dateCommande=:date ORDER BY l.idproduit.nom");
        /* 64 */ query.setParameter("date", date);
        /* 65 */ ligneCommandeClients = query.getResultList();
        /* 66 */ return ligneCommandeClients;
    }

    @Override
    public List<LigneCommandeClient> findByMois(int idmois) throws Exception {
        /* 71 */ List<LigneCommandeClient> ligneCommandeClients = null;
        /* 72 */ Query query = this.em.createQuery("SELECT l FROM LigneCommandeClient l WHERE l.idCmdeClient.idmois.idAnneeMois=:mois ORDER BY l.idCmdeClient.dateCommande,l.idproduit.nom");
        /* 73 */ query.setParameter("mois", Integer.valueOf(idmois));
        /* 74 */ ligneCommandeClients = query.getResultList();
        /* 75 */ return ligneCommandeClients;
    }

    @Override
    public List<LigneCommandeClient> findByLot(Long idlot) throws Exception {
        /* 80 */ List<LigneCommandeClient> ligneCommandeClients = null;
        /* 81 */ Query query = this.em.createQuery("SELECT l FROM LigneCommandeClient l WHERE l.idlot.idlot=:lot ORDER BY l.idCmdeClient.dateCommande");
        /* 82 */ query.setParameter("lot", idlot);
        /* 83 */ ligneCommandeClients = query.getResultList();
        /* 84 */ return ligneCommandeClients;
    }
}
