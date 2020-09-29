package com.assignment2.everyDayDrinking.services.TableOccupation;

import com.assignment2.everyDayDrinking.model.OccupiedTable;

import java.util.List;
import java.util.UUID;

public interface InterfaceTableOccupationService {
    OccupiedTable addTable(OccupiedTable table);
    List<OccupiedTable> getAll();
    OccupiedTable getById(UUID id);
    void deleteById(UUID id);
}
