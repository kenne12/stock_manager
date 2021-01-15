package sessions;

import entities.Mois;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MoisFacadeLocal {

    void create(Mois paramMois);

    void edit(Mois paramMois);

    void remove(Mois paramMois);

    Mois find(Object paramObject);

    List<Mois> findAll();

    List<Mois> findRange(int[] paramArrayOfint);

    int count();

    List<Mois> findAllRange();
}
