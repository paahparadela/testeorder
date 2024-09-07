package testeorder.com.pt.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItemId implements Serializable {

	private static final long serialVersionUID = 4005072035056309548L;
	private Long orderId;
    private Long itemId;
    
	public OrderItemId() {
	}
    
	public OrderItemId(Long orderId, Long itemId) {
		this.orderId = orderId;
		this.itemId = itemId;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

}

