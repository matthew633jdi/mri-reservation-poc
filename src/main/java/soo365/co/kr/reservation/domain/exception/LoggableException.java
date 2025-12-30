package soo365.co.kr.reservation.domain.exception;

import java.util.Map;

public interface LoggableException {
    Map<String, Object> logContext();
}
