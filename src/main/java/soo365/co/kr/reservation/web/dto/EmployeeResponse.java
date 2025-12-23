package soo365.co.kr.reservation.web.dto;

import soo365.co.kr.reservation.domain.Employee;
import soo365.co.kr.reservation.domain.Role;

public record EmployeeResponse(
    Long id,
    String emrId,
    String name,
    Role role
) {

    public static EmployeeResponse from(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getEmrId(),
                employee.getName(),
                employee.getRole()
        );
    }
}
