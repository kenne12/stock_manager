package controllers.recette;

import controllers.recette.AbstractRecetteController;
import entities.Facture;
import entities.Journee;
import java.io.Serializable;
import java.util.List;
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
public class RecetteController
        extends AbstractRecetteController
        implements Serializable {

    @PostConstruct
    private void init() {
        /*  39 */ this.password.add("momo1234");
        /*  40 */ this.password.add("kenne1234");
    }

    public void updateMois() {
        /*  44 */ this.listMois.clear();
        try {
            /*  46 */ if (this.annee.getIdannee() != null) {
                /*  47 */ this.listMois = this.anneeMoisFacadeLocal.findByAnnee(this.annee);
            }
            /*  49 */        } catch (Exception e) {
            /*  50 */ e.printStackTrace();
        }
    }

    public void notifyError(String message) {
        /*  55 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /*  56 */ this.routine.feedBack("avertissement", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(message));
        /*  57 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifySuccess() {
        /*  61 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /*  62 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /*  63 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void notifyFail(Exception e) {
        /*  67 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /*  68 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
        /*  69 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void checkSession() {
        try {
            /*  74 */ if (!"".equals(this.sessionPassword)) {
                /*  75 */ if (this.password.contains(this.sessionPassword)) {
                    /*  76 */ HttpSession session1 = SessionMBean.getSession();
                    /*  77 */ session1.setAttribute("session", Boolean.valueOf(true));
                    /*  78 */ this.session = Boolean.valueOf(false);
                } else {
                    /*  80 */ JsfUtil.addErrorMessage("Mot de passe incorrect");
                }
            }
            /*  83 */        } catch (Exception e) {
            /*  84 */ e.printStackTrace();
        }
    }

    public void afficher() {
        /*  89 */ this.journees.clear();
        /*  90 */ if (this.anneeMois.getIdAnneeMois() != null) {
            /*  91 */ this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());
            /*  92 */ this.journees = this.journeeFacadeLocal.findByIdmois(this.anneeMois.getIdAnneeMois().intValue());
        }
    }

    public void conciliate() {
        try {
            /*  98 */ if (this.anneeMois.getIdAnneeMois() != null) {

                /* 100 */ this.anneeMois = this.anneeMoisFacadeLocal.find(this.anneeMois.getIdAnneeMois());

                /* 121 */ List<Journee> list = this.journeeFacadeLocal.findByIdmois(this.anneeMois.getIdAnneeMois().intValue());
                /* 122 */ for (Journee j : list) {
                    /* 123 */ List<Facture> factures = this.factureFacadeLocal.findAllDate(j.getDateJour());

                    /* 125 */ Double somme = Double.valueOf(0.0D);
                    /* 126 */ Double bord = Double.valueOf(0.0D);

                    /* 128 */ for (Facture f : factures) {
                        /* 129 */ somme = Double.valueOf(somme.doubleValue() + f.getMontantTtc().doubleValue());
                        /* 130 */ bord = Double.valueOf(bord.doubleValue() + f.getBord().doubleValue());
                    }

                    /* 133 */ j.setMontant(somme);
                    /* 134 */ j.setBord(bord);
                    /* 135 */ this.journeeFacadeLocal.edit(j);
                }

                /* 138 */ this.journees = this.journeeFacadeLocal.findByIdmois(this.anneeMois.getIdAnneeMois().intValue());
            }
            /* 140 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            /* 141 */        } catch (Exception e) {
            /* 142 */ notifyFail(e);
        }
    }

    public void printRecette() {
        try {
            /* 148 */ if (!Utilitaires.isAccess(Long.valueOf(32L))) {
                /* 149 */ notifyError("acces_refuse");

                return;
            }
            /* 153 */ this.fileName1 = PrintUtils.printRecette(this.anneeMois, this.journees);
            /* 154 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
            /* 155 */ RequestContext.getCurrentInstance().execute("PF('StockImprimerDialog').show()");
            /* 156 */        } catch (Exception e) {
            /* 157 */ notifyFail(e);
        }
    }
}
