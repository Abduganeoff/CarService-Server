package com.carservice.thesis.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderManagerResponseDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String role;
    private String phoneNumber;
    private Date birthDate;
}
