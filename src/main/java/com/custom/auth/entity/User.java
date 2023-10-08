package com.custom.auth.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "user_details")
@Data
@ToString
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column(unique = false)
    private String email;
    @Column
    private Boolean isActive;
    @Column
    private Long activateLinkAttempt;

    @Column
    private Boolean isActivationLinkSent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Roles> roles;

    @CreationTimestamp
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    private LocalDateTime updatedDateTime;
}
