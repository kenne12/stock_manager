package sessions;

import entities.AnneeMois;
import entities.Commande;
import entities.Facture;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CommandeFacade
        extends AbstractFacade<Commande>
        implements CommandeFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 30 */ return this.em;
    }

    public CommandeFacade() {
        /* 34 */ super(Commande.class);
    }

    @Override
    public Long nextVal() {
        /* 39 */ Query query = this.em.createQuery("SELECT MAX(c.idcommande) FROM Commande c");
        /* 40 */ Long result = (Long) query.getSingleResult();
        /* 41 */ if (result == null) {
            /* 42 */ result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public List<Commande> findByFacture(Facture facture) {
        /* 51 */ List<Commande> commandes = null;
        /* 52 */ Query query = this.em.createQuery("SELECT c FROM Commande c WHERE c.idfacture.idfacture=:facture ORDER BY c.idproduit.nom");
        /* 53 */ query.setParameter("facture", facture.getIdfacture());
        /* 54 */ commandes = query.getResultList();
        /* 55 */ return commandes;
    }

    @Override
    public List<Commande> findByDate(Date date) throws Exception {
        /* 61 */ List<Commande> commandes = null;
        /* 62 */ Query query = this.em.createQuery("SELECT c FROM Commande c WHERE c.idfacture.dateAchat=:date ORDER BY c.idproduit.nom");
        /* 63 */ query.setParameter("date", date);
        /* 64 */ commandes = query.getResultList();
        /* 65 */ return commandes;
    }

    @Override
    public List<Commande> findByMois(AnneeMois anneeMois) throws Exception {
        /* 70 */ List<Commande> commandes = null;
        /* 71 */ Query query = this.em.createQuery("SELECT c FROM Commande c WHERE c.idfacture.idAnneeMois.idAnneeMois=:mois ORDER BY c.idfacture.dateAchat,c.idproduit.nom");
        /* 72 */ query.setParameter("mois", anneeMois.getIdAnneeMois());
        /* 73 */ commandes = query.getResultList();
        /* 74 */ return commandes;
    }

    @Override
    public List<Commande> findByLot(Long idlot) throws Exception {
        /* 79 */ List<Commande> commandes = null;
        /* 80 */ Query query = this.em.createQuery("SELECT c FROM Commande c WHERE c.idlot.idlot=:lot ORDER BY c.idfacture.dateAchat");
        /* 81 */ query.setParameter("lot", idlot);
        /* 82 */ commandes = query.getResultList();
        /* 83 */ return commandes;
    }

    @Override
    public List<Commande> findByFamilleIntervalNotCount(int idfamille, int idbailleur, Date date_debut, Date date_fin) throws Exception {
        /* 88 */ Query query = this.em.createQuery("SELECT c FROM Commande c WHERE c.idproduit.idfamille.idfamille=:idfamille AND c.idlot.idbailleur.idbailleur=:idbailleur AND c.comptabilise=false AND c.idfacture.dateAchat BETWEEN :date_debut AND :date_fin");
        /* 89 */ query.setParameter("idfamille", (idfamille)).setParameter("date_debut", date_debut).setParameter("date_fin", date_fin).setParameter("idbailleur", (idbailleur));
        /* 90 */ return query.getResultList();
    }
}
