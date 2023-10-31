package com.grapplesoft.meil_backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "project", schema = "meil", indexes = {
        @Index(name = "dupprojid", columnList = "dupprojid"),
        @Index(name = "status", columnList = "status"),
        @Index(name = "sectorcode", columnList = "sectorcode"),
        @Index(name = "projhodid", columnList = "projhodid"),
        @Index(name = "pmrepauthid", columnList = "pmrepauthid"),
        @Index(name = "projcoordid", columnList = "projcoordid"),
        @Index(name = "hsecoordid", columnList = "hsecoordid"),
        @Index(name = "hsemgrid", columnList = "hsemgrid"),
        @Index(name = "statecode", columnList = "statecode"),
        @Index(name = "createuserid", columnList = "createuserid"),
        @Index(name = "edituserid", columnList = "edituserid")
})
public class Project {
    @Id
    @Column(name = "projid", nullable = false)
    private Long id;

    @Size(max = 150)
    @Column(name = "projname", length = 150)
    private String projname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dupprojid")
    private Project dupprojid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sectorcode")
    private Sector sectorcode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projhodid")
    private Employee projhodid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pmrepauthid")
    private Employee pmrepauthid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projcoordid")
    private Employee projcoordid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hsecoordid")
    private Employee hsecoordid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hsemgrid")
    private Employee hsemgrid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statecode")
    private State statecode;

    @Column(name = "projvalue", precision = 10, scale = 2)
    private BigDecimal projvalue;

    @Column(name = "isoandm")
    private Boolean isoandm;

    @Size(max = 200)
    @Column(name = "remarks", length = 200)
    private String remarks;

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