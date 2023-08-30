package com.blogapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User { // this is your parents table. // we need to join user table and role table.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//user_id join from here

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String password;

    // it is going to load everything on the memory basically.
    @ManyToMany(fetch = FetchType.EAGER)  // this is help to join two table.
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),// given user_id // left to right - join
            inverseJoinColumns = @JoinColumn(name = "role_id"))  // and give role_id // Right to left -inverse join. //

        private Set<Role> roles = new HashSet<>();   // for the unique not a duplicate , roles are not duplicate. because of can  can only get admin or user.

    // Constructors, getters, and setters

    // ...
}

//{
//        "name":"mike",
//        "username":"mike",
//        "email":"mike@gmail.com",
//        "password":"testing"
//        }

