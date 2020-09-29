package com.assignment2.everyDayDrinking.services.TableLeaving;

import com.assignment2.everyDayDrinking.model.FreeTable;
import com.assignment2.everyDayDrinking.repository.FreeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TableLeavingService implements InterfaceTableLeavingService {
    @Autowired
    FreeTableRepository tableRepository;

    public FreeTable addTable(FreeTable table) {
        FreeTable savedTable = tableRepository.save(table);
        return savedTable;
    }

    public List<FreeTable> getAll() {
        return tableRepository.findAll();
    }

    public FreeTable getById(UUID id) {
        return tableRepository.getOne(id);
    }

    public void deleteById(UUID id) {
        tableRepository.deleteById(id);
    }
}
