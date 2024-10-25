package io.kamelbenarous.service;

import io.kamelbenarous.dto.ScrapingResponseDTO;

import java.util.List;

public interface ScraperService {
    List<ScrapingResponseDTO> getTrendingTechs();

}
