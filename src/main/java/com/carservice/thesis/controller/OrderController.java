package com.carservice.thesis.controller;


import com.carservice.thesis.dto.*;
import com.carservice.thesis.entity.OrderState;
import com.carservice.thesis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/clients/count")
    public ResponseEntity<Map<String, Long>> getClientsCount(@RequestBody ClientCountRequestDto clientCountRequestDto) {
        Map<String, Long> count = orderService.getClientsCount(clientCountRequestDto.getTimePeriod());
        return ResponseEntity.ok(count);
    }


    @PostMapping("/cost")
    public ResponseEntity<Double> calculateCost(@RequestBody TimePeriodRequest request) {
        double cost = orderService.calculateCostForPeriod(request.getTimePeriod());
        return ResponseEntity.ok(cost);
    }


    @GetMapping("/state/{state}")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByState(@PathVariable("state") String state) {
        OrderState orderState = OrderState.valueOf(state.toUpperCase());
        List<OrderResponseDto> orders = orderService.getOrdersByState(orderState);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/search")
    public ResponseEntity<List<OrderResponseDto>> searchOrdersByClientNameOrSurname(@RequestParam String name) {
        List<OrderResponseDto> orders = orderService.searchOrdersByClientNameOrSurname(name);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/stations/count")
    public ResponseEntity<Map<String, Object>> getStationsCountAndGroup() {
        Map<String, Object> result = orderService.getStationsCountAndPercentage();
        return ResponseEntity.ok(result);
    }

}
