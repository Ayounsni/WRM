package com.it.wrm.models.dtos.WaitingList;

import com.it.wrm.models.enums.Mode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddableWaitingListDTO {
    private Long id;

    private LocalDate date;

    private String algorithmType;

    private Integer capacity;

    private Mode mode;
}
