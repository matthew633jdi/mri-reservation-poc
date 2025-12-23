package soo365.co.kr.reservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import soo365.co.kr.reservation.domain.Employee;
import soo365.co.kr.reservation.web.dto.EmployeeSearchCondition;

public interface CustomEmployeeRepository {

    Page<Employee> search(EmployeeSearchCondition condition, Pageable pageable);

}
