package server.rem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import server.rem.common.messages.CampaignMessages;
import server.rem.dtos.*;
import server.rem.dtos.campaign.*;
import server.rem.services.CampaignService;

@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignService campaignService;

    @PostMapping
    @PreAuthorize("hasAuthority('campaign.create')")
    public ResponseEntity<APIResponse<CampaignResponse>> createCampaign(
        @Valid @RequestBody CreateCampaignDto dto, 
        @RequestAttribute("businessId") String businessId
    ) throws Exception {
        return ResponseEntity.ok().body(APIResponse.success(
            201,
            CampaignMessages.CREATED,
            campaignService.createCampaign(dto, businessId))
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('campaign.edit')")
    public ResponseEntity<APIResponse<CampaignResponse>> updateCampaign(
        @Valid @RequestBody UpdateCampaignDto dto, 
        @RequestAttribute("businessId") String businessId,
        @PathVariable String id
    ) throws Exception {
        return ResponseEntity.ok().body(APIResponse.success(
            200,
            CampaignMessages.UPDATED,
            campaignService.updateCampaign(dto, businessId, id))
        );
    }

    @GetMapping
    @PreAuthorize("hasAuthority('campaign.view')")
    public ResponseEntity<APIResponse<CustomPageResponse<CampaignResponse>>> getCampaigns(
        @ModelAttribute QueryCampaign dto, 
        @RequestAttribute("businessId") String businessId
    ) {
        return ResponseEntity.ok().body(APIResponse.success(
            200,
            CampaignMessages.LIST_RETRIEVED,
            campaignService.getCampaigns(dto, businessId))
        );
    }
}