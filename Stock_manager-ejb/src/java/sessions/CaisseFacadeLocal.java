package sessions;

import entities.Caisse;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CaisseFacadeLocal {
  void create(Caisse paramCaisse);
  
  void edit(Caisse paramCaisse);
  
  void remove(Caisse paramCaisse);
  
  Caisse find(Object paramObject);
  
  List<Caisse> findAll();
  
  List<Caisse> findRange(int[] paramArrayOfint);
  
  int count();
  
  Integer nextVal();
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\CaisseFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */