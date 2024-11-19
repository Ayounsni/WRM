package com.it.wrm.services.interfaces;


import com.it.wrm.models.dtos.Pagination.PageDTO;
import com.it.wrm.models.dtos.Visit.CreateVisitDTO;
import com.it.wrm.models.dtos.Visit.ResponseVisitDTO;
import com.it.wrm.models.dtos.Visit.UpdateVisitDTO;
import com.it.wrm.models.dtos.WaitingList.CreateWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.ResponseWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.UpdateWaitingListDTO;

import java.util.List;


public interface IVisitService {
    PageDTO<ResponseVisitDTO> findAll(int page, int size);
    ResponseVisitDTO createVisit(CreateVisitDTO createVisitDTO);
    ResponseVisitDTO getVisitById(Long visitorId, Long waitingListId);
    void deleteVisitById(Long visitorId, Long waitingListId);
    ResponseVisitDTO updateVisit(Long visitorId, Long waitingListId, UpdateVisitDTO updateVisitDTO);
    List<ResponseVisitDTO> findAllByWaitingList(Long waitingListId);
    double calculateAverageWaitingTimeForWaitingList(Long waitingListId);
    double calculateAverageWaitingTimeForAll();
}
