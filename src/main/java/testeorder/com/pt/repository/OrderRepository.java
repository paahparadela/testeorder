package testeorder.com.pt.repository;

import org.springframework.data.repository.CrudRepository;

import testeorder.com.pt.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
