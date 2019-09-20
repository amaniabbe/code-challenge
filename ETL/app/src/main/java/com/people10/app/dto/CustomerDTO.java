package com.people10.app.dto;


import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CustomerDTO {

     
    private Long id;
    private LocalDateTime createdAt;
    private String ip;
    private String longitude;
    private String latitude;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime updatedAt;

   
}