package com.it.wrm.models.embeddableId;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitId implements Serializable {
    private Long visitorId;
    private Long waitingListId;
}
