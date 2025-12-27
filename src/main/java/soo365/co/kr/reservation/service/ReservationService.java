package soo365.co.kr.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soo365.co.kr.reservation.domain.Employee;
import soo365.co.kr.reservation.domain.Reservation;
import soo365.co.kr.reservation.repository.EmployeeRepository;
import soo365.co.kr.reservation.repository.ReservationRepository;
import soo365.co.kr.reservation.service.dto.CreateReservationCommand;
import soo365.co.kr.reservation.web.dto.ReservationResponse;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final EmployeeRepository employeeRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public ReservationResponse createReservation(CreateReservationCommand command) {
        Employee employee = employeeRepository.findByEmployeeId(command.employeeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직원입니다."));
        Reservation reservation = Reservation.create(
                employee,
                command.reservationAt(),
                command.chartNumber(),
                command.patientName(),
                command.department()
        );
        reservation.changeTreatmentStatus(command.treatmentStatus());
        reservation.updateDirection(command.direction());
        reservation.updateBodyPart(command.bodyPart());
        if (command.contrastUsed()) {
            reservation.useContrast();
        }
        reservation.updateMemo(command.memo());
        reservationRepository.save(reservation);
        return ReservationResponse.from(reservation);
    }
}
