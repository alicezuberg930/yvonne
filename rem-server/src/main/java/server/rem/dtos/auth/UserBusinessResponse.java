package server.rem.dtos.auth;

import server.rem.dtos.business.BusinessResponse;
import server.rem.views.Views;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.*;

@Getter
@AllArgsConstructor
public class UserBusinessResponse {
    @JsonUnwrapped // flattens all BusinessResponse fields into this object
    @JsonView(Views.Public.class)
    private final BusinessResponse business;

    @JsonView(Views.Public.class)
    private final String isActive;

    @JsonView(Views.Public.class)
    private final String isVerified;

    @JsonView(Views.Public.class)
    private final Integer salary;

    @JsonView(Views.Public.class)
    private final String bankOwner;

    @JsonView(Views.Public.class)
    private final String bankAccount;

    @JsonView(Views.Public.class)
    private final String bankName;

    @JsonView(Views.Public.class)
    private final String bankCode;

    @JsonView(Views.Public.class)
    private final String bankBranch;

    @JsonView(Views.Public.class)
    private final Integer dependants;

    @JsonView(Views.Public.class)
    private final RoleResponse role;
}
