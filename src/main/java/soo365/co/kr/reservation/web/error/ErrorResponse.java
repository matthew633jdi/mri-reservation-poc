package soo365.co.kr.reservation.web.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import soo365.co.kr.reservation.domain.exception.ErrorCode;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    private final String code;
    private final String message;
    private final List<ValidationError> errors;

    public ErrorResponse(String code, String message, List<ValidationError> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    /** DomainException 전용 */
    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(
                errorCode.name(),
                errorCode.getMessage(),
                List.of()
        );
    }

    /** Validation 에러 전용 */
    public static ErrorResponse validation(MethodArgumentNotValidException e) {
        List<ValidationError> errors =
                e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(error -> new ValidationError(
                                error.getField(),
                                error.getRejectedValue(),
                                error.getDefaultMessage()
                        ))
                        .toList();

        return new ErrorResponse(
                "INVALID_REQUEST",
                "요청 값이 올바르지 않습니다.",
                errors
        );
    }

    public static ErrorResponse validation(ConstraintViolationException e) {
        List<ValidationError> errors =
                e.getConstraintViolations()
                        .stream()
                        .map(v -> new ValidationError(
                                v.getPropertyPath().toString(),
                                v.getInvalidValue(),
                                v.getMessage()
                        ))
                        .toList();

        return new ErrorResponse(
                "INVALID_REQUEST",
                "요청 값이 올바르지 않습니다.",
                errors
        );
    }

    /** Enum 값 검증 전용 */
    public static ErrorResponse validation(String field, Object rejectedValue, List<String> allowedValues) {
        ValidationError error = new ValidationError(
                field,
                rejectedValue,
                "허용되지 않는 값입니다. 허용 값: " + allowedValues
        );

        return new ErrorResponse(
                "INVALID_REQUEST",
                "요청 값이 올바르지 않습니다.",
                List.of(error)
        );
    }



    /** 임의 에러 (DataIntegrity, 예상 못한 예외 등) */
    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message, List.of());
    }
}
