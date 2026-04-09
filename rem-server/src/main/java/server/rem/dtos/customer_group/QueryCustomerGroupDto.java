package server.rem.dtos.customer_group;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
 
@Getter
@AllArgsConstructor
@Builder
@ToString
public class QueryCustomerGroupDto {
    @NotBlank()
    private final String businessId;

    private final Integer page;

    private final Integer limit;
}
