package soo365.co.kr.reservation.service.dto;

import soo365.co.kr.reservation.domain.Department;
import soo365.co.kr.reservation.domain.TreatmentStatus;

import java.time.LocalDateTime;

public record CreateReservationCommand(
        Long employeeId,
        LocalDateTime reservationAt,
        Long chartNumber,
        String patientName,
        Department department,
        TreatmentStatus treatmentStatus,
        String direction,
        String bodyPart,
        boolean contrastUsed,
        String memo
) {
}
