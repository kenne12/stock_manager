package sessions;

import entities.Utilisateur;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UtilisateurFacadeLocal {
  void create(Utilisateur paramUtilisateur);
  
  void edit(Utilisateur paramUtilisateur);
  
  void remove(Utilisateur paramUtilisateur);
  
  Utilisateur find(Object paramObject);
  
  List<Utilisateur> findAll();
  
  List<Utilisateur> findRange(int[] paramArrayOfint);
  
  int count();
  
  Integer nextVal();
  
  Utilisateur login(String paramString1, String paramString2) throws Exception;
  
  List<Utilisateur> findByActif(Boolean paramBoolean);
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\UtilisateurFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */