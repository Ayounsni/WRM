package com.it.wrm.repository;

import com.it.wrm.models.embeddableId.VisitId;
import com.it.wrm.models.entities.Visit;
import com.it.wrm.models.entities.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, VisitId> {
}
