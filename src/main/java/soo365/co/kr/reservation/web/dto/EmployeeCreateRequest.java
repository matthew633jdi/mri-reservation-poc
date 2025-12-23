package soo365.co.kr.reservation.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import soo365.co.kr.reservation.domain.Role;

public record EmployeeCreateRequest(
//        @JsonProperty("emr_id")
        @NotBlank
        @Size(min = 4, max = 20)
        String emrId,

        @NotBlank
        @Size(min = 3, max = 20)
        String name,

        @NotBlank
        @Size(min = 8, max = 64)
        String password,

        @NotNull
        Role role
) {
}
