package com.grapplesoft.meil_backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "status", schema = "meil", indexes = {
        @Index(name = "createuserid", columnList = "createuserid"),
        @Index(name = "edituserid", columnList = "edituserid")
})
public class Status {
    @Id
    @Column(name = "statusid", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Column(name = "status", nullable = false, length = 200)
    private String status;

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