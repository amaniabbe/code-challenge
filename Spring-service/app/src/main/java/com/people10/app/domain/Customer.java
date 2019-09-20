package com.people10.app.domain;


import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private Long id;

    private LocalDateTime createdAt;
    private String ip;
    private double longitude;
    private double latitude;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime updatedAt;

   
}
