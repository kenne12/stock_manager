package controllers.versement;

import entities.Facture;
import entities.Versement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import sessions.FactureFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.VersementFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractVersementController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected VersementFacadeLocal versementFacadeLocal;
    protected Versement versement;
    /*  33 */    protected List<Versement> versements = new ArrayList<>();

    @EJB
    protected FactureFacadeLocal factureFacadeLocal;
    /*  37 */    protected Facture facture = new Facture();
    /*  38 */    protected List<Facture> factures = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    /*  43 */    protected Routine routine = new Routine();

    /*  45 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  46 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  48 */    protected Boolean showClient = Boolean.valueOf(true);

    /*  50 */    protected String mode = "";
    /*  51 */    protected String fileName = "";

    public Boolean getModifier() {
        /*  54 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /*  58 */ this.modifier = modifier;
    }

    public Boolean getSupprimer() {
        /*  62 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /*  66 */ this.supprimer = supprimer;
    }

    public Versement getVersement() {
        /*  70 */ return this.versement;
    }

    public void setVersement(Versement versement) {
        /*  74 */ this.versement = versement;
        /*  75 */ this.supprimer = this.modifier = Boolean.valueOf((versement == null));
    }

    public List<Versement> getVersements() {
        /*  79 */ this.versements = this.versementFacadeLocal.findAllRange(SessionMBean.getMois().getIdannee().getIdannee().intValue());
        /*  80 */ return this.versements;
    }

    public void setVersements(List<Versement> versements) {
        /*  84 */ this.versements = versements;
    }

    public Boolean getShowClient() {
        /*  88 */ return this.showClient;
    }

    public void setShowClient(Boolean showClient) {
        /*  92 */ this.showClient = showClient;
    }

    public Facture getFacture() {
        /*  96 */ return this.facture;
    }

    public void setFacture(Facture facture) {
        /* 100 */ this.facture = facture;
    }

    public List<Facture> getFactures() {
        /* 104 */ return this.factures;
    }

    public void setFactures(List<Facture> factures) {
        /* 108 */ this.factures = factures;
    }

    public Routine getRoutine() {
        /* 112 */ return this.routine;
    }

    public String getFileName() {
        /* 116 */ return this.fileName;
    }
}
