package com.carservice.thesis.dto;


import com.carservice.thesis.entity.Role;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegisterRequestDto {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
    private String phoneNumber;
    private Date birthDate;
    private double salary;
    private int totalOrders;
}
