package io.kamelbenarous.repository;

import io.kamelbenarous.entity.MediaSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaSourceRepository extends JpaRepository<MediaSource, Long> {
    MediaSource getByContext(String context);
    @Query(value = "select id from MediaSource", nativeQuery = true)
    List<Long> getAllMediaSourceId();

    @Query("select id from MediaSource where chapterId like chapterId")
    List<Long> getChapterMediaSourceId(String chapterId);

    List<MediaSource> getAllByChapterId(String chapterId);
}
