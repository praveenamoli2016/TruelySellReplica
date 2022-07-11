package com.kaamcube.truelysell.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kaamcube.truelysell.utility.enums.PaymentMethod;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddWalletAmountRequest {
    private Long customerId;
    private Double amount;
    private String date;
    private PaymentMethod paymentMethod;
}
