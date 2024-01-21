package com.carservice.thesis.dto;

import com.carservice.thesis.entity.Client;
import com.carservice.thesis.entity.OrderState;
import com.carservice.thesis.entity.Station;
import com.carservice.thesis.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderResponseDto {
    private Integer id;
    private String registerNumber;
    private Date savedDate;
    private String serviceType;
    private Double cost;
    private String licenceNumber;
    private OrderState state;
    private OrderManagerResponseDto manager;
    private OrderClientResponseDto client;
    private OrderStationResponseDto station;
}
