package testeorder.com.pt.model;

import java.time.LocalDate;

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
public class StockMovement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private LocalDate creationDate;
	@OneToOne(mappedBy = "stockMovement")
	private Item item;
	private long quantity;

}
