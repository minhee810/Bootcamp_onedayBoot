package com.springboot.onedayboot.book.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookListDTO {

    @NonNull
    private Integer bookId;

    @NonNull
    private String title;

    @NonNull
    private Integer price;


}
