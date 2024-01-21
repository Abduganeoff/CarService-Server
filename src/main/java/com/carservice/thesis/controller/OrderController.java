package com.carservice.thesis.controller;


import com.carservice.thesis.dto.OrderRequestDto;
import com.carservice.thesis.dto.OrderResponseDto;
import com.carservice.thesis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto newOrder = orderService.createOrder(orderRequestDto);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Integer id) {
        OrderResponseDto order = orderService.getOrder(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(@PathVariable Integer id, @RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto updatedOrder = orderService.updateOrder(id, orderRequestDto);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
