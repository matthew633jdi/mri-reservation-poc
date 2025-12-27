package soo365.co.kr.reservation.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import soo365.co.kr.reservation.service.ReservationService;
import soo365.co.kr.reservation.service.dto.CreateReservationCommand;
import soo365.co.kr.reservation.web.dto.ReservationCreateRequest;
import soo365.co.kr.reservation.web.dto.ReservationResponse;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> create(@RequestBody @Valid ReservationCreateRequest request) {
        CreateReservationCommand command = new CreateReservationCommand(
                request.employeeId(),
                request.reservationAt(),
                request.chartNumber(),
                request.patientName(),
                request.department(),
                request.treatmentStatus(),
                request.direction(),
                request.bodyPart(),
                Boolean.TRUE.equals(request.contrastedUsed()),
                request.memo()
                );

        ReservationResponse response = reservationService.createReservation(command);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);
    }
}
