-- reservation_date + reservation_time → reservation_at
ALTER TABLE reservation ADD COLUMN reservation_at TIMESTAMP;

UPDATE reservation SET reservation_at = reservation_date + reservation_time;

ALTER TABLE reservation ALTER COLUMN reservation_at SET NOT NULL;

ALTER TABLE reservation DROP CONSTRAINT uq_reservation_date_time;
ALTER TABLE reservation DROP COLUMN reservation_date;
ALTER TABLE reservation DROP COLUMN reservation_time;

-- treatment_status nullable
ALTER TABLE reservation ALTER COLUMN treatment_status DROP NOT NULL;

-- contrast_used NOT NULL DEFAULT FALSE
UPDATE reservation SET contrast_used = FALSE WHERE contrast_used IS NULL;

ALTER TABLE reservation ALTER COLUMN contrast_used SET DEFAULT FALSE;

ALTER TABLE reservation ALTER COLUMN contrast_used SET NOT NULL;

-- direction, body_part nullable
ALTER TABLE reservation ALTER COLUMN direction DROP NOT NULL;

ALTER TABLE reservation ALTER COLUMN body_part DROP NOT NULL;