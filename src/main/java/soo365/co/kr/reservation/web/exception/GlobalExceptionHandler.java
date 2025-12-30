package soo365.co.kr.reservation.web.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import soo365.co.kr.reservation.domain.exception.DomainException;
import soo365.co.kr.reservation.domain.exception.ErrorCode;
import soo365.co.kr.reservation.domain.exception.LoggableException;
import soo365.co.kr.reservation.web.error.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. 도메인 예외 (핵심)
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomain(DomainException e) {
        if (e instanceof LoggableException loggable) {
            log.warn(
                    "Domain exception occurred. code={}, context={}",
                    e.getErrorCode().name(),
                    loggable.logContext()
            );
        } else {
            log.warn(
                    "Domain exception occurred. code={}",
                    e.getErrorCode().name()
            );
        }
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.from(e.getErrorCode()));
    }

    // 2. Validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        log.warn("Validation failed", e);
        return ErrorResponse.validation(e);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolation(ConstraintViolationException e) {
        log.warn("Constraint violation", e);
        return ErrorResponse.validation(e);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        Throwable cause = e.getMostSpecificCause();
        if (cause instanceof InvalidEnumValueException iee) {
            return ErrorResponse.validation(
                    iee.getField(),
                    iee.getValue(),
                    iee.getAllowedValues()
            );
        }

        log.warn("Invalid request body", e);
        return ErrorResponse.of("INVALID_REQUEST_BODY", "요청 본문의 형식이 올바르지 않습니다.");
    }


    // 3. DB 무결성
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponse.of("DATA_INTEGRITY_ERROR", "데이터 무결성 오류"));
    }

    // 4. 최후의 보루
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknown(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of("INTERNAL_ERROR", "서버 오류"));
    }

}
