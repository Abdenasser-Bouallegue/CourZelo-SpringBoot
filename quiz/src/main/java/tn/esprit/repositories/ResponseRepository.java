package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.entities.Response;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {

}
