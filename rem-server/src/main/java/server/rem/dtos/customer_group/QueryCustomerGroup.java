package server.rem.dtos.customer_group;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import server.rem.dtos.QueryPaginate;

@Getter
@AllArgsConstructor
public class QueryCustomerGroup extends QueryPaginate {
    @NotBlank()
    private final String businessId;
}
