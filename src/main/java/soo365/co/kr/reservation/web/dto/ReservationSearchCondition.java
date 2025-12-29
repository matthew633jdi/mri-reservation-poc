package soo365.co.kr.reservation.web.dto;

import soo365.co.kr.reservation.domain.Department;
import soo365.co.kr.reservation.domain.TreatmentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReservationSearchCondition(
        String emrId,
        LocalDate reservationDate,
        LocalDateTime reservationFrom,
        LocalDateTime reservationTo,
        TreatmentStatus treatmentStatus,
        Department department,
        Long chartNumber,
        String patientName,
        String bodyPart,
        Boolean contrastUsed,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public void validate() {
        validateDateCondition();
        validateRange();
    }

    private void validateDateCondition() {
        if (reservationDate != null &&
                (reservationFrom != null || reservationTo != null)) {
            throw new IllegalArgumentException(
                    "reservationDate cannot be used with reservationFrom/reservationTo"
            );
        }
    }

    private void validateRange() {
        if (reservationFrom != null && reservationTo != null) {
            if (reservationFrom.isAfter(reservationTo)) {
                throw new IllegalArgumentException(
                        "reservationFrom must be before reservationTo"
                );
            }
        }
    }
}
