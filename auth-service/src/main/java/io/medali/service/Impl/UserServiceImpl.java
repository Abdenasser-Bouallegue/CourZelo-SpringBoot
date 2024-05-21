package io.medali.service.Impl;

import io.medali.config.JwtService;
import io.medali.dto.ChangePasswordRequest;
import io.medali.dto.TfaEnable;
import io.medali.dto.UpdateUserRequest;
import io.medali.dto.UserResponse;
import io.medali.entity.Quest;
import io.medali.entity.Role;
import io.medali.entity.User;
import io.medali.repository.QuestRepository;
import io.medali.repository.UserRepository;
import io.medali.service.EmailService;
import io.medali.service.UserService;
import io.medali.tfa.TwoFactorAuthenticationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final QuestRepository questRepository;
    private final JwtService jwtService;
    private final EmailService emailService;
    private final TwoFactorAuthenticationService tfaService;


    public ResponseEntity<UserResponse> changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user1 = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user1.getId());
        userResponse.setFirstname(user1.getFirstname());
        userResponse.setLastname(user1.getLastname());
        userResponse.setEmail(user1.getEmail());
        userResponse.setBirthday(user1.getBirthday());
        userResponse.setPhoneNumber(user1.getPhoneNumber());
        userResponse.setRole(user1.getRole());
        userResponse.setRegistrationNumber(user1.getRegistrationNumber());
        userResponse.setNcin(user1.getNcin());
        userResponse.setCompany(user1.getCompany());
        userResponse.setBirthday(user1.getBirthday());
        userResponse.setImage(user1.getImage());
        if (!passwordEncoder.matches(request.getCurrentPassword(), user1.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        user1.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user1);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userResponse);
    }

    public List<User> findall() {
        return userRepository.findAll();
    }

    @Override
    public void DeleteUser(Integer id) {
        userRepository.deleteById(id);
    }


    @Override
    public void modifierUser(Integer id, User user) {
        User existingUser = userRepository.findById(id).get();

        existingUser.setLastname(user.getLastname());
        existingUser.setFirstname(user.getFirstname());
        existingUser.setRegistrationNumber(user.getRegistrationNumber());
        existingUser.setCompany(user.getCompany());
        existingUser.setEmail(user.getEmail());
        existingUser.setNcin(user.getNcin());
        existingUser.setImage(user.getImage());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setBirthday(user.getBirthday());
        userRepository.save(existingUser);
    }

    @Transactional
    public void userblocking(Integer ID) {
        userRepository.userBlocage(ID);
    }

    @Transactional
    public void debloqueUser(Integer ID) {
        userRepository.deblocageuser(ID);
    }

    @Override
    public void disable2ta(Integer ID) {
        User user = userRepository.findById(ID).orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + ID));
        user.setMfaEnabled(false);
        user.setSecret(null);
        userRepository.save(user);
    }

    @Override
    public TfaEnable enable2ta(Integer ID) {
        User user = userRepository.findById(ID).get();
        user.setMfaEnabled(true);
        user.setSecret(tfaService.generateNewSecret());
        userRepository.save(user);

        TfaEnable tfaEnable = new TfaEnable();
        tfaEnable.setSecretImageUri(tfaService.generateQrCodeImageUri(user.getSecret()));
        ;
        return TfaEnable.builder().secretImageUri(tfaService.generateQrCodeImageUri(user.getSecret())).build();
    }


    @Override
    public ResponseEntity<UserResponse> currentUser(Principal connectedUser) {
        var user1 = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();


        UserResponse userResponse = new UserResponse();
        userResponse.setId(user1.getId());
        userResponse.setFirstname(user1.getFirstname());
        userResponse.setLastname(user1.getLastname());
        userResponse.setEmail(user1.getEmail());
        userResponse.setBirthday(user1.getBirthday());
        userResponse.setPhoneNumber(user1.getPhoneNumber());
        userResponse.setRole(user1.getRole());
        userResponse.setRegistrationNumber(user1.getRegistrationNumber());
        userResponse.setNcin(user1.getNcin());
        userResponse.setCompany(user1.getCompany());
        userResponse.setBirthday(user1.getBirthday());
        userResponse.setImage(user1.getImage());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userResponse);
    }

    @Override
    public Map<Role, Long> getUsersCountByRole() {
        List<User> allUsers = userRepository.findAll();
        Map<Role, Long> userCountByRole = allUsers.stream()
                .collect(Collectors.groupingBy(User::getRole, Collectors.counting()));
        return userCountByRole;
    }

    @Override
    public List<User> findallUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public ResponseEntity<UserResponse> Findbyid(Integer id) {
            User user1=userRepository.findById(id).orElse(null);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user1.getId());
        userResponse.setFirstname(user1.getFirstname());
        userResponse.setLastname(user1.getLastname());
        userResponse.setEmail(user1.getEmail());
        userResponse.setBirthday(user1.getBirthday());
        userResponse.setPhoneNumber(user1.getPhoneNumber());
        userResponse.setRegistrationNumber(user1.getRegistrationNumber());
        userResponse.setNcin(user1.getNcin());
        userResponse.setCompany(user1.getCompany());
        userResponse.setBirthday(user1.getBirthday());
        userResponse.setImage(user1.getImage());
         return ResponseEntity
            .status(HttpStatus.OK)
            .body(userResponse);

       }

    @Override
    public ResponseEntity<io.medali.dto.User> getUserByUserName(String username) {
        User user1=userRepository.findByFirstname(username);
        io.medali.dto.User userResponse = new io.medali.dto.User();
        userResponse.setUserName(user1.getFirstname());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userResponse);

    }

    public void acceptQuest(Integer idQuest) {
        Quest quest=questRepository.findById(idQuest).get();

        var user = User.builder()
                .firstname(quest.getFirstname())
                .lastname(quest.getLastname())
                .email(quest.getEmail())
                .password(passwordEncoder.encode(quest.getNcin()))
                .role(quest.convertToRole())
                .image(quest.getImage())
                .phoneNumber(quest.getPhoneNumber())
                .registrationNumber(quest.getRegistrationNumber())
                .ncin(quest.getNcin())
                .mfaEnabled((quest.isMfaEnabled()))
                .secret(quest.getSecret())
                .build();
        var savedUser = userRepository.save(user);
        questRepository.deleteById(quest.getId());
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        String to = user.getEmail();
        String subject = "Welcome to Corzelo - Your E-Learning Platform";
        String text = "Dear " +user.getFirstname()+",\n\n"
                + "We are delighted to welcome you to Corzelo, your new e-learning platform. Congratulations on taking the first step towards advancing your education!\n\n"
                + "At Corzelo, we are committed to providing you with high-quality educational resources, interactive courses, and a supportive learning community to help you achieve your learning goals.\n\n"
                + "As a member of Corzelo, you will have access to a wide range of courses covering various subjects, including programming, business, design, and more. Whether you're a beginner or an experienced professional, there's something for everyone.\n\n"
                + "To get started, simply log in to your Corzelo account using the credentials below:\n\n"
                + "Email: " + user.getEmail() + "\n"
                + "Password: " + user.getNcin() + "\n\n"
                + "Once logged in, you can explore our course catalog, enroll in courses, participate in discussions, and track your progress.\n\n"
                + "If you have any questions or need assistance, our support team is here to help. Feel free to reach out to us at support@corzelo.com.\n\n"
                + "Thank you for choosing Corzelo as your learning partner. We look forward to embarking on this educational journey together!\n\n"
                + "Best regards,\n"
                + "The Corzelo Team";
        emailService.sendSimpleMessage(to, subject, text);
    }

    @Override
    public void rejectQuest(Integer idQuest) {
        Quest quest=questRepository.findById(idQuest).get();



        String to = quest.getEmail();
        String subject = "Your Application to Corzelo - E-Learning Platform";
        String text = "Dear " + quest.getFirstname() + ",\n\n"
                + "We regret to inform you that your application to join Corzelo, our e-learning platform, has been rejected.\n\n"
                + "While we appreciate your interest in joining our platform, we have determined that your application does not meet our current criteria for membership.\n\n"
                + "We encourage you to explore other educational opportunities that may better suit your needs and qualifications.\n\n"
                + "Thank you for considering Corzelo, and we wish you the best of luck in your future endeavors.\n\n"
                + "If you have any questions or would like further information regarding the status of your application, please don't hesitate to contact us at support@corzelo.com.\n\n"
                + "Best regards,\n"
                + "The Corzelo Team";

        emailService.sendSimpleMessage(to, subject, text);
        questRepository.deleteById(idQuest);

    }

}
