package me.hoon.subscribewithme.domain.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseEntity {

    private LocalDateTime createAt;
    private LocalDateTime lastModifiedAt;

    public BaseEntity() {
        this.createAt = LocalDateTime.now();
        this.lastModifiedAt = LocalDateTime.now();
    }
}
