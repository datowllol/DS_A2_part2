package com.assignment2.everyDayDrinking.services.beerPurchase;

import com.assignment2.everyDayDrinking.model.SoldBeer;
import com.assignment2.everyDayDrinking.model.Visitors;

import java.util.List;
import java.util.UUID;

public interface InterfaceBeerPurchaseService {
    SoldBeer addSale(SoldBeer soldBeer);
    List<SoldBeer> getAll();
    public SoldBeer getById(UUID id);
    public void deleteById(UUID id);
}
