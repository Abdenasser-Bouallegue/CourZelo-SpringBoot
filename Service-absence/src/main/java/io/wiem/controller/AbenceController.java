package io.wiem.controller;


import io.wiem.entity.Absence;
import io.wiem.service.AbsenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/absence")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AbenceController {

    private final AbsenceService absenceService;
    @PostMapping("/add")
    public ResponseEntity<String> addQuest(@RequestBody List<Integer> userids){
        absenceService.addAbsence(userids);
        return ResponseEntity.ok("c bon bon");
    }

    @GetMapping("/all")
    public List<Absence> getAll() {
        return absenceService.findall();
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveSelectedUsers(@RequestBody List<Absence> selectedUsers) {
        absenceService.saveSelectedUsers(selectedUsers);
        return ResponseEntity.ok("Selected users saved successfully");
    }
}
