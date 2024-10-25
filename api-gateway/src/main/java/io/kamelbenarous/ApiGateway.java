package io.kamelbenarous;

import jakarta.ws.rs.HttpMethod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@EnableDiscoveryClient
@SpringBootApplication
@CrossOrigin("*")
public class ApiGateway {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateway.class, args);
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        "courses-service",
                        r -> r.path("/courses/getall")
                                .and().method(HttpMethod.GET)
                                .filters(GatewayFilterSpec::preserveHostHeader)
                                .uri("lb://courses-service/courses/getall")
                )
                .route(
                        "courses-service",
                        r -> r.path("/courses/{segment}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://courses-service/courses")
                )
                .route(
                        "courses-service",
                        r -> r.path("/courses/add")
                                .and().method(HttpMethod.POST)
                                .uri("lb://courses-service/courses/add")
                )
                .route(
                        "courses-service",
                        r -> r.path("/courses/{segment}")
                                .and().method(HttpMethod.DELETE)
                                .uri("lb://courses-service/courses")
                )
                .route(
                        "courses-service",
                        r -> r.path("/chapters/{segment}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://courses-service/chapters")
                )
                .route(
                        "courses-service",
                        r -> r.path("/chapters/addchapterresource/{segment}")
                                .and().method(HttpMethod.POST)
                                .uri("lb://courses-service/chapters/addchapterresource")
                )
                .route(
                        "media-source-service",
                        r -> r.path("/resources/get/{segment}")
                                .and().method(HttpMethod.GET)
                                .filters(GatewayFilterSpec::preserveHostHeader)
                                .uri("lb://media-source-service/resources/get")

                )
                .route(
                        "media-source-service",
                        r -> r.path("/mediasource")
                                .and().method(HttpMethod.POST)
                                .uri("lb://media-source-service")
                )
                .route(
                        "scheduler-service",
                        r -> r.path("/calendar-events")
                                .and().method(HttpMethod.GET)
                                .uri("lb://scheduler-service")
                )
                .route(
                        "scheduler-service",
                        r -> r.path("/calendar-events")
                                .and().method(HttpMethod.POST)
                                .uri("lb://scheduler-service")
                )
                .route(
                        "scheduler-service",
                        r -> r.path("/participants/{participantId}/events")
                                .and().method(HttpMethod.GET)
                                .uri("lb://scheduler-service")
                )
                .route(
                        "scheduler-service",
                        r -> r.path("/participants/{participantId}/events/{eventId}")
                                .and().method(HttpMethod.PUT)
                                .uri("lb://scheduler-service")
                )
                .route(
                        "scheduler-service",
                        r -> r.path("/scraptechnologiespweasesmileyface")
                                .and().method(HttpMethod.GET)
                                .uri("lb://scheduler-service")
                )
                .route(
                        "auth-service",
                        r -> r.path("/api/v1/auth/register")
                                .and().method(HttpMethod.POST)
                                .uri("lb://auth-service/api/v1/auth/register")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/auth/authenticate")
                                .and().method(HttpMethod.POST)
                                .uri("lb://auth-service/api/v1/auth/authenticate")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/auth/verify")
                                .and().method(HttpMethod.POST)
                                .uri("lb://auth-service/api/v1/auth/verify")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/auth/verifyuser")
                                .and().method(HttpMethod.POST)
                                .uri("lb://auth-service/api/v1/auth/verifyuser")
                )
                .route(
                        "auth-service",
                        r -> r.path("/api/uploadFile")
                                .and().method(HttpMethod.POST)
                                .uri("lb://auth-service/api/uploadFile")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/quest/all")
                                .and().method(HttpMethod.GET)
                                .uri("lb://auth-service/api/v1/quest/all")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/curentuser")
                                .and().method(HttpMethod.GET)
                                .uri("lb://auth-service/api/v1/users/curentuser")
                ).route(
                        "auth-service",
                        r -> r.path("/download/{fileName}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://auth-service/download")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/rejecterQuest/{idQuest}")
                                .and().method(HttpMethod.POST)
                                .uri("lb://auth-service/api/v1/users/rejecterQuest")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/acceptQuest/{idQuest}")
                                .and().method(HttpMethod.POST)
                                .uri("lb://auth-service/api/v1/users/acceptQuest")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/changepass")
                                .and().method(HttpMethod.POST)
                                .uri("lb://auth-service/api/v1/users/changepass")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/updateUser/{userId}")
                                .and().method(HttpMethod.PUT)
                                .uri("lb://auth-service/api/v1/users/updateUser")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/allUser")
                                .and().method(HttpMethod.GET)
                                .uri("lb://auth-service/api/v1/users/allUser")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/userBlocking/{id}")
                                .and().method(HttpMethod.PUT)
                                .uri("lb://auth-service/api/v1/users/userBlocking")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/deblocafeuser/{id}")
                                .and().method(HttpMethod.PUT)
                                .uri("lb://auth-service/api/v1/users/deblocafeuser")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/delete/{id}")
                                .and().method(HttpMethod.DELETE)
                                .uri("lb://auth-service/api/v1/users/delete")
                ).route(
                "auth-service",
                r -> r.path("/api/v1/auth/adduser")
                        .and().method(HttpMethod.POST)
                        .uri("lb://auth-service/api/v1/auth/adduser")

                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/disable-2fa/{id}")
                                .and().method(HttpMethod.POST)
                                .uri("lb://auth-service/api/v1/users/disable-2fa")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/enable-2fa/{id}")
                                .and().method(HttpMethod.POST)
                                .uri("lb://auth-service/api/v1/users/enable-2fa")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/countByRole")
                                .and().method(HttpMethod.GET)
                                .uri("lb://auth-service/api/v1/users/countByRole")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/findallUsersByRole/{role}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://auth-service/api/v1/users/allStudent")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/socialprofil/add")
                                .and().method(HttpMethod.POST)
                                .uri("lb://auth-service/api/v1/socialprofil/add")
                ).route(
                    "auth-service",
                    r -> r.path("/api/v1/auth/logout")
                            .and().method(HttpMethod.POST)
                            .uri("lb://auth-service/api/v1/auth/logout")
            ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/allUserProfil/{role}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://auth-service/api/v1/users/allUserProfil")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/findbyid/{id}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://auth-service/api/v1/users/findbyid")
                ).route(
                        "auth-service",
                        r -> r.path("/api/v1/users/findbyName/{name}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://auth-service/api/v1/users/findbyName")
                )
                .route(
                        "absence-source-service",
                        r -> r.path("/api/v1/absence/save")
                                .and().method(HttpMethod.POST)
                                .uri("lb://absence-source-service/api/v1/absence/save")
                ).route(
                        "absence-source-service",
                        r -> r.path("/api/v1/absence/all")
                                .and().method(HttpMethod.GET)
                                .uri("lb://absence-source-service/api/v1/absence/all")
                )
                .route(
                        "chat-service",
                        r -> r.path("/chats/add")
                                .and().method(HttpMethod.POST)
                                .uri("lb://chat-service/chats/add")
                )
                .route(
                        "chat-service",
                        r -> r.path("/chats/all")
                                .and().method(HttpMethod.GET)
                                .uri("lb://chat-service/chats/all")
                )
                .route(
                        "chat-service",
                        r -> r.path("/chats/{id}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://chat-service/chats/")
                )
                .route(
                        "chat-service",
                        r -> r.path("/chats/firstUserName/{username}")
                                .and().method(HttpMethod.POST)
                                .uri("lb://chat-service/chats/firstUserName/")
                )
                .route(
                        "chat-service",
                        r -> r.path("/chats/secondUserName/{username}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://chat-service/chats/scondUserName/")
                )
                .route(
                        "chat-service",
                        r -> r.path("/chats/getChatByFirstUserNameOrSecondUserName/{username}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://chat-service/chats/getChatByFirstUserNameOrSecondUserName/")
                )
                .route(
                        "chat-service",
                        r -> r.path("/chats/getChatByFirstUserNameAndSecondUserName")
                                .and().method(HttpMethod.GET)
                                .uri("lb://chat-service/chats/getChatByFirstUserNameAndSecondUserName")
                )
                .route(
                        "chat-service",
                        r -> r.path("/chats/message/{chatId}")
                                .and().method(HttpMethod.PUT)
                                .uri("lb://chat-service/chats/message/")
                ).route("feedback-service",
                        r -> r.path("/feedback/getAllFeedback")
                                .and().method(HttpMethod.GET)
                                .uri("lb://feedback-service/feedback/getAllFeedback")
                ).route(
                        "feedback-service",
                        r -> r.path("/feedback/addFeedback")
                                .and().method(HttpMethod.POST)
                                .uri("lb://feedback-service/feedback/addFeedback")
                ).route(
                        "feedback-service",
                        r -> r.path("/feedback/remove/{feedback-id}")
                                .and().method(HttpMethod.DELETE)
                                .uri("lb://feedback-service/feedback/remove")
                ).route(
                        "feedback-service",
                        r -> r.path("/feedback/SetArchive/{id}")
                                .and().method(HttpMethod.PUT)
                                .uri("lb://feedback-service/feedback/SetArchive")
                ).route(
                        "feedback-service",
                        r -> r.path("/feedback/UnArchived/{id}")
                                .and().method(HttpMethod.PUT)
                                .uri("lb://feedback-service/feedback/UnArchived")
                ).route(
                        "feedback-service",
                        r -> r.path("/feedback/forward/{id}")
                                .and().method(HttpMethod.PUT)
                                .uri("lb://feedback-service/feedback/forward")
                ).route("forum-service",
                        r -> r.path("/myforum/getAllForum")
                                .and().method(HttpMethod.GET)
                                .uri("lb://forum-service/myforum/getAllForum")
                ).route("forum-service",
                        r -> r.path("/myforum/getForumById/{id}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://forum-service/myforum/getForumById")
                ).route(
                        "forum-service",
                        r -> r.path("/myforum/addForum")
                                .and().method(HttpMethod.POST)
                                .uri("lb://forum-service/myforum/addForum")
                ).route(
                        "forum-service",
                        r -> r.path("/myforum/update")
                                .and().method(HttpMethod.PUT)
                                .uri("lb://forum-service/myforum/update")
                ).route(
                        "forum-service",
                        r -> r.path("/myforum/remove/{id}")
                                .and().method(HttpMethod.DELETE)
                                .uri("lb://forum-service/myforum/remove")
                )
                .route(
                        "forum-service",
                        r -> r.path("/myforum/like/{forum-id}")
                                .and().method(HttpMethod.POST)
                                .uri("lb://forum-service/myforum/like")
                ).route(
                        "forum-service",
                        r -> r.path("/myforum/dislike/{forum-id}")
                                .and().method(HttpMethod.POST)
                                .uri("lb://forum-service/myforum/dislike")
                ).route(
                        "forum-service",
                        r -> r.path("/myforum/filter/{title}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://forum-service/myforum/filter")
                )
                .route(
                        "forum-service",
                        r -> r.path("/myforum/filterb/{body}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://forum-service/myforum/filterb")
                ).route(
                        "forum-service",
                        r -> r.path("/myforum/countbydate")
                                .and().method(HttpMethod.GET)
                                .uri("lb://forum-service/myforum/countbydate")
                ).route(
                        "dep-service",
                        r -> r.path("/api/lessons")
                                .and().method(HttpMethod.GET)
                                .uri("lb://dep-service")
                )
                .route(
                        "quiz-service",
                        r -> r.path("/api/quizzes/show")
                                .and().method(HttpMethod.GET)
                                .uri("lb://quiz-service")
                )

                .route(
                        "quiz-service",
                        r -> r.path("/api/quizzes/{quizId}")
                                .and().method(HttpMethod.GET)
                                .uri("lb://quiz-service")
                )
                .route(
                        "quiz-service",
                        r -> r.path("/api/quizzes/add")
                                .and().method(HttpMethod.POST)
                                .uri("lb://quiz-service")
                )

                .route(
                        "quiz-service",
                        r -> r.path("/api/quizzes/{quizId}")
                                .and().method(HttpMethod.DELETE)
                                .uri("lb://quiz-service")
                )
                .build();
    }
}