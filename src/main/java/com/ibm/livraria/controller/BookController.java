package com.ibm.livraria.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    return new ResponseEntity<BookDTO>(response, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<BookDTO>> index() {
    List<BookDTO> bookList = bookService.getAllBooks();
    return new ResponseEntity<List<BookDTO>>(bookList, HttpStatus.OK);
  }

  @GetMapping(value = "{sbn}")
  public ResponseEntity<BookDTO> show(@PathVariable final String a_sbn) {
    BookDTO response = bookService.getBook(a_sbn);
    return new ResponseEntity<BookDTO>(response, HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<BookDTO> update(@PathVariable final BookDTO a_sbn) {
    BookDTO response = bookService.editBook(a_sbn);
    return new ResponseEntity<BookDTO>(response, HttpStatus.OK);
  }

  @DeleteMapping(value = "{sbn}")
  public ResponseEntity<BookDTO> delete(@PathVariable final String a_sbn) {
    BookDTO response = bookService.deleteBook(a_sbn);
    return new ResponseEntity<BookDTO>(response, HttpStatus.OK);
  }

  private final BookService bookService;
}