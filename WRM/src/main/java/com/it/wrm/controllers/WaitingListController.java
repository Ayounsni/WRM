package com.it.wrm.controllers;

import com.it.wrm.models.dtos.Pagination.PageDTO;
import com.it.wrm.models.dtos.WaitingList.CreateWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.ResponseWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.UpdateWaitingListDTO;
import com.it.wrm.models.entities.WaitingList;
import com.it.wrm.services.implementation.WaitingListService;
import com.it.wrm.validation.annotations.Exists;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/waitingList")
public class WaitingListController {
    @Autowired
    private WaitingListService waitingListService;

    @PostMapping
    public ResponseEntity<ResponseWaitingListDTO> createWaitingList(@Valid @RequestBody CreateWaitingListDTO createOwnerDTO) {
        ResponseWaitingListDTO waitingList = waitingListService.create(createOwnerDTO);
        return new ResponseEntity<>(waitingList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWaitingListDTO> getWaitingListById(@Exists(entity = WaitingList.class , message = "Cette waitingList n'existe pas.")  @PathVariable("id") Long id) {
            ResponseWaitingListDTO waitingList = waitingListService.findById(id);
            return new ResponseEntity<>(waitingList, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PageDTO<ResponseWaitingListDTO>> getAllWaitingListsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        PageDTO<ResponseWaitingListDTO> owners = waitingListService.findAll(page, size);
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWaitingList(@Exists(entity = WaitingList.class , message = "Cette waitingList n'existe pas.") @PathVariable("id") Long id) {
            waitingListService.deleteById(id);
            return new ResponseEntity<>("WaitingList est supprimé avec succès", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWaitingListDTO> updateOwner(@Exists(entity = WaitingList.class , message = "Cet waitingList n'existe pas.") @PathVariable("id") Long id, @Valid @RequestBody UpdateWaitingListDTO updateWaitingListDTO) {

            ResponseWaitingListDTO updatedWaitingList = waitingListService.update(id, updateWaitingListDTO);
            return new ResponseEntity<>(updatedWaitingList, HttpStatus.OK);
    }


}
