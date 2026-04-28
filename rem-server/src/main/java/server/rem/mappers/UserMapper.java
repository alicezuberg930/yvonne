package server.rem.mappers;

import server.rem.dtos.auth.SignUpRequest;
import server.rem.entities.User;
import server.rem.enums.Provider;

public class UserMapper {

    public static User toEntity(SignUpRequest dto) {
        return User.builder()
                .fullname(dto.getFullname())
                .phone(dto.getPhone())
                .avatar(dto.getAvatar())
                .provider(dto.getProvider() != null ? dto.getProvider() : Provider.LOCAL)
                .birthday(dto.getBirthday())
                .email(dto.getEmail())
                .password(dto.getPassword()) // will hash later
                .build();
    }
}