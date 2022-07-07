package com.kaamcube.truelysell.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.kaamcube.truelysell.utility.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "booked_services")
public class BookedServices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIdentityReference(alwaysAsId=true)
    @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @JsonIdentityReference(alwaysAsId=true)
    @OneToOne
    @JoinColumn(name = "service_id", nullable = false)
    private VendorServices vendorServices;

    private String serviceLocation;
    private String serviceAmount;
    private String date;
    private String timeSlot;
    private String notes;
    private PaymentMethod paymentMethod;

}
