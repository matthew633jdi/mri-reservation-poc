package soo365.co.kr.reservation.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import soo365.co.kr.reservation.web.exception.InvalidEnumValueException;

public enum Role {
    ADMIN,
    USER;

    @JsonCreator
    public static Role from(String value) {
        if (value == null) {
            return null;
        }

        try {
            return Role.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException(
                    "role",
                    value,
                    Role.class
            );
        }
    }
}
