package com.it.wrm.models.dtos.Visit;

import com.it.wrm.models.dtos.Visitor.EmbeddableVisitorDTO;
import com.it.wrm.models.dtos.WaitingList.EmbeddableWaitingListDTO;
import com.it.wrm.models.entities.Visitor;
import com.it.wrm.models.entities.WaitingList;
import com.it.wrm.models.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVisitDTO {

    private EmbeddableVisitorDTO visitor;

    private EmbeddableWaitingListDTO waitingList;

    private LocalTime arrivalTime;

    private LocalTime startTime;

    private LocalTime endTime;

    private Status status;

    private byte priority;

    private Duration ept;
}
