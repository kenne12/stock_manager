/*    */ package controllers.annee;
/*    */ 
/*    */ import entities.Annee;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.ejb.EJB;
/*    */ import sessions.AnneeFacadeLocal;
/*    */ import sessions.MouchardFacadeLocal;
/*    */ import utils.Routine;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbstractAnneCtrl
/*    */ {
/*    */   @EJB
/*    */   protected AnneeFacadeLocal anneeFacadeLocal;
/* 24 */   protected Annee annee = new Annee();
/* 25 */   protected Annee selectedAnnee = new Annee();
/* 26 */   protected List<Annee> annees = new ArrayList<>();
/* 27 */   protected List<Annee> anneeCourantes = new ArrayList<>();
/*    */   
/*    */   @EJB
/*    */   protected MouchardFacadeLocal mouchardFacadeLocal;
/*    */   
/* 32 */   protected Routine routine = new Routine();
/*    */   
/*    */   protected boolean detail = true;
/*    */   protected boolean modifier = true;
/*    */   protected boolean supprimer = true;
/*    */   protected boolean imprimer = true;
/*    */   
/*    */   public List<Annee> getAnnees() {
/* 40 */     this.annees = this.anneeFacadeLocal.findByRange();
/* 41 */     return this.annees;
/*    */   }
/*    */   
/*    */   public Annee getSelectedAnnee() {
/* 45 */     return this.selectedAnnee;
/*    */   }
/*    */   
/*    */   public void setSelectedAnnee(Annee selectedAnnee) {
/* 49 */     this.selectedAnnee = selectedAnnee;
/* 50 */     this.modifier = this.supprimer = this.detail = (selectedAnnee == null);
/*    */   }
/*    */   
/*    */   public boolean isDetail() {
/* 54 */     return this.detail;
/*    */   }
/*    */   
/*    */   public boolean isModifier() {
/* 58 */     return this.modifier;
/*    */   }
/*    */   
/*    */   public boolean isSupprimer() {
/* 62 */     return this.supprimer;
/*    */   }
/*    */   
/*    */   public boolean isImprimer() {
/* 66 */     this.imprimer = this.anneeFacadeLocal.findAll().isEmpty();
/* 67 */     return this.imprimer;
/*    */   }
/*    */   
/*    */   public Annee getAnnee() {
/* 71 */     return this.annee;
/*    */   }
/*    */   
/*    */   public void setAnnee(Annee annee) {
/* 75 */     this.annee = annee;
/*    */   }
/*    */   
/*    */   public void setAnneeCourantes(List<Annee> anneeCourantes) {
/* 79 */     this.anneeCourantes = anneeCourantes;
/*    */   }
/*    */   
/*    */   public Routine getRoutine() {
/* 83 */     return this.routine;
/*    */   }
/*    */   
/*    */   public void setRoutine(Routine routine) {
/* 87 */     this.routine = routine;
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\annee\AbstractAnneCtrl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */