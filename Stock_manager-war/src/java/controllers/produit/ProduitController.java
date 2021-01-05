package controllers.produit;

import controllers.produit.AbstractProduitController;
import entities.Bailleur;
import entities.Famille;
import entities.Formeproduit;
import entities.Formestockage;
import entities.Fournisseur;
import entities.Lot;
import entities.Produit;
import entities.Unite;
import entities.UserP;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class ProduitController
        extends AbstractProduitController
        implements Serializable {

    @PostConstruct
    private void init() {
        /*  45 */ this.fournisseur = new Fournisseur();
        /*  46 */ this.famille = new Famille();
        /*  47 */ this.password.add("momo1234");
        /*  48 */ this.password.add("kenne1234");
    }

    public void prepareCreate() {
        /*  52 */ if (!Utilitaires.isAccess(Long.valueOf(13L))) {
            /*  53 */ notifyError("acces_refuse");

            return;
        }
        /*  57 */ this.mode = "Create";
        /*  58 */ this.fournisseur = new Fournisseur();
        /*  59 */ this.unite = new Unite();
        /*  60 */ this.famille = new Famille();
        /*  61 */ this.produit = new Produit();
        /*  62 */ this.produit.setQuantite(Double.valueOf(0.0D));
        /*  63 */ this.produit.setStockCritique(Integer.valueOf(0));
        /*  64 */ this.produit.setPerissable(Boolean.valueOf(true));
        /*  65 */ this.produit.setEtat(Boolean.valueOf(true));
        /*  66 */ this.produit.setQteDosage(Double.valueOf(0.0D));
        /*  67 */ this.produit.setPhoto("article.jpeg");
        /*  68 */ this.produit.setPrixachat(Double.valueOf(0.0D));
        /*  69 */ this.produit.setPrixMaximal(Double.valueOf(0.0D));
        /*  70 */ this.produit.setTva(SessionMBean.getParametrage().getTauxTva());

        /*  72 */ this.formeproduit = new Formeproduit();
        /*  73 */ this.formestockage = new Formestockage();
        /*  74 */ this.userP = new UserP();
        /*  75 */ this.bailleur = new Bailleur();
        /*  76 */ this.lot = new Lot();

        /*  78 */ this.showLot = true;
        /*  79 */ this.showUser = SessionMBean.getParametrage().getEtatuser().booleanValue();
        /*  80 */ this.showBailleur = SessionMBean.getParametrage().getEtatbailleur().booleanValue();
        /*  81 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreerDialog').show()");
    }

    public void prepareEdit() {
        /*  85 */ this.mode = "Edit";
        try {
            /*  87 */ if (!Utilitaires.isAccess(Long.valueOf(14L))) {
                /*  88 */ notifyError("acces_refuse");
                return;
            }
            /*  91 */ if (this.produit != null) {
                /*  92 */ this.showLot = false;
                /*  93 */ this.famille = this.produit.getIdfamille();
                /*  94 */ this.unite = this.produit.getIdUnite();
                /*  95 */ this.formeproduit = this.produit.getIdforme();
                /*  96 */ this.formestockage = this.produit.getIdformeStockage();
                /*  97 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreerDialog').show()");

                return;
            }
            /* 101 */ notifyError("not_row_select");
            /* 102 */        } catch (Exception e) {
            /* 103 */ notifyFail(e);
        }
    }

    public void updateLot() {
        /* 108 */ if ("Create".equals(this.mode)) {
            /* 109 */ this.showLot = this.produit.getPerissable().booleanValue();
            /* 110 */ if (!this.showLot) {
                /* 111 */ this.showBailleur = this.showUser = false;
            } else {
                /* 113 */ if (SessionMBean.getParametrage().getEtatbailleur().booleanValue()) {
                    /* 114 */ this.showBailleur = true;
                }
                /* 116 */ if (SessionMBean.getParametrage().getEtatuser().booleanValue()) {
                    /* 117 */ this.showUser = true;
                }
            }
            /* 120 */ this.lot = new Lot();
            return;
        }
        /* 123 */ this.showLot = false;
    }

    public void create() {
        try {
            /* 128 */ if (this.mode.equals("Create")) {

                /* 130 */ if (!this.produitFacadeLocal.findByCode(this.produit.getCode()).isEmpty()) {
                    /* 131 */ notifyError("code_article_existant");

                    return;
                }
                /* 135 */ if (!Utilitaires.checkBenefice(this.produit.getPrixachat().doubleValue(), this.produit.getPrixMaximal().doubleValue())) {
                    /* 136 */ notifyError("prix_achat_vente_incorrect");

                    return;
                }
                /* 140 */ this.produit.setIdproduit(this.produitFacadeLocal.nextVal());

                /* 142 */ if (this.famille.getIdfamille() != null) {
                    /* 143 */ this.produit.setIdfamille(this.famille);
                }

                /* 146 */ this.produit.setIdUnite(this.unite);

                /* 148 */ if (this.showFormeProduit) {
                    /* 149 */ this.produit.setIdforme(this.formeproduit);
                } else {
                    /* 151 */ this.produit.setIdforme(this.formeproduitFacadeLocal.find(Integer.valueOf(1)));
                }

                /* 154 */ if (this.showFormeStockage) {
                    /* 155 */ this.produit.setIdformeStockage(this.formestockage);
                } else {
                    /* 157 */ this.produit.setIdformeStockage(this.formestockageFacadeLocal.find(Integer.valueOf(1)));
                }

                /* 160 */ this.ut.begin();

                /* 162 */ this.produitFacadeLocal.create(this.produit);

                /* 164 */ this.lot.setIdlot(this.lotFacadeLocal.nextLongVal());
                /* 165 */ this.lot.setIdproduit(this.produit);
                /* 166 */ this.lot.setPrixVente(this.produit.getPrixMaximal());
                /* 167 */ this.lot.setPrixAchat(this.produit.getPrixachat());
                /* 168 */ this.lot.setQuantite(this.produit.getQuantite());
                /* 169 */ this.lot.setStockCritique(Double.valueOf(this.produit.getStockCritique().doubleValue()));
                /* 170 */ this.lot.setDateEnregistrement(new Date());
                /* 171 */ this.lot.setEtat(Boolean.valueOf(true));

                /* 173 */ if (this.showBailleur) {
                    /* 174 */ this.lot.setIdbailleur(this.bailleur);
                } else {
                    /* 176 */ this.lot.setIdbailleur(this.bailleurFacadeLocal.find(Integer.valueOf(1)));
                }

                /* 179 */ if (this.showUser) {
                    /* 180 */ this.lot.setIduserP(this.userP);
                } else {
                    /* 182 */ this.lot.setIduserP(this.userPFacadeLocal.find(Integer.valueOf(1)));
                }

                /* 185 */ if (this.showLot) {
                    /* 186 */ if (this.lot.getDatePeremption().before(this.lot.getDateFabrication())) {
                        /* 187 */ Date d1 = this.lot.getDatePeremption();
                        /* 188 */ this.lot.setDatePeremption(this.lot.getDateFabrication());
                        /* 189 */ this.lot.setDateFabrication(d1);
                    }
                    /* 191 */ this.lotFacadeLocal.create(this.lot);
                } else {
                    /* 193 */ this.lot.setDateFabrication(null);
                    /* 194 */ this.lot.setDatePeremption(null);
                    /* 195 */ this.lot.setNumeroLot(this.produit.getCode());
                    /* 196 */ this.lotFacadeLocal.create(this.lot);
                }

                /* 199 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du produit : " + this.produit.getNom(), SessionMBean.getUserAccount());

                /* 201 */ this.ut.commit();
                /* 202 */ this.fournisseur = null;
                /* 203 */ this.famille = new Famille();
                /* 204 */ this.produit = new Produit();

                /* 206 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf(true);

                /* 208 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreerDialog').hide()");
                /* 209 */ notifySuccess();
            } /* 211 */ else if (this.produit != null) {

                /* 213 */ Produit p = this.produitFacadeLocal.find(this.produit.getIdproduit());

                /* 215 */ if (!p.getCode().equals(this.produit.getCode())
                        && /* 216 */ !this.produitFacadeLocal.findByCode(this.produit.getCode()).isEmpty()) {
                    /* 217 */ notifyError("code_article_existant");

                    return;
                }

                /* 222 */ if (this.famille.getIdfamille() != p.getIdfamille().getIdfamille()) {
                    /* 223 */ this.produit.setIdfamille(this.familleFacadeLocal.find(this.famille.getIdfamille()));
                }
                /* 225 */ this.produit.setIdUnite(this.uniteFacadeLocal.find(this.unite.getIdUnite()));

                /* 227 */ if (this.showFormeProduit) {
                    /* 228 */ this.produit.setIdforme(this.formeproduitFacadeLocal.find(this.formeproduit.getIdforme()));
                }

                /* 231 */ if (this.showFormeStockage) {
                    /* 232 */ this.produit.setIdformeStockage(this.formestockageFacadeLocal.find(this.formestockage.getIdformeStockage()));
                }

                /* 235 */ this.ut.begin();
                /* 236 */ this.produitFacadeLocal.edit(this.produit);
                /* 237 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Modification du produit : " + this.produit.getNom() + " Ancienne quantité : " + p.getQuantite() + " ; Nouvelle quantité : " + this.produit.getQuantite(), SessionMBean.getUserAccount());
                /* 238 */ this.ut.commit();

                /* 240 */ this.modifier = this.supprimer = this.detail = Boolean.valueOf(true);
                /* 241 */ this.produit = new Produit();
                /* 242 */ RequestContext.getCurrentInstance().execute("PF('ArticleCreerDialog').hide()");
                /* 243 */ notifySuccess();
            } else {
                /* 245 */ notifyError("not_row_select");
            }

            /* 248 */        } catch (Exception e) {
            /* 249 */ notifyFail(e);
        }
    }

    public void notifyError(String message) {
        /* 254 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 255 */ this.routine.feedBack("avertissement", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(message));
        /* 256 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /* 260 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 261 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 262 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /* 266 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 267 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /* 268 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void checkSession() {
        try {
            /* 273 */ if (!"".equals(this.sessionPassword)) {
                /* 274 */ if (this.password.contains(this.sessionPassword)) {
                    /* 275 */ HttpSession session1 = SessionMBean.getSession();
                    /* 276 */ session1.setAttribute("session", Boolean.valueOf(true));
                    /* 277 */ this.session = Boolean.valueOf(false);
                } else {
                    /* 279 */ JsfUtil.addErrorMessage("Mot de passe incorrect");
                }
            }
            /* 282 */        } catch (Exception e) {
            /* 283 */ e.printStackTrace();
        }
    }

    public void delete() {
        try {
            /* 289 */ if (this.produit != null) {

                /* 291 */ if (!Utilitaires.isAccess(Long.valueOf(15L))) {
                    /* 292 */ notifyError("acces_refuse");

                    return;
                }
                /* 296 */ this.ut.begin();
                /* 297 */ this.produitFacadeLocal.remove(this.produit);
                /* 298 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion du produit : " + this.produit.getNom(), SessionMBean.getUserAccount());
                /* 299 */ this.ut.commit();

                /* 301 */ this.produit = null;

                /* 303 */ notifySuccess();
            } else {
                /* 305 */ notifyError("not_row_selected");
            }
            /* 307 */        } catch (Exception e) {
            /* 308 */ notifyFail(e);
        }
    }

    public void print() {
        try {
            /* 314 */ if (!Utilitaires.isAccess(Long.valueOf(29L))) {
                /* 315 */ notifyError("acces_refuse");
                return;
            }
            /* 318 */ this.fileName = PrintUtils.printProductReport(this.produits);
            /* 319 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            /* 320 */ RequestContext.getCurrentInstance().execute("PF('ArticleImprimerDialog').show()");
            /* 321 */        } catch (Exception e) {
            /* 322 */ notifyFail(e);
        }
    }

    public void printStockGeneral() {
        try {
            /* 328 */ if (!Utilitaires.isAccess(Long.valueOf(32L))) {
                /* 329 */ notifyError("acces_refuse");
                return;
            }
            /* 332 */ this.fileName1 = PrintUtils.printGeneralStockReport(this.produits);
            /* 333 */ RequestContext.getCurrentInstance().execute("PF('StockImprimerDialog').show()");
            /* 334 */        } catch (Exception e) {
            /* 335 */ notifyFail(e);
        }
    }

    public void printInventory() {
        try {
            /* 341 */ if (!Utilitaires.isAccess(Long.valueOf(31L))) {
                /* 342 */ notifyError("acces_refuse");

                return;
            }
            /* 346 */ this.fileName1 = PrintUtils.printInventoryReport(this.produits);
            /* 347 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            /* 348 */ RequestContext.getCurrentInstance().execute("PF('InventaireImprimerDialog').show()");
            /* 349 */        } catch (Exception e) {
            /* 350 */ notifyFail(e);
        }
    }

    public void printSousStock() {
        try {
            /* 356 */ if (!Utilitaires.isAccess(Long.valueOf(30L))) {
                /* 357 */ notifyError("acces_refuse");

                return;
            }
            /* 361 */ this.fileName2 = PrintUtils.printCritickStockReport(this.produits1);
            /* 362 */ RequestContext.getCurrentInstance().execute("PF('StockImprimerDialog').show()");
            /* 363 */        } catch (Exception e) {
            /* 364 */ notifyFail(e);
        }
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\produit\ProduitController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
