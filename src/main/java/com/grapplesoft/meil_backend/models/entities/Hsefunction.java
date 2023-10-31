package com.grapplesoft.meil_backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "hsefunction", schema = "meil", indexes = {
        @Index(name = "createuserid", columnList = "createuserid"),
        @Index(name = "edituserid", columnList = "edituserid")
})
public class Hsefunction {
    @Id
    @Size(max = 10)
    @Column(name = "hsefunccode", nullable = false, length = 10)
    private String hsefunccode;

    @Size(max = 200)
    @Column(name = "hsefuncion_name", length = 200)
    private String hsefuncionName;

    @Column(name = "createdate")
    private LocalDate createdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createuserid")
    private Employee createuserid;

    @Column(name = "editdate")
    private LocalDate editdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edituserid")
    private Employee edituserid;

    @Column(name = "isdeleted")
    private Boolean isdeleted;

}