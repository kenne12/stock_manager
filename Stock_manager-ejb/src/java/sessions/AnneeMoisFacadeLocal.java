package sessions;

import entities.Annee;
import entities.AnneeMois;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface AnneeMoisFacadeLocal {
  void create(AnneeMois paramAnneeMois);
  
  void edit(AnneeMois paramAnneeMois);
  
  void remove(AnneeMois paramAnneeMois);
  
  AnneeMois find(Object paramObject);
  
  List<AnneeMois> findAll();
  
  List<AnneeMois> findRange(int[] paramArrayOfint);
  
  int count();
  
  Integer nextVal();
  
  List<AnneeMois> findByAnneeEtat(Annee paramAnnee, boolean paramBoolean) throws Exception;
  
  List<AnneeMois> findByAnnee(Annee paramAnnee) throws Exception;
  
  List<AnneeMois> findByEtat(Boolean paramBoolean) throws Exception;
  
  List<AnneeMois> findByRange() throws Exception;
  
  AnneeMois findByDate(Date paramDate) throws Exception;
}


/* Location:              C:\Users\USER\Desktop\jar\Stock_manager4694646969736841869\Stock_manager-ejb.jar!\sessions\AnneeMoisFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */