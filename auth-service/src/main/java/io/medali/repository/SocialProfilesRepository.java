package io.medali.repository;

import io.medali.entity.SocialProfiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialProfilesRepository extends JpaRepository<SocialProfiles, Integer> {
    SocialProfiles findByIduser(Integer id);
}
