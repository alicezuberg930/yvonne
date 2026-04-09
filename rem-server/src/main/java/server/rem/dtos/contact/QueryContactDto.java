package server.rem.dtos.contact;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import server.rem.enums.ContactType;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class QueryContactDto {
    @NotBlank()
    private final String businessId;

    private final String customerGroupId;

    private final String page;

    private final String limit;

    private final ContactType type;
}
