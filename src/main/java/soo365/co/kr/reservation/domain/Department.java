package soo365.co.kr.reservation.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import soo365.co.kr.reservation.web.exception.InvalidEnumValueException;

public enum Department {
    OS1,
    OS2,
    OS3,
    OS4,
    PS1;

    @JsonCreator
    public static Department from(String value) {
        if (value == null) {
            return null;
        }

        try {
            return Department.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException(
                    "department",
                    value,
                    Department.class
            );
        }
    }
}
