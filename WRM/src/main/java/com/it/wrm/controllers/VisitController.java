package com.it.wrm.controllers;

import com.it.wrm.models.dtos.Pagination.PageDTO;
import com.it.wrm.models.dtos.Visit.CreateVisitDTO;
import com.it.wrm.models.dtos.Visit.ResponseVisitDTO;
import com.it.wrm.models.dtos.Visit.UpdateVisitDTO;
import com.it.wrm.models.dtos.WaitingList.CreateWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.ResponseWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.UpdateWaitingListDTO;
import com.it.wrm.models.entities.WaitingList;
import com.it.wrm.services.implementation.VisitService;
import com.it.wrm.services.implementation.WaitingListService;
import com.it.wrm.validation.annotations.Exists;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/visit")
public class VisitController {
    @Autowired
    private VisitService visitService;

    @PostMapping
    public ResponseEntity<ResponseVisitDTO> createVisit(@Valid @RequestBody CreateVisitDTO createVisitDTO) {
        ResponseVisitDTO visit = visitService.createVisit(createVisitDTO);
        return new ResponseEntity<>(visit, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageDTO<ResponseVisitDTO>> getAllVisitsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        PageDTO<ResponseVisitDTO> visits = visitService.findAll(page, size);
        return new ResponseEntity<>(visits, HttpStatus.OK);
    }

    @GetMapping("/{waitingListId}")
    public ResponseEntity<List<ResponseVisitDTO>> getAllVisitsByWaitingList(@PathVariable("waitingListId") Long waitingListId) {
        List<ResponseVisitDTO> visits = visitService.findAllByWaitingList(waitingListId);
        return new ResponseEntity<>(visits, HttpStatus.OK);
    }

    @GetMapping("/{visitorId}/{waitingListId}")
    public ResponseEntity<ResponseVisitDTO> getVisitById(@PathVariable("visitorId") Long visitorId, @PathVariable("waitingListId") Long waitingListId) {
            ResponseVisitDTO visit = visitService.getVisitById(visitorId, waitingListId);
            return new ResponseEntity<>(visit, HttpStatus.OK);

    }

    @DeleteMapping("/{visitorId}/{waitingListId}")
    public ResponseEntity<String> deleteStageCyclist(@PathVariable("visitorId") Long visitorId, @PathVariable("waitingListId") Long waitingListId) {
            visitService.deleteVisitById(visitorId, waitingListId);
            return new ResponseEntity<>("La visite a été supprimée avec succès", HttpStatus.OK);

    }

    @PutMapping("/{visitorId}/{waitingListId}")
    public ResponseEntity<ResponseVisitDTO> updateVisit(@PathVariable("visitorId") Long visitorId, @PathVariable("waitingListId") Long waitingListId, @Valid @RequestBody UpdateVisitDTO updateVisitDTO) {

        ResponseVisitDTO updatedVisit = visitService.updateVisit(visitorId, waitingListId, updateVisitDTO);
        return new ResponseEntity<>(updatedVisit, HttpStatus.OK);
    }


}
