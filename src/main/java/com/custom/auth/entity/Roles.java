package com.custom.auth.entity;

import com.custom.auth.model.Role;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Data
public class Roles {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Role role;
}
