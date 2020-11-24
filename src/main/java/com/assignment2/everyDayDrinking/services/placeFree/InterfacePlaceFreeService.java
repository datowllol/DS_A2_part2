package com.assignment2.everyDayDrinking.services.placeFree;

import com.assignment2.everyDayDrinking.model.Visitors;

import java.util.UUID;

public interface InterfacePlaceFreeService {
    Visitors visitorsLeavePub(UUID id);
}
