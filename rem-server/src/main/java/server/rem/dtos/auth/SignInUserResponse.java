package server.rem.dtos.auth;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInUserResponse {
    private UserProfileResponse user;

    private String accessToken;
}
