package com.it.wrm.services.implementation;



import com.it.wrm.mappers.VisitMapper;
import com.it.wrm.models.dtos.Pagination.PageDTO;
import com.it.wrm.models.dtos.Visit.CreateVisitDTO;
import com.it.wrm.models.dtos.Visit.ResponseVisitDTO;
import com.it.wrm.models.dtos.Visit.UpdateVisitDTO;
import com.it.wrm.models.dtos.WaitingList.ResponseWaitingListDTO;
import com.it.wrm.models.embeddableId.VisitId;
import com.it.wrm.models.entities.Visit;
import com.it.wrm.repository.VisitRepository;
import com.it.wrm.repository.VisitorRepository;
import com.it.wrm.repository.WaitingListRepository;
import com.it.wrm.services.interfaces.IVisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class VisitService implements IVisitService {

    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;
    private final WaitingListService waitingListService;

    @Value("${spring.app.default.capacity}")
    private int defaultCapacity;

    @Value("${spring.app.default.algorithm-type}")
    private String defaultAlgo;

    @Override
    public PageDTO<ResponseVisitDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Visit> pagedResult = visitRepository.findAll(pageable);

        List<ResponseVisitDTO> content = pagedResult.getContent()
                .stream()
                .map(visitMapper::toDTO)
                .toList();

        return new PageDTO<>(
                content,
                pagedResult.getNumber(),
                pagedResult.getSize(),
                pagedResult.getTotalElements(),
                pagedResult.getTotalPages(),
                pagedResult.isLast()
        );
    }

    @Override
    public List<ResponseVisitDTO> findAllByWaitingList(Long waitingListId) {
        ResponseWaitingListDTO responseWaitingListDTO = waitingListService.findById(waitingListId);
        String algo = responseWaitingListDTO.getAlgorithmType() != null
                ? responseWaitingListDTO.getAlgorithmType()
                : defaultAlgo;
        if (Objects.equals(algo, "FIFO")) {
            return visitRepository.findByWaitingList_Id(waitingListId)
                    .stream().sorted(Comparator.comparing(Visit::getArrivalTime))
                    .map(visitMapper::toDTO)
                    .toList();
        } else if (Objects.equals(algo, "PRIORITY")) {
            return visitRepository.findByWaitingList_Id(waitingListId).stream()
                    .sorted(Comparator.comparingInt(Visit::getPriority).reversed()
                            .thenComparing(Visit::getArrivalTime))
                    .map(visitMapper::toDTO)
                    .toList();
        }else if (Objects.equals(algo, "SJF")) {
            return visitRepository.findByWaitingList_Id(waitingListId)
                    .stream().sorted(Comparator.comparing(Visit::getEpt))
                    .map(visitMapper::toDTO)
                    .toList();
        }

        throw new IllegalArgumentException("Type d'algorithme inconnu : " + algo);
    }
    @Override
    public ResponseVisitDTO createVisit(CreateVisitDTO createVisitDTO) {
        Long waitingListId = createVisitDTO.getId().getWaitingListId();
        ResponseWaitingListDTO responseWaitingListDTO = waitingListService.findById(waitingListId);
        int capacity = responseWaitingListDTO.getCapacity() != null
                ? responseWaitingListDTO.getCapacity()
                : defaultCapacity;
        int currentVisits = visitRepository.countByWaitingList_Id(waitingListId);

        if (currentVisits >= capacity) {
            throw new IllegalStateException("La capacité maximale de la liste d'attente est atteinte. Impossible d'ajouter une autre visite.");
        }

        Visit visit = visitMapper.toEntity(createVisitDTO);
        Visit savedVisit = visitRepository.save(visit);
        return visitMapper.toDTO(savedVisit);
    }

    @Override
    public ResponseVisitDTO getVisitById(Long visitorId, Long waitingListId) {
        VisitId visitId = new VisitId(visitorId,waitingListId);
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("Visit with visitor ID " + visitorId + " and waitingList ID " + waitingListId + " does not exist."));

        return visitMapper.toDTO(visit);
    }

    @Override
    public void deleteVisitById(Long visitorId, Long waitingListId) {
        VisitId visitId = new VisitId(visitorId,waitingListId);
        if (!visitRepository.existsById(visitId)) {
            throw new IllegalArgumentException("Visit with visitor ID " + visitorId + " and waitingList ID " + waitingListId + " does not exist.");
        }
        visitRepository.deleteById(visitId);
    }

    @Override
    public ResponseVisitDTO updateVisit(Long visitorId, Long waitingListId, UpdateVisitDTO updateVisitDTO) {
        VisitId visitId = new VisitId(visitorId,waitingListId);
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("Visit with visitor ID " + visitorId + " and waitingList ID " + waitingListId + " does not exist."));
        visitMapper.updateEntityFromDTO(updateVisitDTO, visit);
        Visit updatedVisit = visitRepository.save(visit);
        return visitMapper.toDTO(updatedVisit);
    }





}
