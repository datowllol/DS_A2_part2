package com.assignment2.everyDayDrinking;

import com.assignment2.everyDayDrinking.BeerOrder.BeerOrder;
import com.assignment2.everyDayDrinking.client.GrpcClient;
import com.assignment2.everyDayDrinking.client.RabittClient;
import com.assignment2.everyDayDrinking.client.RestClient;
import com.assignment2.everyDayDrinking.model.FreeTable;
import com.assignment2.everyDayDrinking.model.Saloon;
import com.assignment2.everyDayDrinking.model.SoldBeer;
import com.assignment2.everyDayDrinking.model.Visitors;
import com.beer_purchase.Beer_Purchase_Service.BeerPurchaseServiceGrpc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.placeManage.Place_Manage_Service.PlaceManageServiceGrpc;
import com.saloon.Saloon.SaloonServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@SpringBootApplication
public class EveryDayDrinkingApplication {
    private final String url = "127.0.0.1";
    private final ManagedChannel channel = ManagedChannelBuilder.forAddress(url, 65264).usePlaintext().build();
    BeerPurchaseServiceGrpc.BeerPurchaseServiceBlockingStub soldBeerStub = BeerPurchaseServiceGrpc.newBlockingStub(channel);
    PlaceManageServiceGrpc.PlaceManageServiceBlockingStub placeManageStub = PlaceManageServiceGrpc.newBlockingStub(channel);
    SaloonServiceGrpc.SaloonServiceBlockingStub saloonStub = SaloonServiceGrpc.newBlockingStub(channel);

    public static void main(String[] args) {
           RabittClient rabbitTest = new RabittClient();
           rabbitTest.testClient();
            GrpcClient grpcClient = new GrpcClient();
            grpcClient.test();
    }

}
