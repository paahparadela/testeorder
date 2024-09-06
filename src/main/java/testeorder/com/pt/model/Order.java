package testeorder.com.pt.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

import testeorder.com.pt.model.Item;
import testeorder.com.pt.model.User;

@Data
@Entity
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private LocalDate creationDate;
	@OneToMany(mappedBy = "order")
    private Set<Item> items;
	private long quantity;
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;

}
