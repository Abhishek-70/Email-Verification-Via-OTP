package com.OtpBasedLogIn.OtpBasedLogIn.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "userDetails")
public class User2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    private String name;
    private String email;
    private String password;
    private String phone;

   // Getters and Setters


}
