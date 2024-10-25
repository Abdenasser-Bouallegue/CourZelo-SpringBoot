package io.medali.controller;

import io.medali.dto.*;
import io.medali.entity.Role;
import io.medali.entity.SocialProfiles;
import io.medali.entity.User;
import io.medali.service.SocialProfilesService;
import io.medali.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {


    private final UserService userServiceI;
    private final SocialProfilesService socialProfilesService;

    @GetMapping("/curentuser")
    public ResponseEntity<UserResponse> currentUser(Principal connectedUser){
        return userServiceI.currentUser(connectedUser);
    }
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<UserResponse> findbyid(@PathVariable Integer id){
        return userServiceI.Findbyid(id);
    }
    @GetMapping("/findbyName/{name}")
    public ResponseEntity<io.medali.dto.User> findbyName(@PathVariable String name){
        return userServiceI.getUserByUserName(name);
    }
    @PostMapping("/acceptQuest/{idQuest}")
    public void acceptQuest(@PathVariable Integer idQuest){
        userServiceI.acceptQuest(idQuest);
    }
    @PostMapping ("/rejecterQuest/{idQuest}")
    public void rejecterQuest(@PathVariable Integer idQuest){
        userServiceI.rejectQuest(idQuest);
    }

    @PostMapping("/changepass")
    public ResponseEntity<?> changePassword(
          @RequestBody ChangePasswordRequest request,
          Principal connectedUser
    ) {
       return userServiceI.changePassword(request, connectedUser);
    }


    @GetMapping("/allUser")
    public ResponseEntity<List<UserResponse>> get() {
        List<User> users = userServiceI.findall();
        List<UserResponse> userResponses = users.stream()
                .map(user -> {
                    UserResponse userResponse = new UserResponse();
                    userResponse.setId(user.getId());
                    userResponse.setFirstname(user.getFirstname());
                    userResponse.setLastname(user.getLastname());
                    userResponse.setEmail(user.getEmail());
                    userResponse.setBirthday(user.getBirthday());
                    userResponse.setPhoneNumber(user.getPhoneNumber());
                    userResponse.setRole(user.getRole());
                    userResponse.setRegistrationNumber(user.getRegistrationNumber());
                    userResponse.setNcin(user.getNcin());
                    userResponse.setBlocking(String.valueOf(user.isBlocking()));
                    userResponse.setImage(user.getImage());
                    userResponse.setCompany(user.getCompany());
                    userResponse.setBirthday(user.getBirthday());

                    return userResponse;
                })
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userResponses);
    }

    @PutMapping("/userBlocking/{id}")
    public void bloquerUser(@PathVariable Integer id) {
        userServiceI.userblocking(id);
    }
    @PutMapping("/deblocafeuser/{id}")
    public void debloqueruser(@PathVariable Integer id) {
        userServiceI.debloqueUser(id);
    }


    @DeleteMapping("delete/{id}")
    public  void DeleteUser(@PathVariable Integer id) {
        userServiceI.DeleteUser(id);
    }
    @PutMapping("updateUser/{userId}")
    public void updateUser(@PathVariable Integer userId, @RequestBody User updatedUser) {
        userServiceI.modifierUser(userId,updatedUser);
    }

    @PostMapping("disable-2fa/{id}")
    public ResponseEntity<Void> disable2fa(@PathVariable("id") Integer id) {
        userServiceI.disable2ta(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("enable-2fa/{id}")
    public TfaEnable enable2fa(@PathVariable("id") Integer id) {

        return  userServiceI.enable2ta(id);
    }
    @GetMapping("/countByRole")
    public ResponseEntity<Map<Role, Long>> getUsersCountByRole() {
        Map<Role, Long> userCountByRole = userServiceI.getUsersCountByRole();
        return ResponseEntity.ok(userCountByRole);
    }
    @GetMapping("/findallUsersByRole/{role}")
    public ResponseEntity<List<UserResponse>> getStudent(@PathVariable("role") Role role) {
        List<User> users = userServiceI.findallUsersByRole(role);
        List<UserResponse> userResponses = users.stream()
                .map(user -> {
                    UserResponse userResponse = new UserResponse();
                    userResponse.setId(user.getId());
                    userResponse.setFirstname(user.getFirstname());
                    userResponse.setLastname(user.getLastname());
                    userResponse.setEmail(user.getEmail());
                    userResponse.setBirthday(user.getBirthday());
                    userResponse.setPhoneNumber(user.getPhoneNumber());
                    userResponse.setRole(user.getRole());
                    userResponse.setRegistrationNumber(user.getRegistrationNumber());
                    userResponse.setNcin(user.getNcin());
                    userResponse.setImage(user.getImage());
                    userResponse.setCompany(user.getCompany());
                    userResponse.setBirthday(user.getBirthday());

                    return userResponse;
                })
                .collect(Collectors.toList());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userResponses);
    }

    @GetMapping("/allUserProfil/{role}")
    public ResponseEntity<List<AllUserProfil>> getUsersProfil(@PathVariable("role") Role role) {
        List<User> users = userServiceI.findallUsersByRole( role);
        List<AllUserProfil> allUserProfiles = new ArrayList<>();



        for (User user : users) {
            AllUserProfil userProfile = new AllUserProfil();
            userProfile.setId(user.getId());
            userProfile.setFirstname(user.getFirstname());
            userProfile.setLastname(user.getLastname());
            userProfile.setEmail(user.getEmail());
            userProfile.setBirthday(user.getBirthday());
            userProfile.setPhoneNumber(user.getPhoneNumber());
            userProfile.setRegistrationNumber(user.getRegistrationNumber());
            userProfile.setNcin(user.getNcin());
            userProfile.setImage(user.getImage());
            userProfile.setCompany(user.getCompany());

            // Fetch social profile based on user id
            SocialProfiles socialProfile = socialProfilesService.findByUserId(user.getId());
            if (socialProfile != null) {
                userProfile.setTwitter(socialProfile.getTwitter());
                userProfile.setFacebook(socialProfile.getFacebook());
                userProfile.setInstagram(socialProfile.getInstagram());
                userProfile.setLinkedin(socialProfile.getLinkedin());
                userProfile.setYoutube(socialProfile.getYoutube());
            }

            allUserProfiles.add(userProfile);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allUserProfiles);
    }

}
