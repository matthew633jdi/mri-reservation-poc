package soo365.co.kr.reservation.web.dto;

import soo365.co.kr.reservation.domain.*;

import java.time.LocalDateTime;

public record ReservationResponse(
        Long id,
        Long employeeId,
        LocalDateTime reservationAt,
        ReservationStatus reservationStatus,
        TreatmentStatus treatmentStatus,
        Department department,
        Long chartNumber,
        String patientName,
        String direction,
        String bodyPart,
        boolean contrastUsed,
        String memo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getEmployee().getId(),
                reservation.getReservationAt(),
                reservation.getReservationStatus(),
                reservation.getTreatmentStatus(),
                reservation.getDepartment(),
                reservation.getChartNumber(),
                reservation.getPatientName(),
                reservation.getDirection(),
                reservation.getBodyPart(),
                reservation.isContrastUsed(),
                reservation.getMemo(),
                reservation.getCreatedAt(),
                reservation.getUpdatedAt()
        );
    }
}
