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
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.NamedQueries;
/*     */ import javax.persistence.NamedQuery;
/*     */ import javax.persistence.OneToMany;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.validation.constraints.NotNull;
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
/*     */ @Entity
/*     */ @Table(name = "annee_mois")
/*     */ @XmlRootElement
/*     */ @NamedQueries({@NamedQuery(name = "AnneeMois.findAll", query = "SELECT a FROM AnneeMois a"), @NamedQuery(name = "AnneeMois.findByIdAnneeMois", query = "SELECT a FROM AnneeMois a WHERE a.idAnneeMois = :idAnneeMois"), @NamedQuery(name = "AnneeMois.findByDateDebut", query = "SELECT a FROM AnneeMois a WHERE a.dateDebut = :dateDebut"), @NamedQuery(name = "AnneeMois.findByDateFin", query = "SELECT a FROM AnneeMois a WHERE a.dateFin = :dateFin"), @NamedQuery(name = "AnneeMois.findByEtat", query = "SELECT a FROM AnneeMois a WHERE a.etat = :etat")})
/*     */ public class AnneeMois
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @NotNull
/*     */   @Column(name = "id_annee_mois")
/*     */   private Integer idAnneeMois;
/*     */   @Column(name = "date_debut")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateDebut;
/*     */   @Column(name = "date_fin")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateFin;
/*     */   private Boolean etat;
/*     */   @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
/*     */   private List<CommandeFournisseur> commandeFournisseurList;
/*     */   @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
/*     */   private List<Journee> journeeList;
/*     */   @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
/*     */   private List<CommandeClient> commandeClientList;
/*     */   @JoinColumn(name = "idannee", referencedColumnName = "idannee")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Annee idannee;
/*     */   @JoinColumn(name = "idmois", referencedColumnName = "idmois")
/*     */   @ManyToOne(fetch = FetchType.LAZY)
/*     */   private Mois idmois;
/*     */   @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
/*     */   private List<FactureBailleur> factureBailleurList;
/*     */   @OneToMany(mappedBy = "idAnneeMois", fetch = FetchType.LAZY)
/*     */   private List<Facture> factureList;
/*     */   @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
/*     */   private List<Inventaire> inventaireList;
/*     */   @OneToMany(mappedBy = "idAnneeMois", fetch = FetchType.LAZY)
/*     */   private List<Stock> stockList;
/*     */   @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
/*     */   private List<Depense> depenseList;
/*     */   @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
/*     */   private List<MvtStock> mvtStockList;
/*     */   @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
/*     */   private List<Salaire> salaireList;
/*     */   @OneToMany(mappedBy = "idmois", fetch = FetchType.LAZY)
/*     */   private List<Recette> recetteList;
/*     */   
/*     */   public AnneeMois() {}
/*     */   
/*     */   public AnneeMois(Integer idAnneeMois) {
/*  88 */     this.idAnneeMois = idAnneeMois;
/*     */   }
/*     */   
/*     */   public Integer getIdAnneeMois() {
/*  92 */     return this.idAnneeMois;
/*     */   }
/*     */   
/*     */   public void setIdAnneeMois(Integer idAnneeMois) {
/*  96 */     this.idAnneeMois = idAnneeMois;
/*     */   }
/*     */   
/*     */   public Date getDateDebut() {
/* 100 */     return this.dateDebut;
/*     */   }
/*     */   
/*     */   public void setDateDebut(Date dateDebut) {
/* 104 */     this.dateDebut = dateDebut;
/*     */   }
/*     */   
/*     */   public Date getDateFin() {
/* 108 */     return this.dateFin;
/*     */   }
/*     */   
/*     */   public void setDateFin(Date dateFin) {
/* 112 */     this.dateFin = dateFin;
/*     */   }
/*     */   
/*     */   public Boolean getEtat() {
/* 116 */     return this.etat;
/*     */   }
/*     */   
/*     */   public void setEtat(Boolean etat) {
/* 120 */     this.etat = etat;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<CommandeFournisseur> getCommandeFournisseurList() {
/* 125 */     return this.commandeFournisseurList;
/*     */   }
/*     */   
/*     */   public void setCommandeFournisseurList(List<CommandeFournisseur> commandeFournisseurList) {
/* 129 */     this.commandeFournisseurList = commandeFournisseurList;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Journee> getJourneeList() {
/* 134 */     return this.journeeList;
/*     */   }
/*     */   
/*     */   public void setJourneeList(List<Journee> journeeList) {
/* 138 */     this.journeeList = journeeList;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<CommandeClient> getCommandeClientList() {
/* 143 */     return this.commandeClientList;
/*     */   }
/*     */   
/*     */   public void setCommandeClientList(List<CommandeClient> commandeClientList) {
/* 147 */     this.commandeClientList = commandeClientList;
/*     */   }
/*     */   
/*     */   public Annee getIdannee() {
/* 151 */     return this.idannee;
/*     */   }
/*     */   
/*     */   public void setIdannee(Annee idannee) {
/* 155 */     this.idannee = idannee;
/*     */   }
/*     */   
/*     */   public Mois getIdmois() {
/* 159 */     return this.idmois;
/*     */   }
/*     */   
/*     */   public void setIdmois(Mois idmois) {
/* 163 */     this.idmois = idmois;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<FactureBailleur> getFactureBailleurList() {
/* 168 */     return this.factureBailleurList;
/*     */   }
/*     */   
/*     */   public void setFactureBailleurList(List<FactureBailleur> factureBailleurList) {
/* 172 */     this.factureBailleurList = factureBailleurList;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Facture> getFactureList() {
/* 177 */     return this.factureList;
/*     */   }
/*     */   
/*     */   public void setFactureList(List<Facture> factureList) {
/* 181 */     this.factureList = factureList;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Inventaire> getInventaireList() {
/* 186 */     return this.inventaireList;
/*     */   }
/*     */   
/*     */   public void setInventaireList(List<Inventaire> inventaireList) {
/* 190 */     this.inventaireList = inventaireList;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Stock> getStockList() {
/* 195 */     return this.stockList;
/*     */   }
/*     */   
/*     */   public void setStockList(List<Stock> stockList) {
/* 199 */     this.stockList = stockList;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Depense> getDepenseList() {
/* 204 */     return this.depenseList;
/*     */   }
/*     */   
/*     */   public void setDepenseList(List<Depense> depenseList) {
/* 208 */     this.depenseList = depenseList;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<MvtStock> getMvtStockList() {
/* 213 */     return this.mvtStockList;
/*     */   }
/*     */   
/*     */   public void setMvtStockList(List<MvtStock> mvtStockList) {
/* 217 */     this.mvtStockList = mvtStockList;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Salaire> getSalaireList() {
/* 222 */     return this.salaireList;
/*     */   }
/*     */   
/*     */   public void setSalaireList(List<Salaire> salaireList) {
/* 226 */     this.salaireList = salaireList;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public List<Recette> getRecetteList() {
/* 231 */     return this.recetteList;
/*     */   }
/*     */   
/*     */   public void setRecetteList(List<Recette> recetteList) {
/* 235 */     this.recetteList = recetteList;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 240 */     int hash = 0;
/* 241 */     hash += (this.idAnneeMois != null) ? this.idAnneeMois.hashCode() : 0;
/* 242 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 248 */     if (!(object instanceof AnneeMois)) {
/* 249 */       return false;
/*     */     }
/* 251 */     AnneeMois other = (AnneeMois)object;
/* 252 */     if ((this.idAnneeMois == null && other.idAnneeMois != null) || (this.idAnneeMois != null && !this.idAnneeMois.equals(other.idAnneeMois))) {
/* 253 */       return false;
/*     */     }
/* 255 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 260 */     return "entities.AnneeMois[ idAnneeMois=" + this.idAnneeMois + " ]";
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\entities\AnneeMois.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */