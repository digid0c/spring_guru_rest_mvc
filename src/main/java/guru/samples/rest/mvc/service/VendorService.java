package guru.samples.rest.mvc.service;

import guru.samples.rest.mvc.api.v1.model.VendorDTO;

import java.util.List;

public interface VendorService {

    List<VendorDTO> findAll();

    VendorDTO findById(Long vendorId);

    VendorDTO create(VendorDTO vendorDTO);

    VendorDTO update(Long vendorId, VendorDTO vendorDTO);

    VendorDTO patch(Long vendorId, VendorDTO vendorDTO);

    void delete(Long vendorId);
}
