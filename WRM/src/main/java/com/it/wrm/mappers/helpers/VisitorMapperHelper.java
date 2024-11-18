package com.it.wrm.mappers.helpers;


import com.it.wrm.models.entities.Visitor;
import com.it.wrm.models.entities.WaitingList;
import com.it.wrm.repository.VisitorRepository;
import com.it.wrm.repository.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VisitorMapperHelper {

    @Autowired
    private VisitorRepository visitorRepository;

    public Visitor mapVisitorIdToVisitor(Long visitorId) {
        return visitorRepository.findById(visitorId).orElse(null);
    }
}
