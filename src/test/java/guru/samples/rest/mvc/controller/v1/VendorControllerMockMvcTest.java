package guru.samples.rest.mvc.controller.v1;

import guru.samples.rest.mvc.api.v1.model.VendorDTO;
import guru.samples.rest.mvc.service.VendorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static guru.samples.rest.mvc.controller.v1.VendorController.BASE_URL;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {VendorController.class})
public class VendorControllerMockMvcTest {

    private static final Long VENDOR_ID = 1L;
    private static final String VENDOR_NAME = "KFC";

    private static final String BASE_URL_WITH_VENDOR_ID = BASE_URL + "/" + VENDOR_ID;

    @MockBean
    private VendorService vendorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldFindAllVendors() throws Exception {
        List<VendorDTO> vendors = asList(new VendorDTO(), new VendorDTO(), new VendorDTO());
        when(vendorService.findAll()).thenReturn(vendors);

        mockMvc.perform(get(BASE_URL)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(vendors.size())));
    }

    @Test
    public void shouldFindVendorById() throws Exception {
        VendorDTO vendor = createExistingVendor();
        when(vendorService.findById(VENDOR_ID)).thenReturn(vendor);

        mockMvc.perform(get(BASE_URL_WITH_VENDOR_ID)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(equalTo(VENDOR_ID.intValue()))))
                .andExpect(jsonPath("$.name", is(equalTo(VENDOR_NAME))));
    }

    private VendorDTO createExistingVendor() {
        return VendorDTO.builder()
                .id(VENDOR_ID)
                .name(VENDOR_NAME)
                .build();
    }
}
