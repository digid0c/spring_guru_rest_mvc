package guru.samples.rest.mvc.api.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "This model represents Vendor entity")
public class VendorDTO {

    @ApiModelProperty(value = "Vendor ID")
    private Long id;

    @ApiModelProperty(value = "Vendor name", required = true)
    private String name;
}
