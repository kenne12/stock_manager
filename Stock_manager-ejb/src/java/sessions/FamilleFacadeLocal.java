package sessions;

import entities.Famille;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FamilleFacadeLocal {
  void create(Famille paramFamille);
  
  void edit(Famille paramFamille);
  
  void remove(Famille paramFamille);
  
  Famille find(Object paramObject);
  
  List<Famille> findAll();
  
  List<Famille> findRange(int[] paramArrayOfint);
  
  int count();
  
  Integer nextVal();
  
  List<Famille> findAllRange();
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\FamilleFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */