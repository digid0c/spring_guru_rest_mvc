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
@ApiModel(value = "This model represents Customer entity")
public class CustomerDTO {

    @ApiModelProperty(value = "Customer ID")
    private Long id;

    @ApiModelProperty(value = "Customer first name")
    private String firstName;

    @ApiModelProperty(value = "Customer last name")
    private String lastName;
}
