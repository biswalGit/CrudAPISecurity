package com.pit.crudapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    String role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;   // ✅ primary key

    @Column(unique = true, nullable = false)
    private String username;   // ✅ unique constraint

    String password;
}
