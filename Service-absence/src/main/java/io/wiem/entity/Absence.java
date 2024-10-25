package io.wiem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_absence")

public class Absence {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Integer iduser;
  private String firstname;
  private String lastname;
  private String email;
  private Integer phoneNumber;
  private String registrationNumber;
  private String claase;
  private Date date;




}
