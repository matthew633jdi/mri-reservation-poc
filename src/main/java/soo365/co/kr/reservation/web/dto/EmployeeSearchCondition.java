package soo365.co.kr.reservation.web.dto;

import soo365.co.kr.reservation.domain.Role;

public record EmployeeSearchCondition(
        String emrId,
        String name,
        Role role
) {
}
