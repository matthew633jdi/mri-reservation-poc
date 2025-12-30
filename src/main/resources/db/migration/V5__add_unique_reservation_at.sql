ALTER TABLE reservation DROP CONSTRAINT uq_reservation_employee_at;
ALTER TABLE reservation
    ADD CONSTRAINT uq_reservation_at
        UNIQUE (reservation_at);