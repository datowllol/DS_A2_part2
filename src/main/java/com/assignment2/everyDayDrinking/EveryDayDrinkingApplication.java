package com.assignment2.everyDayDrinking;

import com.assignment2.everyDayDrinking.BeerOrder.BeerOrder;
import com.assignment2.everyDayDrinking.model.Saloon;
import com.assignment2.everyDayDrinking.model.SoldBeer;
import com.assignment2.everyDayDrinking.model.Visitors;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EveryDayDrinkingApplication {


    private static final String URL = "http://localhost:0005";
    private static final HttpHeaders headers = new HttpHeaders();
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpEntity<Object> headersEntity = new HttpEntity<>(headers);

    public static void main(String[] args) {

        headers.setContentType(MediaType.APPLICATION_JSON);

        Saloon table1 = new Saloon("Big table", 8);
        addEntity("/saloon", table1);

        Saloon table2 = new Saloon("Small table under window", 5);
        addEntity("/saloon", table2);


        Visitors visitorsGroup1 = new Visitors(6);
        addEntity("/visitors", visitorsGroup1);

        Visitors visitorsGroup2 = new Visitors(4);
        addEntity("/visitors", visitorsGroup2);

        Visitors visitorsGroup3 = new Visitors(3);
        addEntity("/visitors", visitorsGroup3);

        SoldBeer order1 = new SoldBeer(50, "Unfiltered dark");
        beerPurchase(visitorsGroup1, order1);

        SoldBeer order2 = new SoldBeer(100, "Vodka");
        beerPurchase(visitorsGroup1, order2);

        SoldBeer order3 = new SoldBeer(20, "Teteriv");
        beerPurchase(visitorsGroup2, order3);

        visitorsLeave("/visitors", visitorsGroup2);

        Visitors visitorsGroup4 = new Visitors(3);
        addEntity("/visitors", visitorsGroup4);

        SoldBeer order4 = new SoldBeer(200, "Moonshine");
        beerPurchase(visitorsGroup4, order4);

        visitorsLeave("/visitors", visitorsGroup1);
        visitorsLeave("/visitors", visitorsGroup4);

        getDayResult();
    }

    private static void getDayResult() {
        ResponseEntity<String> response = restTemplate.getForEntity(URL + "/beerSale", String.class);
            System.out.println(response);
    }

    private static void beerPurchase(Visitors visitors, SoldBeer soldBeer) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String Request = objectMapper.writeValueAsString(new
                    BeerOrder(soldBeer.getBeerType(), soldBeer.getMoneyGain(), visitors.getVisitorId()));
            HttpEntity<String> httpRequest = new HttpEntity<>(Request, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(URL +
                            "/beerSale/",
                    httpRequest, String.class);
            System.out.println(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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

    private static void visitorsLeave(String path, Visitors visitors) {
        restTemplate.delete(URL + path + "/" + visitors.getVisitorId(), Void.class);
    }
}
