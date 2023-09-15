package com.springboot.onedayboot.book.service;

import com.springboot.onedayboot.book.dto.*;
import com.springboot.onedayboot.book.entity.Book;
import com.springboot.onedayboot.book.entity.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public Integer insert(BookCreateDTO bookCreateDTO){
        Book book = Book.builder()
                .title(bookCreateDTO.getTitle())
                .price(bookCreateDTO.getPrice())
                .build();

        this.bookRepository.save(book);
        return book.getBookId();
    }

    public BookReadResponseDTO read(Integer bookId) throws NoSuchElementException{
        Book book = this.bookRepository.findById(bookId).orElseThrow();

        BookReadResponseDTO bookReadResponseDTO = new BookReadResponseDTO();

        bookReadResponseDTO.fromBook(book);
        return bookReadResponseDTO;

    }

    public BookEditResponseDTO edit(BookEditDTO bookEditDTO) throws Exception {
        Optional<Book> selectedBook = bookRepository.findById(bookEditDTO.getBookId());
        BookEditResponseDTO bookEditResponseDTO = new BookEditResponseDTO();
        Book updatedBook;
        if(selectedBook.isPresent()){
            Book book = selectedBook.get();

            book.setTitle(bookEditDTO.getTitle());
            book.setPrice(bookEditDTO.getPrice());

            updatedBook = bookRepository.save(book);

            bookEditResponseDTO.fromBook(updatedBook);

        }else{
            throw new Exception();
        }

        return bookEditResponseDTO;
    }

    public void delete(Integer bookId) throws Exception {
        Optional<Book> selectedBook = bookRepository.findById(bookId);

        if(selectedBook.isPresent()){
            Book book = selectedBook.get();

            bookRepository.delete(book);
        }else{
            throw new Exception();
        }
    }

    public List<BookListResponseDTO> list() throws Exception{
       List<Book> bookListResponseDTOList = bookRepository.findAll();

       return bookListResponseDTOList;

    }


}
