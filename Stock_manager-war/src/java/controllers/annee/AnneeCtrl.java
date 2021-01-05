/*     */ package controllers.annee;
/*     */ 
/*     */ import controllers.annee.AbstractAnneCtrl;
/*     */ import controllers.annee.AnneeInterfaceCtrl;
/*     */ import entities.Annee;
/*     */ import java.io.Serializable;
/*     */ import javax.annotation.PostConstruct;
/*     */ import javax.faces.bean.ManagedBean;
/*     */ import javax.faces.bean.ViewScoped;
/*     */ import org.primefaces.context.RequestContext;
/*     */ import utils.JsfUtil;
/*     */ import utils.SessionMBean;
/*     */ import utils.Utilitaires;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ManagedBean(name = "anneeCtrl")
/*     */ @ViewScoped
/*     */ public class AnneeCtrl
/*     */   extends AbstractAnneCtrl
/*     */   implements AnneeInterfaceCtrl, Serializable
/*     */ {
/*     */   @PostConstruct
/*     */   private void initAnnee() {
/*  28 */     this.selectedAnnee = new Annee();
/*  29 */     this.annee = new Annee();
/*     */   }
/*     */   
/*     */   public void prepareCreate() {
/*     */     try {
/*  34 */       if (!Utilitaires.isAccess(Long.valueOf(33L))) {
/*  35 */         this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("acces_refuse"));
/*  36 */         RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
/*     */       } else {
/*  38 */         this.annee = new Annee();
/*  39 */         this.annee.setEtat(Boolean.valueOf(true));
/*  40 */         RequestContext.getCurrentInstance().execute("PF('AnneeCreerDialog').show()");
/*     */       } 
/*  42 */     } catch (Exception e) {
/*  43 */       this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
/*  44 */       RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void prepareEdit() {
/*     */     try {
/*  50 */       if (!Utilitaires.isAccess(Long.valueOf(34L))) {
/*  51 */         this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("acces_refuse"));
/*  52 */         RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
/*     */       } else {
/*  54 */         RequestContext.getCurrentInstance().execute("PF('AnneeModifierDialog').show()");
/*     */       } 
/*  56 */     } catch (Exception e) {
/*  57 */       this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
/*  58 */       RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void enregistrerAnnee() {
/*     */     try {
/*  66 */       Annee an = this.anneeFacadeLocal.findByCode(this.annee.getNom());
/*  67 */       if (an != null) {
/*  68 */         JsfUtil.addErrorMessage("Un exercice ayant ces paramètres existe déjà");
/*     */       } else {
/*  70 */         this.annee.setIdannee(this.anneeFacadeLocal.nextVal());
/*  71 */         this.anneeFacadeLocal.create(this.annee);
/*     */         
/*  73 */         this.modifier = this.detail = this.supprimer = true;
/*     */         
/*  75 */         Utilitaires.saveOperation(this.mouchardFacadeLocal, "Création  de l'exercice : " + this.annee.getNom(), SessionMBean.getUserAccount());
/*  76 */         this.annee = null;
/*     */         
/*  78 */         RequestContext.getCurrentInstance().execute("PF('AnneeCreerDialog').hide()");
/*  79 */         this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
/*  80 */         RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
/*     */       } 
/*  82 */     } catch (Exception e) {
/*  83 */       this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
/*  84 */       RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void modifier() {
/*     */     try {
/*  91 */       if (this.selectedAnnee == null) {
/*  92 */         this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("not_row_selected"));
/*  93 */         RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
/*     */       } else {
/*  95 */         this.anneeFacadeLocal.edit(this.selectedAnnee);
/*  96 */         Utilitaires.saveOperation(this.mouchardFacadeLocal, "Modification de l'exercice -> " + this.selectedAnnee.getNom(), SessionMBean.getUserAccount());
/*  97 */         this.modifier = this.detail = this.supprimer = true;
/*  98 */         this.selectedAnnee = null;
/*     */         
/* 100 */         RequestContext.getCurrentInstance().execute("PF('AnneeModifierDialog').hide()");
/* 101 */         this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
/* 102 */         RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
/*     */       } 
/* 104 */     } catch (Exception e) {
/* 105 */       this.routine.catchException(e, this.routine.localizeMessage("echec_operation"));
/* 106 */       RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void supprimer() {
/*     */     try {
/* 113 */       if (!Utilitaires.isAccess(Long.valueOf(35L))) {
/* 114 */         this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("acces_refuse"));
/* 115 */         RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
/*     */         
/*     */         return;
/*     */       } 
/* 119 */       if (this.selectedAnnee == null || this.selectedAnnee.getIdannee() == null) {
/* 120 */         this.routine.feedBack("information", "/resources/tool_images/error.png", this.routine.localizeMessage("not_row_selected"));
/* 121 */         RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
/*     */         return;
/*     */       } 
/* 124 */       this.anneeFacadeLocal.remove(this.selectedAnnee);
/* 125 */       this.modifier = this.detail = this.supprimer = true;
/* 126 */       Utilitaires.saveOperation(this.mouchardFacadeLocal, "Suppression de l'exercice -> " + this.selectedAnnee.getNom(), SessionMBean.getUserAccount());
/* 127 */       this.selectedAnnee = null;
/* 128 */       this.routine.feedBack("information", "/resources/tool_images/success.png", this.routine.localizeMessage("operation_reussie"));
/* 129 */       RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
/*     */     }
/* 131 */     catch (Exception e) {
/* 132 */       this.routine.catchException(e, this.routine.localizeMessage("suppression_impossible"));
/* 133 */       RequestContext.getCurrentInstance().execute("PF('NotifyDialog1').show()");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void imprimerAnneePdf() {}
/*     */   
/*     */   public void imprimerAnneeHtml() {}
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\annee\AnneeCtrl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */