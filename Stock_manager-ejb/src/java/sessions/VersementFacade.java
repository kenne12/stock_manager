package sessions;

import entities.Client;
import entities.Versement;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class VersementFacade extends AbstractFacade<Versement> implements VersementFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    public VersementFacade() {
        super(Versement.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(v.idversement) FROM Versement v");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public Long nextVal(Date date_deut, Date date_fin) throws Exception {
        Query query = this.em.createQuery("SELECT MAX(v.idversement) FROM Versement v WHERE v.date BETWEEN :date_debut AND :date_fin");
        query.setParameter("date_debut", date_deut).setParameter("date_fin", date_fin);
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public List<Versement> find(Client client) {
        Query query = this.em.createQuery("SELECT v FROM Versement v WHERE v.idfacture.idclient.idclient=:client");
        query.setParameter("client", client.getIdclient());
        return query.getResultList();
    }

    @Override
    public List<Versement> find(Client client, Date datedebut, Date datefin) {
        /* 70 */ Query query = this.em.createQuery("SELECT v FROM Versement v WHERE v.idfacture.idclient.idclient=:client AND v.date BETWEEN :datedebut AND  :datefin");
        /* 71 */ query.setParameter("client", client.getIdclient());
        /* 72 */ query.setParameter("datedebut", datedebut).setParameter("datefin", datefin);
        /* 73 */ return query.getResultList();
    }

    @Override
    public List<Versement> find(Client client, Date date) {
        /* 79 */ Query query = this.em.createQuery("SELECT v FROM Versement v WHERE v.idfacture.idclient.idclient=:client AND v.date=:date");
        /* 80 */ query.setParameter("client", client.getIdclient()).setParameter("date", date);
        /* 81 */ return query.getResultList();
    }

    @Override
    public List<Versement> findAllRange(int idannee) {
        /* 86 */ Query query = this.em.createQuery("SELECT v FROM Versement v WHERE v.idfacture.idAnneeMois.idannee.idannee=:idannee ORDER BY v.date DESC");
        /* 87 */ query.setParameter("idannee", (idannee));
        /* 88 */ return query.getResultList();
    }
}
