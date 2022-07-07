package com.kaamcube.truelysell.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id")
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name= "name")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Column(name= "email")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(name= "password")
    private String password;

    @Column(name= "country_code")
    private int countryCode;

    @NotBlank(message = "MobileNumber is mandatory")
    @Column(name= "mobile_number", unique=true)
    private String mobileNumber;

    @Column(name = "otp")
    private String otp;

    @Column(name= "date_of_birth")
    private String dateOfBirth;

    @NotBlank(message = "TermAndCondition is mandatory")
    @Column(name = "terms_condition")
    private Boolean termsAndCondition;

    @NotBlank(message = "Address is mandatory")
    @Column(name= "address")
    private String address;

    @NotBlank(message = "Name is mandatory")
    @Column(name= "country")
    private String country;

    @NotBlank(message = "Country is mandatory")
    @Column(name= "state")
    private String state;

    @NotBlank(message = "State is mandatory")
    @Column(name= "city")
    private String city;

    @NotBlank(message = "City is mandatory")
    @Column(name= "postal_code")
    private int postalCode;

    @NotBlank(message = "CreateDate is mandatory")
    @Column(name = "created_date")
    private String createdDate;

    @NotBlank(message = "Updated is mandatory")
    @Column(name = "updated_date")
    private String updatedDate;

    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(
            mappedBy = "customer")
    private BookedServices bookedServices;
}
