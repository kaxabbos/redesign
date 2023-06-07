package com.redesign.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Classifier {
    GOOD("Хорошо"),
    BAD("Плохо"),
    NORMAL("Нормально");

    private final String name;

}

