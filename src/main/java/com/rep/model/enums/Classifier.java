package com.rep.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public enum Classifier {
    GOOD("Хорошо"),
    BAD("Плохо"),
    NORMAL("Нормально");

    private final String name;

}

