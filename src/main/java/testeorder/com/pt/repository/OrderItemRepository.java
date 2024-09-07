package testeorder.com.pt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import testeorder.com.pt.model.OrderItem;
import testeorder.com.pt.model.OrderItemId;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, OrderItemId>{
	
	OrderItem findByIdAndComplete(OrderItemId id, boolean complete);
	
}
