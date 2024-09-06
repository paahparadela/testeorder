package testeorder.com.pt.repository;

import org.springframework.data.repository.CrudRepository;

import testeorder.com.pt.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
