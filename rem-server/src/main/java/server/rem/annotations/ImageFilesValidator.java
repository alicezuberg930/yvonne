package server.rem.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Set;

public class ImageFilesValidator implements ConstraintValidator<ValidImageFiles, List<MultipartFile>> {
    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/png", "image/jpg", "image/jpeg", "image/webp"
    );
    private long maxSizeBytes;
    private long maxSizeMb;

    @Override
    public void initialize(ValidImageFiles annotation) {
        this.maxSizeMb = annotation.maxSizeMb();
        this.maxSizeBytes = maxSizeMb * 1024 * 1024;
    }

    @Override
    public boolean isValid(List<MultipartFile> files, ConstraintValidatorContext ctx) {
        if (files == null || files.isEmpty()) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate("Files must not be empty").addConstraintViolation();
            return false;
        }
        for (MultipartFile file : files) {
            if (!ALLOWED_TYPES.contains(file.getContentType())) {
                ctx.disableDefaultConstraintViolation();
                ctx.buildConstraintViolationWithTemplate(
                    "File '" + file.getOriginalFilename() + "': only png, jpg, jpeg, webp are allowed"
                ).addConstraintViolation();
                return false;
            }
            if (file.getSize() > maxSizeBytes) {
                ctx.disableDefaultConstraintViolation();
                ctx.buildConstraintViolationWithTemplate(
                    "File '" + file.getOriginalFilename() + "' exceeds " + maxSizeMb + "MB limit"
                ).addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}