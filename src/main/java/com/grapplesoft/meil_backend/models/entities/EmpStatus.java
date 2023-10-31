package com.grapplesoft.meil_backend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "empstatus", schema = "meil", indexes = {
        @Index(name = "createuserid", columnList = "createuserid"),
        @Index(name = "edituserid", columnList = "edituserid")
})
public class EmpStatus {
    @Id
    @Column(name = "empstatusid", nullable = false)
    private Integer id;

    @Size(max = 150)
    @Column(name = "empstatus", length = 150)
    private String empstatus;

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