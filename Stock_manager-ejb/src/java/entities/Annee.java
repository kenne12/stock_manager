/*     */ package entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.NamedQuery;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.validation.constraints.NotNull;
/*     */ import javax.validation.constraints.Size;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
/*     */ import javax.xml.bind.annotation.XmlTransient;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Annee.findAll", query = "SELECT a FROM Annee a"), @NamedQuery(name = "Annee.findByIdannee", query = "SELECT a FROM Annee a WHERE a.idannee = :idannee"), @NamedQuery(name = "Annee.findByNom", query = "SELECT a FROM Annee a WHERE a.nom = :nom"), @NamedQuery(name = "Annee.findByEtat", query = "SELECT a FROM Annee a WHERE a.etat = :etat"), @NamedQuery(name = "Annee.findByDateDebut", query = "SELECT a FROM Annee a WHERE a.dateDebut = :dateDebut"), @NamedQuery(name = "Annee.findByDateFin", query = "SELECT a FROM Annee a WHERE a.dateFin = :dateFin")})
/*     */ public class Annee
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Integer idannee;
/*     */   @Size(max = 2147483647)
/*     */   private String nom;
/*     */   private Boolean etat;
/*     */   @Column(name = "date_debut")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateDebut;
/*     */   @Column(name = "date_fin")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateFin;
/*     */   @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
/*     */   private List<AnneeMois> anneeMoisList;
/*     */   
/*     */   public Annee() {}
/*     */   
/*     */   public Annee(Integer idannee) {
/*  61 */     this.idannee = idannee;
/*     */   }
/*     */   
/*     */   public Integer getIdannee() {
/*  65 */     return this.idannee;
/*     */   }
/*     */   
/*     */   public void setIdannee(Integer idannee) {
/*  69 */     this.idannee = idannee;
/*     */   }
/*     */   
/*     */   public String getNom() {
/*  73 */     return this.nom;
/*     */   }
/*     */   
/*     */   public void setNom(String nom) {
/*  77 */     this.nom = nom;
/*     */   }
/*     */   
/*     */   public Boolean getEtat() {
/*  81 */     return this.etat;
/*     */   }
/*     */   
/*     */   public void setEtat(Boolean etat) {
/*  85 */     this.etat = etat;
/*     */   }
/*     */   
/*     */   public Date getDateDebut() {
/*  89 */     return this.dateDebut;
/*     */   }
/*     */   
/*     */   public void setDateDebut(Date dateDebut) {
/*  93 */     this.dateDebut = dateDebut;
/*     */   }
/*     */   
/*     */   public Date getDateFin() {
/*  97 */     return this.dateFin;
/*     */   }
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/* 101 */     this.dateFin = dateFin;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<AnneeMois> getAnneeMoisList() {
/* 106 */     return this.anneeMoisList;
/*     */   }
/*     */   
/*     */   public void setAnneeMoisList(List<AnneeMois> anneeMoisList) {
/* 110 */     this.anneeMoisList = anneeMoisList;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 115 */     int hash = 0;
/* 116 */     hash += (this.idannee != null) ? this.idannee.hashCode() : 0;
/* 117 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 123 */     if (!(object instanceof Annee)) {
/* 124 */       return false;
/*     */     }
/* 126 */     Annee other = (Annee)object;
/* 127 */     if ((this.idannee == null && other.idannee != null) || (this.idannee != null && !this.idannee.equals(other.idannee))) {
/* 128 */       return false;
/*     */     }
/* 130 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 135 */     return "entities.Annee[ idannee=" + this.idannee + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Annee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */