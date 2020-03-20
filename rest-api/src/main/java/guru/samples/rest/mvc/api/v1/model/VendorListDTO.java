package guru.samples.rest.mvc.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VendorListDTO {

    List<VendorDTO> vendors;
}
