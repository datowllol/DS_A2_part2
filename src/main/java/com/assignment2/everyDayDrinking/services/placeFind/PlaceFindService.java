package com.assignment2.everyDayDrinking.services.placeFind;

import com.assignment2.everyDayDrinking.model.*;
import com.assignment2.everyDayDrinking.repository.VisitorsRepository;
import com.assignment2.everyDayDrinking.services.saloonService.SaloonService;
import com.assignment2.everyDayDrinking.services.tableLeaving.TableLeavingService;
import com.assignment2.everyDayDrinking.services.tableOccupation.TableOccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.UUID;

@Service
public class PlaceFindService implements InterfacePlaceFindService {
    @Autowired
    private VisitorsRepository visitorsRepository;
    @Autowired
    private TableOccupationService tableOccupationService;


    public Visitors addVisitors(Visitors visitors) {

        RestTemplate restTemplate = new RestTemplate();

        Saloon Response;

        OccupiedTable tempTable = new OccupiedTable();
        visitors.setOccupiedTableID(tempTable.getOccupiedTableId());
        HttpEntity<Visitors> visitorsDTO = new HttpEntity<>(visitors);
        Response = restTemplate.postForObject("https://pub-saloon:8084/saloon/getbynum", visitorsDTO, Saloon.class);
        if (Response!= null) {
            tempTable.setSaloonId(Response.getTableId());
            tempTable.setVisitorsId(visitors.getVisitorId());
            tableOccupationService.addTable(tempTable);
            visitors.setOccupiedTableID(tempTable.getOccupiedTableId());
        }
        Visitors visitorsSaved = visitorsRepository.save(visitors);
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
