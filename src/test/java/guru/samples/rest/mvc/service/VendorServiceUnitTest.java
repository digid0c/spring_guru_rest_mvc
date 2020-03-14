package guru.samples.rest.mvc.service;

import guru.samples.rest.mvc.api.v1.mapper.VendorMapper;
import guru.samples.rest.mvc.api.v1.model.VendorDTO;
import guru.samples.rest.mvc.domain.Vendor;
import guru.samples.rest.mvc.repository.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class VendorServiceUnitTest {

    private static final Long VENDOR_ID = 1L;
    private static final String VENDOR_NAME = "KFC";

    @Mock
    private VendorRepository vendorRepository;

    private VendorService tested;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        tested = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void shouldFindAllVendors() {
        List<Vendor> vendors = asList(new Vendor(), new Vendor(), new Vendor());
        when(vendorRepository.findAll()).thenReturn(vendors);

        List<VendorDTO> vendorDTOs = tested.findAll();

        assertThat(vendorDTOs, is(notNullValue()));
        assertThat(vendorDTOs.size(), is(equalTo(vendors.size())));
    }

    @Test
    public void shouldFindVendorById() {
        Vendor vendor = createExistingVendor();
        when(vendorRepository.findById(VENDOR_ID)).thenReturn(Optional.of(vendor));

        VendorDTO vendorDTO = tested.findById(VENDOR_ID);

        assertThat(vendorDTO, is(notNullValue()));
        assertThat(vendorDTO.getId(), is(equalTo(VENDOR_ID)));
        assertThat(vendorDTO.getName(), is(equalTo(VENDOR_NAME)));
    }

    @Test
    public void shouldCreateNewVendor() {
        VendorDTO vendorToCreate = createIncomingRequestVendorData();
        Vendor existingVendor = createExistingVendor();
        when(vendorRepository.save(any(Vendor.class))).thenReturn(existingVendor);

        VendorDTO createdVendorDTO = tested.create(vendorToCreate);

        assertThat(createdVendorDTO, is(notNullValue()));
        assertThat(createdVendorDTO.getId(), is(equalTo(VENDOR_ID)));
        assertThat(createdVendorDTO.getName(), is(equalTo(VENDOR_NAME)));
    }

    @Test
    public void shouldUpdateExistingVendor() {
        VendorDTO vendorToUpdate = createIncomingRequestVendorData();
        Vendor existingVendor = createExistingVendor();
        when(vendorRepository.save(any(Vendor.class))).thenReturn(existingVendor);

        VendorDTO updatedVendorDTO = tested.update(VENDOR_ID, vendorToUpdate);

        assertThat(updatedVendorDTO, is(notNullValue()));
        assertThat(updatedVendorDTO.getId(), is(equalTo(VENDOR_ID)));
        assertThat(updatedVendorDTO.getName(), is(equalTo(VENDOR_NAME)));
    }

    @Test
    public void shouldDeleteExistingVendor() {
        tested.delete(VENDOR_ID);

        verify(vendorRepository).deleteById(VENDOR_ID);
    }

    private Vendor createExistingVendor() {
        return Vendor.builder()
                .id(VENDOR_ID)
                .name(VENDOR_NAME)
                .build();
    }

    private VendorDTO createIncomingRequestVendorData() {
        return VendorDTO.builder()
                .name(VENDOR_NAME)
                .build();
    }
}
