package io.kamelbenarous.repository;

import io.kamelbenarous.entity.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChapterRepository extends MongoRepository<Chapter, String> {
}
