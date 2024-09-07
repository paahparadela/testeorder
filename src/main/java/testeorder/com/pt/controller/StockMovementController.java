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

import testeorder.com.pt.model.StockMovement;
import testeorder.com.pt.model.User;
import testeorder.com.pt.repository.StockMovementRepository;
import testeorder.com.pt.repository.UserRepository;

@RestController
@RequestMapping("stockmovement")
public class StockMovementController {
	
	@Autowired
	StockMovementRepository stockMovementRepository;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		Iterable<StockMovement> stockMovements = stockMovementRepository.findAll();

		return new ResponseEntity<>(
			stockMovements.iterator(),
			HttpStatus.OK
		);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Optional<StockMovement> stockMovement = stockMovementRepository.findById(id);

		return stockMovement.isPresent()
			? new ResponseEntity<>(
				stockMovement.get(), 
				HttpStatus.OK
			) : new ResponseEntity<>(
				"{ \"errorMessage\": \"Error: stock movement not found!\" }", 
				HttpStatus.NOT_FOUND
			);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody StockMovement stockMovement) {
		try {
			stockMovementRepository.save(stockMovement);
			return ResponseEntity.created(null).build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			stockMovementRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody StockMovement stockMovement) {
		try {
			Optional<StockMovement> oldStockMovement = stockMovementRepository.findById(stockMovement.getId());
			if(oldStockMovement.isPresent()){
				stockMovementRepository.save(stockMovement);
				return ResponseEntity.status(200).body(stockMovement);
	        }
			return new ResponseEntity<>(
					"{ \"errorMessage\": \"Error: stock movement not found!\" }", 
					HttpStatus.NOT_FOUND
				);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

}
