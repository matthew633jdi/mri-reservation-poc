package soo365.co.kr.reservation.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import soo365.co.kr.reservation.domain.Department;
import soo365.co.kr.reservation.domain.Reservation;
import soo365.co.kr.reservation.domain.TreatmentStatus;
import soo365.co.kr.reservation.service.dto.SearchReservationQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static soo365.co.kr.reservation.domain.QReservation.reservation;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements CustomReservationRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Reservation> search(SearchReservationQuery query, Pageable pageable) {
        List<Reservation> content = queryFactory.selectFrom(reservation)
                .where(
                        employeeIdEq(query.employeeId()),
                        reservationDateEq(query.reservationDate()),
                        reservationBetween(query.reservationFrom(), query.reservationTo()),
                        treatmentStatusEq(query.treatmentStatus()),
                        departmentEq(query.department()),
                        chartNumberEq(query.chartNumber()),
                        patientNameContains(query.patientName()),
                        bodyPartContains(query.bodyPart()),
                        contrastUsedEq(query.contrastUsed())
                ).offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory.select(reservation.count())
                .where(
                        employeeIdEq(query.employeeId()),
                        reservationDateEq(query.reservationDate()),
                        reservationBetween(query.reservationFrom(), query.reservationTo()),
                        treatmentStatusEq(query.treatmentStatus()),
                        departmentEq(query.department()),
                        chartNumberEq(query.chartNumber()),
                        patientNameContains(query.patientName()),
                        bodyPartContains(query.bodyPart()),
                        contrastUsedEq(query.contrastUsed())
                ).fetchOne();
        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression contrastUsedEq(Boolean flag) {
        return Objects.nonNull(flag) ? reservation.contrastUsed.eq(flag) : null;
    }

    private BooleanExpression bodyPartContains(String bodyPart) {
        return Objects.nonNull(bodyPart) ? reservation.bodyPart.containsIgnoreCase(bodyPart) : null;
    }

    private BooleanExpression patientNameContains(String patientName) {
        return Objects.nonNull(patientName) ? reservation.patientName.containsIgnoreCase(patientName) : null;
    }

    private BooleanExpression chartNumberEq(Long chartNumber) {
        return Objects.nonNull(chartNumber) ? reservation.chartNumber.eq(chartNumber) : null;
    }

    private BooleanExpression departmentEq(Department department) {
        return Objects.nonNull(department) ? reservation.department.eq(department) : null;
    }

    private BooleanExpression treatmentStatusEq(TreatmentStatus treatmentStatus) {
        return Objects.nonNull(treatmentStatus) ? reservation.treatmentStatus.eq(treatmentStatus) : null;
    }

    private BooleanExpression employeeIdEq(Long employeeId) {
        return Objects.nonNull(employeeId) ? reservation.employee.id.eq(employeeId) : null;
    }

    private BooleanExpression reservationDateEq(LocalDate date) {
        return date != null ? reservation.reservationAt.between(
                date.atStartOfDay(),
                date.atTime(LocalTime.MAX)
        ) : null;
    }

    private BooleanExpression reservationBetween(LocalDateTime from, LocalDateTime to) {
        if (from != null && to != null) {
            return reservation.reservationAt.between(from, to);
        }
        if (from != null) {
            return reservation.reservationAt.goe(from);
        }
        if (to != null) {
            return reservation.reservationAt.loe(to);
        }
        return null;
    }


}
