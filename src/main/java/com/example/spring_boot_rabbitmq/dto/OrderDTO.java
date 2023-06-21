package com.example.spring_boot_rabbitmq.dto;

import com.example.spring_boot_rabbitmq.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Order order;
    private String orderStatus;

    private String message;
}
