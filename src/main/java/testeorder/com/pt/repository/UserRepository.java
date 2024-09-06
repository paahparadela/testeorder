package testeorder.com.pt.repository;

import org.springframework.data.repository.CrudRepository;

import testeorder.com.pt.model.User;


public interface UserRepository extends CrudRepository<User, Long>{

}
