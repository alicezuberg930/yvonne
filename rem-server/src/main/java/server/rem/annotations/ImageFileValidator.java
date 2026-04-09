package server.rem.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;
import java.util.Set;

public class ImageFileValidator implements ConstraintValidator<ValidImageFile, MultipartFile> {
    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/png", "image/jpg", "image/jpeg", "image/webp"
    );
    private long maxSizeBytes;

    @Override
    public void initialize(ValidImageFile annotation) {
        this.maxSizeBytes = annotation.maxSizeMb() * 1024 * 1024;
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext ctx) {
        if (file == null || file.isEmpty()) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate("File must not be empty").addConstraintViolation();
            return false;
        }
        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(
                "Only png, jpg, jpeg, webp images are allowed"
            ).addConstraintViolation();
            return false;
        }
        if (file.getSize() > maxSizeBytes) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(
                "File '" + file.getOriginalFilename() + "' exceeds " + (this.maxSizeBytes / (1024 * 1024)) + "MB limit"
            ).addConstraintViolation();
            return false;
        }
        return true;
    }
}