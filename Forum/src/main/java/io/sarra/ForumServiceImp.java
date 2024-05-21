package io.sarra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;


@Service
public class ForumServiceImp implements ForumService{
    @Autowired
    private ForumRepository forumRepository;


    public List<Forum> getAllForums() {
        return forumRepository.findAll();
    }

    public Forum getForumById(Long id) {
        return forumRepository.findById(id).orElse(null);
    }

    public Forum saveForum(Forum forum) {
        return forumRepository.save(forum);
    }

    public void deleteForum(Long idForum) {
        forumRepository.deleteById(idForum);
    }

    public Forum updateForum(Forum forum) {
       return forumRepository.save(forum);
    }
    public Forum likePost(Long postId) {
        Forum forum = forumRepository.findById(postId).orElse(null);
        if (forum != null) {
            forum.setLikes(forum.getLikes() + 1);
            return forumRepository.save(forum);
        }
        return null;
    }

    public Forum dislikePost(Long idForum) {
        Forum forum = forumRepository.findById(idForum).orElse(null);
        if (forum != null) {
            forum.setDislikes(forum.getDislikes() + 1);
            return forumRepository.save(forum);
        }
        return null;
    }

    public List<Forum> getPostsByTitle(String title) {
        return forumRepository.findByTitleContaining(title);
    }

    public List<Forum> getPostsByBody(String body) {
        return forumRepository.findByBodyContaining(body);
    }

    public Page<Forum> getPostsByTitle(String title, Pageable pageable) {
        return forumRepository.findByTitleContaining(title, pageable);
    }
    public List<ForumType> countByDate(  ){

        return forumRepository.countByDate();
    }

}
