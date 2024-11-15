package com.it.wrm.services.implementation;


import com.it.wrm.mappers.WaitingListMapper;
import com.it.wrm.models.dtos.WaitingList.CreateWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.ResponseWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.UpdateWaitingListDTO;
import com.it.wrm.models.entities.WaitingList;
import com.it.wrm.repository.WaitingListRepository;
import com.it.wrm.services.interfaces.IWaitingListService;
import org.springframework.stereotype.Service;

@Service
public class WaitingListService extends GenericService<WaitingList, CreateWaitingListDTO, UpdateWaitingListDTO, ResponseWaitingListDTO> implements IWaitingListService {

    public WaitingListService(WaitingListRepository waitingListRepository, WaitingListMapper waitingListMapper) {
        super(waitingListRepository, waitingListMapper);
    }

}
