ALTER TABLE reservation
    ADD CONSTRAINT uq_reservation_employee_at
        UNIQUE (employee_id, reservation_at);