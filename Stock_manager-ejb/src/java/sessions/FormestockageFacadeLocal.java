package sessions;

import entities.Formestockage;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FormestockageFacadeLocal {
  void create(Formestockage paramFormestockage);
  
  void edit(Formestockage paramFormestockage);
  
  void remove(Formestockage paramFormestockage);
  
  Formestockage find(Object paramObject);
  
  List<Formestockage> findAll();
  
  List<Formestockage> findRange(int[] paramArrayOfint);
  
  int count();
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\FormestockageFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */