package com.assignment2.everyDayDrinking.services.PlaceFind;

import com.assignment2.everyDayDrinking.model.*;
import com.assignment2.everyDayDrinking.repository.VisitorsRepository;
import com.assignment2.everyDayDrinking.services.SaloonService.SaloonService;
import com.assignment2.everyDayDrinking.services.TableLeaving.TableLeavingService;
import com.assignment2.everyDayDrinking.services.TableOccupation.TableOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.UUID;

@Service
public class PlaceFindService {
    @Autowired
    private VisitorsRepository visitorsRepository;
    @Autowired
    private SaloonService saloonService;
    @Autowired
    private TableOccupationService tableOccupationService;
    @Autowired
    private TableLeavingService tableLeavingService;

    public Visitors addVisitors(Visitors visitors) {
        Visitors visitorsSaved = visitorsRepository.save(visitors);
        List<Saloon> tempSaloon = saloonService.getByPlaceNum(visitorsSaved.getVisitorsNum());
        List<FreeTable> tables = tableLeavingService.getAll();
        OccupiedTable tempTable = new OccupiedTable();
        for (FreeTable t :
                tables)
            for (Saloon s :
                    tempSaloon) {
                if (t.getSaloon().getTableId() == s.getTableId()) {
                    FreeTable table = t;
                    tempTable.setSaloon(table.getSaloon());
                    tempTable.setVisitors(visitorsSaved);
                    tableOccupationService.addTable(tempTable);
                    tableLeavingService.deleteById(table.getFreeTableId());
                    return visitorsSaved;
                }
            }
        return visitorsSaved;
    }

    public List<Visitors> getAll() {
        return visitorsRepository.findAll();
    }

    public Visitors getById(UUID id) {
        return visitorsRepository.getOne(id);
    }

    public Visitors getByVisitorsNum(Integer visitorsNum) {
        return visitorsRepository.findByVisitorsNum(visitorsNum);
    }

}
