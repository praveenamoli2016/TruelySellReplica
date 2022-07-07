package com.kaamcube.truelysell.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VendorRequest {
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
    private String accountHolderName;
    private String bankName;
    private String bankAddress;
    private String iFSCCode;
    private String panNo;
    private String sortCode;
    private String routingNo;
    private String acountEmailId;
    private String contactNo;
    private String paymentmode;
    private String paymentPurchage;
}
