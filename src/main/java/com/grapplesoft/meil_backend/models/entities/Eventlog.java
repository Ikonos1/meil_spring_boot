package com.grapplesoft.meil_backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "eventlog", schema = "meil", indexes = {
        @Index(name = "loguserid", columnList = "loguserid"),
        @Index(name = "createuserid", columnList = "createuserid"),
        @Index(name = "edituserid", columnList = "edituserid")
})
public class Eventlog {
    @Id
    @NotNull
    @Column(name = "eventlogid", nullable = false)
    private Integer eventlogid;

    @NotNull
    @Column(name = "logtime", nullable = false)
    private Instant logtime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loguserid")
    private Employee loguserid;

    @Size(max = 200)
    @Column(name = "screenname", length = 200)
    private String screenname;

    @Size(max = 200)
    @Column(name = "description", length = 200)
    private String description;

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