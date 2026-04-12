package server.rem.dtos.business;

import lombok.*;
import server.rem.views.Views;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonView;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BusinessResponse {
    @JsonView(Views.Public.class)
    private String id;

    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Public.class)
    private String slug;

    @JsonView(Views.Public.class)
    private String logoUrl;

    @JsonView(Views.Public.class)
    private LocalTime workStartTime;

    @JsonView(Views.Public.class)
    private Integer insuranceContributionSalary;

    @JsonView({ Views.Business.class, Views.Attendance.class })
    private String twilioAccountSid;

    
}
