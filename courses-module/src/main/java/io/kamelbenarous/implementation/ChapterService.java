package io.kamelbenarous.implementation;

import io.kamelbenarous.dto.ResourceDTO;
import io.kamelbenarous.dto.ResourceMetaDataDTO;
import io.kamelbenarous.entity.Chapter;
import io.kamelbenarous.entity.Course;
import io.kamelbenarous.entity.ResourceType;
import io.kamelbenarous.repository.ChapterRepository;
import io.kamelbenarous.repository.CourseRepository;
import io.kamelbenarous.repository.EducationalProgramRepository;
import io.kamelbenarous.service.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;

@Service
public class ChapterService implements IChapterService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Qualifier("webApplicationContext")
    @Autowired
    private ResourceLoader resourceLoader;
    private final ChapterRepository chapterRepository;

    public ChapterService(
            ChapterRepository chapterRepository

    ) {
        this.chapterRepository = chapterRepository;

    }
    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    public Chapter getChapterById(String chapterId) {
        Chapter chapter = chapterRepository.findById(chapterId).orElse(null);
        if(chapter != null) {
            try{
                String[] response = webClientBuilder.build().get()
                        .uri("http://media-source-service/resources/getcontext/" + chapterId)
                        .retrieve()
                        .bodyToMono(String[].class)
                        .block();
                List<String> ls = new ArrayList<>(Arrays.asList(response));
                chapter.setResourseRefList(ls);
            }catch(Exception e){
                System.out.println(e);
            }
        }

        return chapter;
    }

    public Chapter addChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    public Chapter update(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    public int deleteChapter(String chapterId) {
        Chapter chapter = chapterRepository.findById(chapterId).orElse(null);
        if (chapter != null) {
            chapterRepository.delete(chapter);
            return 1;
        }
        return 0;
    }


    @Override
    public String saveChapterResource(String context, ResourceType type, MultipartFile dto, String chapterId) throws IOException {

        Resource resource = resourceLoader.getResource("classpath:MediaSourceServicesHostingAddresses.txt");
        boolean isServerAvailable = false;
        try {
            System.out.println("pinging");
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);

            while(scanner.hasNextLine()){
                String[] address = scanner.nextLine().split(":");
                if(pingServer(address[0], Integer.parseInt(address[1]))){
                    isServerAvailable = true;
                }
            }
            System.out.println("ping is done");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (isServerAvailable) {
            System.out.println("server is available");
            ResourceMetaDataDTO out = new ResourceMetaDataDTO();
            out.setContext(context);
            out.setType(type);
            out.setChapterId(chapterId);

            byte[] byteArray = dto.getBytes();
            String response = webClientBuilder.build().post()
                    .uri("http://media-source-service/" + chapterId, uriBuilder -> {

                            return uriBuilder
                                    .queryParam("context", out.getContext())
                                    .queryParam("type", out.getType())
                                    .build();

                    })
                    .bodyValue(new ByteArrayResource(byteArray))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            return response;
        }else{
            return "No Media Server available";
        }
    }

    private static boolean pingServer(String host, int port) {
        System.out.println(host + ":" + port);
        String timeStamp = "";
        Socket socket = null;
        BufferedReader br = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), 5000);
            return true;
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception for debugging purposes
            return false;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace(); // Log the exception for debugging purposes
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace(); // Log the exception for debugging purposes
                }
            }
        }
    }


}
