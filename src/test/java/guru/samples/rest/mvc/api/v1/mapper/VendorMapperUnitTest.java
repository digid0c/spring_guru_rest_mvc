package guru.samples.rest.mvc.api.v1.mapper;

import guru.samples.rest.mvc.api.v1.model.VendorDTO;
import guru.samples.rest.mvc.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class VendorMapperUnitTest {

    private static final VendorMapper VENDOR_MAPPER = VendorMapper.INSTANCE;
    private static final Long VENDOR_ID = 1L;
    private static final String VENDOR_NAME = "KFC";

    @Test
    public void shouldMapVendorToVendorDTO() {
        Vendor vendor = Vendor.builder()
                .id(VENDOR_ID)
                .name(VENDOR_NAME)
                .build();

        VendorDTO vendorDTO = VENDOR_MAPPER.vendorToVendorDTO(vendor);

        assertThat(vendorDTO, is(notNullValue()));
        assertThat(vendorDTO.getId(), is(equalTo(VENDOR_ID)));
        assertThat(vendorDTO.getName(), is(equalTo(VENDOR_NAME)));
    }

    @Test
    public void shouldMapVendorDTOToVendor() {
        VendorDTO vendorDTO = VendorDTO.builder()
                .id(VENDOR_ID)
                .name(VENDOR_NAME)
                .build();

        Vendor vendor = VENDOR_MAPPER.vendorDTOToVendor(vendorDTO);

        assertThat(vendor, is(notNullValue()));
        assertThat(vendor.getId(), is(equalTo(VENDOR_ID)));
        assertThat(vendor.getName(), is(equalTo(VENDOR_NAME)));
    }
}
