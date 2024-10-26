package io.kamelbenarous.implementation;

import io.kamelbenarous.dto.ScrapingResponseDTO;
import io.kamelbenarous.service.ScraperService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@ConfigurationProperties("website")
public class ScraperServiceImpl implements ScraperService {
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public List<ScrapingResponseDTO> getTrendingTechs() {
        List<ScrapingResponseDTO> ls = new ArrayList<>();

        for (String url: urls) {
            log.info(url);
            if(url.contains("geeksforgeeks")) getFromGeeksForGeeks(ls, url);
            if(url.contains("simplilearn")) getFromSimpliLearn(ls, url);
        }
        return ls;
    }

    private void getFromGeeksForGeeks(List<ScrapingResponseDTO> ls, String url){
        try {
            Document document = Jsoup.connect(url).get();
            Element element = document.getElementsByTag("ol").first();
            Elements elements = element.getElementsByTag("li");
            for (Element e : elements) {
                ScrapingResponseDTO dto = new ScrapingResponseDTO();
                dto.setLabel(e.getAllElements().first().text());
                ls.add(dto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getFromSimpliLearn(List<ScrapingResponseDTO> ls, String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.getElementsByTag("h3");
            for (Element e : elements){
                ScrapingResponseDTO dto = new ScrapingResponseDTO();
                dto.setLabel(e.text());
                ls.add(dto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
