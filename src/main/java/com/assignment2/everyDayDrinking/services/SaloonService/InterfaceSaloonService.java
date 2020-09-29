package com.assignment2.everyDayDrinking.services.SaloonService;

import com.assignment2.everyDayDrinking.model.Saloon;

import java.util.List;
import java.util.UUID;

public interface InterfaceSaloonService {

    Saloon addSaloon(Saloon saloon);

    List<Saloon> getAll();

    Saloon getById(UUID id);

    List<Saloon> getByPlaceNum(Integer placeNum);

    void deleteSaloonById(UUID id);
}
