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
public class AllUserProfil {
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
    private String twitter;
    private String facebook;
    private String instagram;
    private String linkedin;
    private String youtube;
}
