package com.carservice.thesis.dto;

import com.carservice.thesis.entity.Station;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class ManagedStationDTO {
    private Station station;
    private Integer managerId;
}
