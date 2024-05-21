package io.medali.service.Impl;

import io.medali.entity.Quest;
import io.medali.repository.QuestRepository;
import io.medali.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestServiceImpl implements QuestService {
    private final QuestRepository questRepository;


    @Override
    public List<Quest> findAll() {
        return questRepository.findAll();
    }

    @Override
    public void deleteQuest(Integer id) {
        questRepository.deleteById(id);
    }

    @Override
    public Quest saveQuest(Quest quest) {
        return questRepository.save(quest);
    }
}
