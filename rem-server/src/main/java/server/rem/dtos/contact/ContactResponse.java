package server.rem.dtos.contact;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.*;
import server.rem.views.Views;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactResponse {
    @JsonView(Views.Public.class)
    private String id;

    @JsonView(Views.Public.class)
    private String type;

    @JsonView(Views.Public.class)
    private String firstName;

    @JsonView(Views.Public.class)
    private String lastName;

    @JsonView(Views.Public.class)
    private String surname;

    @JsonView(Views.Public.class)
    private String phone;

    @JsonView(Views.Public.class)
    private String mobilePhone;

    @JsonView(Views.Public.class)
    private String email;

    @JsonView(Views.Public.class)
    private String birthday;

    @JsonView(Views.Public.class)
    private String occupation;

    @JsonView(Views.Public.class)
    private String taxCode;

    @JsonView(Views.Public.class)
    private String website;

    @JsonView(Views.Public.class)
    private String facebook;

    @JsonView(Views.Public.class)
    private String instagram;

    @JsonView(Views.Public.class)
    private String zalo;

    @JsonView(Views.Public.class)
    private String identityCard;

    @JsonView(Views.Public.class)
    private String identityIssuedOn;

    @JsonView(Views.Public.class)
    private String identityIssuedAt;

    @JsonView(Views.Public.class)
    private String insuranceNumber;

    @JsonView(Views.Public.class)
    private String note;

    @JsonView(Views.Public.class)
    private String address1;

    @JsonView(Views.Public.class)
    private String address2;

    @JsonView(Views.Public.class)
    private String country;

    @JsonView(Views.Public.class)
    private String zipCode;
}