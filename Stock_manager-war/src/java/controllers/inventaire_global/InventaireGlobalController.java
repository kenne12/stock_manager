package controllers.inventaire_global;

import controllers.inventaire_global.AbstractInventaireGlobalController;
import entities.Inventaire;
import entities.LigneMvtStock;
import entities.Ligneinventaire;
import entities.Lot;
import entities.MvtStock;
import entities.Produit;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@SessionScoped
public class InventaireGlobalController
        extends AbstractInventaireGlobalController
        implements Serializable {

    @PostConstruct
    private void init() {
    }

    public void updateMois() {
        try {
            /*  47 */ this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
            /*  48 */        } catch (Exception e) {
            /*  49 */ e.printStackTrace();
        }
    }

    public void prepareCreate() {
        try {
            /*  56 */ if (!Utilitaires.isAccess(Long.valueOf(48L))) {
                /*  57 */ notifyError("acces_refuse");

                return;
            }
            /*  61 */ this.mode = "Create";
            /*  62 */ this.valideBtn = this.routine.localizeMessage("valider");
            /*  63 */ this.editQuantite = false;

            /*  65 */ this.inventaire = new Inventaire();
            /*  66 */ this.inventaire.setDateInventaire(SessionMBean.getDateOuverture());

            /*  68 */ this.ligneinventaires.clear();
            /*  69 */ this.produits.clear();

            /*  71 */ String code = "INV-" + this.anneeMois.getIdannee().getNom() + "-" + this.anneeMois.getIdmois().getNom().toUpperCase().substring(0, 2);
            /*  72 */ Long nextPayement = this.inventaireFacadeLocal.nextVal(this.anneeMois);
            /*  73 */ code = Utilitaires.genererCodeInventaire(code, nextPayement);
            /*  74 */ this.inventaire.setCode(code);

            /*  76 */ this.lots = Utilitaires.filterNotPeremptLot(this.lotFacadeLocal.findAllRange());

            /*  78 */ for (Lot l : this.lots) {
                /*  79 */ Ligneinventaire li = new Ligneinventaire();
                /*  80 */ li.setIdLigneinventaire(Long.valueOf(0L));
                /*  81 */ li.setPrix(l.getPrixVente());
                /*  82 */ li.setIdlot(l);
                /*  83 */ li.setQuantiteLogique(l.getQuantite());
                /*  84 */ li.setQuantitePhysique(l.getQuantite());
                /*  85 */ li.setEcart(Double.valueOf(0.0D));
                /*  86 */ li.setObservation("-");
                /*  87 */ this.ligneinventaires.add(li);

                /*  89 */ if (!this.produits.contains(l.getIdproduit())) {
                    /*  90 */ this.produits.add(l.getIdproduit());
                }
            }
            /*  93 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            /*  94 */        } catch (Exception e) {
            /*  95 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /*  96 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void prepareCreatePartial() {
        try {
            /* 103 */ if (!Utilitaires.isAccess(Long.valueOf(48L))) {
                /* 104 */ notifyError("acces_refuse");

                return;
            }
            /* 108 */ this.mode = "Create";
            /* 109 */ this.valideBtn = "" + this.routine.localizeMessage("valider");
            /* 110 */ this.showSelectArticle = false;

            /* 112 */ this.inventaire = new Inventaire();
            /* 113 */ this.inventaire.setDateInventaire(SessionMBean.getDateOuverture());

            /* 115 */ this.ligneinventaires.clear();
            /* 116 */ this.produits.clear();

            /* 118 */ String code = "INV-" + this.anneeMois.getIdannee().getNom() + "-" + this.anneeMois.getIdmois().getNom().toUpperCase().substring(0, 2);
            /* 119 */ Long nextPayement = this.inventaireFacadeLocal.nextVal(this.anneeMois);
            /* 120 */ code = Utilitaires.genererCodeInventaire(code, nextPayement);
            /* 121 */ this.inventaire.setCode(code);

            /* 123 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            /* 124 */        } catch (Exception e) {
            /* 125 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /* 126 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void prepareAddArticle() {
        try {
            /* 132 */ this.lots = Utilitaires.filterNotPeremptLot(this.lotFacadeLocal.findAllRange());
            /* 133 */ if ("Create".equals(this.mode)) {
                /* 134 */ this.selectedLots.clear();
                /* 135 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').show()");
                return;
            }
            /* 138 */ this.selectedLots.clear();
            /* 139 */ this.produits.clear();
            /* 140 */ for (Ligneinventaire l : this.ligneinventaires) {
                /* 141 */ this.selectedLots.add(l.getIdlot());
                /* 142 */ if (!this.produits.contains(l.getIdlot().getIdproduit())) {
                    /* 143 */ this.produits.add(l.getIdlot().getIdproduit());
                }
            }
            /* 146 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').show()");
            /* 147 */        } catch (Exception e) {
            /* 148 */ notifyFail(e);
        }
    }

    public void prepareEdit() {
        try {
            /* 155 */ if (this.inventaire == null) {
                /* 156 */ notifyError("not_row_selected");

                return;
            }
            /* 160 */ if (!Utilitaires.isAccess(Long.valueOf(49L))) {
                /* 161 */ notifyError("acces_refuse");
                /* 162 */ this.inventaire = null;

                return;
            }
            /* 166 */ if (this.inventaire.getValidee().booleanValue()) {
                /* 167 */ notifyError("inventaire_validee");

                return;
            }
            /* 171 */ this.mode = "Edit";
            /* 172 */ this.editQuantite = false;
            /* 173 */ this.showSelectArticle = true;
            /* 174 */ this.valideBtn = "" + this.routine.localizeMessage("valider");
            /* 175 */ this.ligneinventaires = this.ligneinventaireFacadeLocal.findByInventaire(this.inventaire.getIdinventaire());
            /* 176 */ this.produits.clear();
            /* 177 */ if (!this.inventaire.getValidee().booleanValue()) {
                /* 178 */ this.showSelectArticle = false;
                /* 179 */ for (Ligneinventaire l : this.ligneinventaires) {
                    /* 180 */ if (!this.produits.contains(l.getIdlot().getIdproduit())) {
                        /* 181 */ this.produits.add(l.getIdlot().getIdproduit());
                    }
                }
            }
            /* 185 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
            /* 186 */        } catch (Exception e) {
            /* 187 */ notifyFail(e);
        }
    }

    public void prepareValidate() {
        try {
            if (this.inventaire == null) {
                notifyError("not_row_selected");
                return;
            }
            if (!Utilitaires.isAccess((49L))) {
                notifyError("acces_refuse");
                this.inventaire = null;
                return;
            }
            if (this.inventaire.getValidee()) {
                notifyError("inventaire_validee");
                return;
            }
            this.mode = "Validate";
            this.editQuantite = true;
            this.showSelectArticle = true;
            this.valideBtn = this.routine.localizeMessage("valider_inventaire");
            this.ligneinventaires = this.ligneinventaireFacadeLocal.findByInventaire(this.inventaire.getIdinventaire());
            this.produits.clear();
            if (!this.inventaire.getValidee()) {
                this.showSelectArticle = false;
                for (Ligneinventaire l : this.ligneinventaires) {
                    if (!this.produits.contains(l.getIdlot().getIdproduit())) {
                        this.produits.add(l.getIdlot().getIdproduit());
                    }
                }
            }
            RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').show()");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void prepareview() {
        try {
            if (this.inventaire != null) {
                this.ligneinventaires = this.ligneinventaireFacadeLocal.findByInventaire(this.inventaire.getIdinventaire());
                if (!this.ligneinventaires.isEmpty()) {
                    RequestContext.getCurrentInstance().execute("PF('FactureDetailDialog').show()");
                    return;
                }
                notifyError("liste_article_vide");
            } else {
                notifyError("not_row_selected");
            }
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void create() {
        try {
            if ("Create".equals(this.mode)) {

                if (!this.ligneinventaires.isEmpty()) {

                    this.ut.begin();

                    this.inventaire.setIdmois(this.anneeMois);
                    this.inventaire.setIdinventaire(this.inventaireFacadeLocal.nextVal());
                    this.inventaire.setValidee((false));
                    this.inventaireFacadeLocal.create(this.inventaire);

                    for (Ligneinventaire li : this.ligneinventaires) {
                        li.setIdLigneinventaire(this.ligneinventaireFacadeLocal.nextVal());
                        li.setIdInventaire(this.inventaire);
                        this.ligneinventaireFacadeLocal.create(li);
                    }

                    Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'inventaire : " + this.inventaire.getCode(), SessionMBean.getUserAccount());

                    this.ut.commit();
                    /* 270 */ this.inventaire = null;
                    /* 271 */ this.ligneinventaires.clear();
                    /* 272 */ this.detail = this.imprimer = (true);

                    /* 274 */ notifySuccess();
                    /* 275 */ RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
                } else {
                    notifyError("liste_article_vide");
                }
            } else if ("Edit".equals(this.mode)) {
                if (this.inventaire != null) {

                    this.ut.begin();
                    this.inventaireFacadeLocal.edit(this.inventaire);

                    if (!this.ligneinventaires.isEmpty()) {
                        for (Ligneinventaire li : this.ligneinventaires) {
                            this.ligneinventaireFacadeLocal.edit(li);
                        }
                    }

                    this.ut.commit();
                    this.inventaire = null;
                    this.ligneinventaires.clear();
                    this.detail = this.imprimer = (true);

                    notifySuccess();
                    RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
                } else {
                    notifyError("not_row_selected");
                }

            } else if (this.inventaire != null) {

                this.ut.begin();

                this.mvtStock.setIdMvtStock(this.mvtStockFacadeLocal.nextLongVal());
                /* 309 */ this.mvtStock.setDateMvt(this.inventaire.getDateInventaire());
                /* 310 */ this.mvtStock.setIdmois(this.inventaire.getIdmois());
                /* 311 */ this.mvtStock.setCode(this.inventaire.getCode());
                /* 312 */ this.mvtStock.setIdinventaire(BigInteger.valueOf(this.inventaire.getIdinventaire()));
                /* 313 */ this.mvtStockFacadeLocal.create(this.mvtStock);

                /* 315 */ this.inventaire.setIdMvtStock(BigInteger.valueOf(this.mvtStock.getIdMvtStock()));
                /* 316 */ this.inventaire.setValidee((true));
                /* 317 */ this.inventaireFacadeLocal.edit(this.inventaire);

                if (!this.ligneinventaires.isEmpty()) {
                    for (Ligneinventaire li : this.ligneinventaires) {

                        if (li.getEcart() == 0.0D) {
                            /* 323 */ li.setObservation("Normal");
                            /* 324 */                        } else if (li.getEcart() > 0.0D) {
                            /* 325 */ li.getIdlot().setQuantite(li.getQuantitePhysique());

                            /* 327 */ li.setObservation("Ajout de " + li.getEcart() + " Article(s)");
                            /* 328 */ this.lotFacadeLocal.edit(li.getIdlot());

                            /* 330 */ Produit pTemp = this.produitFacadeLocal.find(li.getIdlot().getIdproduit().getIdproduit());
                            /* 331 */ pTemp.setQuantite(Double.valueOf(pTemp.getQuantite().doubleValue() + li.getEcart()));
                            /* 332 */ this.produitFacadeLocal.edit(pTemp);

                            /* 334 */ LigneMvtStock lmvts = new LigneMvtStock();
                            /* 335 */ lmvts.setIdLigneMvtStock(this.ligneMvtStockFacadeLocal.nextLongVal());
                            /* 336 */ lmvts.setIdMvtStock(this.mvtStock);
                            /* 337 */ lmvts.setIdlot(li.getIdlot());
                            /* 338 */ lmvts.setClient("INVENTAIRE");
                            /* 339 */ lmvts.setFournisseur("/");
                            /* 340 */ lmvts.setQuantiteEntree(li.getEcart());
                            /* 341 */ lmvts.setQuantiteSortie((0.0D));
                            /* 342 */ lmvts.setReste(li.getIdlot().getQuantite());
                            /* 343 */ lmvts.setType("ENTREE");
                            /* 344 */ this.ligneMvtStockFacadeLocal.create(lmvts);
                        } else {
                            /* 346 */ li.getIdlot().setQuantite((li.getIdlot().getQuantite() - Math.abs(li.getEcart())));
                            /* 347 */ this.lotFacadeLocal.edit(li.getIdlot());
                            /* 348 */ li.setObservation("Retrait de " + Math.abs(li.getEcart()) + " Articles");

                            /* 350 */ Produit pTemp = this.produitFacadeLocal.find(li.getIdlot().getIdproduit().getIdproduit());
                            /* 351 */ pTemp.setQuantite((pTemp.getQuantite() - Math.abs(li.getEcart())));
                            /* 352 */ this.produitFacadeLocal.edit(pTemp);

                            /* 354 */ LigneMvtStock lmvts = new LigneMvtStock();
                            /* 355 */ lmvts.setIdLigneMvtStock(this.ligneMvtStockFacadeLocal.nextLongVal());
                            /* 356 */ lmvts.setIdMvtStock(this.mvtStock);
                            /* 357 */ lmvts.setIdlot(li.getIdlot());
                            /* 358 */ lmvts.setClient("/");
                            /* 359 */ lmvts.setFournisseur("INVENTAIRE");
                            /* 360 */ lmvts.setQuantiteEntree((0.0D));
                            /* 361 */ lmvts.setQuantiteSortie((Math.abs(li.getEcart())));
                            /* 362 */ lmvts.setReste(li.getIdlot().getQuantite());
                            /* 363 */ lmvts.setType("SORTIE");
                            /* 364 */ this.ligneMvtStockFacadeLocal.create(lmvts);
                        }
                        this.ligneinventaireFacadeLocal.edit(li);
                    }
                }

                this.ut.commit();
                this.inventaire = null;
                this.ligneinventaires.clear();
                this.detail = this.imprimer = (true);

                notifySuccess();
                RequestContext.getCurrentInstance().execute("PF('CommandeCreateDialog').hide()");
            } else {
                notifyError("not_row_selected");
            }

        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void delete() {
        try {
            if (this.inventaire == null) {
                notifyError("not_row_selected");
                return;
            }
            if (!Utilitaires.isAccess((50L))) {
                notifyError("acces_refuse");
                this.detail = this.imprimer = (true);
                this.inventaire = null;
                return;
            }
            if (this.inventaire.getValidee()) {
                notifyError("inventaire_validee");
                this.detail = this.imprimer = (true);
                this.inventaire = null;
                return;
            }
            this.ut.begin();

            List<Ligneinventaire> temp = this.ligneinventaireFacadeLocal.findByInventaire(this.inventaire.getIdinventaire());
            if (!temp.isEmpty()) {
                for (Ligneinventaire li : temp) {
                    this.ligneinventaireFacadeLocal.remove(li);
                }
            }
            this.inventaireFacadeLocal.remove(this.inventaire);

            this.ut.commit();
            Utilitaires.saveOperation(this.mouchardFacadeLocal, "Annulation de l'inventaire : " + this.inventaire.getCode(), SessionMBean.getUserAccount());
            this.inventaire = null;
            this.detail = this.imprimer = (true);
            notifySuccess();
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void annuler() {
        try {
            /* 452 */ if (this.inventaire == null) {
                /* 453 */ this.detail = this.imprimer = (true);
                /* 454 */ notifyError("not_row_selected");

                return;
            }
            if (!Utilitaires.isAccess((50L))) {
                this.detail = this.imprimer = (true);
                notifyError("acces_refuse");
                this.inventaire = null;
                return;
            }
            if (!this.inventaire.getValidee()) {
                notifyError("inventaire_non_validee");

                return;
            }
            this.ut.begin();

            List<Ligneinventaire> temp = this.ligneinventaireFacadeLocal.findByInventaire(this.inventaire.getIdinventaire());
            if (!temp.isEmpty()) {
                for (Ligneinventaire li : temp) {
                    this.ligneinventaireFacadeLocal.remove(li);
                    Produit pTemp = this.produitFacadeLocal.find(li.getIdlot().getIdproduit().getIdproduit());
                    if (li.getEcart().doubleValue() > 0.0D) {
                        pTemp.setQuantite(Double.valueOf(pTemp.getQuantite().doubleValue() - li.getEcart().doubleValue()));
                        li.getIdlot().setQuantite(Double.valueOf(li.getIdlot().getQuantite().doubleValue() - li.getEcart().doubleValue()));
                    } else {
                        pTemp.setQuantite(Double.valueOf(pTemp.getQuantite().doubleValue() + Math.abs(li.getEcart().doubleValue())));
                        li.getIdlot().setQuantite(Double.valueOf(li.getIdlot().getQuantite().doubleValue() + Math.abs(li.getEcart().doubleValue())));
                    }
                    this.lotFacadeLocal.edit(li.getIdlot());
                    this.produitFacadeLocal.edit(pTemp);
                }
            }
            this.inventaireFacadeLocal.remove(this.inventaire);

            MvtStock mTemp = this.mvtStockFacadeLocal.find(Long.valueOf(this.inventaire.getIdMvtStock().longValue()));
            List<LigneMvtStock> lmvt = this.ligneMvtStockFacadeLocal.findByMvt(mTemp.getIdMvtStock());
            for (LigneMvtStock l : lmvt) {
                this.ligneMvtStockFacadeLocal.remove(l);
            }
            this.mvtStockFacadeLocal.remove(mTemp);

            this.ut.commit();
            Utilitaires.saveOperation(this.mouchardFacadeLocal, "Annulation de l'inventaire : " + this.inventaire.getCode(), SessionMBean.getUserAccount());
            this.inventaire = null;
            this.detail = this.supprimer = this.imprimer = (true);
            notifySuccess();
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void initPrinter(Inventaire i) {
        /* 510 */ this.inventaire = i;
        /* 511 */ print();
    }

    public void initEdit(Inventaire i) {
        /* 515 */ this.inventaire = i;
        /* 516 */ prepareEdit();
    }

    public void initView(Inventaire i) {
        /* 520 */ this.inventaire = i;
        /* 521 */ prepareview();
    }

    public void initDelete(Inventaire i) {
        /* 525 */ this.inventaire = i;
        /* 526 */ delete();
    }

    public void print() {
        try {
            if (!Utilitaires.isAccess((51L))) {
                notifyError("acces_refuse");
                this.inventaire = null;

                return;
            }
            if (this.inventaire != null) {
                List<Ligneinventaire> l = this.ligneinventaireFacadeLocal.findByInventaire(this.inventaire.getIdinventaire());
                this.inventaire.setLigneinventaireList(l);
                this.fileName = PrintUtils.printInventaire(this.inventaire, SessionMBean.getParametrage());
                RequestContext.getCurrentInstance().execute("PF('FactureImprimerDialog').show()");
            } else {
                notifyError("not_row_selected");
            }
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public void addProduit() {
        try {
            for (Lot l : this.selectedLots) {

                if (!ifExistLot(this.ligneinventaires, l)) {
                    Ligneinventaire li = new Ligneinventaire();
                    li.setIdLigneinventaire((0L));
                    li.setPrix(l.getPrixVente());
                    li.setIdlot(l);
                    li.setQuantiteLogique(l.getQuantite());
                    li.setQuantitePhysique(l.getQuantite());
                    li.setEcart((0.0D));
                    li.setObservation("-");
                    this.ligneinventaires.add(li);
                }

                if (!this.produits.contains(l.getIdproduit())) {
                    this.produits.add(l.getIdproduit());
                }
            }
            RequestContext.getCurrentInstance().execute("PF('ArticleCreateDialog').hide()");
        } catch (Exception e) {
            notifyFail(e);
        }
    }

    public boolean ifExistLot(List<Ligneinventaire> ligneinventaires, Lot lot) {
        boolean result = false;
        for (Ligneinventaire l : ligneinventaires) {
            if (l.getIdlot().equals(lot)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void removeProduit(int index) {
        try {
            boolean trouve = false;
            this.ut.begin();

            Ligneinventaire li = this.ligneinventaires.get(index);
            if (li.getIdLigneinventaire() != 0L) {
                trouve = true;
                this.ligneinventaireFacadeLocal.remove(li);
                Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression du lot : " + li.getIdlot().getNumeroLot() + " dans l'inventaire : " + this.inventaire.getCode(), SessionMBean.getUserAccount());
            }
            this.ligneinventaires.remove(index);
            this.ut.commit();
            JsfUtil.addSuccessMessage("Supprimé");
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(this.routine.localizeMessage("echec_operation"));
        }
    }

    public void updateEcart(int index) {
        try {
            Ligneinventaire li = this.ligneinventaires.get(index);
            try {
                this.ligneinventaires.get(index).setEcart((li.getQuantitePhysique() - li.getIdlot().getQuantite()));
            } catch (Exception e) {
                ((Ligneinventaire) this.ligneinventaires.get(index)).setEcart((0.0D));
                ((Ligneinventaire) this.ligneinventaires.get(index)).setQuantitePhysique(li.getIdlot().getQuantite());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notifyError(String message) {
        /* 623 */ this.routine.feedBack("avertissement", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(message));
        /* 624 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /* 628 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 629 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 630 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /* 634 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 635 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /* 636 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}
