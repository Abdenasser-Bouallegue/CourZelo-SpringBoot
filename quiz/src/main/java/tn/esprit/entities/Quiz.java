package tn.esprit.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quizzes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;
    private String description;
    private Integer duration;
    @OneToMany(mappedBy = "quiz",cascade = CascadeType.ALL , orphanRemoval = true,fetch = FetchType.EAGER)
@JsonProperty ("questions")
    private List<Question> questions = new ArrayList<>();






}
