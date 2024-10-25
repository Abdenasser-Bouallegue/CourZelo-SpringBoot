package io.medali.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialProfileResponse {
    private Integer id;
    private Integer iduser;
    private String twitter;
    private String facebook;
    private String instagram;
    private String linkedin;
    private String youtube;
}
