package com.it.wrm.models.dtos.Visit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddableIdVisitDTO {
    private Long visitorId;
    private Long waitingListId;
}
