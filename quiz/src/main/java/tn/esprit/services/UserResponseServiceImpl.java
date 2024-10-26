package tn.esprit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.entities.Response;
import tn.esprit.repositories.ResponseRepository;

import java.util.List;

@Service
public class UserResponseServiceImpl implements UserReponseService{
    @Autowired
    private ResponseRepository userResponseRepository;

    @Override
    public Response getUserResponseById(Long responseId) {
        return userResponseRepository.findById(responseId).orElse(null);
    }

    @Override
    public void saveUserResponse(Response userResponse) {
        userResponseRepository.save(userResponse);
    }

    @Override
    public void updateUserResponse(Response userResponse) {
        userResponseRepository.save(userResponse);
    }

    @Override
    public void deleteUserResponse(Long responseId) {
        userResponseRepository.deleteById(responseId);
    }


}
