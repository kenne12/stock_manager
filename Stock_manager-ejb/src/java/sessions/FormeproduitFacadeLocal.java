package sessions;

import entities.Formeproduit;
import java.util.List;
import javax.ejb.Local;

@Local
public interface FormeproduitFacadeLocal {
  void create(Formeproduit paramFormeproduit);
  
  void edit(Formeproduit paramFormeproduit);
  
  void remove(Formeproduit paramFormeproduit);
  
  Formeproduit find(Object paramObject);
  
  List<Formeproduit> findAll();
  
  List<Formeproduit> findRange(int[] paramArrayOfint);
  
  int count();
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\FormeproduitFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */