package soo365.co.kr.reservation.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emrId;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Employee(String emrId, String name, String password, Role role) {
        this.emrId = emrId;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public static Employee create(String emrId, String name, String password, Role role) {
        return new Employee(emrId, name, password, role);
    }
}
