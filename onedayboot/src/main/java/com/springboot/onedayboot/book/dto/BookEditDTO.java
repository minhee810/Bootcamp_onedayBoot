package com.springboot.onedayboot.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookEditDTO {

    @NonNull
    private Integer bookId;

    @NonNull
    private String title;

    @NonNull
    private Integer price;
    
}
