package sessions;

import entities.CommandeClient;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CommandeClientFacadeLocal {
  void create(CommandeClient paramCommandeClient);
  
  void edit(CommandeClient paramCommandeClient);
  
  void remove(CommandeClient paramCommandeClient);
  
  CommandeClient find(Object paramObject);
  
  List<CommandeClient> findAll();
  
  List<CommandeClient> findRange(int[] paramArrayOfint);
  
  int count();
  
  Long nextLongVal();
  
  List<CommandeClient> findAllRange();
  
  List<CommandeClient> findAllDate(Date paramDate) throws Exception;
  
  List<CommandeClient> findByInterval(Date paramDate1, Date paramDate2) throws Exception;
  
  List<CommandeClient> findByLivre(boolean paramBoolean) throws Exception;
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\CommandeClientFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */