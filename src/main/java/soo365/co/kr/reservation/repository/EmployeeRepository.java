package soo365.co.kr.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soo365.co.kr.reservation.domain.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, CustomEmployeeRepository {
    Optional<Employee> findByEmrId(String emrId);

    Optional<Long> findIdByEmrId(String emrId);

}
