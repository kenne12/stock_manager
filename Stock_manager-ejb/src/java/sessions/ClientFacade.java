package sessions;

import entities.Client;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ClientFacade
        extends AbstractFacade<Client>
        implements ClientFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 27 */ return this.em;
    }

    public ClientFacade() {
        /* 31 */ super(Client.class);
    }

    @Override
    public Integer nextVal() {
        /* 44 */ Query query = this.em.createQuery("SELECT MAX(c.idclient) FROM Client c");
        /* 45 */ Integer result = (Integer) query.getSingleResult();
        /* 46 */ if (result == null) {
            /* 47 */ result = (1);
        } else {
            /* 49 */ result = (result + 1);
        }
        /* 51 */ return result;
    }

    @Override
    public List<Client> findAllRange() {
        List<Client> clients = null;
        Query query = this.em.createQuery("SELECT c FROM Client c ORDER BY c.nom");
        /* 80 */ clients = query.getResultList();
        /* 81 */ return clients;
    }
}
