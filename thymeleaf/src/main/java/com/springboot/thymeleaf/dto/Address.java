package com.springboot.thymeleaf.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Address {
    private String postCode;
    private String location;
    private String address;
    private String attribute;
}
