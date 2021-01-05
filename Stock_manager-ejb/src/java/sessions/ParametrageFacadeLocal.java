package sessions;

import entities.Parametrage;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ParametrageFacadeLocal {
  void create(Parametrage paramParametrage);
  
  void edit(Parametrage paramParametrage);
  
  void remove(Parametrage paramParametrage);
  
  Parametrage find(Object paramObject);
  
  List<Parametrage> findAll();
  
  List<Parametrage> findRange(int[] paramArrayOfint);
  
  int count();
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\ParametrageFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */