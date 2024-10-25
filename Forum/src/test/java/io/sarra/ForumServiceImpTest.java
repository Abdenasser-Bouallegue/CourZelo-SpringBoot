package io.sarra;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ForumServiceImpTest {

    @Mock
    private ForumRepository forumRepository;

    @InjectMocks
    private ForumServiceImp forumService;

    private Forum forum;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        forum = Forum.builder()
                .idForum(1L)
                .title("Test Title")
                .body("Test Body")
                .likes(0)
                .dislikes(0)
                .build();
    }

    @Test
    void getAllForums() {
        when(forumRepository.findAll()).thenReturn(Arrays.asList(forum));

        List<Forum> forums = forumService.getAllForums();

        assertNotNull(forums);
        assertEquals(1, forums.size());
        verify(forumRepository, times(1)).findAll();
    }

    @Test
    void getForumById() {
        when(forumRepository.findById(1L)).thenReturn(Optional.of(forum));

        Forum foundForum = forumService.getForumById(1L);

        assertNotNull(foundForum);
        assertEquals(forum.getIdForum(), foundForum.getIdForum());
        verify(forumRepository, times(1)).findById(1L);
    }

    @Test
    void saveForum() {
        when(forumRepository.save(forum)).thenReturn(forum);

        Forum savedForum = forumService.saveForum(forum);

        assertNotNull(savedForum);
        assertEquals(forum.getTitle(), savedForum.getTitle());
        verify(forumRepository, times(1)).save(forum);
    }

    @Test
    void deleteForum() {
        doNothing().when(forumRepository).deleteById(1L);

        forumService.deleteForum(1L);

        verify(forumRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateForum() {
        when(forumRepository.save(forum)).thenReturn(forum);

        Forum updatedForum = forumService.updateForum(forum);

        assertNotNull(updatedForum);
        assertEquals(forum.getTitle(), updatedForum.getTitle());
        verify(forumRepository, times(1)).save(forum);
    }

    @Test
    void likePost() {
        when(forumRepository.findById(1L)).thenReturn(Optional.of(forum));
        forum.setLikes(forum.getLikes() + 1);
        when(forumRepository.save(forum)).thenReturn(forum);

        Forum likedForum = forumService.likePost(1L);

        assertEquals(1, likedForum.getLikes());
        verify(forumRepository, times(1)).findById(1L);
        verify(forumRepository, times(1)).save(forum);
    }

    @Test
    void dislikePost() {
        when(forumRepository.findById(1L)).thenReturn(Optional.of(forum));
        forum.setDislikes(forum.getDislikes() + 1);
        when(forumRepository.save(forum)).thenReturn(forum);

        Forum dislikedForum = forumService.dislikePost(1L);

        assertEquals(1, dislikedForum.getDislikes());
        verify(forumRepository, times(1)).findById(1L);
        verify(forumRepository, times(1)).save(forum);
    }

    @Test
    void getPostsByTitle() {
        when(forumRepository.findByTitleContaining("Test")).thenReturn(Arrays.asList(forum));

        List<Forum> forums = forumService.getPostsByTitle("Test");

        assertNotNull(forums);
        assertEquals(1, forums.size());
        verify(forumRepository, times(1)).findByTitleContaining("Test");
    }

    @Test
    void getPostsByBody() {
        when(forumRepository.findByBodyContaining("Test Body")).thenReturn(Arrays.asList(forum));

        List<Forum> forums = forumService.getPostsByBody("Test Body");

        assertNotNull(forums);
        assertEquals(1, forums.size());
        verify(forumRepository, times(1)).findByBodyContaining("Test Body");
    }

    @Test
    void testGetPostsByTitleWithPagination() {
        // Add Pageable logic and tests if needed
    }

    @Test
    void countByDate() {
        // Simulate your custom logic and assert results if needed
    }
}
