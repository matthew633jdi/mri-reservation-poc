package soo365.co.kr.reservation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import soo365.co.kr.reservation.domain.Reservation;
import soo365.co.kr.reservation.service.dto.SearchReservationQuery;

public interface CustomReservationRepository {

    Page<Reservation> search(SearchReservationQuery condition, Pageable pageable);
}
