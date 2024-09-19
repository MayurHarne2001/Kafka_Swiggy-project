package com.example.OrderService.Controller;

import com.example.OrderService.Kafka.OrderProducer;
import com.example.base_domain1.dto.Order;
import com.example.base_domain1.dto.OrderEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/place")
    public String placeOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        if (order.getRegion().equalsIgnoreCase("North")) {
            orderEvent.setMessage("Chapati-Bhaji is ordered for you");
        } else if (order.getRegion().equalsIgnoreCase("South")) {
            orderEvent.setMessage("Biryani is ordered for you");
        } else {
            orderEvent.setMessage("Default order notification");
        }

        orderEvent.setStatus("ORDER_PLACED");
        orderEvent.setOrder(order);

        orderProducer.sendMessage(orderEvent);
        return "Order placed successfully!";
    }
}
