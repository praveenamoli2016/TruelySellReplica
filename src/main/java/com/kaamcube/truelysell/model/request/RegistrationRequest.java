package com.kaamcube.truelysell.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationRequest {

	@NotBlank(message = "Name not null")
	private String name;
	@NotBlank(message = "Email not null")
	private String email;
	@NotBlank(message = "Password not null")
	private String password;
	private String mobileNo;
	private Boolean termsAndCondition;

}
