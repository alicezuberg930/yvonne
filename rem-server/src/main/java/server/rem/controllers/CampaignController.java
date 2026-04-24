package server.rem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import server.rem.common.messages.CampaignMessages;
import server.rem.dtos.APIResponse;
import server.rem.dtos.campaign.CampaignResponse;
import server.rem.dtos.campaign.CreateCampaignDto;
import server.rem.dtos.campaign.QueryCampaignDto;
import server.rem.dtos.campaign.UpdateCampaignDto;
import server.rem.services.CampaignService;
import server.rem.views.Views;

import java.util.List;

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

    @JsonView(Views.Public.class)
    @GetMapping
    @PreAuthorize("hasAuthority('campaign.view')")
    public ResponseEntity<APIResponse<List<CampaignResponse>>> getCampaignList(@ModelAttribute QueryCampaignDto dto) {
        return ResponseEntity.ok().body(APIResponse.success(
            200,
            "Campaigns retrieved successfully",
            campaignService.getCampaignList(dto))
        );
    }
}