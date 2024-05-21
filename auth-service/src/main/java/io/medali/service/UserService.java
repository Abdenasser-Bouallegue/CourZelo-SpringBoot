package io.medali.service;

import io.medali.dto.ChangePasswordRequest;
import io.medali.dto.TfaEnable;
import io.medali.dto.UpdateUserRequest;
import io.medali.dto.UserResponse;
import io.medali.entity.Role;
import io.medali.entity.User;
import org.springframework.http.ResponseEntity;


import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface UserService {
    public ResponseEntity<UserResponse> changePassword(ChangePasswordRequest request, Principal connectedUser);
    public List<User> findall();
    public void DeleteUser(Integer id);
    public void acceptQuest(Integer idQuest);
    public void rejectQuest(Integer idQuest);
//    void updateProfil(UpdateUserRequest user, Integer id);
    void modifierUser(Integer id,User user);
    public void userblocking(Integer ID);
    public void debloqueUser(Integer ID);
    public void disable2ta(Integer ID);
    public TfaEnable enable2ta(Integer ID);
    ResponseEntity<UserResponse> currentUser(Principal connectedUser);
    public Map<Role, Long> getUsersCountByRole();
    public List<User> findallUsersByRole(Role role);
    ResponseEntity<UserResponse> Findbyid(Integer id);
    ResponseEntity<io.medali.dto.User> getUserByUserName(String username);
}
