package com.grapplesoft.meil_backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "address", schema = "meil", indexes = {
        @Index(name = "statecode", columnList = "statecode"),
        @Index(name = "createuserid", columnList = "createuserid"),
        @Index(name = "edituserid", columnList = "edituserid")
})
public class Address {
    @Id
    @Column(name = "addressid", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "addressline1", length = 100)
    private String addressline1;

    @Size(max = 100)
    @Column(name = "addressline2", length = 100)
    private String addressline2;

    @Size(max = 100)
    @Column(name = "place", length = 100)
    private String place;

    @Size(max = 100)
    @Column(name = "district", length = 100)
    private String district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statecode")
    private State statecode;

    @Size(max = 100)
    @Column(name = "statename", length = 100)
    private String statename;

    @Column(name = "pincode")
    private Integer pincode;

    @Size(max = 100)
    @Column(name = "countryname", length = 100)
    private String countryname;

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