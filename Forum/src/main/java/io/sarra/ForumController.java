package io.sarra;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/myforum")
public class ForumController {
    @Autowired
    private ForumService forumService;

    @GetMapping("/getAllForum")
    public List<Forum> getAllForums() {
        return forumService.getAllForums();
    }

    @GetMapping("/getForumById/{id}")
    public Forum getForumById(@PathVariable Long id) {
        return forumService.getForumById(id);
    }


    @PostMapping("/addForum")
    public Forum createForum(
            @RequestParam("title") String title,
            @RequestParam("body") String body,
            @RequestParam("image") MultipartFile file) throws IOException {

        Forum forum = new Forum();
        forum.setImage(file.getBytes());
        forum.setTitle(title);
        forum.setBody(body);
        return forumService.saveForum(forum);
    }

    @PutMapping("/update")
    public Forum update( @RequestBody Forum forum ){
        return forumService.updateForum(forum);
    }

    @DeleteMapping("/remove/{forum-id}")
    public void removeForum(@PathVariable("forum-id")Long idForum){
        forumService.deleteForum(idForum);
    }

    @PostMapping("/like/{forum-id}")

    public Forum likePost(@PathVariable("forum-id")Long postId){
        return forumService.likePost(postId);
    }

    @PostMapping("/dislike/{forum-id}")


    public Forum dislikePost(@PathVariable("forum-id")Long idForum){
        return forumService.dislikePost(idForum);
    }

    @GetMapping ("/filter/{title}")
    public List<Forum> getPostsByTitle(@PathVariable String title) {
        return forumService.getPostsByTitle(title);
    }

    @GetMapping ("/filterb/{body}")
    public List<Forum> getPostsByBody(@PathVariable String body) {
        return forumService.getPostsByBody(body);
    }

    @GetMapping("/pagination")
    public Page<Forum> getPostsByTitle(@RequestParam String title, Pageable pageable) {
        return forumService.getPostsByTitle(title, pageable);
    }


    @GetMapping("/countbydate")
    public List<ForumType> countByDate() {
        return forumService.countByDate();
    }
    }
