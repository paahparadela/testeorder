package testeorder.com.pt.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public StockMovement getStockMovement() {
		return stockMovement;
	}
	public void setStockMovement(StockMovement stockMovement) {
		this.stockMovement = stockMovement;
	}
	
}
