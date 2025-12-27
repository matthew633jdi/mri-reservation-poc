package soo365.co.kr.reservation.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Department {
    OS1,
    OS2,
    OS3,
    OS4,
    PS1;

    @JsonCreator
    public static Department from(String value) {
        return Department.valueOf(value.toUpperCase());
    }
}
