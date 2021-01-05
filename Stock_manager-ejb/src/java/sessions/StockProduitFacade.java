package sessions;

import entities.Stock;
import entities.StockProduit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class StockProduitFacade
        extends AbstractFacade<StockProduit>
        implements StockProduitFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 28 */ return this.em;
    }

    public StockProduitFacade() {
        /* 32 */ super(StockProduit.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(s.idStockProduit) FROM StockProduit s");
        /* 38 */ Long result = (Long) query.getSingleResult();
        /* 39 */ if (result == null) {
            /* 40 */ result = (1L);
        } else {
            /* 42 */ result = (result + 1L);
        }
        /* 44 */ return result;
    }

    @Override
    public List<StockProduit> findByStock(Stock stock) {
        /* 49 */ List<StockProduit> stockProduits = null;
        /* 50 */ Query query = this.em.createQuery("SELECT s FROM StockProduit s WHERE s.idstock.idstock=:stock ORDER BY s.idproduit.nom");
        /* 51 */ query.setParameter("stock", stock.getIdstock());
        /* 52 */ stockProduits = query.getResultList();
        /* 53 */ return stockProduits;
    }

    @Override
    public List<StockProduit> findAllRange() {
        /* 58 */ List<StockProduit> stockProduits = null;
        /* 59 */ Query query = this.em.createQuery("SELECT s FROM StockProduit s ORDER BY s.idstock.dateAchat,s.idproduit.nom");
        /* 60 */ stockProduits = query.getResultList();
        /* 61 */ return stockProduits;
    }

    @Override
    public List<StockProduit> findByLot(Long idlot) throws Exception {
        /* 66 */ List<StockProduit> stockProduits = null;
        /* 67 */ Query query = this.em.createQuery("SELECT s FROM StockProduit s WHERE s.idlot.idlot=:idlot ORDER BY s.idstock.dateAchat");
        /* 68 */ query.setParameter("idlot", idlot);
        /* 69 */ stockProduits = query.getResultList();
        /* 70 */ return stockProduits;
    }

    @Override
    public List<StockProduit> findByMois(int mois) throws Exception {
        /* 75 */ List<StockProduit> stockProduits = null;
        /* 76 */ Query query = this.em.createQuery("SELECT s FROM StockProduit s WHERE s.idstock.idAnneeMois.idAnneeMois=:idmois ORDER BY s.idstock.dateAchat");
        /* 77 */ query.setParameter("idmois", (mois));
        /* 78 */ stockProduits = query.getResultList();
        /* 79 */ return stockProduits;
    }
}
