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
@Table(name = "questions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Question implements Serializable {

    @Getter

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private String questionText;
    private String questionType;
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JsonProperty ("options")

    private List<Option> options = new ArrayList<>(); // for multiple-choice questions


    public void setId(Long questionId) {
        this.questionId = questionId;
    }


}
