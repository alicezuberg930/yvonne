package server.rem.dtos.customer_group;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
public class CreateCustomerGroupRequest {
    @NotBlank(message = "Group name is required")
    private final String name;

    @NotNull(message = "Business ID is required")
    private final String businessId;

    private final Double percentage;
}