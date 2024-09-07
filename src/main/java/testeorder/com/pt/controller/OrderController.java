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

import testeorder.com.pt.model.Order;
import testeorder.com.pt.model.User;
import testeorder.com.pt.repository.OrderRepository;

@RestController
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	OrderRepository orderRepository;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		Iterable<Order> orders = orderRepository.findAll();

		return new ResponseEntity<>(
			orders.iterator(),
			HttpStatus.OK
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Optional<Order> order = orderRepository.findById(id);

		return order.isPresent()
			? new ResponseEntity<>(
				order.get(), 
				HttpStatus.OK
			) : new ResponseEntity<>(
				"{ \"errorMessage\": \"Error: order not found!\" }", 
				HttpStatus.NOT_FOUND
			);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody Order order) {
		try {
			orderRepository.save(order);
			return ResponseEntity.created(null).build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			orderRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody Order order) {
		try {
			Optional<Order> oldOrder = orderRepository.findById(order.getId());
			if(oldOrder.isPresent()){
				orderRepository.save(order);
				return ResponseEntity.status(200).body(order);
	        }
			return new ResponseEntity<>(
					"{ \"errorMessage\": \"Error: order not found!\" }", 
					HttpStatus.NOT_FOUND
				);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

}
