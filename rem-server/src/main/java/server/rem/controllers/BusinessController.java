package server.rem.controllers;

import jakarta.annotation.Nullable;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.rem.annotations.RequestUser;
import server.rem.dtos.APIResponse;
import server.rem.dtos.business.AddUserToBusinessDto;
import server.rem.dtos.business.CreateBusinessDto;
import server.rem.dtos.business.UpdateBusinessDto;
import server.rem.entities.Business;
import server.rem.entities.User;
import server.rem.services.BusinessService;

import java.util.List;

@RestController
@RequestMapping("/businesses")
@RequiredArgsConstructor
public class BusinessController {
    final private BusinessService businessService;

    @PostMapping
    public ResponseEntity<APIResponse<Business>> createBusiness(@RequestUser String userId, @Valid @RequestBody CreateBusinessDto dto) {
        return ResponseEntity.ok().body(APIResponse.success(
                200,
                "Business created successfully",
                businessService.createBusinesses(userId, dto)
        ));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Business>>> getAllBusinesses(@Nullable @RequestUser String userId) {
        return ResponseEntity.ok().body(APIResponse.success(
                200,
                "Business list retrieved successfully",
                businessService.getAllBusinesses(userId)
        ));
    }

    @PostMapping("/add/{businessId}")
    public ResponseEntity<APIResponse<User>> addUserToBusiness(@RequestUser String invitorId, @Valid @RequestBody AddUserToBusinessDto dto, @PathVariable String businessId) throws MessagingException {
        try {
                return ResponseEntity.ok().body(APIResponse.success(
                200,
                "User added to business successfully",
                businessService.addUserToBusiness(invitorId, dto, businessId)
        ));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{businessId}")
    public ResponseEntity<APIResponse<Business>> updateBusiness(@PathVariable String businessId, @RequestBody UpdateBusinessDto dto) {
        return ResponseEntity.ok().body(APIResponse.success(
                200,
                "Business updated successfully",
                businessService.updateBusiness(businessId, dto)
        ));
    }
}