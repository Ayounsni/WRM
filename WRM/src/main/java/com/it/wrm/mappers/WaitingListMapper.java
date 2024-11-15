package com.it.wrm.mappers;


import com.it.wrm.models.dtos.WaitingList.CreateWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.ResponseWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.UpdateWaitingListDTO;
import com.it.wrm.models.entities.WaitingList;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WaitingListMapper extends GenericMapper<WaitingList, CreateWaitingListDTO, UpdateWaitingListDTO, ResponseWaitingListDTO> {

}
