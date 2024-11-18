package com.it.wrm.repository;

import com.it.wrm.models.embeddableId.VisitId;
import com.it.wrm.models.entities.Visit;
import com.it.wrm.models.entities.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, VisitId> {
    int countByWaitingList_Id(Long waitingListId);
    List<Visit> findByWaitingList_Id(Long waitingListId);
}
