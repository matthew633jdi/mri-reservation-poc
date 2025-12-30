package soo365.co.kr.reservation.domain.exception.reservation;

import soo365.co.kr.reservation.domain.exception.DomainException;
import soo365.co.kr.reservation.domain.exception.ErrorCode;

public abstract class ReservationDomainException extends DomainException {

    protected ReservationDomainException(ErrorCode errorCode) {
        super(errorCode);
    }
}
