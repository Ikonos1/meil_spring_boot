package com.grapplesoft.meil_backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@Table(name = "projectsite", schema = "meil", indexes = {
        @Index(name = "projid", columnList = "projid"),
        @Index(name = "sitemanagerid", columnList = "sitemanagerid"),
        @Index(name = "projcoordid", columnList = "projcoordid"),
        @Index(name = "courierpcode", columnList = "courierpcode"),
        @Index(name = "addressid", columnList = "addressid"),
        @Index(name = "createuserid", columnList = "createuserid"),
        @Index(name = "edituserid", columnList = "edituserid")
})
@NoArgsConstructor
@AllArgsConstructor
public class Projectsite {
    @Id
    @Column(name = "projsiteid", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projid")
    private Project projid;

    @Size(max = 200)
    @Column(name = "sitename", length = 200)
    private String sitename;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sitemanagerid")
    private Employee sitemanagerid;

//    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projcoordid", nullable = true)
    private Employee projcoordid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courierpcode")
    private Employee courierpcode;

    @Size(max = 200)
    @Column(name = "courierpmobile", length = 200)
    private String courierpmobile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressid")
    private Address addressid;

    @Column(name = "latitude", precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 11, scale = 8)
    private BigDecimal longitude;

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