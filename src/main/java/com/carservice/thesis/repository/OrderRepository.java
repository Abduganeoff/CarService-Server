package com.carservice.thesis.repository;

import com.carservice.thesis.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface OrderRepository extends JpaRepository<CustomerOrder, Integer> {
    boolean existsByLicenceNumber(String licenceNumber);

    @Query("SELECT o FROM CustomerOrder o JOIN FETCH o.client c JOIN FETCH o.station s WHERE o.id = :orderId")
    Optional<CustomerOrder> findByIdWithDetails(@Param("orderId") Integer orderId);
}
