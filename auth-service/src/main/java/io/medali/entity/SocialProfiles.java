package io.medali.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_socialprofiles")
public class SocialProfiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer iduser;
    private String twitter;
    private String facebook;
    private String instagram;
    private String linkedin;
    private String youtube;

}
