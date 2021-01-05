package sessions;

import entities.CommandeClient;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CommandeClientFacade extends AbstractFacade<CommandeClient>
        implements CommandeClientFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 28 */ return this.em;
    }

    public CommandeClientFacade() {
        /* 32 */ super(CommandeClient.class);
    }

    @Override
    public Long nextLongVal() {
        /* 37 */ Query query = this.em.createQuery("SELECT MAX(c.idCommandeClient) FROM CommandeClient c");
        /* 38 */ Long result = (Long) query.getSingleResult();
        /* 39 */ if (result == null) {
            /* 40 */ result = (1L);
        } else {
            /* 42 */ result = (result + 1L);
        }
        /* 44 */ return result;
    }

    @Override
    public List<CommandeClient> findAllRange() {
        /* 49 */ Query query = this.em.createQuery("SELECT c FROM CommandeClient c ORDER BY c.idmois.idannee.nom DESC,c.idmois.idmois.numero DESC, c.code DESC");
        /* 50 */ return query.getResultList();
    }

    @Override
    public List<CommandeClient> findAllDate(Date date) throws Exception {
        /* 55 */ Query query = this.em.createQuery("SELECT c FROM CommandeClient c WHERE c.dateCommande=:date ORDER BY c.code DESC");
        /* 56 */ query.setParameter("date", date);
        /* 57 */ return query.getResultList();
    }

    @Override
    public List<CommandeClient> findByInterval(Date dateDebut, Date dateFin) throws Exception {
        /* 62 */ Query query = this.em.createQuery("SELECT c FROM CommandeClient c WHERE c.dateCommande BETWEEN :datedebut AND :datefin ORDER BY c.code DESC");
        /* 63 */ query.setParameter("datedebut", dateDebut).setParameter("datefin", dateFin);
        /* 64 */ return query.getResultList();
    }

    @Override
    public List<CommandeClient> findByLivre(boolean livree) throws Exception {
        /* 69 */ Query query = this.em.createQuery("SELECT c FROM CommandeClient c WHERE c.livre=:livre ORDER BY c.dateCommande DESC");
        /* 70 */ query.setParameter("livre", (livree));
        /* 71 */ return query.getResultList();
    }
}
