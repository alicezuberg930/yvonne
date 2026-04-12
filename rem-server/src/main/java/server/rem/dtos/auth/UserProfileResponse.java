package server.rem.dtos.auth;

import lombok.*;
import server.rem.enums.Provider;
import server.rem.views.Views;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponse {
    @JsonView(Views.Public.class)
    private String id;

    @JsonView(Views.Public.class)
    private String avatar;

    @JsonView(Views.Public.class)
    private String birthday;

    @JsonView(Views.Public.class)
    private LocalDateTime createdAt;

    @JsonView(Views.Public.class)
    private String fullname;

    @JsonView(Views.Public.class)
    private String email;

    @JsonView(Views.Public.class)
    private String phone;

    @JsonView(Views.Public.class)
    private Provider provider;

    @JsonView(Views.Public.class)
    private Boolean isVerified;

    @JsonView(Views.Public.class)
    private List<UserBusinessResponse> businesses;
}
