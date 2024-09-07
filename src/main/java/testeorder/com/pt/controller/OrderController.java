package testeorder.com.pt.controller;

import java.util.Optional;
import java.util.Set;

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

import testeorder.com.pt.model.Item;
import testeorder.com.pt.model.Order;
import testeorder.com.pt.model.OrderItem;
import testeorder.com.pt.model.StockMovement;
import testeorder.com.pt.repository.ItemRepository;
import testeorder.com.pt.repository.OrderItemRepository;
import testeorder.com.pt.repository.OrderRepository;
import testeorder.com.pt.repository.StockMovementRepository;

@RestController
@RequestMapping("order")
public class OrderController {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	StockMovementRepository stockMovementRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
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
			Set<Item> items = order.getItems();
			Optional<Order> oldOrder = Optional.ofNullable(orderRepository.findTopByOrderByIdDesc());
			if (oldOrder.isPresent()) {
			    order.setId(oldOrder.get().getId() + 1);
			} else {
			    order.setId((long) 1);
			}
			order.setStatus("complete");
			for (int i = 0; i < items.size(); i++) {
				Optional<Item> itemOptional = itemRepository.findById(items.iterator().next().getId());
				Optional<StockMovement> stockMovementOptional = stockMovementRepository.findById(itemOptional.get().getStockMovement().getId());
				if (stockMovementOptional.get().getQuantity() == 0 || order.getQuantity() > stockMovementOptional.get().getQuantity()) {
					order.setStatus("incomplete");
					OrderItem orderItem = new OrderItem();
					orderItem.setOrder(order);
					orderItem.setItem(itemOptional.get());
					orderItem.setQuantity(order.getQuantity());
					orderItem.setComplete(false);
					order.getOrderItems().add(orderItem);
					orderItemRepository.save(orderItem);
				} else {
					stockMovementOptional.get().setQuantity(stockMovementOptional.get().getQuantity()-order.getQuantity());
					stockMovementRepository.save(stockMovementOptional.get());
				}
			}
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
