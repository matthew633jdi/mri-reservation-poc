package soo365.co.kr.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soo365.co.kr.reservation.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, CustomEmployeeRepository {
}
