package com.carservice.thesis.dto;

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
public class StationRequestDto {
    private Integer id;
    private Integer managerId;
    private String stationName;
    private ColorType colorType;
    private List<Integer> employeeIds;

}
