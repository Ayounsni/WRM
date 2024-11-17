package com.it.wrm.services.interfaces;


import com.it.wrm.models.dtos.Visit.CreateVisitDTO;
import com.it.wrm.models.dtos.Visit.ResponseVisitDTO;
import com.it.wrm.models.dtos.Visit.UpdateVisitDTO;
import com.it.wrm.models.dtos.WaitingList.CreateWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.ResponseWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.UpdateWaitingListDTO;


public interface IVisitService extends IGenericService<CreateVisitDTO, UpdateVisitDTO, ResponseVisitDTO> {

}
