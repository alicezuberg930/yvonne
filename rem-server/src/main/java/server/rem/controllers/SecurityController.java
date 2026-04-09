package server.rem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import server.rem.dtos.APIResponse;
import server.rem.dtos.security.CreatePermissionDto;
import server.rem.dtos.security.CreateRoleDto;
import server.rem.entities.Permission;
import server.rem.entities.Role;
import server.rem.services.SecurityService;

@RestController
@RequestMapping("/security")
@RequiredArgsConstructor
public class SecurityController {
    private final SecurityService securityService;

    @PostMapping("/roles")
    public ResponseEntity<APIResponse<Role>> createRole(@Valid @RequestBody CreateRoleDto dto) {
        return ResponseEntity.ok().body(APIResponse.success(
                201,
                "Role created successfully",
                securityService.createRole(dto)
            )
        );
    }

    @PostMapping("/permissions")
    public ResponseEntity<APIResponse<Permission>> checkIn(@Valid @RequestBody CreatePermissionDto dto) {
        return ResponseEntity.ok().body(APIResponse.success(
                201,
                "Permission created successfully",
                securityService.createPermission(dto)
            )
        );
    }

}
