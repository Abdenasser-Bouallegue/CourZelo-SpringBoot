package io.medali.service;

import io.medali.entity.Quest;


import java.util.List;

public interface QuestService {
     List<Quest> findAll();
     void deleteQuest(Integer id);
    Quest saveQuest(Quest quest);
}
