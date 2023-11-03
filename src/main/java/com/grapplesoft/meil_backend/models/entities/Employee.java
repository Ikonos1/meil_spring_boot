package com.grapplesoft.meil_backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Entity
@Table(name = "employee", schema = "meil", indexes = {
        @Index(name = "empstatus", columnList = "empstatus"),
        @Index(name = "deptcode", columnList = "deptcode"),
        @Index(name = "projcode", columnList = "projcode"),
        @Index(name = "hsefunctionid", columnList = "hsefunctionid"),
        @Index(name = "createuserid", columnList = "createuserid"),
        @Index(name = "edituserid", columnList = "edituserid"),
        @Index(name = "role_id", columnList = "role_id")
})
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @Column(name = "employeeid", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private Long id;

    @Size(max = 200)
    @Column(name = "empname", length = 200)
    private String employeeName;

    @Size(max = 200)
    @Column(name = "empfirstname", length = 200)
    private String employeeFirstName;

    @Size(max = 200)
    @Column(name = "emplastname", length = 200)
    private String employeeLastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empstatus")
    private EmpStatus empStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptcode")
    private Department deptCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projcode")
    private Project projCode;

    @Size(max = 30)
    @Column(name = "mobileoffice", length = 30)
    private String mobileOffice;

    @Size(max = 30)
    @Column(name = "mobile2", length = 30)
    private String mobile2;

    @Size(max = 30)
    @Column(name = "mobile3", length = 30)
    private String mobile3;

    @Size(max = 30)
    @Column(name = "whatsappnum", length = 30)
    private String whatsAppNum;

    @Size(max = 50)
    @Column(name = "emailoffice", length = 50)
    private String emailOffice;

    @Size(max = 50)
    @Column(name = "email2", length = 50)
    private String email2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hsefunctionid")
    private Hsefunction hseFunctionId;

    @Size(max = 100)
    @Column(name = "designation", length = 100)
    private String designation;

    @Column(name = "dateofjoining")
    private LocalDate dateOfJoining;

    @Column(name = "dateofbirth")
    private LocalDate dateOfBirth;

    @Size(max = 100)
    @Column(name = "drivlicense", length = 100)
    private String drivingLicense;

    @Column(name = "hsecomplevel")
    private Integer hseCompLevel;

    @Column(name = "hsecomppoints")
    private Integer hseCompPoints;

    @Size(max = 40)
    @Column(name = "hseleadership", length = 40)
    private String hseLeadership;

    @Size(max = 200)
    @Column(name = "remarks", length = 200)
    private String remarks;

    @Column(name = "createdate")
    private LocalDate createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createuserid")
    private Employee createUserId;

    @Column(name = "editdate")
    private LocalDate editDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edituserid")
    private Employee editUserId;

    @Column(name = "isdeleted")
    private Boolean isDeleted;

    @ManyToOne( cascade = {CascadeType.MERGE})
    @JoinColumn(name = "role_id", referencedColumnName = "id", unique = false)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Role role;

    @Column(name = "password")
    private String password;

    @Column(name = "is_password_set")
    private Boolean isPasswordSet;

    // tokens
    @OneToOne(mappedBy = "employee", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Token token;
}