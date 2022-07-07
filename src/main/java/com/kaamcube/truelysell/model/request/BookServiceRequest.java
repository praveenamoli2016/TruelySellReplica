package com.kaamcube.truelysell.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kaamcube.truelysell.utility.enums.PaymentMethod;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookServiceRequest {
    private Long customerId;
    private Long serviceId;
    private String serviceLocation;
    private String serviceAmount;
    private String date;
    private String timeSlot;
    private String notes;
    private PaymentMethod paymentMethod;
}
