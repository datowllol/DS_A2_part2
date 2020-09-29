package com.assignment2.everyDayDrinking.services.PlaceFree;

import com.assignment2.everyDayDrinking.model.FreeTable;
import com.assignment2.everyDayDrinking.model.OccupiedTable;
import com.assignment2.everyDayDrinking.model.Visitors;
import com.assignment2.everyDayDrinking.repository.VisitorsRepository;
import com.assignment2.everyDayDrinking.services.PlaceFind.PlaceFindService;
import com.assignment2.everyDayDrinking.services.TableLeaving.TableLeavingService;
import com.assignment2.everyDayDrinking.services.TableOccupation.TableOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlaceFreeService implements InterfacePlaceFreeService {
    @Autowired
    private VisitorsRepository visitorsRepository;
    @Autowired
    private PlaceFindService placeFindService;
    @Autowired
    private TableOccupationService tableOccupationService;
    @Autowired
    private TableLeavingService tableFreeService;


    public Visitors visitorsLeavePub(UUID id) {


        FreeTable table = new FreeTable();
        table.setSaloon(placeFindService.getById(id).getOccupiedTable().getSaloon());

        tableFreeService.addTable(table);
        tableOccupationService.deleteById(placeFindService.getById(id).getOccupiedTable().getOccupiedTableId());
        return placeFindService.getById(id);
    }
}
