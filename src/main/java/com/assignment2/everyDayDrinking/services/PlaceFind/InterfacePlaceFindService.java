package com.assignment2.everyDayDrinking.services.PlaceFind;

import com.assignment2.everyDayDrinking.model.Visitors;


import java.util.List;
import java.util.UUID;

public interface InterfacePlaceFindService {
    Visitors addVisitors(Visitors visitors);

    List<Visitors> getAll();

    Visitors getById(UUID id);

    Visitors getByVisitorsNum(Integer visitorsNum);
}
