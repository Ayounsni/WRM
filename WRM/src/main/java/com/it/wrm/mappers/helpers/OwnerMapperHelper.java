package com.it.itlens.models.mappers.helpers;

import com.it.itlens.models.entities.Owner;
import com.it.itlens.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapperHelper {

    @Autowired
    private OwnerRepository ownerRepository;

    public Owner mapOwnerIdToOwner(Long ownerId) {
        return ownerRepository.findById(ownerId).orElse(null);
    }
}
