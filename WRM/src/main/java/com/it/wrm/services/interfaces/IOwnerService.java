package com.it.itlens.services.interfaces;

import com.it.itlens.models.dtos.Owner.CreateOwnerDTO;
import com.it.itlens.models.dtos.Owner.ResponseOwnerDTO;
import com.it.itlens.models.dtos.Owner.UpdateOwnerDTO;
import com.it.itlens.models.entities.Owner;

import java.util.List;

public interface IOwnerService extends IGenericService<CreateOwnerDTO,UpdateOwnerDTO,ResponseOwnerDTO> {

}
