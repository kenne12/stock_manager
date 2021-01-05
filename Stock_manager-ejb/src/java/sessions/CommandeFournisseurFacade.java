package sessions;

import entities.CommandeFournisseur;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CommandeFournisseurFacade
        extends AbstractFacade<CommandeFournisseur>
        implements CommandeFournisseurFacadeLocal {

    @PersistenceContext(unitName = "Stock_manager-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        /* 29 */ return this.em;
    }

    public CommandeFournisseurFacade() {
        /* 33 */ super(CommandeFournisseur.class);
    }

    @Override
    public Long nextLongVal() {
        /* 38 */ Query query = this.em.createQuery("SELECT MAX(c.idCmdeFournisseur) FROM CommandeFournisseur c");
        /* 39 */ Long result = (Long) query.getSingleResult();
        /* 40 */ if (result == null) {
            /* 41 */ result = Long.valueOf(1L);
        } else {
            /* 43 */ result = Long.valueOf(result.longValue() + 1L);
        }
        /* 45 */ return result;
    }

    @Override
    public List<CommandeFournisseur> findAllRange() {
        /* 50 */ List<CommandeFournisseur> commandeFournisseurs = null;
        /* 51 */ Query query = this.em.createQuery("SELECT c FROM CommandeFournisseur c ORDER BY c.idmois.idannee.nom DESC,c.idmois.idmois.numero DESC, c.code DESC");
        /* 52 */ commandeFournisseurs = query.getResultList();
        /* 53 */ return commandeFournisseurs;
    }

    @Override
    public List<CommandeFournisseur> findAllDate(Date date) throws Exception {
        /* 58 */ List<CommandeFournisseur> commandeFournisseurs = null;
        /* 59 */ Query query = this.em.createQuery("SELECT c FROM CommandeFournisseur c WHERE c.dateCmde=:date ORDER BY c.code DESC");
        /* 60 */ query.setParameter("date", date);
        /* 61 */ commandeFournisseurs = query.getResultList();
        /* 62 */ return commandeFournisseurs;
    }

    @Override
    public List<CommandeFournisseur> findByInterval(Date dateDebut, Date dateFin) throws Exception {
        /* 67 */ List<CommandeFournisseur> commandeFournisseurs = null;
        /* 68 */ Query query = this.em.createQuery("SELECT c FROM CommandeFournisseur c WHERE c.dateCmde BETWEEN :datedebut AND :datefin ORDER BY c.code DESC");
        /* 69 */ query.setParameter("datedebut", dateDebut).setParameter("datefin", dateFin);
        /* 70 */ commandeFournisseurs = query.getResultList();
        /* 71 */ return commandeFournisseurs;
    }

    @Override
    public List<CommandeFournisseur> findByLivre(boolean livree) throws Exception {
        /* 76 */ List<CommandeFournisseur> commandeFournisseurs = null;
        /* 77 */ Query query = this.em.createQuery("SELECT c FROM CommandeFournisseur c WHERE c.livre=:livre ORDER BY c.dateCmde DESC");
        /* 78 */ query.setParameter("livre", Boolean.valueOf(livree));
        /* 79 */ commandeFournisseurs = query.getResultList();
        /* 80 */ return commandeFournisseurs;
    }
}
