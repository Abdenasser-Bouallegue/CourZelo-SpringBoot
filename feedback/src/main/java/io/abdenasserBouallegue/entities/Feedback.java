package io.abdenasserBouallegue.entities;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Feedback implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFeedback;
    private String title;
    private String body;
    @Enumerated(EnumType.STRING)
    private Category reclamationCategory;
    private LocalDateTime CreatedAT=LocalDateTime.now();
    private boolean archived=false;
    private Integer user_id ;
    private Integer forward_to_user=0;
}

