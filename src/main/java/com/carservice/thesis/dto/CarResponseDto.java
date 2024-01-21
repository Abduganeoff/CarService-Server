package com.carservice.thesis.dto;


import com.carservice.thesis.entity.ColorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarResponseDto {
    private Integer id;
    private String model;
    private String make;
    private String licenceNumber;
    private ColorType color;
}
