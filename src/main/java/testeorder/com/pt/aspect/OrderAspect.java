package testeorder.com.pt.aspect;

import java.util.Optional;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import testeorder.com.pt.model.Item;
import testeorder.com.pt.model.Order;
import testeorder.com.pt.model.OrderItem;
import testeorder.com.pt.model.OrderItemId;
import testeorder.com.pt.repository.OrderItemRepository;
import testeorder.com.pt.repository.OrderRepository;
import testeorder.com.pt.service.EmailService;

@Aspect
@Component
public class OrderAspect {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
    EmailService emailService;
	
	@After("execution(* testeorder.com.pt.repository.OrderItemRepository.save(..))")
    public void afterSave(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        OrderItem orderItem = (OrderItem) args[0];
        Optional<Order> order = orderRepository.findById(orderItem.getId().getOrderId());
        Set<Item> items = order.get().getItems();
        boolean complete = true;
        for (int i = 1; i < items.size(); i++) {
        	OrderItemId orderItemId = new OrderItemId(order.get().getId(), items.iterator().next().getId());
        	OrderItem orderItemCurrent = orderItemRepository.findByIdAndComplete(orderItemId, false);
        	if (orderItemCurrent != null) {
        		complete = false;
        	}
        }
        if (complete) {
        	order.get().setStatus("complete");
        	orderRepository.save(order.get());
        	emailService.sendSimpleEmail(order.get().getUser().getEmail(), "Order complete", "Dear "+order.get().getUser().getName()+", "
        			+ "your order #"+order.get().getId()+" is already complete."
        					+ ""
        					+ "Kind regards,"
        					+ "Void Team");
        }
    }

}
