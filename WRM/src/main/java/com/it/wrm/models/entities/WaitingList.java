package com.it.wrm.models.entities;

import com.it.wrm.models.enums.Mode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WaitingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private String algorithmType;

    @NotNull
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Mode mode;

    @OneToMany(mappedBy = "waitingList",cascade = CascadeType.REMOVE,  fetch = FetchType.EAGER)

    private List<Visit> visits ;


}
