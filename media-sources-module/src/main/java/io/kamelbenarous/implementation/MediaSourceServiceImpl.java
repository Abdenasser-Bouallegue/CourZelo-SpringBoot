package io.kamelbenarous.implementation;

import io.kamelbenarous.entity.MediaSource;
import io.kamelbenarous.entity.MediaSourceType;
import io.kamelbenarous.repository.MediaSourceRepository;
import io.kamelbenarous.service.IMediaSourceService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaSourceServiceImpl implements IMediaSourceService {

    private final MediaSourceRepository mediaSourceRepository;

    public MediaSourceServiceImpl(MediaSourceRepository mediaSourceRepository) {
        this.mediaSourceRepository = mediaSourceRepository;
    }

    /**
     * @param multipartFile
     * @param context
     * @param type
     * @param chapterId
     * @return
     */
    @Override
    public long addMediaSource(
            MultipartFile multipartFile,
            String context,
            MediaSourceType type,
            String chapterId
    ) throws IOException {
        MediaSource mediaSource = new MediaSource();
        mediaSource.setContext(context);
        mediaSource.setMediaSourceType(type);
        mediaSource.setFile(multipartFile.getBytes());
        mediaSource.setChapterId(chapterId);
        return mediaSourceRepository.save(mediaSource).getId();
    }

    /**
     * @param context
     * @return
     */
    @Override
    public MediaSource getMediaSource(String context) {
        return mediaSourceRepository.getByContext(context);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public MediaSource getMediaSource(long id) {
        return mediaSourceRepository.getById(id);
    }

    /**
     * @return
     */
    @Override
    public List<Long> getAllMediaSourceId() {
        return mediaSourceRepository.getAllMediaSourceId();
    }

    /**
     * @param chapterId
     * @return
     */
    @Override
    public List<Long> getChapterMediaSourceId(String chapterId) {
        return mediaSourceRepository.getChapterMediaSourceId(chapterId);
    }

    /**
     * @param mediaSourceId
     */
    @Override
    public void deleteMediaSource(long mediaSourceId) {
        mediaSourceRepository.deleteById(mediaSourceId);
    }

    @Override
    public List<String> getChapterMediaSourceContext(String chapterId) {
        return mediaSourceRepository.getAllByChapterId(chapterId)
                .stream()
                .map(MediaSource::getContext)
                .collect(Collectors.toList());
    }
}
