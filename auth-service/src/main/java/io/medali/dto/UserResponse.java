package io.medali.dto;

import io.medali.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
    private Date birthday;
    private Integer phoneNumber;
    private String registrationNumber;
    private String ncin;
    private String image;
    private String company;
    private String blocking;


}
