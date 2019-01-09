package com.africaprudential.backenddemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class SmsNotification {
    private String mobileNumber;
    private String message;
}
