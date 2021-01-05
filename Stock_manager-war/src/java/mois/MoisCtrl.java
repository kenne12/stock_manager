package mois;

import entities.Annee;
import entities.AnneeMois;
import entities.Mois;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import mois.AbstractMoisCtrl;
import mois.MoisInterfaceCtrl;
import org.primefaces.context.RequestContext;
import utils.SessionMBean;
import utils.Utilitaires;

@ManagedBean(name = "moisCtrl")
@ViewScoped
public class MoisCtrl
        extends AbstractMoisCtrl
        implements MoisInterfaceCtrl, Serializable {

    @PostConstruct
    private void initAcces() {
    }

    public void prepareCreate() {
        try {
            /*  37 */ if (!Utilitaires.isAccess(Long.valueOf(36L))) {
                /*  38 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("acces_refuse"));
                /*  39 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            } else {
                /*  41 */ this.annee = new Annee();
                /*  42 */ this.moises.clear();
                /*  43 */ this.selectedMois.clear();
                /*  44 */ RequestContext.getCurrentInstance().execute("PF('MoisCreerDialog').show()");
            }
            /*  46 */        } catch (Exception e) {
            /*  47 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /*  48 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void prepareEdit() {
        try {
            /*  54 */ if (!Utilitaires.isAccess(Long.valueOf(36L))) {
                /*  55 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("acces_refuse"));
                /*  56 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            } else {
                /*  58 */ RequestContext.getCurrentInstance().execute("PF('MoisModifierDialog').show()");
            }
            /*  60 */        } catch (Exception e) {
            /*  61 */ this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
            /*  62 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void updateMois() {
        /*  67 */ this.moises.clear();
        /*  68 */ this.selectedMois.clear();
        try {
            /*  70 */ List<AnneeMois> temp = this.anneeMoisFacadeLocal.findByAnnee(this.annee);

            /*  72 */ if (temp.isEmpty()) {
                /*  73 */ this.moises = this.moisFacadeLocal.findAllRange();
            } else {
                /*  75 */ List<Mois> temp1 = this.moisFacadeLocal.findAllRange();
                /*  76 */ List<Mois> temp2 = new ArrayList<>();
                /*  77 */ for (AnneeMois a : temp) {
                    /*  78 */ temp2.add(a.getIdmois());
                }

                /*  81 */ for (Mois m : temp1) {
                    /*  82 */ if (!temp2.contains(m)) {
                        /*  83 */ this.moises.add(m);
                    }
                }
            }
            /*  87 */        } catch (Exception e) {
            /*  88 */ e.printStackTrace();
        }
    }

    public void enregistrerMois() {
        try {
            /*  95 */ if (!this.selectedMois.isEmpty()) {
                /*  96 */ for (Mois m : this.selectedMois) {
                    /*  97 */ this.anneeMois = new AnneeMois();
                    /*  98 */ this.anneeMois.setIdAnneeMois(this.anneeMoisFacadeLocal.nextVal());
                    /*  99 */ this.anneeMois.setIdannee(this.annee);
                    /* 100 */ this.anneeMois.setIdmois(m);
                    /* 101 */ this.anneeMois.setEtat(Boolean.valueOf(true));
                    /* 102 */ this.anneeMoisFacadeLocal.create(this.anneeMois);

                    /* 104 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement du mois  -> " + m.getNom() + " Année -> " + this.annee.getNom(), SessionMBean.getUserAccount());
                }
                /* 106 */ RequestContext.getCurrentInstance().execute("PF('MoisCreerDialog').hide()");
                /* 107 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
                /* 108 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
                /* 109 */ this.anneeMois = null;
                /* 110 */ this.moises.clear();
                /* 111 */ this.selectedMois.clear();
            }
            /* 113 */        } catch (Exception e) {
            /* 114 */ this.routine.catchException(e, this.routine.localizeMessage("suppression_impossible"));
            /* 115 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void modifier() {
        try {
            /* 122 */ if (this.anneeMois != null) {
                /* 123 */ if (this.anneeMois.getDateFin().after(this.anneeMois.getDateDebut())) {
                    /* 124 */ this.anneeMoisFacadeLocal.edit(this.anneeMois);
                } else {
                    /* 126 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("date_non_conforme"));
                    /* 127 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
                }
                /* 129 */ RequestContext.getCurrentInstance().execute("PF('MoisModifierDialog').hide()");
                /* 130 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
                /* 131 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            } else {
                /* 133 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("not_row_selected"));
                /* 134 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            }
            /* 136 */        } catch (Exception e) {
            /* 137 */ this.routine.catchException(e, this.routine.localizeMessage("suppression_impossible"));
            /* 138 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void supprimer() {
        try {
            /* 145 */ if (!Utilitaires.isAccess(Long.valueOf(37L))) {
                /* 146 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("acces_refuse"));
                /* 147 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");

                return;
            }
            /* 151 */ if (this.anneeMois != null) {
                /* 152 */ this.anneeMoisFacadeLocal.remove(this.anneeMois);
                /* 153 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression du mois -> " + this.anneeMois.getIdmois().getNom() + " de l'exercice -> " + this.anneeMois.getIdannee().getNom(), SessionMBean.getUserAccount());
                /* 154 */ this.anneeMois = null;
                /* 155 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
                /* 156 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            } else {
                /* 158 */ this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("not_row_selectd"));
                /* 159 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
            }
            /* 161 */        } catch (Exception e) {
            /* 162 */ this.routine.catchException(e, this.routine.localizeMessage("suppression_impossible"));
            /* 163 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
        }
    }

    public void imprimerPrivilegePdf() {
        /* 169 */ throw new UnsupportedOperationException("Not supported yet.");
    }

    public void imprimerPrivilegeHtml() {
        /* 174 */ throw new UnsupportedOperationException("Not supported yet.");
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\mois\MoisCtrl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
