package com.assignment2.everyDayDrinking.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public final class FreeTable {

    @Id
    private UUID freeTableId;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "tableId")
    private Saloon saloon;

    public FreeTable(){
        freeTableId = UUID.randomUUID();
    }
}