package guru.samples.rest.mvc.controller.v1;

import guru.samples.rest.mvc.api.v1.model.VendorDTO;
import guru.samples.rest.mvc.api.v1.model.VendorListDTO;
import guru.samples.rest.mvc.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static guru.samples.rest.mvc.controller.v1.VendorController.BASE_URL;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    @ResponseStatus(OK)
    public VendorListDTO findAll() {
        return new VendorListDTO(vendorService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public VendorDTO findById(@PathVariable Long id) {
        return vendorService.findById(id);
    }
}
