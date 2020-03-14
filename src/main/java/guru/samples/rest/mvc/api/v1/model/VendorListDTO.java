package guru.samples.rest.mvc.api.v1.model;

import guru.samples.rest.mvc.domain.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VendorListDTO {

    List<Vendor> vendors;
}
