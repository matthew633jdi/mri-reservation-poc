package soo365.co.kr.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soo365.co.kr.reservation.domain.Employee;
import soo365.co.kr.reservation.domain.Reservation;
import soo365.co.kr.reservation.domain.exception.employee.EmployeeNotFoundException;
import soo365.co.kr.reservation.domain.exception.reservation.DuplicateReservationException;
import soo365.co.kr.reservation.repository.EmployeeRepository;
import soo365.co.kr.reservation.repository.ReservationRepository;
import soo365.co.kr.reservation.service.dto.CreateReservationCommand;
import soo365.co.kr.reservation.service.dto.SearchReservationCommand;
import soo365.co.kr.reservation.service.dto.SearchReservationQuery;
import soo365.co.kr.reservation.web.dto.ReservationResponse;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final EmployeeRepository employeeRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    public ReservationResponse createReservation(CreateReservationCommand command) {
        try {

            Employee employee = employeeRepository.findByEmrId(command.emrId())
                    .orElseThrow(() -> new EmployeeNotFoundException(command.emrId()));
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
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateReservationException(command.reservationAt());
        }
    }

    @Transactional(readOnly = true)
    public Page<ReservationResponse> search(SearchReservationCommand command, Pageable pageable) {
        Long employeeId = null;
        if (command.emrId() != null) {
            employeeId = employeeRepository.findIdByEmrId(command.emrId())
                    .orElseThrow(() -> new EmployeeNotFoundException(command.emrId()));
        }
        SearchReservationQuery query = new SearchReservationQuery(
                employeeId,
                command.reservationDate(),
                command.reservationFrom(),
                command.reservationTo(),
                command.treatmentStatus(),
                command.department(),
                command.chartNumber(),
                command.patientName(),
                command.bodyPart(),
                command.contrastUsed(),
                command.createdAt(),
                command.updatedAt()
        );
        return reservationRepository.search(query, pageable).map(ReservationResponse::from);
    }
}
