package io.medali.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TfaEnable {
    private String secretImageUri;

}
