package soo365.co.kr.reservation.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode {
    EMPLOYEE_NOT_FOUND(NOT_FOUND, "존재하지 않는 직원입니다."),
    RESERVATION_ALREADY_EXISTS(CONFLICT, "이미 예약된 시간입니다."),
    INVALID_RESERVATION_STATE(CONFLICT, "현재 예약 상태에서는 해당 작업을 수행할 수 없습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.status = httpStatus;
        this.message = message;
    }
}
