package tn.esprit.services;

import org.springframework.stereotype.Service;
import tn.esprit.entities.Response;

import java.util.List;


public interface UserReponseService {
    Response getUserResponseById(Long responseId);
    void saveUserResponse(Response userResponse);
    void updateUserResponse(Response userResponse);
    void deleteUserResponse(Long responseId);

}
