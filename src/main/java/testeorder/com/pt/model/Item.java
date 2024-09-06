package testeorder.com.pt.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	@ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
	@OneToOne
    @JoinColumn(name = "stock_movement_id")
	private StockMovement stockMovement;
	
}
