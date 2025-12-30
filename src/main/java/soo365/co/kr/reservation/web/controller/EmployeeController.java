package soo365.co.kr.reservation.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import soo365.co.kr.reservation.service.EmployeeService;
import soo365.co.kr.reservation.web.dto.EmployeeCreateRequest;
import soo365.co.kr.reservation.web.dto.EmployeeResponse;
import soo365.co.kr.reservation.web.dto.EmployeeSearchCondition;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponse> signup(@RequestBody @Valid EmployeeCreateRequest request) {
        EmployeeResponse response = employeeService.create(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(response);
    }

    @GetMapping("/employees")
    public Page<EmployeeResponse> retrieve(EmployeeSearchCondition request, Pageable pageable) {
        return employeeService.find(request, pageable);
    }
}
