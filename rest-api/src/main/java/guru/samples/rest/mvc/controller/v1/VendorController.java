package guru.samples.rest.mvc.controller.v1;

import guru.samples.rest.mvc.api.v1.model.VendorDTO;
import guru.samples.rest.mvc.api.v1.model.VendorListDTO;
import guru.samples.rest.mvc.service.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static guru.samples.rest.mvc.controller.v1.VendorController.BASE_URL;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Api(description = "This API allows to perform CRUD operations with vendors")
@RestController
@RequestMapping(BASE_URL)
public class VendorController {

    public static final String BASE_URL = "/api/v1/vendors";

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @ApiOperation(value = "Get a list of all vendors")
    @GetMapping
    @ResponseStatus(OK)
    public VendorListDTO findAll() {
        return new VendorListDTO(vendorService.findAll());
    }

    @ApiOperation(value = "Find a vendor with specified ID", notes = "Throws ResourceNotFoundException")
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public VendorDTO findById(@PathVariable Long id) {
        return vendorService.findById(id);
    }

    @ApiOperation(value = "Create a new vendor")
    @PostMapping
    @ResponseStatus(CREATED)
    public VendorDTO create(@RequestBody VendorDTO vendorDTO) {
        return vendorService.create(vendorDTO);
    }

    @ApiOperation(value = "Full update of an existing vendor details", notes = "Throws ResourceNotFoundException")
    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public VendorDTO update(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.update(id, vendorDTO);
    }

    @ApiOperation(value = "Patch an existing vendor specified properties", notes = "Throws ResourceNotFoundException")
    @PatchMapping("/{id}")
    @ResponseStatus(OK)
    public VendorDTO patch(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patch(id, vendorDTO);
    }

    @ApiOperation(value = "Delete a vendor")
    @DeleteMapping("/{id}")
    @ResponseStatus(OK)
    public void delete(@PathVariable Long id) {
        vendorService.delete(id);
    }
}
