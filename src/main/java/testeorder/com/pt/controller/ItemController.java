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

import testeorder.com.pt.model.Item;
import testeorder.com.pt.model.User;
import testeorder.com.pt.repository.ItemRepository;

@RestController
@RequestMapping("item")
public class ItemController {
	
	@Autowired
	ItemRepository itemRepository;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		Iterable<Item> items = itemRepository.findAll();

		return new ResponseEntity<>(
			items.iterator(),
			HttpStatus.OK
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Optional<Item> item = itemRepository.findById(id);

		return item.isPresent()
			? new ResponseEntity<>(
				item.get(), 
				HttpStatus.OK
			) : new ResponseEntity<>(
				"{ \"errorMessage\": \"Error: item not found!\" }", 
				HttpStatus.NOT_FOUND
			);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody Item item) {
		try {
			itemRepository.save(item);
			return ResponseEntity.created(null).build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			itemRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody Item item) {
		try {
			Optional<Item> oldItem = itemRepository.findById(item.getId());
			if(oldItem.isPresent()){
				itemRepository.save(item);
				return ResponseEntity.status(200).body(item);
	        }
			return new ResponseEntity<>(
					"{ \"errorMessage\": \"Error: item not found!\" }", 
					HttpStatus.NOT_FOUND
				);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

}
