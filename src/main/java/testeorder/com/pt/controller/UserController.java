package testeorder.com.pt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import testeorder.com.pt.model.User;
import testeorder.com.pt.repository.UserRepository;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		Iterable<User> users = userRepository.findAll();

		return new ResponseEntity<>(
			users.iterator(),
			HttpStatus.OK
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Optional<User> user = userRepository.findById(id);

		return user.isPresent()
			? new ResponseEntity<>(
				user.get(), 
				HttpStatus.OK
			) : new ResponseEntity<>(
				"{ \"errorMessage\": \"Error: user not found!\" }", 
				HttpStatus.NOT_FOUND
			);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody User user) {
		try {
			userRepository.save(user);
			return ResponseEntity.created(null).build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			userRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody User user) {
		try {
			Optional<User> oldUser = userRepository.findById(user.getId());
			if(oldUser.isPresent()){
				userRepository.save(user);
				return ResponseEntity.status(200).body(user);
	        }
			return new ResponseEntity<>(
					"{ \"errorMessage\": \"Error: user not found!\" }", 
					HttpStatus.NOT_FOUND
				);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
