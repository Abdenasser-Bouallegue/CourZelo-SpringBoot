package io.medali.service;

import io.medali.entity.SocialProfiles;
import io.medali.entity.User;

import java.util.List;

public interface SocialProfilesService {
    SocialProfiles saveProfil(SocialProfiles socialProfiles);
    public SocialProfiles findByUserId(Integer id);
}
