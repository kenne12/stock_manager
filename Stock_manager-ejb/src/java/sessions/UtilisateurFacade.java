package sessions;

import entities.Utilisateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> implements UtilisateurFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        /* 27 */ return this.em;
    }

    public UtilisateurFacade() {
        /* 31 */ super(Utilisateur.class);
    }

    @Override
    public Integer nextVal() {
        /* 36 */ Query query = this.em.createQuery("SELECT MAX(u.idutilisateur) FROM Utilisateur u");
        /* 37 */ Integer result = (Integer) query.getSingleResult();
        /* 38 */ if (result == null) {
            /* 39 */ result = Integer.valueOf(1);
        } else {
            /* 41 */ result = Integer.valueOf(result.intValue() + 1);
        }
        /* 43 */ return result;
    }

    @Override
    public Utilisateur login(String login, String password) throws Exception {
        /* 48 */ Query query = this.em.createQuery("SELECT u FROM Utilisateur U WHERE u.login=:login AND u.password=:password");
        /* 49 */ query.setParameter("login", login).setParameter("password", password);
        /* 50 */ List<Utilisateur> list = query.getResultList();
        /* 51 */ if (!list.isEmpty()) {
            /* 52 */ return list.get(0);
        }
        /* 54 */ return null;
    }

    @Override
    public List<Utilisateur> findByActif(Boolean actif) {
        /* 59 */ List<Utilisateur> utilisateurs = null;
        /* 60 */ Query query = this.em.createQuery("SELECT u FROM Utilisateur u WHERE u.actif=:actif ORDER BY u.nom,u.prenom");
        /* 61 */ query.setParameter("actif", actif);
        /* 62 */ utilisateurs = query.getResultList();
        /* 63 */ return utilisateurs;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\UtilisateurFacade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
