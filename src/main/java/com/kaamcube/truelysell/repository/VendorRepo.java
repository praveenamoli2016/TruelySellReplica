package com.kaamcube.truelysell.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaamcube.truelysell.model.entity.Vendor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepo extends JpaRepository<Vendor, Long>{
    Optional<Vendor> findByMobileNumber(String mobileNo);

}
