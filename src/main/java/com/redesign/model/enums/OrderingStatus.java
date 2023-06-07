package com.redesign.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

