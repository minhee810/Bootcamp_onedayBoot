package com.springboot.onedayboot.book.dto;

import com.springboot.onedayboot.book.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BookListResponseDTO {

    private Integer bookId;
    private String title;
    private Integer price;
    private LocalDateTime insertDateTime;

    public BookListResponseDTO fromBook(Book book){
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.price = book.getPrice();
        return this;
    }
    public static BookListResponseDTO bookFactory(Book book){
        BookListResponseDTO bookListResponseDTO = new BookListResponseDTO();
        bookListResponseDTO.fromBook(book);
        return bookListResponseDTO;
    }

}
