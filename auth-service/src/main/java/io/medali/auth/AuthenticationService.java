package io.medali.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.medali.config.JwtService;
import io.medali.entity.Quest;
import io.medali.repository.QuestRepository;
import io.medali.tfa.TwoFactorAuthenticationService;
import io.medali.entity.User;
import io.medali.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final QuestRepository questRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TwoFactorAuthenticationService tfaService;

    public AuthenticationResponse register(QuestRegisterRequest request) {
        var quest = Quest.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .mfaEnabled(request.isMfaEnabled())
                .birthday(request.getBirthday())
                .phoneNumber(request.getPhoneNumber())
                .registrationNumber(request.getRegistrationNumber())
                .ncin(request.getNcin())
                .company(request.getCompany())
                .image(request.getImage())
                .build();

        // if MFA enabled --> Generate Secret
        if (request.isMfaEnabled()) {
            quest.setSecret(tfaService.generateNewSecret());
        }
        questRepository.save(quest);
//        var jwtToken = jwtService.generateToken(quest);
//        var refreshToken = jwtService.generateRefreshToken(quest);
        return AuthenticationResponse.builder()
                .secretImageUri(tfaService.generateQrCodeImageUri(quest.getSecret()))
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
                .mfaEnabled(quest.isMfaEnabled())
                .build();
    }
    public AuthenticationResponse UserRegister(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getNcin()))
                .role(request.getRole())
                .mfaEnabled(request.isMfaEnabled())
                .birthday(request.getBirthday())
                .phoneNumber(request.getPhoneNumber())
                .registrationNumber(request.getRegistrationNumber())
                .ncin(request.getNcin())
                .company(request.getCompany())
                .image(request.getImage())
                .build();

        // if MFA enabled --> Generate Secret
        if (request.isMfaEnabled()) {
            user.setSecret(tfaService.generateNewSecret());
        }
        repository.save(user);
      var jwtToken = jwtService.generateToken(user);
      var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .secretImageUri(tfaService.generateQrCodeImageUri(user.getSecret()))
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .mfaEnabled(user.isMfaEnabled())
                .build();
    }
    public AuthenticationResponse adduser(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .mfaEnabled(request.isMfaEnabled())
                .birthday(request.getBirthday())
                .phoneNumber(request.getPhoneNumber())
                .registrationNumber(request.getRegistrationNumber())
                .ncin(request.getNcin())
                .company(request.getCompany())
                .image(request.getImage())
                .build();

        // if MFA enabled --> Generate Secret
        if (request.isMfaEnabled()) {
            user.setSecret(tfaService.generateNewSecret());
        }
        repository.save(user);
//        var jwtToken = jwtService.generateToken(quest);
//        var refreshToken = jwtService.generateRefreshToken(quest);
        return AuthenticationResponse.builder()
                .secretImageUri(tfaService.generateQrCodeImageUri(user.getSecret()))
//                .accessToken(jwtToken)
//                .refreshToken(refreshToken)
                .mfaEnabled(user.isMfaEnabled())
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        if (user.isMfaEnabled()) {
            return AuthenticationResponse.builder()
                    .accessToken("")
                    .refreshToken("")
                    .firstName("")
                    .mfaEnabled(true)
                    .build();
        }
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .blocking(user.isBlocking())
                .role(String.valueOf(user.getRole()))
                .firstName(user.getFirstname())
                .refreshToken(refreshToken)
                .mfaEnabled(false)
                .build();
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .mfaEnabled(false)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public AuthenticationResponse verifyCode(
            VerificationRequest verificationRequest
    ) {
        Quest quest = questRepository
                .findByEmail(verificationRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No Quest found with %S", verificationRequest.getEmail()))
                );
        if (tfaService.isOtpNotValid(quest.getSecret(), verificationRequest.getCode())) {

            throw new BadCredentialsException("Code is not correct");
        }
       // var jwtToken = jwtService.generateToken(quest);
        return AuthenticationResponse.builder()
               // .accessToken(jwtToken)
                .mfaEnabled(quest.isMfaEnabled())
                .build();
    }
    public AuthenticationResponse verifyCodeUser(
            VerificationRequest verificationRequest
    ) {
        User user = repository
                .findByEmail(verificationRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No user found with %S", verificationRequest.getEmail()))
                );
        if (tfaService.isOtpNotValid(user.getSecret(), verificationRequest.getCode())) {

            throw new BadCredentialsException("Code is not correct");
        }
         var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                 .accessToken(jwtToken)
                .blocking(user.isBlocking())
                .firstName(user.getFirstname())
                .role(String.valueOf(user.getRole()))
                .mfaEnabled(user.isMfaEnabled())
                .build();
    }
}
