package com.assignment2.everyDayDrinking.services.tableLeaving;

import com.assignment2.everyDayDrinking.model.FreeTable;
import com.assignment2.everyDayDrinking.model.OccupiedTable;

import java.util.List;
import java.util.UUID;
public interface InterfaceTableLeavingService  {
    FreeTable addTable(FreeTable table);
    List<FreeTable> getAll();
    FreeTable getById(UUID id);
    FreeTable setFree(FreeTable table);
    public void deleteById(UUID id);
}
