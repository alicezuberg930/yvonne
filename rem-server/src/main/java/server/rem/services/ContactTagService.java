package server.rem.services;
 
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import server.rem.dtos.tag.CreateContactTagRequest;
import server.rem.entities.Business;
import server.rem.entities.ContactTag;
import server.rem.mappers.ContactTagMapper;
import server.rem.repositories.BusinessRepository;
import server.rem.repositories.ContactTagRepository;
import server.rem.utils.exceptions.ResourceNotFoundException;

import java.util.List;
 
@Service
@AllArgsConstructor
public class ContactTagService {
    private final ContactTagRepository contactTagRepository;
    private final ContactTagMapper contactTagMapper;
    private final BusinessRepository businessRepository;
 
    public List<ContactTag> getAll() {
        return contactTagRepository.findAll();
    }
 
    public List<ContactTag> getAllActive() {
        return contactTagRepository.findByIsActive(true);
    }
 
    public ContactTag getById(String id) {
        return contactTagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
    }
 
    public ContactTag create(CreateContactTagRequest dto) {
        // if (contactTagRepository.existsByName(dto.getName())) {
        //     throw new RuntimeException("Tag with name '" + dto.getName() + "' already exists");
        // }
        Business business = businessRepository.findById(dto.getBusinessId())
                .orElseThrow(() -> new ResourceNotFoundException("Business not found"));
        ContactTag tag = contactTagMapper.toEntity(dto, business);
        return contactTagRepository.save(tag);
    }
 
    public ContactTag update(String id, CreateContactTagRequest dto) {
        ContactTag tag = getById(id);
        Business business = businessRepository.findById(dto.getBusinessId())
                .orElseThrow(() -> new ResourceNotFoundException("Business not found"));
        contactTagMapper.updateEntity(dto, business, tag);
        return contactTagRepository.save(tag);
    }
 
    public void delete(String id) {
        contactTagRepository.delete(getById(id));
    }
 
    public ContactTag toggleActive(String id) {
        ContactTag tag = getById(id);
        tag.setIsActive(!tag.getIsActive());
        return contactTagRepository.save(tag);
    }
}