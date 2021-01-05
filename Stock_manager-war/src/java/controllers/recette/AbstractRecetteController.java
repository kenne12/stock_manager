package controllers.recette;

import entities.Annee;
import entities.AnneeMois;
import entities.Bailleur;
import entities.Formestockage;
import entities.Journee;
import entities.Lot;
import entities.UserP;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.BailleurFacadeLocal;
import sessions.CommandeFacadeLocal;
import sessions.FactureFacadeLocal;
import sessions.FormestockageFacadeLocal;
import sessions.JourneeFacadeLocal;
import sessions.LotFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.UserPFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractRecetteController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected FormestockageFacadeLocal formestockageFacadeLocal;
    /*  44 */    protected Formestockage formestockage = new Formestockage();
    /*  45 */    protected List<Formestockage> formestockages = new ArrayList<>();

    @EJB
    protected LotFacadeLocal lotFacadeLocal;
    /*  49 */    protected Lot lot = new Lot();
    /*  50 */    protected List<Lot> lots = new ArrayList<>();

    @EJB
    protected BailleurFacadeLocal bailleurFacadeLocal;
    /*  54 */    protected Bailleur bailleur = new Bailleur();
    /*  55 */    protected List<Bailleur> bailleurs = new ArrayList<>();

    @EJB
    protected UserPFacadeLocal userPFacadeLocal;
    /*  59 */    protected UserP userP = new UserP();
    /*  60 */    protected List<UserP> userPs = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  64 */    protected Annee annee = new Annee();
    /*  65 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  69 */    protected AnneeMois anneeMois = new AnneeMois();
    /*  70 */    protected List<AnneeMois> listMois = new ArrayList<>();

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;

    @EJB
    protected JourneeFacadeLocal journeeFacadeLocal;
    /*  77 */    protected List<Journee> journees = new ArrayList<>();

    @EJB
    protected FactureFacadeLocal factureFacadeLocal;

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    /*  85 */    protected Routine routine = new Routine();

    /*  87 */    protected List<String> password = new ArrayList<>();

    /*  89 */    protected String sessionPassword = "";

    /*  91 */    protected Boolean session = Boolean.valueOf(true);

    /*  93 */    protected Boolean detail = Boolean.valueOf(true);
    /*  94 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  95 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  96 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  97 */    protected Boolean supprimer = Boolean.valueOf(true);

    /*  99 */    protected boolean showQuantiteDosage = SessionMBean.getParametrage().getEtatQuantiteDosage().booleanValue();
    /* 100 */    protected boolean showFormeProduit = SessionMBean.getParametrage().getEtatFormeProduit().booleanValue();
    /* 101 */    protected boolean showFormeStockage = SessionMBean.getParametrage().getEtatFormeStockage().booleanValue();
    /* 102 */    protected boolean showUser = SessionMBean.getParametrage().getEtatuser().booleanValue();
    /* 103 */    protected boolean showBailleur = SessionMBean.getParametrage().getEtatbailleur().booleanValue();

    protected boolean showLot = true;

    /* 107 */    protected String mode = "";

    /* 109 */    protected String fileName = "";
    /* 110 */    protected String fileName1 = "";
    /* 111 */    protected String fileName2 = "";

    public Boolean getDetail() {
        /* 118 */ return this.detail;
    }

    public void setDetail(Boolean detail) {
        /* 122 */ this.detail = detail;
    }

    public Boolean getModifier() {
        /* 126 */ return this.modifier;
    }

    public void setModifier(Boolean modifier) {
        /* 130 */ this.modifier = modifier;
    }

    public Boolean getConsulter() {
        /* 134 */ return this.consulter;
    }

    public void setConsulter(Boolean consulter) {
        /* 138 */ this.consulter = consulter;
    }

    public Boolean getImprimer() {
        /* 142 */ return this.imprimer;
    }

    public void setImprimer(Boolean imprimer) {
        /* 146 */ this.imprimer = imprimer;
    }

    public Boolean getSupprimer() {
        /* 150 */ return this.supprimer;
    }

    public void setSupprimer(Boolean supprimer) {
        /* 154 */ this.supprimer = supprimer;
    }

    public String getFileName() {
        /* 158 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 162 */ this.fileName = fileName;
    }

    public String getFileName1() {
        /* 166 */ return this.fileName1;
    }

    public void setFileName1(String fileName1) {
        /* 170 */ this.fileName1 = fileName1;
    }

    public String getFileName2() {
        /* 174 */ return this.fileName2;
    }

    public void setFileName2(String fileName2) {
        /* 178 */ this.fileName2 = fileName2;
    }

    public Boolean getSession() {
        try {
            /* 183 */ if (SessionMBean.getSession1().booleanValue()) {
                /* 184 */ this.session = Boolean.valueOf(false);
            } else {
                /* 186 */ this.session = Boolean.valueOf(true);
            }
            /* 188 */        } catch (Exception e) {
            /* 189 */ e.printStackTrace();
        }
        /* 191 */ return this.session;
    }

    public void setSession(Boolean session) {
        /* 195 */ this.session = session;
    }

    public String getSessionPassword() {
        /* 199 */ return this.sessionPassword;
    }

    public void setSessionPassword(String sessionPassword) {
        /* 203 */ this.sessionPassword = sessionPassword;
    }

    public Routine getRoutine() {
        /* 207 */ return this.routine;
    }

    public Formestockage getFormestockage() {
        /* 213 */ return this.formestockage;
    }

    public void setFormestockage(Formestockage formestockage) {
        /* 217 */ this.formestockage = formestockage;
    }

    public List<Formestockage> getFormestockages() {
        /* 223 */ this.formestockages = this.formestockageFacadeLocal.findAll();
        /* 224 */ return this.formestockages;
    }

    public boolean isShowQuantiteDosage() {
        /* 228 */ return this.showQuantiteDosage;
    }

    public boolean isShowFormeProduit() {
        /* 232 */ return this.showFormeProduit;
    }

    public boolean isShowFormeStockage() {
        /* 236 */ return this.showFormeStockage;
    }

    public Lot getLot() {
        /* 240 */ return this.lot;
    }

    public void setLot(Lot lot) {
        /* 244 */ this.lot = lot;
    }

    public boolean isShowLot() {
        /* 248 */ return this.showLot;
    }

    public Bailleur getBailleur() {
        /* 252 */ return this.bailleur;
    }

    public void setBailleur(Bailleur bailleur) {
        /* 256 */ this.bailleur = bailleur;
    }

    public List<Bailleur> getBailleurs() {
        /* 260 */ this.bailleurs = this.bailleurFacadeLocal.findAll();
        /* 261 */ return this.bailleurs;
    }

    public void setBailleurs(List<Bailleur> bailleurs) {
        /* 265 */ this.bailleurs = bailleurs;
    }

    public UserP getUserP() {
        /* 269 */ return this.userP;
    }

    public void setUserP(UserP userP) {
        /* 273 */ this.userP = userP;
    }

    public List<UserP> getUserPs() {
        /* 277 */ this.userPs = this.userPFacadeLocal.findAll();
        /* 278 */ return this.userPs;
    }

    public void setUserPs(List<UserP> userPs) {
        /* 282 */ this.userPs = userPs;
    }

    public boolean isShowUser() {
        /* 286 */ return this.showUser;
    }

    public boolean isShowBailleur() {
        /* 290 */ return this.showBailleur;
    }

    public String getMode() {
        /* 294 */ return this.mode;
    }

    public List<Annee> getAnnees() {
        /* 298 */ this.annees = this.anneeFacadeLocal.findByRange();
        /* 299 */ return this.annees;
    }

    public void setAnnees(List<Annee> annees) {
        /* 303 */ this.annees = annees;
    }

    public AnneeMois getAnneeMois() {
        /* 307 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 311 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getListMois() {
        /* 315 */ return this.listMois;
    }

    public void setListMois(List<AnneeMois> listMois) {
        /* 319 */ this.listMois = listMois;
    }

    public List<Journee> getJournees() {
        /* 323 */ return this.journees;
    }

    public void setJournees(List<Journee> journees) {
        /* 327 */ this.journees = journees;
    }

    public Annee getAnnee() {
        /* 331 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 335 */ this.annee = annee;
    }
}
