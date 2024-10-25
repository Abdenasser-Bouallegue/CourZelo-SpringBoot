package io.medali.auth;

import io.medali.entity.Quest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestRegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Quest.WantedRole role;
    private boolean mfaEnabled;
    private Date birthday;
    private Integer phoneNumber;
    private String registrationNumber;
    private String ncin;
    private String company;
    private String image;
}
