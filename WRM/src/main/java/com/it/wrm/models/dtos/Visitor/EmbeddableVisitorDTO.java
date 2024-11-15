package com.it.wrm.models.dtos.Visitor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddableVisitorDTO {
    private Long id;

    private String firstName;

    private String lastName;
}
