package io.kamelbenarous.service;

import io.kamelbenarous.entity.MediaSource;
import io.kamelbenarous.entity.MediaSourceType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IMediaSourceService {
    long addMediaSource(MultipartFile multipartFile, String context, MediaSourceType type, String chapterId) throws IOException;
    MediaSource getMediaSource(String context);
    MediaSource getMediaSource(long id);
    List<Long> getAllMediaSourceId();
    List<Long> getChapterMediaSourceId(String chapterId);
    void deleteMediaSource(long mediaSourceId);
    List<String> getChapterMediaSourceContext(String chapterId);

}
