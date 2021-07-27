package com.ibm.livraria.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ibm.livraria.model.dto.BookDTO;
import com.ibm.livraria.service.BookService;

@CrossOrigin
@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired
  public BookController(BookService a_bookService) {
    bookService = a_bookService;
  }

  @PostMapping
  public ResponseEntity<BookDTO> create(@RequestBody final BookDTO a_book) {
    BookDTO response = bookService.addBook(a_book);
    if (response != null)
      return new ResponseEntity<BookDTO>(response, HttpStatus.CREATED);
    return new ResponseEntity<BookDTO>(response, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @GetMapping
  public ResponseEntity<List<BookDTO>> index(@RequestParam(defaultValue = "0") Integer pageNo,
      @RequestParam(defaultValue = "10") Integer pageSize) {
    List<BookDTO> bookList = bookService.getAllBooks(pageNo, pageSize);
    return new ResponseEntity<List<BookDTO>>(bookList, HttpStatus.OK);
  }

  @RequestMapping(value = "{a_sbn}", method = RequestMethod.GET)
  public ResponseEntity<BookDTO> show(@PathVariable final String a_sbn) {
    BookDTO response = bookService.getBook(a_sbn);
    if (response == null) {
      return new ResponseEntity<BookDTO>(response, HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<BookDTO>(response, HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<BookDTO> update(@RequestBody final BookDTO a_book) {
    BookDTO response = bookService.editBook(a_book);
    if (response != null)
      return new ResponseEntity<BookDTO>(response, HttpStatus.OK);
    return new ResponseEntity<BookDTO>(response, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @RequestMapping(value = "{a_sbn}", method = RequestMethod.DELETE)
  public ResponseEntity<BookDTO> delete(@PathVariable final String a_sbn) {
    BookDTO response = bookService.deleteBook(a_sbn);
    if (response != null)
      return new ResponseEntity<BookDTO>(response, HttpStatus.OK);
    return new ResponseEntity<BookDTO>(response, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  private final BookService bookService;
}