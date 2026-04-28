package server.rem.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import server.rem.dtos.APIResponse;
import server.rem.dtos.tag.CreateContactTagRequest;
import server.rem.entities.ContactTag;
import server.rem.services.ContactTagService;

import java.util.List;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
public class ContactTagController {
    private final ContactTagService contactTagService;

    @GetMapping
    public ResponseEntity<APIResponse<List<ContactTag>>> getAll() {
        return ResponseEntity.ok(APIResponse.success(200, "Tags fetched", contactTagService.getAll()));
    }

    @GetMapping("/active")
    public ResponseEntity<APIResponse<List<ContactTag>>> getAllActive() {
        return ResponseEntity.ok(APIResponse.success(200, "Active tags fetched", contactTagService.getAllActive()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ContactTag>> getById(@PathVariable String id) {
        return ResponseEntity.ok(APIResponse.success(200, "Tag fetched", contactTagService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<APIResponse<ContactTag>> create(@Valid @RequestBody CreateContactTagRequest dto) {
        return ResponseEntity.ok(APIResponse.success(201, "Tag created", contactTagService.create(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ContactTag>> update(@PathVariable String id, @Valid @RequestBody CreateContactTagRequest dto) {
        return ResponseEntity.ok(APIResponse.success(200, "Tag updated", contactTagService.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> delete(@PathVariable String id) {
        contactTagService.delete(id);
        return ResponseEntity.ok(APIResponse.success(200, "Tag deleted", null));
    }

    @PatchMapping("/{id}/toggle")
    public ResponseEntity<APIResponse<ContactTag>> toggle(@PathVariable String id) {
        return ResponseEntity.ok(APIResponse.success(200, "Tag toggled", contactTagService.toggleActive(id)));
    }
}