package utils;

import entities.AnneeMois;
import entities.Journee;
import entities.Parametrage;
import entities.Utilisateur;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionMBean {

    public static HttpSession getSession() {
        /*  21 */ return (HttpSession) FacesContext.getCurrentInstance()
                /*  22 */.getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        /*  26 */ return (HttpServletRequest) FacesContext.getCurrentInstance()
                /*  27 */.getExternalContext().getRequest();
    }

    public static String getUserName() {
        /*  32 */ HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        /*  33 */ return session.getAttribute("login").toString();
    }

    public static String getUserId() {
        /*  37 */ HttpSession session = getSession();
        /*  38 */ if (session != null) {
            /*  39 */ return (String) session.getAttribute("utilisateurid");
        }
        /*  41 */ return null;
    }

    public static Utilisateur getUserAccount() {
        /*  46 */ HttpSession session = getSession();
        /*  47 */ if (session != null) {
            /*  48 */ return (Utilisateur) session.getAttribute("compte");
        }
        /*  50 */ return null;
    }

    public static Boolean getSession1() {
        /*  55 */ HttpSession session = getSession();
        /*  56 */ if (session != null) {
            /*  57 */ return (Boolean) session.getAttribute("session");
        }
        /*  59 */ return Boolean.valueOf(false);
    }

    public static Parametrage getParametrage() {
        /*  64 */ HttpSession session = getSession();
        /*  65 */ if (session != null) {
            /*  66 */ return (Parametrage) session.getAttribute("parametre");
        }
        /*  68 */ return null;
    }

    public static List<Long> getAccess() {
        /*  73 */ HttpSession session = getSession();
        /*  74 */ if (session != null) {
            /*  75 */ return (List<Long>) session.getAttribute("accesses");
        }
        /*  77 */ return null;
    }

    public static Date getDateOuverture() {
        /*  82 */ HttpSession session = getSession();
        /*  83 */ if (session != null) {
            /*  84 */ return (Date) session.getAttribute("date");
        }
        /*  86 */ return null;
    }

    public static AnneeMois getMois() {
        /*  91 */ HttpSession session = getSession();
        /*  92 */ if (session != null) {
            /*  93 */ return (AnneeMois) session.getAttribute("mois");
        }
        /*  95 */ return null;
    }

    public static Journee getDay() {
        /* 100 */ HttpSession session = getSession();
        /* 101 */ if (session != null) {
            /* 102 */ return (Journee) session.getAttribute("journee");
        }
        /* 104 */ return null;
    }
}
