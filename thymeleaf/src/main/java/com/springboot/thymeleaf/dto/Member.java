package com.springboot.thymeleaf.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor      // 기본 생성자 
public class Member {
    private String userId;
    private String password;
    private String email;
    private String userName;
    private LocalDate dateOfBirth;      // 생년월일 LocalDateTime사용

    // 추가
    private Address address;

    private String[] hobbyArray;
    private boolean foreigner;
    private String gender;
    private List hobbyList;

    private List<Card> cardList;  // 카드 객체를 넣을 것이기 때문에 예약 타입 카드
}
