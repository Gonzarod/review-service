package com.evertix.reviewservice.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
public class User {

    public User() { }

    public User(Long id,String username, String email, String name,
                String lastName, String dni, String phone, LocalDate birthday, String address) {
        this.id=id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.lastName = lastName;


    }

    private Long id;
    private String username;
    private String email;
    private String name;
    private String lastName;

}
