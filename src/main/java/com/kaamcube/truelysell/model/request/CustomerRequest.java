package com.kaamcube.truelysell.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRequest {
    private Long id;
    private String name;
    private String email;
    private String password;
    private int countryCode;
    private String mobileNumber;
    private String otp;
    private String dateOfBirth;
    private Boolean termsAndCondition;
    private String address;
    private String country;
    private String state;
    private String city;
    private int postalCode;
    private String createdDate;
    private String updatedDate;

}
