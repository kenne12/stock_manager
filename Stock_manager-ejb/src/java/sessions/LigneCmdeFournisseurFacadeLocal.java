package sessions;

import entities.LigneCmdeFournisseur;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface LigneCmdeFournisseurFacadeLocal {
  void create(LigneCmdeFournisseur paramLigneCmdeFournisseur);
  
  void edit(LigneCmdeFournisseur paramLigneCmdeFournisseur);
  
  void remove(LigneCmdeFournisseur paramLigneCmdeFournisseur);
  
  LigneCmdeFournisseur find(Object paramObject);
  
  List<LigneCmdeFournisseur> findAll();
  
  List<LigneCmdeFournisseur> findRange(int[] paramArrayOfint);
  
  int count();
  
  Long nextVal();
  
  List<LigneCmdeFournisseur> findByCommande(Long paramLong) throws Exception;
  
  List<LigneCmdeFournisseur> findByDate(Date paramDate) throws Exception;
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\LigneCmdeFournisseurFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */