package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    //@OneToOne(mappedBy = "user")
    //private Cart cart;

   // @OneToMany(mappedBy = "user")
    //private List<Order> orders;
}