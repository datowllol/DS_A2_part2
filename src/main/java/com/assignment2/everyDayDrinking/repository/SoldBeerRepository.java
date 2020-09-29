package com.assignment2.everyDayDrinking.repository;

import com.assignment2.everyDayDrinking.model.SoldBeer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SoldBeerRepository extends JpaRepository<SoldBeer, UUID> {
}
