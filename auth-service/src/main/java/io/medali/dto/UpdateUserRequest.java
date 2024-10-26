package io.medali.dto;

import io.medali.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.sql.Date;

@Getter
@Setter
@Builder
public class UpdateUserRequest {
    private String firstname;
    private String lastname;
    private String email;
    private Date birthday;
    private Integer phoneNumber;
    private String registrationNumber;
    private String ncin;
    private String image;
    private String company;

}
