package com.assignment2.everyDayDrinking.repository;

import com.assignment2.everyDayDrinking.model.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VisitorsRepository extends JpaRepository<Visitors, UUID> {
    Visitors findByVisitorsNum(Integer visitorsNum);
}
