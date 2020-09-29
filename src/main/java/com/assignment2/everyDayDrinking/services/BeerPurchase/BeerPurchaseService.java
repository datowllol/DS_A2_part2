package com.assignment2.everyDayDrinking.services.BeerPurchase;

import com.assignment2.everyDayDrinking.model.SoldBeer;
import com.assignment2.everyDayDrinking.model.Visitors;
import com.assignment2.everyDayDrinking.repository.SoldBeerRepository;
import com.assignment2.everyDayDrinking.services.PlaceFind.PlaceFindService;
import com.assignment2.everyDayDrinking.services.TableLeaving.InterfaceTableLeavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BeerPurchaseService implements InterfaceBeerPurchaseService {
    @Autowired
    private SoldBeerRepository soldBeerRepository;
    @Autowired
    private PlaceFindService placeFindService;

    public SoldBeer addSale(SoldBeer soldBeer) {

        Visitors visitors = placeFindService.getById(soldBeer.getVisitorsId());
        soldBeer.setVisitors(visitors);
        SoldBeer soldBeerSaved = soldBeerRepository.save(soldBeer);
        return soldBeerSaved;
    }

    public List<SoldBeer> getAll() {
        return soldBeerRepository.findAll();
    }

    public SoldBeer getById(UUID id) {
        return soldBeerRepository.getOne(id);
    }

    public void deleteById(UUID id) {
        soldBeerRepository.deleteById(id);
    }
}
