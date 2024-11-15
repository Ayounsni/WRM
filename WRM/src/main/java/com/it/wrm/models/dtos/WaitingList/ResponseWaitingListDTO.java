package com.it.wrm.models.dtos.WaitingList;

import com.it.wrm.models.dtos.Visit.EmbeddableVisitDTO;
import com.it.wrm.models.entities.Visit;
import com.it.wrm.models.enums.Mode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWaitingListDTO {
    private Long id;

    private LocalDate date;

    private String algorithmType;

    private Integer capacity;

    private Mode mode;

    private List<EmbeddableVisitDTO> visits ;
}
