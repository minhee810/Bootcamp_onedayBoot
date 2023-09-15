package com.springboot.onedayboot.book.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookDeleteDTO {

    @NonNull
    private Integer bookId;

    @NonNull
    private String title;

    @NonNull
    private Integer price;

}
