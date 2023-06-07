package com.rep.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum OrderingStatus {
    IN_THE_DESIGN("В оформлении"),
    IN_WAITING("В ожидании"),
    CONFIRMED("Подтверждено"),
    REFUSED("Отказано"),
    COMPLETED("Завершено");

    private final String name;
}

