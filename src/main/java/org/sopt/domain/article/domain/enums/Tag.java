package org.sopt.domain.article.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Tag {
    CS("Computer Science"),
    DB("Database"),
    SPRING("Spring"),
    ETC("ETC");

    private final String description;
}
