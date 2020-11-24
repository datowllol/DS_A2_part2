package com.assignment2.everyDayDrinking.services.saloonService;

import com.assignment2.everyDayDrinking.model.Saloon;
import com.assignment2.everyDayDrinking.model.Visitors;

import java.util.List;
import java.util.UUID;


public interface InterfaceSaloonService {

    Saloon addSaloon(Saloon saloon);

    List<Saloon> getAll();

    Saloon getById(UUID id);

    Saloon getByPlaceNum(Visitors visitorsDto);

    void deleteSaloonById(UUID Id);
}
