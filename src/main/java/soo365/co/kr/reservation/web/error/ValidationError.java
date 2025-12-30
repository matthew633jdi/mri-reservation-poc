package soo365.co.kr.reservation.web.error;

public record ValidationError (
        String field,
        Object rejectedValue,
        String message
) {
}
