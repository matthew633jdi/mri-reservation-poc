package soo365.co.kr.reservation.service.dto;

import soo365.co.kr.reservation.domain.Department;
import soo365.co.kr.reservation.domain.TreatmentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record SearchReservationCommand(
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


}
