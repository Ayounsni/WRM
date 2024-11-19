package com.it.wrm;

import com.it.wrm.mappers.GenericMapper;
import com.it.wrm.models.dtos.Pagination.PageDTO;
import com.it.wrm.models.dtos.WaitingList.CreateWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.ResponseWaitingListDTO;
import com.it.wrm.models.dtos.WaitingList.UpdateWaitingListDTO;
import com.it.wrm.models.entities.WaitingList;
import com.it.wrm.services.implementation.GenericService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenericServiceTest {

    @Mock
    private JpaRepository<WaitingList, Long> repository;

    @Mock
    private GenericMapper<WaitingList, CreateWaitingListDTO, UpdateWaitingListDTO, ResponseWaitingListDTO> mapper;

    @InjectMocks
    private GenericService<WaitingList, CreateWaitingListDTO, UpdateWaitingListDTO, ResponseWaitingListDTO> genericService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate_validData() {
        CreateWaitingListDTO createDTO = new CreateWaitingListDTO();
        WaitingList ownerEntity = new WaitingList();
        ResponseWaitingListDTO responseDTO = new ResponseWaitingListDTO();

        when(mapper.toEntity(createDTO)).thenReturn(ownerEntity);
        when(repository.save(ownerEntity)).thenReturn(ownerEntity);
        when(mapper.toDTO(ownerEntity)).thenReturn(responseDTO);

        ResponseWaitingListDTO result = genericService.create(createDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(repository, times(1)).save(ownerEntity);
        verify(mapper, times(1)).toDTO(ownerEntity);
    }

    @Test
    void testCreate_nullData() {
        CreateWaitingListDTO createDTO = null;

        assertThrows(NullPointerException.class, () -> {
            genericService.create(createDTO);
        });
    }

    @Test
    void testFindById_validId() {
        Long id = 1L;
        WaitingList ownerEntity = new WaitingList();
        ResponseWaitingListDTO responseDTO = new ResponseWaitingListDTO();

        when(repository.findById(id)).thenReturn(Optional.of(ownerEntity));
        when(mapper.toDTO(ownerEntity)).thenReturn(responseDTO);

        ResponseWaitingListDTO result = genericService.findById(id);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(repository, times(1)).findById(id);
    }

    @Test
    void testFindById_invalidId() {
        Long id = 999L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            genericService.findById(id);
        });
    }

    @Test
    void testFindAll_validPagination() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);
        WaitingList ownerEntity = new WaitingList();
        ResponseWaitingListDTO responseDTO = new ResponseWaitingListDTO();
        Page<WaitingList> pagedResult = new PageImpl<>(List.of(ownerEntity));

        when(repository.findAll(pageRequest)).thenReturn(pagedResult);
        when(mapper.toDTO(ownerEntity)).thenReturn(responseDTO);

        PageDTO<ResponseWaitingListDTO> result = genericService.findAll(page, size);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        assertEquals(responseDTO, result.getContent().get(0));
        verify(repository, times(1)).findAll(pageRequest);
    }

    @Test
    void testFindAll_emptyPage() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<WaitingList> emptyPage = Page.empty();

        when(repository.findAll(pageRequest)).thenReturn(emptyPage);

        PageDTO<ResponseWaitingListDTO> result = genericService.findAll(page, size);

        assertNotNull(result);
        assertTrue(result.getContent().isEmpty());
        verify(repository, times(1)).findAll(pageRequest);
    }

    @Test
    void testDeleteById_validId() {
        Long id = 1L;

        genericService.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteById_invalidId() {
        Long id = 999L;

        doThrow(new RuntimeException("Entity not found")).when(repository).deleteById(id);

        assertThrows(RuntimeException.class, () -> {
            genericService.deleteById(id);
        });
    }

    @Test
    void testUpdate_validData() {
        Long id = 1L;
        UpdateWaitingListDTO updateDTO = new UpdateWaitingListDTO();
        WaitingList existingOwner = new WaitingList();
        WaitingList updatedOwner = new WaitingList();
        ResponseWaitingListDTO responseDTO = new ResponseWaitingListDTO();

        when(repository.findById(id)).thenReturn(Optional.of(existingOwner));
        when(mapper.updateEntityFromDTO(updateDTO, existingOwner)).thenReturn(updatedOwner);
        when(repository.save(updatedOwner)).thenReturn(updatedOwner);
        when(mapper.toDTO(updatedOwner)).thenReturn(responseDTO);

        ResponseWaitingListDTO result = genericService.update(id, updateDTO);

        assertNotNull(result);
        assertEquals(responseDTO, result);
        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(updatedOwner);
    }

    @Test
    void testUpdate_invalidId() {
        Long id = 999L;
        UpdateWaitingListDTO updateDTO = new UpdateWaitingListDTO();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            genericService.update(id, updateDTO);
        });
    }

    @Test
    void testUpdate_nullData() {
        Long id = 1L;
        UpdateWaitingListDTO updateDTO = null;

        assertThrows(NullPointerException.class, () -> {
            genericService.update(id, updateDTO);
        });
    }
}

