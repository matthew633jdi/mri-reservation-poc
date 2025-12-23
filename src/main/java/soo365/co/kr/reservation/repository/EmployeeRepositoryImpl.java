package soo365.co.kr.reservation.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import soo365.co.kr.reservation.domain.Employee;
import soo365.co.kr.reservation.domain.Role;
import soo365.co.kr.reservation.web.dto.EmployeeSearchCondition;

import java.util.List;

import static soo365.co.kr.reservation.domain.QEmployee.employee;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements CustomEmployeeRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Employee> search(EmployeeSearchCondition condition, Pageable pageable) {
        List<Employee> content = queryFactory.selectFrom(employee)
                .where(
                        emrIdEq(condition.emrId()),
                        nameContains(condition.name()),
                        roleEq(condition.role())
                ).offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(employee.count())
                .from(employee)
                .where(
                        emrIdEq(condition.emrId()),
                        nameContains(condition.name()),
                        roleEq(condition.role())
                )
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression emrIdEq(String emrId) {
        return StringUtils.hasText(emrId) ? employee.emrId.eq(emrId) : null;
    }

    private BooleanExpression nameContains(String name) {
        return StringUtils.hasText(name) ? employee.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression roleEq(Role role) {
        return role != null ? employee.role.eq(role) : null;
    }
}
