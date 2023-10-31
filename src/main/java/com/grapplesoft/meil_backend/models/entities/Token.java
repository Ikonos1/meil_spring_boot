package com.grapplesoft.meil_backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "tokens", schema = "meil", indexes = {

})
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @Column(name = "token_id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    @JsonProperty("id")
    private Long id;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeid")
    @JsonIgnore
    private Employee employee;

    @Column(name = "token", columnDefinition = "LONGTEXT")
    @JsonProperty("tokenString")
    private String tokenString;

    @Column(name = "created_at")
    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;
}
