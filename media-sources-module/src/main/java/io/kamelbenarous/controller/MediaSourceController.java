package io.kamelbenarous.controller;

import io.kamelbenarous.dto.ResourceMetaDataDTO;
import io.kamelbenarous.entity.MediaSource;
import io.kamelbenarous.entity.MediaSourceType;
import io.kamelbenarous.service.IMediaSourceService;
import jakarta.ws.rs.QueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("")
public class MediaSourceController {
    private final IMediaSourceService iMediaSourceService;

    public MediaSourceController(IMediaSourceService iMediaSourceService) {
        this.iMediaSourceService = iMediaSourceService;
    }

    @PostMapping("/{chapterid}")
    public ResponseEntity<String> saveResource(
            @PathVariable("chapterid") String chapterId,
            @RequestParam("context") String context,
            @RequestParam("type") String type,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        log.info("getting video");
        iMediaSourceService.addMediaSource(file, context, MediaSourceType.valueOf(type), chapterId);
        return ResponseEntity.ok("Video saved successfully.");
    }

    // {name} is a path variable in the url. It is extracted as the String parameter annotated with @PathVariable
    @GetMapping("/resources/get/{context}")
    public ResponseEntity<ByteArrayResource> getResourceByContext(
            @PathVariable("context") String context
    ) {
        HttpHeaders headers = new HttpHeaders();
        MediaSource mediaSource = iMediaSourceService.getMediaSource(context);
        switch (mediaSource.getMediaSourceType()){
            case IMAGE -> {
                headers.setContentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE));
                headers.add("mediaType", "image");
            }
            case PDF -> {
                headers.setContentType(MediaType.parseMediaType("application/pdf"));
                headers.add("mediaType", "pdf");
            }
            case VIDEO -> {
                headers.setContentType(MediaType.parseMediaType("video/mp4"));
                headers.add("mediaType", "video");
            }
        }

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new ByteArrayResource(mediaSource.getFile()));
    }

    @GetMapping("/resources/getcontext/{chapterId}")
    public List<String> getChapterResourcesContext(
        @PathVariable("chapterId") String chapterId
    ) {
        return this.iMediaSourceService.getChapterMediaSourceContext(chapterId);
    }
}
