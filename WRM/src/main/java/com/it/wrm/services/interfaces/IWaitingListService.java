package com.it.wrm.services.interfaces;


import com.it.wrm.models.dtos.WaitingList.CreateWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.ResponseWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.UpdateWaitingListDTO;


public interface IWaitingListService extends IGenericService<CreateWaitingListDTO, UpdateWaitingListDTO, ResponseWaitingListDTO> {

}
