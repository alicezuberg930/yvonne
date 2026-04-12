package server.rem.dtos.auth;

import server.rem.dtos.business.BusinessResponse;
import server.rem.views.Views;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBusinessResponse {
    @JsonUnwrapped // flattens all BusinessResponse fields into this object
    @JsonView(Views.Public.class)
    private BusinessResponse business;
    
    @JsonView(Views.Public.class)
    private String isActive;

    @JsonView(Views.Public.class)
    private String isVerified;

    @JsonView(Views.Public.class)
    private Integer salary;

    @JsonView(Views.Public.class)
    private String bankOwner;

    @JsonView(Views.Public.class)
    private String bankAccount;

    @JsonView(Views.Public.class)
    private String bankName;

    @JsonView(Views.Public.class)
    private String bankCode;

    @JsonView(Views.Public.class)
    private String bankBranch;

    @JsonView(Views.Public.class)
    private Integer dependants;

    @JsonView(Views.Public.class)
    private RoleResponse role;
}
