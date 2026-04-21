package server.rem.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import server.rem.dtos.campaign.CreateCampaignDto;
import server.rem.dtos.campaign.QueryCampaignDto;
import server.rem.entities.Business;
import server.rem.entities.Campaign;
import server.rem.entities.Contact;
import server.rem.entities.Template;
import server.rem.enums.CampaignSendType;
import server.rem.mappers.CampaignMapper;
import server.rem.repositories.BusinessRepository;
import server.rem.repositories.CampaignRepository;
import server.rem.repositories.ContactRepository;
import server.rem.repositories.TemplateRepository;
import server.rem.scheduler.CampaignScheduler;
import server.rem.utils.exceptions.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final CampaignMapper campaignMapper;
    private final BusinessRepository businessRepository;
    private final TemplateRepository templateRepository;
    private final EmailService emailService;
    private final ContactRepository contactRepository;
    private final CampaignScheduler campaignScheduler;

    public Campaign createCampaign(CreateCampaignDto dto, String businessId) throws Exception {
        Business business = businessRepository.findById(businessId).orElseThrow(() -> new ResourceNotFoundException("Business not found"));
        Template template = templateRepository.findById(dto.getTemplateId()).orElseThrow(() -> new ResourceNotFoundException("Template not found"));
        List<Contact> contacts = contactRepository.findAllById(dto.getContactIds());
        Campaign campaign = campaignMapper.toEntity(dto, business, template, contacts);
        if(dto.getSendType().equals(CampaignSendType.IMMEDIATE)){
            sendCampaign(campaign.getId(), businessId);
        } else {
            // schedule to quartz
            campaignScheduler.scheduleCampaign(campaign);
        }
        return campaignRepository.save(campaign);
    }

    public List<Campaign> getCampaignList(QueryCampaignDto dto) {
        return campaignRepository.findAll();
    }

    public void sendCampaign(String campaignId, String businessId) throws Exception{
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));
        List<Contact> contacts = campaign.getContacts().stream().toList();
        String html = campaign.getTemplate().getHeader() + campaign.getTemplate().getBody() + campaign.getTemplate().getFooter();
        for (Contact cc : contacts) {
            emailService.sendMail(businessId, cc.getEmail(), campaign.getName(), html);
        }
    }
    
}