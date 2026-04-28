package server.rem.dtos.business;

import lombok.*;
import server.rem.views.Views;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonView;

@Getter
@AllArgsConstructor
public class BusinessResponse {
    @JsonView(Views.Public.class)
    private final String id;

    @JsonView(Views.Public.class)
    private final String name;

    @JsonView(Views.Public.class)
    private final String slug;

    @JsonView(Views.Public.class)
    private final String logoUrl;

    @JsonView(Views.Public.class)
    private final LocalTime workStartTime;

    @JsonView(Views.Public.class)
    private final Integer insuranceContributionSalary;

    @JsonView({ Views.Business.class, Views.Attendance.class })
    private final String twilioAccountSid;

    @JsonView(Views.Public.class)
    private final String description;

}
