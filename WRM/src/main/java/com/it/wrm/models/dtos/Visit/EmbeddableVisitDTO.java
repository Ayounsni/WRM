package com.it.wrm.models.dtos.Visit;

import com.it.wrm.models.dtos.Visitor.EmbeddableVisitorDTO;
import com.it.wrm.models.dtos.WaitingList.EmbeddableWaitingListDTO;
import com.it.wrm.models.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddableVisitDTO {

    private EmbeddableVisitorDTO visitor;

    private LocalTime arrivalTime;

    private LocalTime startTime;

    private LocalTime endTime;

    private Status status;

    private byte priority;

    private Duration ept;
}
