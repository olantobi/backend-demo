package com.africaprudential.backenddemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SmsNotification {
    private String mobileNumber;
    private String message;
}
