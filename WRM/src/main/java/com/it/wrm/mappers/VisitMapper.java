package com.it.wrm.mappers;


import com.it.wrm.mappers.helpers.VisitorMapperHelper;
import com.it.wrm.mappers.helpers.WaitingListMapperHelper;
import com.it.wrm.models.dtos.Visit.CreateVisitDTO;
import com.it.wrm.models.dtos.Visit.ResponseVisitDTO;
import com.it.wrm.models.dtos.Visit.UpdateVisitDTO;
import com.it.wrm.models.dtos.WaitingList.CreateWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.ResponseWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.UpdateWaitingListDTO;
import com.it.wrm.models.entities.Visit;
import com.it.wrm.models.entities.WaitingList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring",uses = {WaitingListMapperHelper.class , VisitorMapperHelper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VisitMapper extends GenericMapper<Visit, CreateVisitDTO, UpdateVisitDTO, ResponseVisitDTO> {

    @Override
    @Mapping(target = "waitingList", source = "id.waitingListId")
    @Mapping(target = "visitor", source = "id.visitorId")
    @Mapping(target = "status", source = "status", defaultValue = "PENDING")
    Visit toEntity(CreateVisitDTO createVisitDTO);

    @Override
    Visit updateEntityFromDTO(UpdateVisitDTO updateVisitDTO, @MappingTarget Visit entity);

}
