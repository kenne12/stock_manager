package sessions;

import entities.Privilege;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PrivilegeFacadeLocal {
  void create(Privilege paramPrivilege);
  
  void edit(Privilege paramPrivilege);
  
  void remove(Privilege paramPrivilege);
  
  Privilege find(Object paramObject);
  
  List<Privilege> findAll();
  
  List<Privilege> findRange(int[] paramArrayOfint);
  
  int count();
  
  List<Privilege> findAll1();
  
  Long nextVal();
  
  List<Privilege> findByUser(int paramInt);
  
  Privilege findByUser(int paramInt1, int paramInt2);
  
  void delete(int paramInt1, int paramInt2);
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\PrivilegeFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */