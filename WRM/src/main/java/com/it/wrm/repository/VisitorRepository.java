package com.it.wrm.repository;

import com.it.wrm.models.entities.Visitor;
import com.it.wrm.models.entities.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
}
