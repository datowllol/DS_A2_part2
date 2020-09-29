package com.assignment2.everyDayDrinking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.UUID;

@EnableAutoConfiguration
@Entity
@Data
@AllArgsConstructor
public final class SoldBeer {
    @Id
    private UUID soldBeerId;

    @Column
    private int moneyGain;

    @Column
    private String beerType;

    private UUID visitorsId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "visitorId")
    private Visitors visitors;

    public SoldBeer() {
        soldBeerId = UUID.randomUUID();
    }
}
