package soo365.co.kr.reservation.domain.exception.employee;

import lombok.Getter;
import soo365.co.kr.reservation.domain.exception.ErrorCode;
import soo365.co.kr.reservation.domain.exception.LoggableException;

import java.util.Map;

@Getter
public class EmployeeNotFoundException extends EmployeeDomainException implements LoggableException {

    private final String emrId;

    public EmployeeNotFoundException(String emrId) {
        super(ErrorCode.EMPLOYEE_NOT_FOUND);
        this.emrId = emrId;
    }

    @Override
    public Map<String, Object> logContext() {
        return Map.of(
                "emrId", emrId
        );
    }
}
