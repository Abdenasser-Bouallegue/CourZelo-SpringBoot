package io.kamelbenarous.repository;

import io.kamelbenarous.entity.EducationalProgram;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EducationalProgramRepository extends MongoRepository<EducationalProgram, String> {
}
