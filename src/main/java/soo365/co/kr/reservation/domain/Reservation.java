package soo365.co.kr.reservation.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(nullable = false)
    private LocalDateTime reservationAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus reservationStatus;

    @Enumerated(EnumType.STRING)
    private TreatmentStatus treatmentStatus;

    private Long chartNumber;

    private String patientName;

    private String direction;

    private String bodyPart;

    @Column(nullable = false)
    private boolean contrastUsed;

    @Lob
    private String memo;

    private Reservation(Employee employee, LocalDateTime reservationAt, Long chartNumber, String patientName) {
        this.employee = employee;
        this.reservationAt = reservationAt;
        this.contrastUsed = false;  // 설계를 위한 조치
        this.chartNumber = chartNumber;
        this.patientName = patientName;
        this.reservationStatus = ReservationStatus.BOOKED;
    }

    public static Reservation create(Employee employee, LocalDateTime reservationAt, Long chartNumber, String patientName) {
        return new Reservation(employee, reservationAt, chartNumber, patientName);
    }

    public void useContrast() {
        this.contrastUsed = true;
    }

    public void cancelContrast() {
        this.contrastUsed = false;
    }

    public void changeReservationStatus(ReservationStatus status) {
        // TODO 도메인 예외 처리
        if (this.reservationStatus == ReservationStatus.CANCELED) {
            throw new IllegalStateException("이미 취소된 예약입니다.");
        }
        this.reservationStatus = status;
    }


    public void changeTreatmentStatus(TreatmentStatus status) {
        this.treatmentStatus = status;
    }

    public void updateDirection(String direction) {
        this.direction = (direction == null || direction.isBlank()) ? null : direction;
    }

    public void updateBodyPart(String bodyPart) {
        this.bodyPart = (bodyPart == null || bodyPart.isBlank()) ? null : bodyPart;
    }

    public void updatePatient(String name, Long chartNumber) {
        this.patientName = name;
        this.chartNumber = chartNumber;
    }

}