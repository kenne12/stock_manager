package controllers.utilisateur;

import controllers.utilisateur.AbstractUtilisateurController;
import entities.Personnel;
import entities.Utilisateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.ShaHash;
import utils.Utilitaires;

@ManagedBean
@ViewScoped
public class UtilisateurController
        extends AbstractUtilisateurController
        implements Serializable {

    @PostConstruct
    private void init() {
        /*  38 */ this.utilisateur = new Utilisateur();
        /*  39 */ this.templates.clear();
        /*  40 */ for (String temp : SessionMBean.getParametrage().getCheminTemplate().split(";")) {
            /*  41 */ this.templates.add(temp);
        }
    }

    public void prepareCreate() {
        try {
            /*  47 */ if (!Utilitaires.isAccess(Long.valueOf(2L))) {
                /*  48 */ signalError("acces_refuse");

                return;
            }
            /*  52 */ this.mode = "Create";

            /*  54 */ this.utilisateur = new Utilisateur();
            /*  55 */ this.utilisateur.setActif(Boolean.valueOf(true));
            /*  56 */ this.utilisateur.setPhoto("user_avatar.png");

            /*  58 */ List<Utilisateur> usersTemp = this.utilisateurFacadeLocal.findAll();
            /*  59 */ List<Personnel> personnelsTemp1 = new ArrayList<>();

            /*  61 */ for (Utilisateur u : usersTemp) {
                /*  62 */ personnelsTemp1.add(u.getIdpersonnel());
            }
            /*  64 */ this.personnels = this.personnelFacadeLocal.findByEtat(true);
            /*  65 */ this.personnels.removeAll(personnelsTemp1);
            /*  66 */ this.utilisateur.setTemplate(this.templates.get(1));
            /*  67 */ this.utilisateur.setTheme("hot-sneaks");

            /*  69 */ RequestContext.getCurrentInstance().execute("PF('UtilisateurCreerDialog').show()");
            /*  70 */        } catch (Exception e) {
            /*  71 */ signalException(e);
        }
    }

    public void prepareEdit() {
        try {
            /*  77 */ if (!Utilitaires.isAccess(Long.valueOf(3L))) {
                /*  78 */ signalError("acces_refuse");

                return;
            }
            /*  82 */ this.mode = "Edit";
            /*  83 */ this.personnels.clear();

            /*  85 */ if (this.utilisateur != null) {
                /*  86 */ this.personnels.add(this.utilisateur.getIdpersonnel());
                /*  87 */ this.personnel = this.utilisateur.getIdpersonnel();
            }

            /*  90 */ RequestContext.getCurrentInstance().execute("PF('UtilisateurCreerDialog').show()");
            /*  91 */        } catch (Exception e) {
            /*  92 */ signalException(e);
        }
    }

    public void create() {
        try {
            /*  98 */ if (this.mode.equals("Create")) {
                /*  99 */ this.utilisateur.setIdutilisateur(this.utilisateurFacadeLocal.nextVal());
                /* 100 */ this.utilisateur.setIdpersonnel(this.personnel);
                /* 101 */ this.utilisateur.setPassword((new ShaHash()).hash(this.utilisateur.getPassword()));
                /* 102 */ this.utilisateur.setActif(Boolean.valueOf(true));
                /* 103 */ this.utilisateurFacadeLocal.create(this.utilisateur);
                /* 104 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Enregistrement de l'utilisateur : " + this.utilisateur.getNom() + " " + this.utilisateur.getPrenom(), SessionMBean.getUserAccount());
                /* 105 */ this.utilisateur = new Utilisateur();
                /* 106 */ RequestContext.getCurrentInstance().execute("PF('UtilisateurCreerDialog').hide()");
                /* 107 */ signalSuccess();
            } /* 109 */ else if (this.utilisateur != null) {
                /* 110 */ this.utilisateur.setPassword((new ShaHash()).hash(this.utilisateur.getPassword()));
                /* 111 */ this.utilisateurFacadeLocal.edit(this.utilisateur);
                /* 112 */ RequestContext.getCurrentInstance().execute("PF('UtilisateurCreerDialog').hide()");
                /* 113 */ signalSuccess();
            } else {
                /* 115 */ signalError("not_row_selected");
            }

            /* 118 */        } catch (Exception e) {
            /* 119 */ signalException(e);
        }
    }

    public void reinitialiseAccount(Utilisateur utilisateur) {
        try {
            /* 125 */ if (!Utilitaires.isAccess(Long.valueOf(47L))) {
                /* 126 */ signalError("acces_refuse");
                return;
            }
            /* 129 */ utilisateur.setPassword((new ShaHash()).hash("123456"));
            /* 130 */ this.utilisateurFacadeLocal.edit(utilisateur);
            /* 131 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Réinitilisation du compte utilisateur de -> " + utilisateur.getIdpersonnel().getNom() + " " + utilisateur.getIdpersonnel().getPrenom(), SessionMBean.getUserAccount());
            /* 132 */ signalSuccess();
            /* 133 */        } catch (Exception e) {
            /* 134 */ signalException(e);
        }
    }

    public void delete() {
        try {
            /* 140 */ if (this.utilisateur != null) {

                /* 142 */ if (!Utilitaires.isAccess(Long.valueOf(4L))) {
                    /* 143 */ signalError("acces_refuse");

                    return;
                }
                /* 147 */ this.utilisateurFacadeLocal.remove(this.utilisateur);
                /* 148 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppresion de l'utilisateur : " + this.utilisateur.getNom() + " " + this.utilisateur.getPrenom(), SessionMBean.getUserAccount());

                /* 150 */ signalSuccess();
            } else {
                /* 152 */ signalError("not_row_selected");
            }
            /* 154 */        } catch (Exception e) {
            /* 155 */ signalException(e);
        }
    }

    public void changeStatus(Utilisateur utilisateur, String mode) {
        try {
            /* 161 */ if (mode.equals("activer")) {

                /* 163 */ if (!Utilitaires.isAccess(Long.valueOf(27L))) {
                    /* 164 */ signalError("acces_refuse");

                    return;
                }
                /* 168 */ utilisateur.setActif(Boolean.valueOf(true));
                /* 169 */ this.utilisateurFacadeLocal.edit(utilisateur);
                /* 170 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Activation du compte de l'utilisateur : " + utilisateur.getNom() + " " + utilisateur.getPrenom(), SessionMBean.getUserAccount());
                /* 171 */ JsfUtil.addSuccessMessage("Operation réussie");
            } else {

                /* 174 */ if (!Utilitaires.isAccess(Long.valueOf(28L))) {
                    /* 175 */ signalError("acces_refuse");

                    return;
                }
                /* 179 */ utilisateur.setActif(Boolean.valueOf(false));
                /* 180 */ this.utilisateurFacadeLocal.edit(utilisateur);
                /* 181 */ Utilitaires.saveOperation(this.mouchardFacadeLocal, "Désativation du compte de l'utilisateur : " + utilisateur.getNom() + " " + utilisateur.getPrenom(), SessionMBean.getUserAccount());
                /* 182 */ JsfUtil.addSuccessMessage("Operation réussie");
            }
            /* 184 */        } catch (Exception e) {
            /* 185 */ e.printStackTrace();
        }
    }

    public void print() {
    }

    public void signalError(String chaine) {
        /* 194 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 195 */ this.routine.feedBack("information", "/resources/tool_images/warning.jpeg", this.routine.localizeMessage(chaine));
        /* 196 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void signalSuccess() {
        /* 200 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 201 */ this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
        /* 202 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }

    public void signalException(Exception e) {
        /* 206 */ RequestContext.getCurrentInstance().execute("PF('AjaxNotifyDialog').hide()");
        /* 207 */ this.routine.catchException(e, this.routine.localizeMessage("erreur_execution"));
        /* 208 */ RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controller\\utilisateur\UtilisateurController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
