package server.rem.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

@Getter
public class APIResponse<T> {
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;

    public APIResponse(int status, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> APIResponse<T> success(int status, String message, T data) {
        return new APIResponse<>(status, message, data);
    }

    public static <T> APIResponse<T> error(HttpStatus status, String message) {
        return new APIResponse<>(status.value(), message, null);
    }
}