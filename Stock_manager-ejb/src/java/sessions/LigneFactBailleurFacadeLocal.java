package sessions;

import entities.LigneFactBailleur;
import java.util.List;
import javax.ejb.Local;

@Local
public interface LigneFactBailleurFacadeLocal {
  void create(LigneFactBailleur paramLigneFactBailleur);
  
  void edit(LigneFactBailleur paramLigneFactBailleur);
  
  void remove(LigneFactBailleur paramLigneFactBailleur);
  
  LigneFactBailleur find(Object paramObject);
  
  List<LigneFactBailleur> findAll();
  
  List<LigneFactBailleur> findRange(int[] paramArrayOfint);
  
  int count();
  
  Long nextVal();
  
  List<LigneFactBailleur> findByFacture(Long paramLong) throws Exception;
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\LigneFactBailleurFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */