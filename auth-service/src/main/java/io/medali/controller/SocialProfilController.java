package io.medali.controller;

import io.medali.entity.Quest;
import io.medali.entity.SocialProfiles;
import io.medali.service.QuestService;
import io.medali.service.SocialProfilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/socialprofil")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SocialProfilController {
    private final SocialProfilesService socialProfilesService;


    @PostMapping("/add")
    public SocialProfiles addQuest(@RequestBody SocialProfiles socialProfiles){
        return socialProfilesService.saveProfil(socialProfiles);
    }
}
