package tn.esprit.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


public class QuizDTO {
    @Setter
    @JsonProperty("id")
    private Long id;
    @Setter
    @JsonProperty("title")
    private String title;
    @Setter
    @JsonProperty("questions")
    private List<QuestionDTO> questions;


}
