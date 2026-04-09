package server.rem.dtos.customer_group;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
 
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateCustomerGroupDto {
    @NotBlank(message = "Group name is required")
    private String name;
 
    @NotNull(message = "Business ID is required")
    private String businessId;
 
    private Double percentage;
}