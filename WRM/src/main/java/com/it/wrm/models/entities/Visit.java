package com.it.wrm.models.entities;

import com.it.wrm.models.embeddableId.VisitId;
import com.it.wrm.models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Duration;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class
Visit {

    @EmbeddedId
    private VisitId id;

    @ManyToOne
    @MapsId("visitorId")
    private Visitor visitor;

    @ManyToOne
    @MapsId("waitingListId")
    private WaitingList waitingList;

    @NotNull
    private LocalTime arrivalTime;

    private LocalTime startTime;

    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private Status status ;

    @Min(0)
    @Max(255)
    private int priority;

    private Duration ept; // EPT (Estimation du temps de travail)


}
