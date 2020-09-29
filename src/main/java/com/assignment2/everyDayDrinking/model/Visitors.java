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

    @OneToOne(mappedBy = "visitors")
    OccupiedTable occupiedTable;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "visitors", fetch = FetchType.LAZY)
    private List<SoldBeer> soldBeer;

    public Visitors() {
        visitorId = UUID.randomUUID();
    }
}