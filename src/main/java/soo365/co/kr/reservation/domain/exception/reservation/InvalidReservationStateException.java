package soo365.co.kr.reservation.domain.exception.reservation;

import lombok.Getter;
import soo365.co.kr.reservation.domain.ReservationStatus;
import soo365.co.kr.reservation.domain.exception.ErrorCode;
import soo365.co.kr.reservation.domain.exception.LoggableException;

import java.util.Map;

@Getter
public class InvalidReservationStateException extends ReservationDomainException implements LoggableException {

    private final ReservationStatus status;

    public InvalidReservationStateException(ReservationStatus status) {
        super(ErrorCode.INVALID_RESERVATION_STATE);
        this.status = status;
    }

    @Override
    public Map<String, Object> logContext() {
        return Map.of(
                "status", status.name()
        );
    }
}
