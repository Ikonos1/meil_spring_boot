package com.grapplesoft.meil_backend.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction", schema = "meil", indexes = {
        @Index(name = "actiontypeid", columnList = "actiontypeid"),
        @Index(name = "employeeid", columnList = "employeeid"),
        @Index(name = "fromProjectId", columnList = "fromProjectId"),
        @Index(name = "toprojectid", columnList = "toprojectid"),
        @Index(name = "projectsite", columnList = "projectsite"),
        @Index(name = "hsecoordid", columnList = "hsecoordid"),
        @Index(name = "hsemgrid", columnList = "hsemgrid"),
        @Index(name = "deptIdFrom", columnList = "deptIdFrom"),
        @Index(name = "deptIdTo", columnList = "deptIdTo"),
        @Index(name = "function1", columnList = "function1"),
        @Index(name = "function2", columnList = "function2"),
        @Index(name = "newstatus", columnList = "newstatus"),
        @Index(name = "createuserid", columnList = "createuserid"),
        @Index(name = "edituserid", columnList = "edituserid")
})
public class Transaction {
    @Id
    @Column(name = "transid", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actiontypeid")
    private Actiontype actiontypeid;

    @Column(name = "actiondate")
    private LocalDate actiondate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeid")
    private Employee employeeid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fromProjectId")
    private Project fromprojectid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toprojectid")
    private Project toprojectid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectsite")
    private Projectsite projectsite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hsecoordid")
    private Employee hsecoordid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hsemgrid")
    private Employee hsemgrid;

    @Column(name = "noticeperiod")
    private Integer noticeperiod;

    @Column(name = "date1")
    private LocalDate date1;

    @Column(name = "date2")
    private LocalDate date2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptIdFrom")
    private Department dept1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptIdTo")
    private Department dept2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "function1")
    private Hsefunction function1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "function2")
    private Hsefunction function2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "newstatus")
    private Status newstatus;

    @Lob
    @Column(name = "remarks")
    private String remarks;

    @Column(name = "docavailable")
    private Boolean docavailable;

    @Lob
    @Column(name = "searchtext")
    private String searchtext;

    @Column(name = "isactivated")
    private Boolean isactivated;

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

    @Column(name = "isapproved")
    private Boolean isapproved;


}