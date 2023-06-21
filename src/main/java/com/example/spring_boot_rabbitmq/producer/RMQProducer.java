package com.example.spring_boot_rabbitmq.producer;

import com.example.spring_boot_rabbitmq.config.RabbitMQConfig;
import com.example.spring_boot_rabbitmq.consumer.RMQConsumer;
import com.example.spring_boot_rabbitmq.dto.OrderDTO;
import com.example.spring_boot_rabbitmq.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class RMQProducer {
    private final RabbitTemplate rabbitTemplate;

    private final RMQConsumer rmqConsumer;

    @Autowired
    public RMQProducer(RabbitTemplate rabbitTemplate, RMQConsumer rmqConsumer) {
        this.rabbitTemplate = rabbitTemplate;
        this.rmqConsumer = rmqConsumer;
    }

    @PostMapping("/order")
    public OrderDTO orderplaced(@RequestBody Order order)
    {
        OrderDTO orderDTO= OrderDTO.builder()
                .order(order).orderStatus("Order Placed").message("Hi Your order as been placed").build();

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE,RabbitMQConfig.ROUTING_KEY,orderDTO);
        return orderDTO;
    }

    @GetMapping("/consumer")
    public List<OrderDTO> getdata(OrderDTO orderDTO)
    {
        List<OrderDTO> data=rmqConsumer.getConsumedata();
        return data;
    }
}
