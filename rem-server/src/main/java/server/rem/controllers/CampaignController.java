package server.rem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import server.rem.dtos.APIResponse;
import server.rem.dtos.campaign.CreateCampaignDto;
import server.rem.dtos.campaign.QueryCampaignDto;
import server.rem.entities.Campaign;
import server.rem.services.CampaignService;
import java.util.List;

@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignService campaignService;

    @PostMapping
    @PreAuthorize("hasAuthority('campaign.create')")
    public ResponseEntity<APIResponse<Campaign>> createCampaign(@Valid @RequestBody CreateCampaignDto dto) {
        return ResponseEntity.ok().body(APIResponse.success(
            201,
            "Campaigns created successfully",
            campaignService.createCampaign(dto))
        );
    }

    @GetMapping
    @PreAuthorize("hasAuthority('campaign.view')")
    public ResponseEntity<APIResponse<List<Campaign>>> getCampaignList(@ModelAttribute QueryCampaignDto dto) {
        return ResponseEntity.ok().body(APIResponse.success(
            200,
            "Campaigns retrieved successfully",
            campaignService.getCampaignList(dto))
        );
    }

    @PostMapping("/notify/{campaignId}")
    public ResponseEntity<APIResponse<Void>> sendCampaign(@PathVariable String campaignId, @RequestBody QueryCampaignDto dto) throws Exception {
        campaignService.sendCampaign(campaignId, dto.getBusinessId());
        return ResponseEntity.ok().body(APIResponse.success(
            200,
            "Campaign sent to contacts successfully",
            null)
        );
    }
}