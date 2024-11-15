package com.it.wrm.services.implementation;


import com.it.wrm.services.interfaces.IGenericService;
import com.it.wrm.mappers.GenericMapper;
import com.it.wrm.models.dtos.Pagination.PageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class GenericService<Entity, CreateDTO, UpdateDTO, ResponseDTO> implements IGenericService<CreateDTO, UpdateDTO, ResponseDTO> {

    protected final JpaRepository<Entity, Long> repository;
    protected final GenericMapper<Entity, CreateDTO, UpdateDTO, ResponseDTO> mapper;

    public GenericService(JpaRepository<Entity, Long> repository, GenericMapper<Entity, CreateDTO,UpdateDTO, ResponseDTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ResponseDTO create(CreateDTO createDTO) {
        if (createDTO == null) {
            throw new NullPointerException("The DTO cannot be null");
        }
        Entity entity = mapper.toEntity(createDTO);
        Entity savedEntity = repository.save(entity);
        return mapper.toDTO(savedEntity);
    }

    @Override
    public ResponseDTO findById(Long id) {
        Entity entity = repository.findById(id).orElseThrow();
        return mapper.toDTO(entity);
    }

    @Override
    public PageDTO<ResponseDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Entity> pagedResult = repository.findAll(pageable);

        List<ResponseDTO> content = pagedResult.getContent()
                .stream()
                .map(mapper::toDTO)
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
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ResponseDTO update(Long id, UpdateDTO updateDTO) {
        if (updateDTO == null) {
            throw new NullPointerException("The DTO cannot be null");
        }
        Entity entity = repository.findById(id).orElseThrow();
        Entity updatedEntity = mapper.updateEntityFromDTO(updateDTO, entity);
        updatedEntity = repository.save(updatedEntity);
        return mapper.toDTO(updatedEntity);
    }
}
