package com.example.spring_boot_rabbitmq.consumer;

import com.example.spring_boot_rabbitmq.config.RabbitMQConfig;
import com.example.spring_boot_rabbitmq.dto.OrderDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Component
public class RMQConsumer {

    private List<OrderDTO> consumedata=new ArrayList<>();

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void Consume(OrderDTO orderDTO)
    {
        System.out.println("consumed message from queue"+ orderDTO);
        consumedata.add(orderDTO);
    }

    public List<OrderDTO> getConsumedata()
    {
        return consumedata;
    }

}
