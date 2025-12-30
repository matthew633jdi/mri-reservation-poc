package soo365.co.kr.reservation.domain.exception.employee;

import soo365.co.kr.reservation.domain.exception.DomainException;
import soo365.co.kr.reservation.domain.exception.ErrorCode;

public abstract class EmployeeDomainException extends DomainException {

    protected EmployeeDomainException(ErrorCode errorCode) {
        super(errorCode);
    }
}
