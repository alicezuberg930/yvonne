package server.rem.controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import server.rem.annotations.ValidImageFile;
import server.rem.annotations.ValidImageFiles;
import server.rem.dtos.APIResponse;
import server.rem.services.CloudinaryService;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
@Validated
public class CloudinaryController {
    private final CloudinaryService cloudinaryService;

    @PostMapping(value = "/single", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse<String>> uploadFile(@RequestParam("file") @ValidImageFile MultipartFile file, @RequestParam(required = false) String subFolder) throws Exception {
        String url = cloudinaryService.uploadFile(file, subFolder, null);
        return ResponseEntity.ok(APIResponse.success(200, "File uploaded", url));
    }

    @PostMapping(value = "/multiple", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse<List<String>>> uploadFiles(@RequestParam("files") @ValidImageFiles List<MultipartFile> files, @RequestParam(required = false) String subFolder) throws Exception {
        List<String> urls = cloudinaryService.uploadFiles(files, subFolder, null);
        return ResponseEntity.ok(APIResponse.success(200, "Files uploaded", urls));
    }

    @DeleteMapping
    public ResponseEntity<APIResponse<Void>> deleteFile(@RequestParam String url) throws Exception {
        cloudinaryService.deleteFile(url);
        return ResponseEntity.ok(APIResponse.success(200, "File deleted", null));
    }
}