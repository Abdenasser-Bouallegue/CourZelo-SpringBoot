package tn.esprit.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class QuestionDTO {
    @JsonProperty("id")
    @Getter
    @Setter
    private Long id;
    @Setter
    @Getter
    @JsonProperty("questionText")
    private String questionText;

@Getter

    @Setter   @JsonProperty ("options")
    private List<optionsDTO> options; // for multiple-choice questions


}
