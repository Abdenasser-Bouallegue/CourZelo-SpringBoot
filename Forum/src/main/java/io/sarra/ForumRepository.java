package io.sarra;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {
    List<Forum> findByTitleContaining(String title);
    List<Forum> findByBodyContaining(String body);
    Page<Forum> findByTitleContaining(String title, Pageable pageable);

    @Query("SELECT NEW io.sarra.ForumType(COUNT(f), f.createDate) FROM Forum f group by f.createDate ")
    List<ForumType> countByDate();


}
