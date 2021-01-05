package sessions;

import entities.Personnel;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PersonnelFacadeLocal {
  void create(Personnel paramPersonnel);
  
  void edit(Personnel paramPersonnel);
  
  void remove(Personnel paramPersonnel);
  
  Personnel find(Object paramObject);
  
  List<Personnel> findAll();
  
  List<Personnel> findRange(int[] paramArrayOfint);
  
  int count();
  
  Integer nextVal();
  
  List<Personnel> findByEtat(boolean paramBoolean) throws Exception;
  
  List<Personnel> findAllRange();
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\PersonnelFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */