package io.cristos.petmanagement.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@MappedSuperclass
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseIdCreationDate extends BaseId {


    @Column(
            name = "createdAt",
            columnDefinition = "DATE"
    )
    @CreationTimestamp
    private LocalDate createdAt;
}
