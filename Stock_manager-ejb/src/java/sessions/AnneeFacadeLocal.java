package sessions;

import entities.Annee;
import java.util.List;
import javax.ejb.Local;

@Local
public interface AnneeFacadeLocal {
  void create(Annee paramAnnee);
  
  void edit(Annee paramAnnee);
  
  void remove(Annee paramAnnee);
  
  Annee find(Object paramObject);
  
  List<Annee> findAll();
  
  List<Annee> findRange(int[] paramArrayOfint);
  
  int count();
  
  Integer nextVal();
  
  List<Annee> findByEtat(boolean paramBoolean) throws Exception;
  
  List<Annee> findByRange();
  
  Annee findByCode(String paramString);
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\AnneeFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */