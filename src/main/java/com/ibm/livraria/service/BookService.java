package com.ibm.livraria.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.ibm.livraria.model.Book;
import com.ibm.livraria.model.dto.BookDTO;
import com.ibm.livraria.model.exception.BookNotFoundException;
import com.ibm.livraria.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired
  public BookService(BookRepository a_bookRepository) {
    bookRepository = a_bookRepository;
  }

  public BookDTO addBook(BookDTO a_bookDTO) {
    findBySbn(a_bookDTO.getSbn());
    Book book = Book.from(a_bookDTO);
    bookRepository.save(book);
    return BookDTO.from(book);
  }

  public List<BookDTO> getAllBooks() {
    return StreamSupport.stream(bookRepository.findAll().spliterator(), false).map(BookDTO::from)
        .collect(Collectors.toList());
  }

  public BookDTO getBook(String a_snb) {
    return BookDTO.from(findBySbn(a_snb));
  }

  public BookDTO editBook(BookDTO a_bookDTO) {
    Book book = findBySbn(a_bookDTO.getSbn());
    // setting new values on book variable
    book.setName(a_bookDTO.getName());
    book.setAuthor(a_bookDTO.getAuthor());
    book.setDescription(a_bookDTO.getDescription());
    book.setAmount(a_bookDTO.getAmount());
    // updating on database
    bookRepository.save(book);

    return BookDTO.from(book);
  }

  public BookDTO deleteBook(String a_sbn) {
    Book book = findBySbn(a_sbn);
    bookRepository.delete(book);
    return BookDTO.from(book);
  }

  private Book findBySbn(String a_sbn) {
    return bookRepository.findById(a_sbn).orElseThrow(() -> new BookNotFoundException(a_sbn));
  }

  private final BookRepository bookRepository;
}