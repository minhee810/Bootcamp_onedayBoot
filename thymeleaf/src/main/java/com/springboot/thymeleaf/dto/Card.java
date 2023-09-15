package com.springboot.thymeleaf.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
public class Card {
    private String no;
    private YearMonth validMonth;
}
