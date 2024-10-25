package io.wiem.service.Impl;


import io.wiem.dto.UserResponse;
import io.wiem.entity.Absence;
import io.wiem.repository.AbsenceRepository;
import io.wiem.service.AbsenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AbsenceServiceImpl implements AbsenceService {
    private final AbsenceRepository absenceRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public List<Absence> findall() {
        return absenceRepository.findAll() ;
    }

    @Override
    public void addAbsence(List<Integer> E) {
            for (int i :E ) {
                try{
                    UserResponse[] response = webClientBuilder.build().get()
                            .uri("http://auth-service/api/v1/users/findbyid/" + i)
                            .retrieve()
                            .bodyToMono(UserResponse[].class)
                            .block();
                    Absence absence=new Absence();
                    absence.setEmail(Arrays.stream(response).findFirst().get().getEmail());
                    absence.setEmail(Arrays.stream(response).findFirst().get().getNcin());
                    absence.setEmail(Arrays.stream(response).findFirst().get().getLastname());
                    absence.setEmail(Arrays.stream(response).findFirst().get().getFirstname());
                    absence.setEmail(Arrays.stream(response).findFirst().get().getRegistrationNumber());
                    absence.setEmail(String.valueOf(Arrays.stream(response).findFirst().get().getPhoneNumber()));
                    absenceRepository.save(absence);





                    System.out.println(response);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
        }

    @Override
    public List<Absence> saveSelectedUsers(List<Absence> selectedUsers) {
        Date currentDate = new Date();
        for (Absence user : selectedUsers) {
            user.setDate(currentDate);
        }
        return absenceRepository.saveAll(selectedUsers);
    }
}

