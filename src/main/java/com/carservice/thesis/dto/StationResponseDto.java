package com.carservice.thesis.dto;

import com.carservice.thesis.entity.Employee;
import com.carservice.thesis.entity.ColorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StationResponseDto {

    private Integer id;
    private StationManagerResponse manager;
    private String stationName;
    private ColorType colorType;
    private List<Employee> employees;
}
