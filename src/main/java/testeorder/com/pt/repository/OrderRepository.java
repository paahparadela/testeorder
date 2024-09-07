package testeorder.com.pt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import testeorder.com.pt.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
	
	Order findTopByOrderByIdDesc();

}
