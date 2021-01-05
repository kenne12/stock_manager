package sessions;

import entities.AnneeMois;
import entities.Inventaire;
import java.util.List;
import javax.ejb.Local;

@Local
public interface InventaireFacadeLocal {
  void create(Inventaire paramInventaire);
  
  void edit(Inventaire paramInventaire);
  
  void remove(Inventaire paramInventaire);
  
  Inventaire find(Object paramObject);
  
  List<Inventaire> findAll();
  
  List<Inventaire> findRange(int[] paramArrayOfint);
  
  int count();
  
  Long nextVal();
  
  Long nextVal(AnneeMois paramAnneeMois);
  
  List<Inventaire> findAllRange() throws Exception;
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\InventaireFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */