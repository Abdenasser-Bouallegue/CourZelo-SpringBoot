package tn.esprit.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Setter
@Getter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long UserId ;
    private Role role ;

}
