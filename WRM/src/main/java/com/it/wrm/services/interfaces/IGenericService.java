package com.it.wrm.services.interfaces;

import com.it.wrm.models.dtos.Pagination.PageDTO;


public interface IGenericService<CreateDTO, UpdateDTO, ResponseDTO> {
    ResponseDTO create(CreateDTO createDTO);
    ResponseDTO findById(Long id);
    PageDTO<ResponseDTO> findAll(int page, int size);
    void deleteById(Long id);
    ResponseDTO update(Long id, UpdateDTO updateDTO);
}

