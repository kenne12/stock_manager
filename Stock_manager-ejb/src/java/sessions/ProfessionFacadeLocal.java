package sessions;

import entities.Profession;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ProfessionFacadeLocal {
  void create(Profession paramProfession);
  
  void edit(Profession paramProfession);
  
  void remove(Profession paramProfession);
  
  Profession find(Object paramObject);
  
  List<Profession> findAll();
  
  List<Profession> findRange(int[] paramArrayOfint);
  
  int count();
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\ProfessionFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */