package controllers.inventaire_global;

import entities.Annee;
import entities.AnneeMois;
import entities.Inventaire;
import entities.Ligneinventaire;
import entities.Lot;
import entities.MvtStock;
import entities.Produit;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.InventaireFacadeLocal;
import sessions.LigneMvtStockFacadeLocal;
import sessions.LigneinventaireFacadeLocal;
import sessions.LotFacadeLocal;
import sessions.MouchardFacadeLocal;
import sessions.MvtStockFacadeLocal;
import sessions.ProduitFacadeLocal;
import utils.Routine;
import utils.SessionMBean;

public class AbstractInventaireGlobalController {

    @Resource
    protected UserTransaction ut;
    @EJB
    protected InventaireFacadeLocal inventaireFacadeLocal;
    /*  43 */    protected Inventaire inventaire = null;
    /*  44 */    protected List<Inventaire> inventaires = new ArrayList<>();

    @EJB
    protected LigneinventaireFacadeLocal ligneinventaireFacadeLocal;
    /*  48 */    protected List<Ligneinventaire> ligneinventaires = new ArrayList<>();

    @EJB
    protected ProduitFacadeLocal produitFacadeLocal;
    /*  52 */    protected List<Produit> produits = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  56 */    protected Annee annee = SessionMBean.getMois().getIdannee();
    /*  57 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  61 */    protected AnneeMois anneeMois = SessionMBean.getMois();
    /*  62 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected LotFacadeLocal lotFacadeLocal;
    /*  66 */    protected List<Lot> lots = new ArrayList<>();
    /*  67 */    protected List<Lot> selectedLots = new ArrayList<>();

    @EJB
    protected MvtStockFacadeLocal mvtStockFacadeLocal;
    /*  71 */    protected MvtStock mvtStock = new MvtStock();

    @EJB
    protected LigneMvtStockFacadeLocal ligneMvtStockFacadeLocal;

    protected boolean editQuantite = false;

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    /*  81 */    protected Routine routine = new Routine();

    /*  83 */    protected Boolean detail = Boolean.valueOf(true);
    /*  84 */    protected Boolean modifier = Boolean.valueOf(true);
    /*  85 */    protected Boolean consulter = Boolean.valueOf(true);
    /*  86 */    protected Boolean imprimer = Boolean.valueOf(true);
    /*  87 */    protected Boolean supprimer = Boolean.valueOf(true);

    protected boolean valider = true;
    protected boolean cancel = true;
    /*  91 */    protected String fileName = "";

    protected boolean showSelectArticle = false;
    /*  94 */    protected String mode = "";
    /*  95 */    protected String valideBtn = "";

    public Boolean getDetail() {
        /*  98 */ return this.detail;
    }

    public Boolean getModifier() {
        try {
            /* 103 */ if (this.inventaire != null) {
                /* 104 */ if (this.inventaire.getValidee().booleanValue()) {
                    /* 105 */ this.modifier = Boolean.valueOf(true);
                } else {
                    /* 107 */ this.modifier = Boolean.valueOf(false);
                }
            } else {
                /* 110 */ this.modifier = Boolean.valueOf(true);
            }
            /* 112 */        } catch (Exception e) {
            /* 113 */ this.modifier = Boolean.valueOf(true);
        }
        /* 115 */ return this.modifier;
    }

    public boolean isValider() {
        try {
            /* 120 */ if (this.inventaire != null) {
                /* 121 */ if (this.inventaire.getValidee().booleanValue()) {
                    /* 122 */ this.valider = true;
                } else {
                    /* 124 */ this.valider = false;
                }
            } else {
                /* 127 */ this.valider = true;
            }
            /* 129 */        } catch (Exception e) {
            /* 130 */ this.valider = true;
        }
        /* 132 */ return this.valider;
    }

    public Boolean getConsulter() {
        /* 136 */ return this.consulter;
    }

    public Boolean getImprimer() {
        /* 140 */ return this.imprimer;
    }

    public Boolean getSupprimer() {
        try {
            /* 145 */ if (this.inventaire != null) {
                /* 146 */ if (this.inventaire.getValidee().booleanValue()) {
                    /* 147 */ this.supprimer = Boolean.valueOf(true);
                } else {
                    /* 149 */ this.supprimer = Boolean.valueOf(false);
                }
            } else {
                /* 152 */ this.supprimer = Boolean.valueOf(true);
            }
            /* 154 */        } catch (Exception e) {
            /* 155 */ this.supprimer = Boolean.valueOf(true);
        }
        /* 157 */ return this.supprimer;
    }

    public Annee getAnnee() {
        /* 161 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 165 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        try {
            /* 170 */ this.annees = this.anneeFacadeLocal.findByEtat(true);
            /* 171 */        } catch (Exception e) {
            /* 172 */ e.printStackTrace();
        }
        /* 174 */ return this.annees;
    }

    public AnneeMois getAnneeMois() {
        /* 178 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /* 182 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        try {
            /* 187 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByEtat(Boolean.valueOf(true));
            /* 188 */        } catch (Exception e) {
            /* 189 */ e.printStackTrace();
        }
        /* 191 */ return this.anneeMoises;
    }

    public String getFileName() {
        /* 195 */ return this.fileName;
    }

    public Routine getRoutine() {
        /* 199 */ return this.routine;
    }

    public List<Lot> getLots() {
        /* 203 */ return this.lots;
    }

    public Inventaire getInventaire() {
        /* 207 */ return this.inventaire;
    }

    public void setInventaire(Inventaire inventaire) {
        /* 211 */ this.inventaire = inventaire;
        /* 212 */ this.supprimer = this.detail = this.imprimer = Boolean.valueOf((inventaire == null));
    }

    public List<Inventaire> getInventaires() {
        try {
            /* 217 */ this.inventaires = this.inventaireFacadeLocal.findAllRange();
            /* 218 */        } catch (Exception e) {
            /* 219 */ e.printStackTrace();
        }
        /* 221 */ return this.inventaires;
    }

    public void setInventaires(List<Inventaire> inventaires) {
        /* 225 */ this.inventaires = inventaires;
    }

    public List<Ligneinventaire> getLigneinventaires() {
        /* 229 */ return this.ligneinventaires;
    }

    public void setLigneinventaires(List<Ligneinventaire> ligneinventaires) {
        /* 233 */ this.ligneinventaires = ligneinventaires;
    }

    public String getValideBtn() {
        /* 237 */ return this.valideBtn;
    }

    public void setValideBtn(String valideBtn) {
        /* 241 */ this.valideBtn = valideBtn;
    }

    public List<Lot> getSelectedLots() {
        /* 245 */ return this.selectedLots;
    }

    public void setSelectedLots(List<Lot> selectedLots) {
        /* 249 */ this.selectedLots = selectedLots;
    }

    public boolean isShowSelectArticle() {
        /* 253 */ return this.showSelectArticle;
    }

    public boolean isEditQuantite() {
        /* 257 */ return this.editQuantite;
    }

    public boolean isCancel() {
        try {
            /* 262 */ if (this.inventaire != null) {
                /* 263 */ if (this.inventaire.getValidee().booleanValue()) {
                    /* 264 */ this.cancel = false;
                } else {
                    /* 266 */ this.cancel = true;
                }
            } else {
                /* 269 */ this.cancel = true;
            }
            /* 271 */        } catch (Exception e) {
            /* 272 */ this.cancel = true;
        }
        /* 274 */ return this.cancel;
    }
}
