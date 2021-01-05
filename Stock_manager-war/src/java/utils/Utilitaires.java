package utils;
/*     */
/*     */ import entities.Lot;
/*     */ import entities.Mouchard;
/*     */ import entities.Utilisateur;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.faces.application.FacesMessage;
/*     */ import javax.faces.context.FacesContext;
/*     */ import javax.servlet.ServletContext;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.Days;
/*     */ import org.joda.time.ReadableInstant;
/*     */ import org.primefaces.event.FileUploadEvent;
/*     */ import org.primefaces.model.UploadedFile;
/*     */ import sessions.MouchardFacadeLocal;
/*     */ import utils.SessionMBean;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class Utilitaires /*     */ {
    /*  38 */ private static final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    /*  39 */    public static final String path = servletContext.getRealPath("");
    /*     */    public static final String repertoireParDefaultNotesTrim = "rapport/notes/trimestriel";
    /*  41 */    public static final String chemin = servletContext.getContextPath();
    /*     */
    /*     */ public static void saveOperation(MouchardFacadeLocal mouchardFacadeLocal, String action, Utilisateur utilisateur) {
        /*     */ try {
            /*  45 */ Mouchard traceur = new Mouchard();
            /*  46 */ traceur.setIdmouchard(mouchardFacadeLocal.nextVal());
            /*  47 */ traceur.setAction(action);
            /*  48 */ traceur.setIdutilisateur(utilisateur);
            /*  49 */ traceur.setDate(new Date());
            /*  50 */ traceur.setHeure(new Date());
            /*  51 */ mouchardFacadeLocal.create(traceur);
            /*  52 */        } catch (Exception e) {
            /*  53 */ e.printStackTrace();
            /*     */        }
        /*     */    }
    /*     */
    /*     */ public static String getExtension(String nomFichier) {
        /*  58 */ int taille = nomFichier.length();
        /*  59 */ String extension = "";
        /*  60 */ for (int i = 0; i < taille; i++) {
            /*  61 */ if (nomFichier.charAt(i) == '.') {
                /*  62 */ extension = "";
                /*     */            } else {
                /*     */
                /*  65 */ extension = extension + nomFichier.charAt(i);
                /*     */            }
            /*  67 */        }
        return extension;
        /*     */    }
    /*     */
    /*     */ public static boolean estExtensionImage(String extension) {
        /*  71 */ if (extension == null || extension.equals("")) {
            /*  72 */ return false;
            /*     */        }
        /*  74 */ String ext = extension.toUpperCase();
        /*  75 */ if (ext.equals("JPG")) {
            /*  76 */ return true;
            /*     */        }
        /*  78 */ if (ext.equals("JPEG")) {
            /*  79 */ return true;
            /*     */        }
        /*  81 */ if (ext.equals("GIF")) {
            /*  82 */ return true;
            /*     */        }
        /*  84 */ if (ext.equals("PNG")) {
            /*  85 */ return true;
            /*     */        }
        /*  87 */ if (ext.equals("BMP")) {
            /*  88 */ return true;
            /*     */        }
        /*  90 */ return false;
        /*     */    }
    /*     */
    /*     */ public static boolean estFichierImage(String nom) {
        /*  94 */ String extension = getExtension(nom);
        /*  95 */ if (extension == null || extension.equals("")) {
            /*  96 */ return false;
            /*     */        }
        /*  98 */ String ext = extension.toUpperCase();
        /*  99 */ if (ext.equals("JPG")) {
            /* 100 */ return true;
            /*     */        }
        /* 102 */ if (ext.equals("JPEG")) {
            /* 103 */ return true;
            /*     */        }
        /* 105 */ if (ext.equals("GIF")) {
            /* 106 */ return true;
            /*     */        }
        /* 108 */ if (ext.equals("PNG")) {
            /* 109 */ return true;
            /*     */        }
        /* 111 */ if (ext.equals("BMP")) {
            /* 112 */ return true;
            /*     */        }
        /* 114 */ return false;
        /*     */    }
    /*     */
    /*     */
    /*     */ public static boolean handleFileUpload(FileUploadEvent event, String absoluteSavePath) {
        /*     */ try {
            /* 120 */ OutputStream saveFile = new FileOutputStream(new File(absoluteSavePath));
            /* 121 */ InputStream in = event.getFile().getInputstream();
            /* 122 */ byte[] buff = new byte[8];
            /*     */ int n;
            /* 124 */ while ((n = in.read(buff)) >= 0) {
                /* 125 */ saveFile.write(buff);
                /* 126 */ buff = new byte[8];
                /*     */            }
            /* 128 */        } catch (IOException ex) {
            /* 129 */ FacesMessage message = new FacesMessage("Error", "Error While uploading " + event.getFile().getFileName());
            /* 130 */ FacesContext.getCurrentInstance().addMessage(null, message);
            /* 131 */ Logger.getLogger(utils.Utilitaires.class.getName()).log(Level.SEVERE, (String) null, ex);
            /* 132 */ return false;
            /*     */        }
        /* 134 */ return true;
        /*     */    }
    /*     */
    /*     */
    /*     */
    /*     */ public static boolean handleFileUpload(UploadedFile file, String absoluteSavePath) {
        /*     */ try {
            /* 141 */ OutputStream saveFile = new FileOutputStream(new File(absoluteSavePath));
            /* 142 */ InputStream in = file.getInputstream();
            /* 143 */ byte[] buff = new byte[8];
            /*     */ int n;
            /* 145 */ while ((n = in.read(buff)) >= 0) {
                /* 146 */ saveFile.write(buff);
                /* 147 */ buff = new byte[8];
                /*     */            }
            /* 149 */        } catch (IOException ex) {
            /* 150 */ FacesMessage message = new FacesMessage("Error", "Error While uploading " + file.getFileName());
            /* 151 */ FacesContext.getCurrentInstance().addMessage(null, message);
            /* 152 */ Logger.getLogger(utils.Utilitaires.class.getName()).log(Level.SEVERE, (String) null, ex);
            /* 153 */ return false;
            /*     */        }
        /* 155 */ return true;
        /*     */    }
    /*     */
    /*     */ public static boolean CopierFichier(File Source, File Destination) {
        /* 159 */ boolean resultat = false;
        /*     */
        /* 161 */ FileInputStream filesource = null;
        /* 162 */ FileOutputStream fileDestination = null;
        /*     */ try {
            /* 164 */ filesource = new FileInputStream(Source);
            /* 165 */ fileDestination = new FileOutputStream(Destination);
            /* 166 */ byte[] buffer = new byte[1000];
            /*     */ int nblecture;
            /* 168 */ while ((nblecture = filesource.read(buffer)) != -1) {
                /* 169 */ fileDestination.write(buffer, 0, nblecture);
                /* 170 */ buffer = new byte[8];
                /*     */            }
            /* 172 */ resultat = true;
            /* 173 */        } catch (FileNotFoundException nf) {
            /* 174 */ nf.printStackTrace();
            /* 175 */        } catch (IOException io) {
            /* 176 */ io.printStackTrace();
            /*     */        } finally {
            /*     */ try {
                /* 179 */ filesource.close();
                /* 180 */            } catch (Exception e) {
                /* 181 */ e.printStackTrace();
                /*     */            }
            /*     */ try {
                /* 184 */ fileDestination.close();
                /* 185 */            } catch (Exception e) {
                /* 186 */ e.printStackTrace();
                /*     */            }
            /*     */        }
        /* 189 */ return resultat;
        /*     */    }
    /*     */
    /*     */ public static Double arrondiNDecimales(double x, int n) {
        /* 193 */ double pow = Math.pow(10.0D, n);
        /* 194 */ return Double.valueOf(Math.floor(x * pow) / pow);
        /*     */    }
    /*     */
    /*     */ public static String formatPrenomMaj(String prenom) {
        /* 198 */ if (prenom.isEmpty()) {
            /* 199 */ return " ";
            /*     */        }
        /* 201 */ char prenLettre = '0';
        /*     */
        /*     */
        /*     */
        /* 205 */ prenLettre = prenom.charAt(0);
        /*     */
        /* 207 */ String leReste = prenom.substring(1, prenom.length());
        /*     */
        /* 209 */ String lettre1 = String.valueOf(prenLettre);
        /*     */
        /* 211 */ leReste = leReste.toLowerCase();
        /*     */
        /* 213 */ return lettre1.toUpperCase() + leReste;
        /*     */    }
    /*     */
    /*     */ public static boolean isAccess(Long menu) {
        /* 217 */ if (SessionMBean.getAccess().isEmpty()) {
            /* 218 */ return false;
            /*     */        }
        /* 220 */ if (SessionMBean.getAccess().contains(Long.valueOf(1L))) {
            /* 221 */ return true;
            /*     */        }
        /* 223 */ if (SessionMBean.getAccess().contains(menu)) {
            /* 224 */ return true;
            /*     */        }
        /* 226 */ return false;
        /*     */    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */ public static boolean isDayClosed() {
        /* 233 */ return SessionMBean.getDay().getFermetture().booleanValue();
        /*     */    }
    /*     */
    /*     */
    /*     */ public static void permuteDate(Date date1, Date date2) {
    }
    /*     */
    /*     */
    /*     */ public static boolean checkBenefice(double prix_achat, double prix_vente) {
        /* 241 */ if (prix_vente >= prix_achat) {
            /* 242 */ return true;
            /*     */        }
        /* 244 */ return false;
        /*     */    }
    /*     */
    /*     */
    /*     */ public static String genererCodeFacture(String code, Long nextPayement) {
        /* 249 */ if (nextPayement.longValue() < 10L) {
            /* 250 */ code = code + "-000000" + nextPayement.toString();
            /* 251 */        } else if (nextPayement.longValue() >= 10L && nextPayement.longValue() < 100L) {
            /* 252 */ code = code + "-00000" + nextPayement.toString();
            /* 253 */        } else if (nextPayement.longValue() >= 100L && nextPayement.longValue() < 1000L) {
            /* 254 */ code = code + "-0000" + nextPayement.toString();
            /* 255 */        } else if (nextPayement.longValue() >= 1000L && nextPayement.longValue() < 10000L) {
            /* 256 */ code = code + "-000" + nextPayement.toString();
            /* 257 */        } else if (nextPayement.longValue() >= 10000L && nextPayement.longValue() < 100000L) {
            /* 258 */ code = code + "-00" + nextPayement.toString();
            /* 259 */        } else if (nextPayement.longValue() >= 100000L && nextPayement.longValue() < 1000000L) {
            /* 260 */ code = code + "-0" + nextPayement.toString();
            /*     */        } else {
            /* 262 */ code = code + "-" + nextPayement.toString();
            /*     */        }
        /* 264 */ return code;
        /*     */    }
    /*     */
    /*     */ public static String genererCodeStock(String code, Long nextPayement) {
        /* 268 */ if (nextPayement.longValue() < 10L) {
            /* 269 */ code = code + "-000" + nextPayement.toString();
            /* 270 */        } else if (nextPayement.longValue() >= 10L && nextPayement.longValue() < 100L) {
            /* 271 */ code = code + "-00" + nextPayement.toString();
            /* 272 */        } else if (nextPayement.longValue() >= 100L && nextPayement.longValue() < 1000L) {
            /* 273 */ code = code + "-0" + nextPayement.toString();
            /*     */        } else {
            /* 275 */ code = code + "-" + nextPayement.toString();
            /*     */        }
        /* 277 */ return code;
        /*     */    }
    /*     */
    /*     */ public static String genererCodeInventaire(String code, Long nextPayement) {
        /* 281 */ if (nextPayement.longValue() < 10L) {
            /* 282 */ code = code + "-00" + nextPayement.toString();
            /* 283 */        } else if (nextPayement.longValue() >= 10L && nextPayement.longValue() < 100L) {
            /* 284 */ code = code + "-0" + nextPayement.toString();
            /*     */        } else {
            /* 286 */ code = code + "-" + nextPayement.toString();
            /*     */        }
        /* 288 */ return code;
        /*     */    }
    /*     */
    /*     */ public static Integer duration(Date date1, Date date2) {
        /* 292 */ DateTime dateDebut = new DateTime("" + (date1.getYear() + 1900) + "-" + (date1.getMonth() + 1) + "-" + date1.getDate() + "");
        /* 293 */ DateTime dateFin = new DateTime("" + (date2.getYear() + 1900) + "-" + (date2.getMonth() + 1) + "-" + date2.getDate() + "");
        /*     */
        /* 295 */ Integer nbjr = Integer.valueOf(Days.daysBetween((ReadableInstant) dateDebut, (ReadableInstant) dateFin).getDays());
        /* 296 */ return nbjr;
        /*     */    }
    /*     */
    /*     */ public static List<Lot> filterNotPeremptLot(List<Lot> lots) {
        /* 300 */ List<Lot> resultat = new ArrayList<>();
        /*     */ try {
            /* 302 */ for (Lot l : lots) {
                /*     */ try {
                    /* 304 */ if (l.getDatePeremption() != null) {
                        /* 305 */ if ((new Date()).before(l.getDatePeremption())) /* 306 */ {
                            resultat.add(l);
                        }
                        /*     */ continue;
                        /*     */                    }
                    /* 309 */ resultat.add(l);
                    /*     */                } /* 311 */ catch (Exception e) {
                    /* 312 */ resultat.add(l);
                    /*     */                }
                /*     */            }
            /* 315 */        } catch (Exception e) {
            /* 316 */ e.printStackTrace();
            /*     */        }
        /* 318 */ return resultat;
        /*     */    }
    /*     */ }


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-war.war!\WEB-INF\classe\\utils\Utilitaires.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
