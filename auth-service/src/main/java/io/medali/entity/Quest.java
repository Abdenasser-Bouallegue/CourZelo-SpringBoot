package io.medali.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_Quest")
public class Quest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    private Date birthday;
    private Integer phoneNumber;
    private String registrationNumber;
    private String ncin;
    private String company;
    private String image;

    @Column(columnDefinition = "boolean Default False")
    private boolean blocking;


    private boolean mfaEnabled;
    private String secret;


    @Enumerated(EnumType.STRING)
    private WantedRole role;


    public enum WantedRole{
        STUDENT,RECRUITER
    }
    public Role convertToRole() {
        switch (this.role) {
            case STUDENT:
                return Role.STUDENT;
            case RECRUITER:
                return Role.RECRUITER;
            default:
                throw new IllegalArgumentException("Unsupported WantedRole: " + this.role);
        }
    }
}
