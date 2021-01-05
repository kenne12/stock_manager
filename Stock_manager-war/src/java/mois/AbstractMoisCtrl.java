package mois;

import entities.Annee;
import entities.AnneeMois;
import entities.Mois;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import sessions.AnneeFacadeLocal;
import sessions.AnneeMoisFacadeLocal;
import sessions.MoisFacadeLocal;
import sessions.MouchardFacadeLocal;
import utils.Routine;

public class AbstractMoisCtrl {
    /*  26 */ protected String fileName = "";

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    /*  30 */    protected Annee annee = new Annee();
    /*  31 */    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected MoisFacadeLocal moisFacadeLocal;
    /*  35 */    protected Mois mois = new Mois();
    /*  36 */    protected List<Mois> moises = new ArrayList<>();
    /*  37 */    protected List<Mois> selectedMois = new ArrayList<>();

    @EJB
    protected AnneeMoisFacadeLocal anneeMoisFacadeLocal;
    /*  41 */    protected AnneeMois anneeMois = new AnneeMois();
    /*  42 */    protected List<AnneeMois> anneeMoises = new ArrayList<>();

    @EJB
    protected MouchardFacadeLocal mouchardFacadeLocal;

    /*  47 */    protected Routine routine = new Routine();

    /*  49 */    protected String mode = "";

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public boolean isDetail() {
        /*  57 */ return this.detail;
    }

    public boolean isModifier() {
        /*  61 */ return this.modifier;
    }

    public boolean isSupprimer() {
        /*  65 */ return this.supprimer;
    }

    public boolean isImprimer() {
        /*  69 */ return this.imprimer;
    }

    public List<Mois> getMoises() {
        /*  73 */ return this.moises;
    }

    public void setMoises(List<Mois> moises) {
        /*  77 */ this.moises = moises;
    }

    public List<Mois> getSelectedMois() {
        /*  81 */ return this.selectedMois;
    }

    public void setSelectedMois(List<Mois> selectedMois) {
        /*  85 */ this.selectedMois = selectedMois;
    }

    public AnneeMois getAnneeMois() {
        /*  89 */ return this.anneeMois;
    }

    public void setAnneeMois(AnneeMois anneeMois) {
        /*  93 */ this.detail = this.modifier = this.supprimer = (anneeMois == null);
        /*  94 */ this.anneeMois = anneeMois;
    }

    public List<AnneeMois> getAnneeMoises() {
        try {
            /*  99 */ this.anneeMoises = this.anneeMoisFacadeLocal.findByRange();
            /* 100 */        } catch (Exception e) {
            /* 101 */ e.printStackTrace();
        }
        /* 103 */ return this.anneeMoises;
    }

    public void setAnneeMoises(List<AnneeMois> anneeMoises) {
        /* 107 */ this.anneeMoises = anneeMoises;
    }

    public Annee getAnnee() {
        /* 111 */ return this.annee;
    }

    public void setAnnee(Annee annee) {
        /* 115 */ this.annee = annee;
    }

    public List<Annee> getAnnees() {
        /* 119 */ this.annees = this.anneeFacadeLocal.findByRange();
        /* 120 */ return this.annees;
    }

    public void setAnnees(List<Annee> annees) {
        /* 124 */ this.annees = annees;
    }

    public Routine getRoutine() {
        /* 128 */ return this.routine;
    }
}
