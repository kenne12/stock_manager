package sessions;

import entities.AnneeMois;
import entities.Facture;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FactureFacade extends AbstractFacade<Facture> implements FactureFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public FactureFacade() {
        super(Facture.class);
    }

    @Override
    public Long nextLongVal() {
        Query query = this.em.createQuery("SELECT MAX(f.idfacture) FROM Facture f");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public Long nextVal(AnneeMois anneeMois) {
        Query query = this.em.createQuery("SELECT COUNT(f.idfacture) FROM Facture f WHERE f.idAnneeMois.idAnneeMois=:mois");
        query.setParameter("mois", anneeMois.getIdAnneeMois());
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public List<Facture> findAllRange() {

        Query query = this.em.createQuery("SELECT f FROM Facture f ORDER BY f.idAnneeMois.idannee.nom DESC,f.idAnneeMois.idmois.numero DESC, f.numeroFacture DESC");
        return query.getResultList();

    }

    @Override
    public List<Facture> findAllDate(Date date) throws Exception {
        Query query = this.em.createQuery("SELECT f FROM Facture f WHERE f.dateAchat=:date ORDER BY f.numeroFacture DESC");
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public List<Facture> findByInterval(Date dateDebut, Date dateFin) throws Exception {
        Query query = this.em.createQuery("SELECT f FROM Facture f WHERE f.dateAchat BETWEEN :datedebut AND :datefin ORDER BY f.numeroFacture DESC");
        query.setParameter("datedebut", dateDebut).setParameter("datefin", dateFin);
        return query.getResultList();
    }

    @Override
    public List<Facture> findByMois(int mois) throws Exception {
        Query query = this.em.createQuery("SELECT f FROM Facture f WHERE f.idAnneeMois.idAnneeMois=:mois ORDER BY f.numeroFacture DESC");
        query.setParameter("mois", (mois));
        return query.getResultList();
    }

    @Override
    public List<Facture> findByAnnee(int annee) throws Exception {
        Query query = this.em.createQuery("SELECT f FROM Facture f WHERE f.idAnneeMois.idannee.idannee=:annee ORDER BY f.numeroFacture DESC");
        query.setParameter("annee", (annee));
        return query.getResultList();
    }

    @Override
    public List<Facture> findClientDate(int client, Date date) throws Exception {
        /* 107 */ List<Facture> factures = null;
        /* 108 */ Query query = this.em.createQuery("SELECT f FROM Facture f WHERE f.idclient.idclient=:client AND f.dateAchat=:date ORDER BY f.numeroFacture DESC");
        /* 109 */ query.setParameter("date", date).setParameter("client", (client));
        /* 110 */ factures = query.getResultList();
        /* 111 */ return factures;
    }

    @Override
    public List<Facture> findClientInterval(int client, Date dateDebut, Date dateFin) throws Exception {
        /* 116 */ Query query = this.em.createQuery("SELECT f FROM Facture f WHERE f.idclient.idclient=:client AND f.dateAchat BETWEEN :datedebut AND :datefin ORDER BY f.numeroFacture DESC");
        /* 117 */ query.setParameter("datedebut", dateDebut).setParameter("datefin", dateFin).setParameter("client", Integer.valueOf(client));
        /* 118 */ return query.getResultList();
    }

    @Override
    public List<Facture> findNonregle() throws Exception {
        /* 123 */ Query query = this.em.createQuery("SELECT f FROM Facture f WHERE f.credit=true AND f.reste>0D");
        /* 124 */ return query.getResultList();
    }
}
