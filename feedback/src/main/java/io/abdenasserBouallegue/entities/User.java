package io.abdenasserBouallegue.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    private long id;
    private  String nom;
    @Enumerated(EnumType.STRING)
    private Role role;

}
