package com.kaamcube.truelysell.controller;

import com.kaamcube.truelysell.model.request.*;
import com.kaamcube.truelysell.model.responce.Response;
import com.kaamcube.truelysell.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@PostMapping("/registerVendor")
	private Response registerVendor(@Valid @RequestBody RegistrationRequest vendorRequest) {
		return vendorService.registerVendor(vendorRequest);
	}

	@PostMapping("/otpValidation")
	public Response otpValidation(@Valid@RequestBody OtpRequest otpRequest ){
		return vendorService.otpValidation(otpRequest);
	}

	@PostMapping("/vendorLogin")
	public Response vendorLogin(@Valid@RequestBody LoginRequest loginRequest ){
		return vendorService.vendorLogin(loginRequest);
	}

	@GetMapping("/getVendor")
	public Response updateVendor(@Valid@RequestParam Long vendorId){

		return vendorService.getVendor(vendorId);
	}

	@PostMapping("/updateVendor")
	public Response updateVendor(@Valid@RequestBody VendorRequest vendorRequest){
		return vendorService.updateVendor(vendorRequest);
	}

	@PostMapping("/postService")
	public Response postAvailability(@Valid @ RequestBody AvailabilityRequest availabilityRequest,@RequestParam Long vendorId){
		return vendorService.postAvailability(availabilityRequest,vendorId);
	}

	@PostMapping("/addService")
	public Response addService(@Valid @RequestBody AddServiceRequest addServiceRequest,@RequestParam Long vendorId){
		return vendorService.addService(addServiceRequest,vendorId);
	}

	@GetMapping("/getService")
	public Response getService (@Valid @RequestParam Long vendorId){
		return vendorService.getService(vendorId);
	}

	@GetMapping("/getServiceDetails")
	public Response getServiceDetails (@Valid @RequestParam Long vendorId,@RequestParam Long vendorServiceId){
		return vendorService.getServiceDetails(vendorId,vendorServiceId);
	}

	@GetMapping("/getAllServices")
	public Response getAllServices (){
		return vendorService.getAllServices();
	}
}
