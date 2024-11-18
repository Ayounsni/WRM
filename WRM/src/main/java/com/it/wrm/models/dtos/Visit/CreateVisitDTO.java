package com.it.wrm.models.dtos.Visit;

import com.it.wrm.models.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVisitDTO {
    @NotNull
    private EmbeddableIdVisitDTO id;

    @NotNull
    private LocalTime arrivalTime;

    private LocalTime startTime;

    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Min(0)
    @Max(255)
    private int priority;

    private Duration ept;
}
