package testeorder.com.pt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import testeorder.com.pt.model.StockMovement;

@Repository
public interface StockMovementRepository extends CrudRepository<StockMovement, Long>{

}
