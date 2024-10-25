package io.sarra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service

public interface ForumService {
    public List<Forum> getAllForums();
    public Forum getForumById(Long id);
    public Forum saveForum(Forum forum);
    public void deleteForum(Long idForum) ;
    public Forum updateForum(Forum forum);
    public Forum likePost(Long postId);
    public Forum dislikePost(Long idForum);
    public List<Forum> getPostsByTitle(String title);
    public List<Forum> getPostsByBody(String body);

    public Page<Forum> getPostsByTitle(String title, Pageable pageable);
    public  List<ForumType> countByDate( );

}
