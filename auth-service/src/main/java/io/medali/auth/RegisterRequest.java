package io.medali.auth;

import io.medali.entity.Quest;
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
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private Role role;
  private boolean mfaEnabled;
  private Date birthday;
  private Integer phoneNumber;
  private String registrationNumber;
  private String ncin;
  private String company;
  private String image;

}
