package com.assignment2.everyDayDrinking.BeerOrder;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class BeerOrder {
    UUID soldBeerId;
    String beerType;
    int moneyGain;
    UUID visitorsId;
}
