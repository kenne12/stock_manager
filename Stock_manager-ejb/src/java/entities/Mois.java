/*     */ package entities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.NamedQuery;
/*     */ import javax.persistence.OneToMany;
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
/*     */ @Entity
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "Mois.findAll", query = "SELECT m FROM Mois m"), @NamedQuery(name = "Mois.findByIdmois", query = "SELECT m FROM Mois m WHERE m.idmois = :idmois"), @NamedQuery(name = "Mois.findByNom", query = "SELECT m FROM Mois m WHERE m.nom = :nom"), @NamedQuery(name = "Mois.findByNumero", query = "SELECT m FROM Mois m WHERE m.numero = :numero")})
/*     */ public class Mois
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   private Integer idmois;
/*     */   @Size(max = 2147483647)
/*     */   private String nom;
/*     */   private Integer numero;
/*     */   @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
/*     */   private List<AnneeMois> anneeMoisList;
/*     */   
/*     */   public Mois() {}
/*     */   
/*     */   public Mois(Integer idmois) {
/*  49 */     this.idmois = idmois;
/*     */   }
/*     */   
/*     */   public Integer getIdmois() {
/*  53 */     return this.idmois;
/*     */   }
/*     */   
/*     */   public void setIdmois(Integer idmois) {
/*  57 */     this.idmois = idmois;
/*     */   }
/*     */   
/*     */   public String getNom() {
/*  61 */     return this.nom;
/*     */   }
/*     */   
/*     */   public void setNom(String nom) {
/*  65 */     this.nom = nom;
/*     */   }
/*     */   
/*     */   public Integer getNumero() {
/*  69 */     return this.numero;
/*     */   }
/*     */   
/*     */   public void setNumero(Integer numero) {
/*  73 */     this.numero = numero;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<AnneeMois> getAnneeMoisList() {
/*  78 */     return this.anneeMoisList;
/*     */   }
/*     */   
/*     */   public void setAnneeMoisList(List<AnneeMois> anneeMoisList) {
/*  82 */     this.anneeMoisList = anneeMoisList;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  87 */     int hash = 0;
/*  88 */     hash += (this.idmois != null) ? this.idmois.hashCode() : 0;
/*  89 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/*  95 */     if (!(object instanceof Mois)) {
/*  96 */       return false;
/*     */     }
/*  98 */     Mois other = (Mois)object;
/*  99 */     if ((this.idmois == null && other.idmois != null) || (this.idmois != null && !this.idmois.equals(other.idmois))) {
/* 100 */       return false;
/*     */     }
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 107 */     return "entities.Mois[ idmois=" + this.idmois + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\Mois.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */