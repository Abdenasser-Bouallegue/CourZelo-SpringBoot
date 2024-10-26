package io.medali;

import io.medali.auth.AuthenticationService;
import io.medali.auth.RegisterRequest;
import io.medali.entity.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var superadmin = RegisterRequest.builder()
					.firstname("SuperAdmin")
					.lastname("SuperAdmin")
					.email("SuperAdmin@mail.com")
					.password("password")
					.company("esprit")
					.phoneNumber(52505287)
					.ncin("JMT")
					.registrationNumber("2024")
					.role(Role.SUPERADMIN)
					.build();
			System.out.println("SuperAdmin token: " + service.UserRegister(superadmin).getAccessToken());

			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.company("esprit")
					.phoneNumber(52505287)
					.ncin("JMT")
					.registrationNumber("2024")
					.role(Role.ADMIN)
					.build();
			System.out.println("Admin token: " + service.UserRegister(admin).getAccessToken());

			var student = RegisterRequest.builder()
					.firstname("Student")
					.lastname("Student")
					.email("Student@mail.com")
					.password("password")
					.company("esprit")
					.phoneNumber(52505287)
					.ncin("JMT")
					.registrationNumber("2024")
					.role(Role.STUDENT)
					.build();
			System.out.println("Student token: " + service.UserRegister(student).getAccessToken());

			var teacher = RegisterRequest.builder()
					.firstname("Teacher")
					.lastname("Teacher")
					.email("teacher@mail.com")
					.password("password")
					.company("esprit")
					.phoneNumber(52505287)
					.ncin("JMT")
					.registrationNumber("2024")
					.role(Role.TEACHER)
					.build();
			System.out.println("Teacher token: " + service.UserRegister(teacher).getAccessToken());

		};
	}
}
