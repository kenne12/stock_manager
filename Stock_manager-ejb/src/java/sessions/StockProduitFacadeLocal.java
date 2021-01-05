package sessions;

import entities.Stock;
import entities.StockProduit;
import java.util.List;
import javax.ejb.Local;

@Local
public interface StockProduitFacadeLocal {

    void create(StockProduit paramStockProduit);

    void edit(StockProduit paramStockProduit);

    void remove(StockProduit paramStockProduit);

    StockProduit find(Object paramObject);

    List<StockProduit> findAll();

    List<StockProduit> findRange(int[] paramArrayOfint);

    int count();

    Long nextVal();

    List<StockProduit> findByStock(Stock paramStock);

    List<StockProduit> findAllRange();

    List<StockProduit> findByLot(Long paramLong) throws Exception;

    List<StockProduit> findByMois(int paramInt) throws Exception;
}
