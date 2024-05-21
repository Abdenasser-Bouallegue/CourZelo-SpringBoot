package io.kamelbenarous.controller;

import io.kamelbenarous.dto.ScrapingResponseDTO;
import io.kamelbenarous.service.ScraperService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ScraperController {
    ScraperService scraperService;

    @GetMapping("/scraptechnologiespweasesmileyface")
    public List<ScrapingResponseDTO> getTechs() {
        return  scraperService.getTrendingTechs();
    }
}
