package com.assignment2.everyDayDrinking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Data
@Entity

@AllArgsConstructor
public final class Visitors {

    @Id
    private UUID visitorId;

    @Column
    private int visitorsNum;
    private UUID occupiedTableID;


    public Visitors() {
        visitorId = UUID.randomUUID();
    }

    public Visitors(int visitorsNum) {
        visitorId = UUID.randomUUID();
        this.visitorsNum = visitorsNum;
    }
}