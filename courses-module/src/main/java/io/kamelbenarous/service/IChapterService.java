package io.kamelbenarous.service;

import io.kamelbenarous.dto.ResourceDTO;
import io.kamelbenarous.entity.Chapter;
import io.kamelbenarous.entity.Course;
import io.kamelbenarous.entity.ResourceType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IChapterService {
    List<Chapter> getAllChapters();
    Chapter getChapterById(String chapterId);
    Chapter addChapter(Chapter chapter);
    Chapter update(Chapter chapter);
    int deleteChapter(String chapterId);
    String saveChapterResource(String context, ResourceType type, MultipartFile dto, String chapterId) throws IOException;

}
