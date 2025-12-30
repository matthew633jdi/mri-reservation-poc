package soo365.co.kr.reservation.web.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class InvalidEnumValueException extends RuntimeException {

    private final String field;
    private final String value;
    private final List<String> allowedValues;

    public InvalidEnumValueException(String field, String value, Class<? extends Enum<?>> enumType) {
        super("잘못된 Enum 값입니다.");
        this.field = field;
        this.value = value;
        this.allowedValues = Arrays.stream(enumType.getEnumConstants())
                .map(Enum::name)
                .toList();
    }
}
