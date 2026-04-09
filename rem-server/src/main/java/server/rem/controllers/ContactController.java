package server.rem.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import server.rem.dtos.APIResponse;
import server.rem.dtos.contact.CreateContactDto;
import server.rem.dtos.contact.QueryContactDto;
import server.rem.entities.Contact;
import server.rem.services.ContactService;
 
@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;
 
    @GetMapping
    public ResponseEntity<APIResponse<Page<Contact>>> getContactList(@ModelAttribute QueryContactDto dto) {
        return ResponseEntity.ok(APIResponse.success(200, "Contact list fetched successfully", contactService.getContactList(dto)));
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Contact>> getById(@PathVariable String id) {
        return ResponseEntity.ok(APIResponse.success(200, "Contact fetched", contactService.getById(id)));
    }
 
    @PostMapping
    public ResponseEntity<APIResponse<Contact>> create(@Valid @RequestBody CreateContactDto dto) {
        return ResponseEntity.ok(APIResponse.success(201, "Contact created", contactService.create(dto)));
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Contact>> update(@PathVariable String id, @Valid @RequestBody CreateContactDto dto) {
        return ResponseEntity.ok(APIResponse.success(200, "Contact updated", contactService.update(id, dto)));
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> delete(@PathVariable String id) {
        contactService.delete(id);
        return ResponseEntity.ok(APIResponse.success(200, "Contact deleted", null));
    }
}
