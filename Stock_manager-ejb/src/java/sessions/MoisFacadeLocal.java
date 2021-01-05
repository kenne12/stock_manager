package sessions;

import entities.Mois;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MoisFacadeLocal {
  void create(Mois paramMois);
  
  void edit(Mois paramMois);
  
  void remove(Mois paramMois);
  
  Mois find(Object paramObject);
  
  List<Mois> findAll();
  
  List<Mois> findRange(int[] paramArrayOfint);
  
  int count();
  
  List<Mois> findAllRange();
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\MoisFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */