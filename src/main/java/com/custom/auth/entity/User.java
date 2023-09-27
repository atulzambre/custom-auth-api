package com.custom.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity(name = "user_details")
@Getter
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column(unique = true)
    private String email;
}
