package com.assignment2.everyDayDrinking.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
public final class OccupiedTable {

    @Id
    private UUID occupiedTableId;


    @Column
    private UUID visitorsId;

    @Column
    private UUID saloonId;

    public OccupiedTable() {
        occupiedTableId = UUID.randomUUID();
    }
}