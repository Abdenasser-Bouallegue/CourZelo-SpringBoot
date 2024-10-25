package tn.esprit.services;

import tn.esprit.entities.Option;

public interface OptionService {
    Option getOptionById(Long optionId);
    void saveOption(Option option);
    void updateOption(Option option);
    void deleteOption(Long optionId);
}
