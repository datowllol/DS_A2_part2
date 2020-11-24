package com.assignment2.everyDayDrinking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.*;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public final class Saloon {

    @Id
    private UUID tableId;

    @Column
    private int placeNum;

    @Column
    private String uniqueName;

    @Column
    private UUID occupiedTableId;

    @Column
    private UUID freeTableId;

    public Saloon() {
        tableId = UUID.randomUUID();
    }

    public Saloon(String uniqueName, int placeNum) {
        tableId = UUID.randomUUID();
        this.placeNum=placeNum;
        this.uniqueName=uniqueName;
    }
}
