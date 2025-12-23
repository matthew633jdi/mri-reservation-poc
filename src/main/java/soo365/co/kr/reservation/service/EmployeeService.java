package soo365.co.kr.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import soo365.co.kr.reservation.domain.Employee;
import soo365.co.kr.reservation.repository.EmployeeRepository;
import soo365.co.kr.reservation.web.dto.EmployeeCreateRequest;
import soo365.co.kr.reservation.web.dto.EmployeeResponse;
import soo365.co.kr.reservation.web.dto.EmployeeSearchCondition;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeResponse create(EmployeeCreateRequest request) {
        String encodedPassword = passwordEncoder.encode(request.password());
        Employee employee = Employee.create(request.emrId(), request.name(), encodedPassword, request.role());
        employeeRepository.save(employee);
        return EmployeeResponse.from(employee);
    }

    //TODO SQL unique 제약에 대한 예외 처리
    public Page<EmployeeResponse> find(EmployeeSearchCondition request, Pageable pageable) {
        return employeeRepository.search(request, pageable).map(EmployeeResponse::from);
    }
}
