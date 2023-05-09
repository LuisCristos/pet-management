package io.cristos.petmanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseId implements Serializable {

    @Id
    @Column(
            name = "id",
            nullable = false,
            updatable = false,
            unique = true
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_generator"
    )
    private Long id;

}
