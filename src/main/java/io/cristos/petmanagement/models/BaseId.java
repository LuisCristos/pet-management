package io.cristos.petmanagement.models;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
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

    public BaseId(Long id) {
        this.id = id;
    }

    public BaseId() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
