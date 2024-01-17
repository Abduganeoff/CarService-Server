package com.carservice.thesis.dto;

import com.carservice.thesis.entity.Station;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ManagedStationDto {
    private Station station;
    private Integer managerId;
}
