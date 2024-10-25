package io.medali.controller;

import io.medali.entity.Quest;
import io.medali.service.QuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/quest")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuestController {
    private final QuestService questService;
//    private final UserService userServiceI;

    //**********Quest********//

    @PostMapping("/add")
    public Quest addQuest(@RequestBody Quest quest){
        return questService.saveQuest(quest);
    }

    @GetMapping("/all")
    public List<Quest> getAll() {
        return questService.findAll();
    }





}
