package io.kamelbenarous.controller;

import io.kamelbenarous.dto.ChapterDTO;
import io.kamelbenarous.dto.ResourceDTO;
import io.kamelbenarous.entity.Chapter;
import io.kamelbenarous.entity.Course;
import io.kamelbenarous.entity.ResourceType;
import io.kamelbenarous.implementation.ChapterService;
import io.kamelbenarous.implementation.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class ChapterController {
    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("/chapters/getAll")
    public ResponseEntity<List<ChapterDTO>> getAllChapters() {
        List<ChapterDTO> ls = new ArrayList<>();
        ChapterDTO dto = new ChapterDTO();
        for (Chapter chapter: chapterService.getAllChapters()) {
            dto.setId(chapter.getId());
            dto.setTitle(chapter.getTitle());
            dto.setDescription(chapter.getDescription());
            dto.setResources(chapter.getResourseRefList());
        }
        return ResponseEntity.status(HttpStatus.SC_OK)
                .body(ls);
    }

    @GetMapping("/chapters/{chapterId}")
    public ResponseEntity<ChapterDTO> getChapterById(@PathVariable String chapterId) {
        try {
            Chapter chapter = chapterService.getChapterById(chapterId);
            ChapterDTO dto = new ChapterDTO();

            dto.setId(chapter.getId());
            dto.setTitle(chapter.getTitle());
            dto.setDescription(chapter.getDescription());
            dto.setResources(chapter.getResourseRefList());
            return ResponseEntity.status(HttpStatus.SC_OK)
                    .body(dto);
        } catch (NullPointerException nullPointerException) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        }
    }
    @PostMapping("/chapters/addChapter")
    public ResponseEntity<String> addChapter(@RequestBody Chapter chapter) {
        Chapter c = this.chapterService.addChapter(chapter);
        if (c != null) {
            return ResponseEntity.status(HttpStatus.SC_CREATED).body("chapter created");
        } else {
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("can not create chapter");
        }
    }
    @PutMapping("/chapters/update")
    public ResponseEntity<Chapter> update(@RequestBody Chapter chapter) {
        return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body(chapterService.update(chapter));
    }
    @DeleteMapping("/chapters/{id}")
    public int deleteChapter(@PathVariable("id") String chapterId) {
        return chapterService.deleteChapter(chapterId);
    }

    @PostMapping("/chapters/addchapterresource/{chapterid}")
    public ResponseEntity<String> addResourceToChapter(
        @PathVariable("chapterid") String chapterId,
        @RequestParam("context") String context,
        @RequestParam("type") String type,
        @RequestParam("file") MultipartFile file
    ) throws IOException {
        log.info("executing request");
        return ResponseEntity.ok(chapterService.saveChapterResource(context, ResourceType.valueOf(type), file, chapterId));
    }

}
