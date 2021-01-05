package controllers.rapportjournalier;

import entities.Commande;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import sessions.CommandeFacadeLocal;
import sessions.PrivilegeFacadeLocal;
import utils.Routine;

public class AbstractRapportJournalierController {
    /* 23 */ protected Routine routine = new Routine();

    @EJB
    protected CommandeFacadeLocal commandeFacadeLocal;
    /* 27 */    protected List<Commande> commandes = new ArrayList<>();

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;

    /* 32 */    Date date = new Date();

    /* 34 */    protected String fileName = "";

    protected boolean showPrintButton = true;
    protected boolean showReportPrintDialog = false;

    public Date getDate() {
        /* 40 */ return this.date;
    }

    public void setDate(Date date) {
        /* 44 */ this.date = date;
    }

    public boolean isShowPrintButton() {
        /* 48 */ return this.showPrintButton;
    }

    public void setShowPrintButton(boolean showPrintButton) {
        /* 52 */ this.showPrintButton = showPrintButton;
    }

    public String getFileName() {
        /* 56 */ return this.fileName;
    }

    public void setFileName(String fileName) {
        /* 60 */ this.fileName = fileName;
    }

    public boolean isShowReportPrintDialog() {
        /* 64 */ return this.showReportPrintDialog;
    }

    public void setShowReportPrintDialog(boolean showReportPrintDialog) {
        /* 68 */ this.showReportPrintDialog = showReportPrintDialog;
    }

    public List<Commande> getCommandes() {
        /* 72 */ return this.commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        /* 76 */ this.commandes = commandes;
    }

    public Routine getRoutine() {
        /* 80 */ return this.routine;
    }
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classes\controllers\rapportjournalier\AbstractRapportJournalierController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
