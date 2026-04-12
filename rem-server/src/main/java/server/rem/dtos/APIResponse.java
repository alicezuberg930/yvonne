package server.rem.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.*;
import server.rem.views.Views;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

@Getter
public class APIResponse<T> {
    @JsonView(Views.Public.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    @JsonView(Views.Public.class)
    private int status;
    @JsonView(Views.Public.class)
    private String message;
    @JsonView(Views.Public.class)
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