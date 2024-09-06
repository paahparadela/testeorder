package testeorder.com.pt.repository;

import org.springframework.data.repository.CrudRepository;

import testeorder.com.pt.model.StockMovement;


public interface StockMovementRepository extends CrudRepository<StockMovement, Long>{

}
