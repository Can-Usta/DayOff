package com.canusta.DayOff.Model;

import com.canusta.DayOff.Enums.UserType;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String surname;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(columnDefinition = "VARCHAR(150) NOT NULL", unique = true)
    private String mail;



}
