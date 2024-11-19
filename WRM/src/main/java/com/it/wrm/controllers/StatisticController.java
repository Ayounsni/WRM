package com.it.wrm.controllers;

import com.it.wrm.models.dtos.Pagination.PageDTO;
import com.it.wrm.models.dtos.Visit.CreateVisitDTO;
import com.it.wrm.models.dtos.Visit.ResponseVisitDTO;
import com.it.wrm.models.dtos.Visit.UpdateVisitDTO;
import com.it.wrm.services.implementation.VisitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/statistic")
public class StatisticController {
    @Autowired
    private VisitService visitService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getAverageWaitingTimeForWaitingList(@PathVariable("id") Long id) {
        double averageWaitingTime = visitService.calculateAverageWaitingTimeForWaitingList(id);
        String result = String.format("Le temps moyen d'attente de la liste %d est de %.2f minutes.", id, averageWaitingTime / 60.0 );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> getAverageWaitingTime() {
        double averageWaitingTime = visitService.calculateAverageWaitingTimeForAll();
        String result = String.format("Le temps moyen d'attente de toutes les listes est de %.2f minutes.", averageWaitingTime / 60.0 );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
