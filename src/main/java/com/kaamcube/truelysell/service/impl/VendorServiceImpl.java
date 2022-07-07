package com.kaamcube.truelysell.service.impl;

import com.kaamcube.truelysell.model.entity.ServiceOffer;
import com.kaamcube.truelysell.model.entity.Vendor;
import com.kaamcube.truelysell.model.entity.VendorAvailability;
import com.kaamcube.truelysell.model.entity.VendorServices;
import com.kaamcube.truelysell.model.request.*;
import com.kaamcube.truelysell.model.request.dto.ServiceOfferedDto;
import com.kaamcube.truelysell.model.responce.Response;
import com.kaamcube.truelysell.model.responce.VendorRegisterResponse;
import com.kaamcube.truelysell.model.responce.VendorServicesResponse;
import com.kaamcube.truelysell.repository.ServiceOfferRepo;
import com.kaamcube.truelysell.repository.VendorAvailabilityRepo;
import com.kaamcube.truelysell.repository.VendorRepo;
import com.kaamcube.truelysell.repository.VendorServiceRepo;
import com.kaamcube.truelysell.service.VendorService;
import com.kaamcube.truelysell.utility.TruelySellUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    private VendorRepo vendorRepo;

    @Autowired
    private VendorAvailabilityRepo vendorAvailabilityRepo;

    @Override
    public Response registerVendor(RegistrationRequest vendorRequest) {
        Response response = null;
        try {
            //preparing vendor details
            Vendor vendor = new Vendor();
            vendor.setName(vendorRequest.getName());
            vendor.setMobileNumber(vendorRequest.getMobileNo());
            vendor.setEmail(vendorRequest.getEmail());
            vendor.setPassword(vendorRequest.getPassword());
            vendor.setTermsAndCondition(vendorRequest.getTermsAndCondition());
            vendor.setCreatedDate(LocalDateTime.now().toString());



            //saving vendor details on database
            Vendor vendorData = vendorRepo.save(vendor);

            //preparing response
            VendorRegisterResponse vendorRegisterResponse = new VendorRegisterResponse(vendorData.getName(), vendorData.getEmail(), vendorData.getPassword(), vendorData.getMobileNumber(), vendorData.getTermsAndCondition(), vendorData.getCreatedDate(), vendorData.getUpdatedDate());

            //preparing response for sending back
            response = new Response("Success", "201", "Vendor added successfully", vendorRegisterResponse);

            //sending otp to verify mobile no and save otp in data for future validation
            String otp = TruelySellUtility.sendOTP(vendorData.getMobileNumber());
            vendorData.setOtp(otp);
            //updating otp in Database
            vendorRepo.save(vendorData);

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response otpValidation(OtpRequest otpRequest) {
        Response response = null;
        try {
            Optional<Vendor> vendorOptional = vendorRepo.findByMobileNumber(otpRequest.getMobileNo());
            if (vendorOptional.isPresent()) {
                Vendor vendor = vendorOptional.get();
                if (vendor.getOtp().equals(otpRequest.getOtp())) {
                    response = new Response("Success", "200", "otp verified", null);
                } else {
                    response = new Response("Failure", "401", "otp mismatched", null);
                }
            } else {
                response = new Response("Failure", "404", "Vendor not found with given mobile number", null);
            }
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response vendorLogin(LoginRequest loginRequest) {
        Response response = null;
        try {
            Optional<Vendor> vendorOptional = vendorRepo.findByMobileNumber(loginRequest.getMobileNo());
            if (vendorOptional.isPresent()) {
                Vendor vendor = vendorOptional.get();
                String otp = TruelySellUtility.sendOTP(vendor.getMobileNumber());
                vendor.setOtp(otp);
                //updating otp in Database
                vendorRepo.save(vendor);
                response = new Response("Success", "200", "otp sent successfully", null);
            } else {
                response = new Response("Failure", "404", "Vendor not found with given mobile number", null);
            }
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response getVendor(Long vendorId) {
        Response response = null;
        try {
            Optional<Vendor> vendorOptional = vendorRepo.findById(vendorId);
            if (vendorOptional.isPresent()) {
                Vendor vendor = vendorOptional.get();
                response = new Response("Success", "200", "vendor fetched successfully", vendor);
            } else {
                response = new Response("Failure", "404", "Vendor not found ", null);
            }
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response updateVendor(VendorRequest vendorRequest) {
        Response response = null;
        try {
            Optional<Vendor> vendorOptional = vendorRepo.findById(vendorRequest.getId());
            if (vendorOptional.isPresent()) {
                Vendor vendor = vendorOptional.get();
                //preparing vendor details
                vendor.setName(vendorRequest.getName());
                vendor.setMobileNumber(vendorRequest.getMobileNumber());
                vendor.setEmail(vendorRequest.getEmail());
                vendor.setPassword(vendorRequest.getPassword());
                vendor.setTermsAndCondition(vendorRequest.getTermsAndCondition());
                vendor.setAddress(vendorRequest.getAddress());
                vendor.setCountry(vendorRequest.getCountry());
                vendor.setCountryCode(vendorRequest.getCountryCode());
                vendor.setDateOfBirth(vendorRequest.getDateOfBirth());
                vendor.setState(vendorRequest.getState());
                vendor.setCity(vendorRequest.getCity());
                vendor.setAccountHolderName(vendorRequest.getAccountHolderName());
                vendor.setPanNo(vendorRequest.getPanNo());
                vendor.setPostalCode(vendorRequest.getPostalCode());
                vendor.setBankAddress(vendorRequest.getBankAddress());
                vendor.setBankName(vendorRequest.getBankName());
                vendor.setAcountEmailId(vendorRequest.getAcountEmailId());
                vendor.setIFSCCode(vendorRequest.getIFSCCode());
                vendor.setSortCode(vendorRequest.getSortCode());
                vendor.setPaymentPurchage(vendorRequest.getPaymentPurchage());
                vendor.setRoutingNo(vendorRequest.getRoutingNo());
                //saving vendor details on database
                Vendor updatedVendorData = vendorRepo.save(vendor);
                response = new Response("Success", "200", "Vendor updated ", updatedVendorData);
            } else {
                response = new Response("Failure", "404", "Vendor not found ", null);
            }
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response postAvailability(AvailabilityRequest availabilityRequest, Long vendorId) {
        Response response = null;
        try {

            List<VendorAvailability> vendorAvailabilityList = new ArrayList<>();
            if (availabilityRequest.getAvailabilityRequestDtos().get(0).getDay().equalsIgnoreCase("All")) {
                VendorAvailability vendorAvailability = new VendorAvailability();
                vendorAvailability.setVendorId(vendorId);
                vendorAvailability.setDay(availabilityRequest.getAvailabilityRequestDtos().get(0).getDay());
                vendorAvailability.setFromDate(availabilityRequest.getAvailabilityRequestDtos().get(0).getFromDate());
                vendorAvailability.setToDate(availabilityRequest.getAvailabilityRequestDtos().get(0).getToDate());
                vendorAvailabilityList.add(vendorAvailability);
            } else {
                for (int i = 0; i < 7; i++) {
                    VendorAvailability vendorAvailability = new VendorAvailability();
                    vendorAvailability.setVendorId(vendorId);
                    vendorAvailability.setDay(availabilityRequest.getAvailabilityRequestDtos().get(0).getDay());
                    vendorAvailability.setFromDate(availabilityRequest.getAvailabilityRequestDtos().get(0).getFromDate());
                    vendorAvailability.setToDate(availabilityRequest.getAvailabilityRequestDtos().get(0).getToDate());
                    vendorAvailabilityList.add(vendorAvailability);
                }
            }
            List<VendorAvailability> vendorAvailabilities = vendorAvailabilityRepo.saveAll(vendorAvailabilityList);
            response = new Response("Success", "201", "Availibility added succesfully", vendorAvailabilities);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Autowired
    private VendorServiceRepo vendorServiceRepo;

    @Autowired
    private ServiceOfferRepo serviceOfferRepo;

    @Override
    public Response addService(AddServiceRequest addServiceRequest, Long vendorId) {
        Response response = null;
        try {
            VendorServices vendorServices = new VendorServices();
            vendorServices.setVendorId(vendorId);
            vendorServices.setServiceAmount(addServiceRequest.getServiceAmount());
            vendorServices.setServiceLocation(addServiceRequest.getServiceLocation());
            vendorServices.setServiceTitle(addServiceRequest.getServiceTitle());
            vendorServices.setCategory(addServiceRequest.getCategory());
            vendorServices.setSubcategory(addServiceRequest.getSubCategory());
            vendorServices.setDescriptions(addServiceRequest.getDescriptions());

            Set<ServiceOffer> serviceOffers = new HashSet<>();
            for (int i = 0; i < addServiceRequest.getServiceOfferedDtos().size(); i++) {
                ServiceOffer serviceOffer = new ServiceOffer();
                serviceOffer.setServiceOffered(addServiceRequest.getServiceOfferedDtos().get(i).getServiceOffered());
                serviceOffer.setCreatedAt(LocalDateTime.now().toString());
                vendorServices.addService(serviceOffer);
                serviceOffers.add(serviceOffer);
            }
//            serviceOfferRepo.saveAll(serviceOffers);

//            vendorServices.setServiceOffered(serviceOffers);
            vendorServiceRepo.save(vendorServices);

        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

    @Override
    public Response getService(Long vendorId) {
        Response response = null;
        try {
            List<VendorServices> vendorServices = vendorServiceRepo.findByVendorId(vendorId);

            List<VendorServicesResponse> actualResponse = new ArrayList<>();
            vendorServices.forEach(vendorService -> {
                VendorServicesResponse vendorServicesResponse = new VendorServicesResponse();
                Set<ServiceOfferedDto> serviceOfferedDto = new HashSet<>();
                vendorService.getServiceOffered().forEach(service -> {
                    ServiceOfferedDto serviceOffer = new ServiceOfferedDto();
                    serviceOffer.setServiceOffered(service.getServiceOffered());
                    serviceOfferedDto.add(serviceOffer);
                });
                vendorServicesResponse.setServiceOffered(serviceOfferedDto);
                vendorServicesResponse.setVendorId(vendorId);
                vendorServicesResponse.setServiceAmount(vendorService.getServiceAmount());
                vendorServicesResponse.setServiceLocation(vendorService.getServiceLocation());
                vendorServicesResponse.setServiceTitle(vendorService.getServiceTitle());
                vendorServicesResponse.setCategory(vendorService.getCategory());
                vendorServicesResponse.setDescriptions(vendorService.getDescriptions());
                vendorServicesResponse.setSubcategory(vendorService.getSubcategory());
                actualResponse.add(vendorServicesResponse);
            } );

            response = new Response("Success", "200", "VendorServices fetched successfully", actualResponse);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }

        return response;
    }

    @Override
    public Response getServiceDetails(Long vendorId, Long vendorServiceId) {
        Response response = null;
        try {
            Optional<VendorServices> vendorServices = vendorServiceRepo.findByIdAndVendorId(vendorServiceId, vendorId);
            if (vendorServices.isPresent()) {
                VendorServices vendorService = vendorServices.get();
                Set<ServiceOfferedDto> serviceOfferedDtos = new HashSet<>();
                vendorService.getServiceOffered().forEach(serviceOffer -> {
                    ServiceOfferedDto serviceOfferedDto = new ServiceOfferedDto();
                    serviceOfferedDto.setServiceOffered(serviceOffer.getServiceOffered());
                    serviceOfferedDtos.add(serviceOfferedDto);
                });
                VendorServicesResponse vendorServicesResponse = new VendorServicesResponse();
                vendorServicesResponse.setServiceOffered(serviceOfferedDtos);
                vendorServicesResponse.setVendorId(vendorId);
                vendorServicesResponse.setServiceAmount(vendorService.getServiceAmount());
                vendorServicesResponse.setServiceLocation(vendorService.getServiceLocation());
                vendorServicesResponse.setServiceTitle(vendorService.getServiceTitle());
                vendorServicesResponse.setCategory(vendorService.getCategory());
                vendorServicesResponse.setDescriptions(vendorService.getDescriptions());
                vendorServicesResponse.setSubcategory(vendorService.getSubcategory());
                response = new Response("Success", "200", "VendorServiceDetails fetched successfully", vendorServicesResponse);

            } else {
                response = new Response("Success", "404", "VendorServiceDetails notFound", null);

            }


        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }

        return response;
    }

    @Override
    public Response getAllServices() {
        Response response = null;
        try {
            List<VendorServices> vendorServices = vendorServiceRepo.findAll();
            List<VendorServicesResponse> actualResponse = new ArrayList<>();
            vendorServices.forEach(vendorService -> {
                VendorServicesResponse vendorServicesResponse = new VendorServicesResponse();
                Set<ServiceOfferedDto> serviceOfferedDto = new HashSet<>();
                vendorService.getServiceOffered().forEach(service -> {
                    ServiceOfferedDto serviceOffer = new ServiceOfferedDto();
                    serviceOffer.setServiceOffered(service.getServiceOffered());
                    serviceOfferedDto.add(serviceOffer);
                });
                vendorServicesResponse.setServiceOffered(serviceOfferedDto);
                vendorServicesResponse.setVendorId(vendorService.getVendorId());
                vendorServicesResponse.setServiceAmount(vendorService.getServiceAmount());
                vendorServicesResponse.setServiceLocation(vendorService.getServiceLocation());
                vendorServicesResponse.setServiceTitle(vendorService.getServiceTitle());
                vendorServicesResponse.setCategory(vendorService.getCategory());
                vendorServicesResponse.setDescriptions(vendorService.getDescriptions());
                vendorServicesResponse.setSubcategory(vendorService.getSubcategory());
                actualResponse.add(vendorServicesResponse);
            } );
            response = new Response("Success", "200", "All Services fetched successfully", actualResponse);
        } catch (Exception exception) {
            response = new Response("Failure", "500", exception.getMessage(), null);
        }
        return response;
    }

}
