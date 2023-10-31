package com.grapplesoft.meil_backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    private Long id;

    @JsonProperty("roleName")
    @Column(name = "role_name")
    private String roleName;

    @JsonProperty("description")
    @Column(name = "description")
    private String description;

    @JsonProperty("createdDate")
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @JsonProperty("updatedDate")
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public Role(String roleName, String description, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.roleName = roleName;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Role(Long id, String roleName, String description, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

}
