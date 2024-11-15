package com.it.wrm.models.dtos.WaitingList;

import com.it.wrm.models.enums.Mode;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateWaitingListDTO {

    private LocalDate date;

    private String algorithmType;

    private Integer capacity;

    @Enumerated(EnumType.STRING)
    private Mode mode;
}
