package soo365.co.kr.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soo365.co.kr.reservation.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
