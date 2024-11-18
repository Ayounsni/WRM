package com.it.wrm.mappers.helpers;


import com.it.wrm.models.entities.WaitingList;
import com.it.wrm.repository.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WaitingListMapperHelper {

    @Autowired
    private WaitingListRepository waitingListRepository;

    public WaitingList mapWaitingListIdToWaitingList(Long waitingListId) {
        return waitingListRepository.findById(waitingListId).orElse(null);
    }
}
