package guru.samples.rest.mvc.service;

import guru.samples.rest.mvc.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    List<VendorDTO> findAll();

    VendorDTO findById(Long vendorId);
}
