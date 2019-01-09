package com.africaprudential.backenddemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@AllArgsConstructor
public class EmailNotification {


    private String email;
    private String subject;
    private String message;
}
