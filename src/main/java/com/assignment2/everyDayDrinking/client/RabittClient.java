package com.assignment2.everyDayDrinking.client;

import com.assignment2.everyDayDrinking.BeerOrder.BeerOrder;
import com.assignment2.everyDayDrinking.model.Saloon;
import com.assignment2.everyDayDrinking.model.SoldBeer;
import com.assignment2.everyDayDrinking.model.Visitors;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RabittClient {
    private static final String URL = "http://127.0.0.1:64637/";
    private static final HttpHeaders headers = new HttpHeaders();
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    public void testClient() {
        headers.setContentType(MediaType.APPLICATION_JSON);

        Saloon table1 = new Saloon("Big table", 8);
        addEntity("saloon/mq", table1);

        Saloon table2 = new Saloon("Small table under window", 5);
        addEntity("saloon/mq", table2);

        Visitors visitorsGroup1 = new Visitors(6);
        addEntity("saloon/getbynum", visitorsGroup1);

        Visitors visitorsGroup2 = new Visitors(4);

        Visitors visitorsGroup3 = new Visitors(3);

        SoldBeer order1 = new SoldBeer(50, "Unfiltered dark");


        SoldBeer order2 = new SoldBeer(100, "Vodka");

        SoldBeer order3 = new SoldBeer(20, "Teteriv");

        Visitors visitorsGroup4 = new Visitors(3);

        SoldBeer order4 = new SoldBeer(200, "Moonshine");
    }


    private static void addEntity(String path, Object entity) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String entityJson = objectMapper.writeValueAsString(entity);
            HttpEntity<String> entityJsonHttp = new HttpEntity<>(entityJson, headers);
            ResponseEntity<Void> response = restTemplate.postForEntity(URL +
                    path, entityJsonHttp, Void.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    private static void beerPurchase(Visitors visitors, SoldBeer soldBeer) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String Request = objectMapper.writeValueAsString(new
                    BeerOrder(soldBeer.getSoldBeerId(), soldBeer.getBeerType(), soldBeer.getMoneyGain(), visitors.getVisitorId()));
            HttpEntity<String> httpRequest = new HttpEntity<>(Request, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(URL +
                            "mq",
                    httpRequest, String.class);
            System.out.println(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
