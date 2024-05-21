package tn.esprit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.entities.Option;
import tn.esprit.repositories.OptionRepository;

@Service
public class OptionServiceImpl implements OptionService{
    @Autowired
    private OptionRepository optionRepository;

    @Override
    public Option getOptionById(Long optionId) {
        return optionRepository.findById(optionId).orElse(null);
    }

    @Override
    public void saveOption(Option option) {
        optionRepository.save(option);
    }

    @Override
    public void updateOption(Option option) {
        optionRepository.save(option);
    }

    @Override
    public void deleteOption(Long optionId) {
        optionRepository.deleteById(optionId);
    }


}
