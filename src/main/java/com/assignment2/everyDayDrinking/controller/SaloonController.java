package com.assignment2.everyDayDrinking.controller;

import com.assignment2.everyDayDrinking.model.FreeTable;
import com.assignment2.everyDayDrinking.model.Saloon;
import com.assignment2.everyDayDrinking.model.SoldBeer;
import com.assignment2.everyDayDrinking.model.Visitors;
import com.assignment2.everyDayDrinking.services.beerPurchase.BeerPurchaseService;
import com.assignment2.everyDayDrinking.services.saloonService.SaloonService;
import com.assignment2.everyDayDrinking.services.tableLeaving.TableLeavingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/saloon")
@AllArgsConstructor
@NoArgsConstructor
public class SaloonController {
    @Autowired
    SaloonService saloonService;
    @Autowired
    TableLeavingService tableLeavingService;

    @PostMapping()
    public Saloon addSaloon(@RequestBody Saloon saloon) {
        return saloonService.addSaloon(saloon);
    }

    @PostMapping("saloon/getbynum")
    public Saloon getByNumber(@RequestBody Visitors visitorsDTO) {
        return saloonService.getByPlaceNum(visitorsDTO);
    }

    @PostMapping("saloon/setfree")
    public FreeTable setFreeTable(@RequestBody FreeTable freeTable) {
        return tableLeavingService.setFree(freeTable);
    }
    @GetMapping()
    public List<Saloon> getAll() {
        return saloonService.getAll();
    }


    @GetMapping("/{tableID}")
    public Saloon getById(@PathVariable(value = "tableID") UUID id) {
        return saloonService.getById(id);
    }

    @DeleteMapping("/{tableID}")
    public ResponseEntity<Void> deleteSaloonById(@PathVariable(value = "tableID") UUID id) {
        saloonService.deleteSaloonById(id);
        return ResponseEntity.noContent().build();
    }
}