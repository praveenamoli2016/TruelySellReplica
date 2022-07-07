package com.kaamcube.truelysell.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity(name="vendor")
public class Vendor {
	
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
	
	@Column(name= "password")
    private String password;
	
	@Column(name= "country_code")
    private int countryCode;
	
	@Column(name= "mobile_number", unique=true)
    private String mobileNumber;

	@Column(name = "otp")
	private String otp;

	@Column(name= "date_of_birth")
    private String dateOfBirth;

	@Column(name = "terms_condition")
	private Boolean termsAndCondition;

	@Column(name= "address")
    private String address;
	
	@Column(name= "country")
    private String country;
    
	@Column(name= "state")
	private String state;
    
	@Column(name= "city")
    private String city;
	
	@Column(name= "postal_code")
    private int postalCode;
	
	@Column(name= "account_holder_name")
    private String accountHolderName;
	
	@Column(name= "bank_name")
    private String bankName;
	
	@Column(name= "bank_address")
    private String bankAddress;
	
	@Column(name= "ifsc_code")
    private String iFSCCode;
	
	@Column(name= "pan_no")
    private String panNo;
	
	@Column(name= "sort_code")
    private String sortCode;
	
	@Column(name= "routing_no")
    private String routingNo;
	
	@Column(name= "account_email_id")
    private String acountEmailId;
	
	@Column(name= "contact_no")
    private String contactNo;
	
	@Column(name= "payment_mode")
    private String paymentmode;
	
	@Column(name= "payment_purchage")
    private String paymentPurchage;

	
	@Column(name = "created_date")
	private String createdDate;
	
	@Column(name = "updated_date")
	private String updatedDate;
}
