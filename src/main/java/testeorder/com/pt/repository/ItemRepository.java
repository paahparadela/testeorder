package testeorder.com.pt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import testeorder.com.pt.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

}
