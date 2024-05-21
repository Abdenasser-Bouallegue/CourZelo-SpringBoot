package tn.esprit.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class AnswerDTO {
    @Setter
    @Getter
    @JsonProperty("questionId")
    private Long questionId;
    @Setter
    @Getter
    @JsonProperty("selectedOptionId")
    private Long selectedOptionId;
    @Setter
    @Getter
    @JsonProperty("selectedOption")
    private Option selectedOption;


    @Setter
    @JsonProperty("isCorrect")
    private boolean isCorrect;
}
