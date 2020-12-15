package com.assignment2.everyDayDrinking.client;

import com.assignment2.everyDayDrinking.BeerOrder.BeerOrder;
import com.assignment2.everyDayDrinking.model.Saloon;
import com.assignment2.everyDayDrinking.model.SoldBeer;
import com.assignment2.everyDayDrinking.model.Visitors;

import com.beer_purchase.Beer_Purchase_Service.BeerPurchaseServiceGrpc;
import com.beer_purchase.Beer_Purchase_Service.SoldBeerRequest;
import com.placeManage.Place_Manage_Service.PlaceManageServiceGrpc;
import com.placeManage.Place_Manage_Service.VisitorsRequest;
import com.saloon.Saloon.SaloonResponse;
import com.saloon.Saloon.SaloonServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.UUID;

public class GrpcClient {
    private final String url = "127.0.0.1";
    private final ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 64638).usePlaintext().build();
    BeerPurchaseServiceGrpc.BeerPurchaseServiceBlockingStub soldBeerStub = BeerPurchaseServiceGrpc.newBlockingStub(channel);
    PlaceManageServiceGrpc.PlaceManageServiceBlockingStub placeManageStub = PlaceManageServiceGrpc.newBlockingStub(channel);
    SaloonServiceGrpc.SaloonServiceBlockingStub saloonStub = SaloonServiceGrpc.newBlockingStub(channel);

    public void test() {
        Saloon table1 = new Saloon("Big table", 8);
        addSaloon(table1);

        Saloon table2 = new Saloon("Small table under window", 5);
        addSaloon(table2);

        Visitors visitorsGroup1 = new Visitors(6);
       addVisitors(visitorsGroup1);

        Visitors visitorsGroup2 = new Visitors(4);
        addVisitors(visitorsGroup2);

        Visitors visitorsGroup3 = new Visitors(3);
        addVisitors(visitorsGroup3);

        SoldBeer order1 = new SoldBeer(50, "Unfiltered dark");
        beerPurchase(visitorsGroup1, order1);

        SoldBeer order2 = new SoldBeer(100, "Vodka");
        beerPurchase(visitorsGroup2, order2);

        SoldBeer order3 = new SoldBeer(20, "Teteriv");
        beerPurchase(visitorsGroup3, order3);





    }

    private void addSaloon(Saloon saloon) {
        if(saloon.getOccupiedTableId()==null)
            saloon.setOccupiedTableId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        if(saloon.getFreeTableId()==null)
            saloon.setFreeTableId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        if(saloon.getTableId()==null)
            saloon.setTableId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        SaloonResponse saloonResponse = SaloonResponse.newBuilder().
                setTableId(saloon.getTableId().toString()).
                setPlaceNum(saloon.getPlaceNum()).
                setUniqueName(saloon.getUniqueName()).
                setOccupiedTableId(saloon.getOccupiedTableId().toString()).
                setFreeTableId(saloon.getFreeTableId().toString()).
                build();
        saloonStub.add(saloonResponse);
    }

    private void addVisitors(Visitors visitors) {
        if(visitors.getVisitorId()==null)
            visitors.setVisitorId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        if(visitors.getOccupiedTableID()==null)
            visitors.setOccupiedTableID(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        VisitorsRequest visitorsRequest = VisitorsRequest.newBuilder().
                setVisitorId(visitors.getVisitorId().toString()).
                setVisitorsNum(visitors.getVisitorsNum()).
                setOccupiedTableID(visitors.getOccupiedTableID().toString()).
                build();
    }

    private void beerPurchase(Visitors visitors, SoldBeer soldBeer) {
        SoldBeer beerOrder = new SoldBeer(soldBeer.getSoldBeerId(), soldBeer.getMoneyGain(), soldBeer.getBeerType(), visitors.getVisitorId());
        SoldBeerRequest soldBeerRequest = SoldBeerRequest.newBuilder().
                setSoldBeerId(beerOrder.getSoldBeerId().toString()).
                setMoneyGain(beerOrder.getMoneyGain()).
                setBeerType(beerOrder.getBeerType()).
                setVisitorsId(beerOrder.getVisitorsId().toString()).
                build();
    }
}
