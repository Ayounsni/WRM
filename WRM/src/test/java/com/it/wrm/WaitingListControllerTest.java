package com.it.wrm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.wrm.models.dtos.WaitingList.CreateWaitingListDTO;
import com.it.wrm.models.entities.WaitingList;
import com.it.wrm.models.enums.Mode;
import com.it.wrm.repository.WaitingListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {"spring.profiles.active=test"})
@AutoConfigureMockMvc
public class WaitingListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WaitingListRepository waitingListRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        waitingListRepository.deleteAll(); // Nettoyer la base avant chaque test
    }

    @Test
    void testCreateWaitingList() throws Exception {
        // Préparation des données
        CreateWaitingListDTO createWaitingListDTO = new CreateWaitingListDTO();
        createWaitingListDTO.setCapacity(5);
        createWaitingListDTO.setAlgorithmType("FIFO");
        createWaitingListDTO.setDate(LocalDate.now());
        createWaitingListDTO.setMode(Mode.FULL_TIME); // Exemple d'enum

        // Exécution et vérification
        mockMvc.perform(post("/api/waitingList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createWaitingListDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.capacity").value(5))
                .andExpect(jsonPath("$.algorithmType").value("FIFO"));
    }

    @Test
    void testGetWaitingListById() throws Exception {
        // Création d'un élément dans la base
        WaitingList waitingList = new WaitingList();
        waitingList.setCapacity(10);
        waitingList.setAlgorithmType("LIFO");
        waitingList.setDate(LocalDate.now());
        waitingList.setMode(Mode.FULL_TIME); // Exemple d'enum
        waitingList = waitingListRepository.save(waitingList);

        // Exécution et vérification
        mockMvc.perform(get("/api/waitingList/{id}", waitingList.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.capacity").value(10))
                .andExpect(jsonPath("$.algorithmType").value("LIFO"));
    }
    @Test
    void testGetAllWaitingListsPaginated() throws Exception {
        // Création d'une liste d'attente pour tester la pagination
        WaitingList waitingList1 = new WaitingList();
        waitingList1.setCapacity(5);
        waitingList1.setAlgorithmType("LIFO");
        waitingList1.setDate(LocalDate.now());
        waitingList1.setMode(Mode.FULL_TIME);
        waitingListRepository.save(waitingList1);

        WaitingList waitingList2 = new WaitingList();
        waitingList2.setCapacity(10);
        waitingList2.setAlgorithmType("LIFO");
        waitingList2.setDate(LocalDate.now());
        waitingList2.setMode(Mode.FULL_TIME);
        waitingListRepository.save(waitingList2);

        // Exécution et vérification de la pagination
        mockMvc.perform(get("/api/waitingList?page=0&size=2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2)) // Vérifie qu'il y a 2 éléments dans le contenu
                .andExpect(jsonPath("$.content[0].capacity").value(5))
                .andExpect(jsonPath("$.content[1].capacity").value(10));
    }

    @Test
    void testDeleteWaitingList() throws Exception {
        // Création d'une liste d'attente pour tester la suppression
        WaitingList waitingList = new WaitingList();
        waitingList.setCapacity(5);
        waitingList.setAlgorithmType("LIFO");
        waitingList.setDate(LocalDate.now());
        waitingList.setMode(Mode.FULL_TIME);
        waitingList = waitingListRepository.save(waitingList);

        // Exécution et vérification de la suppression
        mockMvc.perform(delete("/api/waitingList/{id}", waitingList.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("WaitingList est supprimé avec succès"));

    }
}
