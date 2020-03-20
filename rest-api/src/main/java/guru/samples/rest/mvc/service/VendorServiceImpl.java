package guru.samples.rest.mvc.service;

import guru.samples.rest.mvc.api.v1.model.VendorDTO;
import guru.samples.rest.mvc.exception.ResourceNotFoundException;
import guru.samples.rest.mvc.api.v1.mapper.VendorMapper;
import guru.samples.rest.mvc.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;
    private final VendorMapper vendorMapper;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }

    @Override
    public List<VendorDTO> findAll() {
        return vendorRepository.findAll()
                .stream()
                .map(vendorMapper::vendorToVendorDTO)
                .collect(toList());
    }

    @Override
    public VendorDTO findById(Long vendorId) {
        return vendorRepository.findById(vendorId)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO create(VendorDTO vendorDTO) {
        return createOrUpdate(vendorDTO);
    }

    @Override
    public VendorDTO update(Long vendorId, VendorDTO vendorDTO) {
        vendorDTO.setId(vendorId);
        return createOrUpdate(vendorDTO);
    }

    @Override
    public VendorDTO patch(Long vendorId, VendorDTO vendorDTO) {
        return vendorRepository.findById(vendorId)
                .map(vendor -> {
                    ofNullable(vendorDTO.getName()).ifPresent(vendor::setName);
                    return vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void delete(Long vendorId) {
        vendorRepository.deleteById(vendorId);
    }

    private VendorDTO createOrUpdate(VendorDTO vendorDTO) {
        return Optional.of(vendorDTO)
                .map(vendorMapper::vendorDTOToVendor)
                .map(vendorRepository::save)
                .map(vendorMapper::vendorToVendorDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
