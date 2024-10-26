package io.medali.service.Impl;

import io.medali.entity.SocialProfiles;
import io.medali.repository.SocialProfilesRepository;
import io.medali.service.SocialProfilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialProfilesServiceImpl implements SocialProfilesService {
    private final SocialProfilesRepository socialProfilesRepository;

    @Override
    public SocialProfiles saveProfil(SocialProfiles socialProfiles) {

        return socialProfilesRepository.save(socialProfiles) ;
    }

    @Override
    public SocialProfiles findByUserId(Integer id) {
        return socialProfilesRepository.findByIduser(id);
    }
}
