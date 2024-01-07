package com.carservice.thesis.repository;

import com.carservice.thesis.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Integer> {
}
