package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.entities.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option,Long> {
}
