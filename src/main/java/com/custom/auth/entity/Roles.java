package com.custom.auth.entity;

import com.custom.auth.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Roles {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Role role;
}
