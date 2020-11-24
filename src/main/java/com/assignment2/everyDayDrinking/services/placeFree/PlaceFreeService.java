package com.assignment2.everyDayDrinking.services.placeFree;

import com.assignment2.everyDayDrinking.model.FreeTable;
import com.assignment2.everyDayDrinking.model.OccupiedTable;
import com.assignment2.everyDayDrinking.model.Visitors;
import com.assignment2.everyDayDrinking.repository.VisitorsRepository;
import com.assignment2.everyDayDrinking.services.placeFind.PlaceFindService;
import com.assignment2.everyDayDrinking.services.tableLeaving.TableLeavingService;
import com.assignment2.everyDayDrinking.services.tableOccupation.TableOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class PlaceFreeService implements InterfacePlaceFreeService {
    @Autowired
    private VisitorsRepository visitorsRepository;
    @Autowired
    private PlaceFindService placeFindService;
    @Autowired
    private TableOccupationService tableOccupationService;



    public Visitors visitorsLeavePub(UUID id) {


        FreeTable table = new FreeTable();
        table.setSaloonId(tableOccupationService.getById(placeFindService.getById(id).getOccupiedTableID()).getSaloonId());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<FreeTable> freeTableDto = new HttpEntity<>(table);
        FreeTable Response;
        Response = restTemplate.postForObject("https://pub-saloon:8084/saloon/setfree", freeTableDto, FreeTable.class);
        tableOccupationService.deleteById(placeFindService.getById(id).getOccupiedTableID());
        return placeFindService.getById(id);
    }
}
