package com.it.itlens.controllers;

import com.it.itlens.models.dtos.Owner.CreateOwnerDTO;
import com.it.itlens.models.dtos.Owner.ResponseOwnerDTO;
import com.it.itlens.models.dtos.Owner.UpdateOwnerDTO;
import com.it.itlens.models.dtos.Pagination.PageDTO;
import com.it.itlens.models.entities.Owner;
import com.it.itlens.services.implementation.OwnerService;
import com.it.itlens.validation.annotations.Exists;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @PostMapping
    public ResponseEntity<ResponseOwnerDTO> createOwner(@Valid @RequestBody CreateOwnerDTO createOwnerDTO) {
        ResponseOwnerDTO owner = ownerService.create(createOwnerDTO);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOwnerDTO> getOwnerById(@Exists(entity = Owner.class , message = "Cet owner n'existe pas.")  @PathVariable("id") Long id) {
            ResponseOwnerDTO owner = ownerService.findById(id);
            return new ResponseEntity<>(owner, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PageDTO<ResponseOwnerDTO>> getAllOwnersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        PageDTO<ResponseOwnerDTO> owners = ownerService.findAll(page, size);
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@Exists(entity = Owner.class , message = "Cet owner n'existe pas.") @PathVariable("id") Long id) {
            ownerService.deleteById(id);
            return new ResponseEntity<>("Owner est supprimé avec succès", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseOwnerDTO> updateOwner(@Exists(entity = Owner.class , message = "Cet owner n'existe pas.") @PathVariable("id") Long id, @Valid @RequestBody UpdateOwnerDTO updateOwnerDTO) {

            ResponseOwnerDTO updatedOwner = ownerService.update(id, updateOwnerDTO);
            return new ResponseEntity<>(updatedOwner, HttpStatus.OK);
    }


}
