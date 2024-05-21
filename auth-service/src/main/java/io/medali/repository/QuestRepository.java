package io.medali.repository;

import io.medali.entity.Quest;
import io.medali.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestRepository extends JpaRepository<Quest,Integer> {
    Optional<Quest> findByEmail(String email);

}
