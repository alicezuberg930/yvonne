package server.rem.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import server.rem.dtos.campaign.CampaignResponse;
import server.rem.dtos.campaign.CreateCampaignRequest;
import server.rem.dtos.campaign.UpdateCampaignRequest;
import server.rem.dtos.contact.ContactResponse;
import server.rem.dtos.template.TemplateResponse;
import server.rem.entities.Business;
import server.rem.entities.Campaign;
import server.rem.entities.Contact;
import server.rem.entities.Template;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CampaignMapper {
    @Mapping(target = "business", source = "business")
    @Mapping(target = "template", source = "template")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "sendType", source = "dto.sendType")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "scheduleAt", source = "dto.scheduleAt")
    @Mapping(target = "contacts", source = "contacts")
    @Mapping(target = "status", ignore = true)
    Campaign toEntity(CreateCampaignRequest dto, Business business, Template template, List<Contact> contacts);

    @Mapping(target = "business", source = "business")
    @Mapping(target = "template", source = "template")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "sendType", source = "dto.sendType")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "scheduleAt", source = "dto.scheduleAt")
    @Mapping(target = "contacts", source = "contacts")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(UpdateCampaignRequest dto, Business business, Template template, List<Contact> contacts, @MappingTarget Campaign campaign);

    TemplateResponse toTemplateResponse(Template template);

    ContactResponse toContactResponse(Contact contact);

    CampaignResponse toCampaignResponse(Campaign campaign);
}