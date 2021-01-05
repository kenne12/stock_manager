package sessions;

import entities.Famille;
import entities.Produit;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ProduitFacade
        extends AbstractFacade<Produit>
        implements ProduitFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /*  28 */ return this.em;
    }

    public ProduitFacade() {
        /*  32 */ super(Produit.class);
    }

    @Override
    public Long nextVal() {
        Query query = this.em.createQuery("SELECT MAX(p.idproduit) FROM Produit p");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = (1L);
        } else {
            result = (result + 1L);
        }
        return result;
    }

    @Override
    public List<Produit> findByCode(String code) throws Exception {
        /*  49 */ Query query = this.em.createQuery("SELECT p FROM Produit p WHERE p.code=:code");
        /*  50 */ return query.setParameter("code", code).getResultList();
    }

    @Override
    public List<Produit> findAllRange() {
        /*  55 */ Query query = this.em.createQuery("SELECT p FROM Produit p ORDER BY p.nom");
        /*  56 */ return query.getResultList();
    }

    @Override
    public List<Produit> findAllRange(boolean etat) {
        /*  61 */ Query query = this.em.createQuery("SELECT p FROM Produit p WHERE p.etat=:etat ORDER BY p.nom");
        /*  62 */ query.setParameter("etat", (etat));
        /*  63 */ return query.getResultList();
    }

    @Override
    public List<Produit> findAllRange1() {
        /*  68 */ Query query = this.em.createQuery("SELECT p FROM Produit p WHERE p.quantite>0D ORDER BY p.nom");
        /*  69 */ return query.getResultList();
    }

    @Override
    public List<Produit> findAllRangeNom() {
        /*  74 */ Query query = this.em.createQuery("SELECT p FROM Produit p ORDER BY p.nom");
        /*  75 */ return query.getResultList();
    }

    @Override
    public List<Produit> findByFamille(Famille famille) throws Exception {
        /*  80 */ Query query = this.em.createQuery("SELECT p FROM Produit p WHERE p.idfamille.idfamille=:famille ORDER BY p.nom");
        /*  81 */ query.setParameter("famille", famille.getIdfamille());
        /*  82 */ return query.getResultList();
    }

    @Override
    public List<Produit> findByFamille(Famille famille, boolean positif) throws Exception {
        /*  87 */ Query query = this.em.createQuery("SELECT p FROM Produit p WHERE p.idfamille.idfamille=:famille AND p.quantite>0D ORDER BY p.nom");
        /*  88 */ query.setParameter("famille", famille.getIdfamille());
        /*  89 */ return query.getResultList();
    }

    @Override
    public List<Produit> findSousStock() {
        /*  94 */ List<Produit> produits = null;
        /*  95 */ Query query = this.em.createQuery("SELECT p FROM Produit p WHERE p.quantite<=p.stockCritique ORDER BY p.nom");
        /*  96 */ produits = query.getResultList();
        /*  97 */ return produits;
    }

    @Override
    public List<Produit> findByPerissable(boolean etat) throws Exception {
        /* 102 */ Query query = this.em.createQuery("SELECT p FROM Produit p WHERE p.perissable = true AND p.etat=:etat ORDER BY p.nom");
        /* 103 */ query.setParameter("etat", (etat));
        /* 104 */ return query.getResultList();
    }
}
