package sessions;

import entities.CommandeFournisseur;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CommandeFournisseurFacadeLocal {
  void create(CommandeFournisseur paramCommandeFournisseur);
  
  void edit(CommandeFournisseur paramCommandeFournisseur);
  
  void remove(CommandeFournisseur paramCommandeFournisseur);
  
  CommandeFournisseur find(Object paramObject);
  
  List<CommandeFournisseur> findAll();
  
  List<CommandeFournisseur> findRange(int[] paramArrayOfint);
  
  int count();
  
  Long nextLongVal();
  
  List<CommandeFournisseur> findAllRange();
  
  List<CommandeFournisseur> findAllDate(Date paramDate) throws Exception;
  
  List<CommandeFournisseur> findByInterval(Date paramDate1, Date paramDate2) throws Exception;
  
  List<CommandeFournisseur> findByLivre(boolean paramBoolean) throws Exception;
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\CommandeFournisseurFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */