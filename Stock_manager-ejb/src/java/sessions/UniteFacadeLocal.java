package sessions;

import entities.Unite;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UniteFacadeLocal {
  void create(Unite paramUnite);
  
  void edit(Unite paramUnite);
  
  void remove(Unite paramUnite);
  
  Unite find(Object paramObject);
  
  List<Unite> findAll();
  
  List<Unite> findRange(int[] paramArrayOfint);
  
  int count();
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\UniteFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */