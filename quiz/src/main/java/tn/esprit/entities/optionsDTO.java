package tn.esprit.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

public class optionsDTO {
@Getter @Setter
@JsonProperty("optionId")
    private Long optionId;
    @Getter @Setter
    @JsonProperty("optionText")
    private String optionText;
    @Getter @Setter @JsonProperty("isCorrectOption")
    private boolean isCorrectOption;
}
