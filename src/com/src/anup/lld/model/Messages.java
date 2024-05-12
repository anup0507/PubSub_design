package com.src.anup.lld.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Messages {
    private  String message;
    private String messageId;
    public Messages(String message) {
        this.message = message;
        UUID uuid = UUID.randomUUID();

        this.messageId = uuid.toString();
    }

}
