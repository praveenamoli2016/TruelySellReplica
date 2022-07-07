package com.kaamcube.truelysell.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OtpRequest {

        private String otp;
        private String mobileNo;

    }

