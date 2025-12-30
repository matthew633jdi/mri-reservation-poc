package soo365.co.kr.reservation.domain.exception.reservation;

import lombok.Getter;
import soo365.co.kr.reservation.domain.exception.ErrorCode;
import soo365.co.kr.reservation.domain.exception.LoggableException;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class DuplicateReservationException extends ReservationDomainException implements LoggableException {

    private final LocalDateTime reservationAt;

    public DuplicateReservationException(LocalDateTime reservationAt) {
        super(ErrorCode.RESERVATION_ALREADY_EXISTS);
        this.reservationAt = reservationAt;
    }

    @Override
    public Map<String, Object> logContext() {
        return Map.of(
                "reservationAt", reservationAt
        );
    }
}
