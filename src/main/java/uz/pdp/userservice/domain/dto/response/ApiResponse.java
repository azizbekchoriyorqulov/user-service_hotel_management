package uz.pdp.userservice.domain.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse<D> {
    private String message;
    private Integer status;
    private D data;
}
