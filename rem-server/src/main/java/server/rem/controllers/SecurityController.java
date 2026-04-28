package server.rem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import server.rem.dtos.APIResponse;
import server.rem.dtos.security.CreatePermissionRequest;
import server.rem.dtos.security.CreateRoleRequest;
import server.rem.entities.Permission;
import server.rem.entities.Role;
import server.rem.services.SecurityService;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecurityController {
    private final SecurityService securityService;

    @PostMapping("/roles")
    public ResponseEntity<APIResponse<Role>> createRole(@Valid @RequestBody CreateRoleRequest dto) {
        return ResponseEntity.ok().body(APIResponse.success(
                201,
                "Role created successfully",
                securityService.createRole(dto)
            )
        );
    }

    @PostMapping("/permissions")
    public ResponseEntity<APIResponse<Permission>> checkIn(@Valid @RequestBody CreatePermissionRequest dto) {
        return ResponseEntity.ok().body(APIResponse.success(
                201,
                "Permission created successfully",
                securityService.createPermission(dto)
            )
        );
    }

}
