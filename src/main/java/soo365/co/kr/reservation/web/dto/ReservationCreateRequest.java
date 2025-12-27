package soo365.co.kr.reservation.web.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import soo365.co.kr.reservation.domain.Department;
import soo365.co.kr.reservation.domain.TreatmentStatus;

import java.time.LocalDateTime;

public record ReservationCreateRequest(
        @NotBlank
        Long employeeId,

        @NotNull
        @FutureOrPresent
        LocalDateTime reservationAt,

        @NotNull
        Long chartNumber,

        @NotBlank
        String patientName,

        @NotNull
        Department department,

        TreatmentStatus treatmentStatus,
        String direction,
        String bodyPart,

        Boolean contrastedUsed,

        String memo

) {
}
