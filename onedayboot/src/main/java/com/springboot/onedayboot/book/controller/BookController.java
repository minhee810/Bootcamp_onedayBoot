package com.springboot.onedayboot.book.controller;

import com.springboot.onedayboot.book.dto.*;
import com.springboot.onedayboot.book.entity.Book;
import com.springboot.onedayboot.book.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class BookController {

    private final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    // 책 생성 화면
    // http://localhost:8090/book/create
    @GetMapping("/book/create")
    public String create (){

        return "create";
    }

    // 책 입력 컨트롤러 : create.html 에서 "submit" 버튼을 통해 호출됨.
    @PostMapping("/book/create")
    public String insert(BookCreateDTO bookCreateDTO){
        Integer bookId = this.bookService.insert(bookCreateDTO);

        return String.format("redirect:/book/read/%s", bookId);
    }

    @GetMapping("/book/read/{bookId}")
    public ModelAndView read(@PathVariable Integer bookId){
        ModelAndView mav = new ModelAndView();
        try{
            BookReadResponseDTO bookReadResponseDTO = this.bookService.read(bookId);
            mav.addObject("bookReadResponseDTO", bookReadResponseDTO);
            mav.setViewName("book/read");
        }catch (NoSuchElementException ex){
            mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
            mav.addObject("message", "책 정보 없음");
            mav.addObject("location", "/book");
            mav.setViewName("common/error/422");
        }
        return mav;
    }

    // 수정 페이지로 이동시켜주는 메서드
    @GetMapping("/book/edit/{bookId}")
    public ModelAndView editView(@PathVariable Integer bookId){
        ModelAndView mav = new ModelAndView();
            BookReadResponseDTO bookReadResponseDTO = this.bookService.read(bookId);
            mav.addObject("bookReadResponseDTO", bookReadResponseDTO);
            mav.setViewName("book/edit");
        return mav;
    }

    // 수정 내용을 객체에 담아서 실제 DB값을 바꿔주는 메서드
    // 책 수정 컨트롤러 : edit.html 에서 "submit" 버튼을 통해 호출됨.
    @PostMapping("/book/edit/{bookId}")
    public String edit(BookEditDTO bookEditDTO) throws Exception {
        BookEditResponseDTO  bookEditResponseDTO = this.bookService.edit(bookEditDTO);
        return String.format("redirect:/book/read/%s", bookEditResponseDTO.getBookId());
    }
    @PostMapping("/book/delete")
    public String delete(@RequestParam Integer bookId)throws Exception{ // hidden 값으로 준 것을 꺼내
        // 번호로 삭제를 해
        bookService.delete(bookId);
        System.out.println("bookId = " + bookId);
        return "book/list";
    }
    @PostMapping("/book/list")
    public String list(Model model) throws Exception {
        List<BookListResponseDTO> bookList = bookService.list();

        LOGGER.info("bookList", bookList);

        model.addAttribute("bookList", bookList);
        return "book/list";
    }

}
