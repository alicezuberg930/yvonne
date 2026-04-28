package server.rem.dtos.customer_group;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import server.rem.dtos.QueryPaginate;

@Getter
@SuperBuilder
public class QueryCustomerGroup extends QueryPaginate {
    @NotBlank()
    private final String businessId;
}
