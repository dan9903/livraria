package com.ibm.livraria.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.ibm.livraria.model.Book;
import com.ibm.livraria.model.dto.BookDTO;
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
    Book book = findBySbn(a_bookDTO.getSbn());
    if (book.getSbn().isEmpty()) {
      book = Book.from(a_bookDTO);
      bookRepository.save(book);
      return BookDTO.from(book);
    }
    return null;
  }

  public List<BookDTO> getAllBooks() {
    return StreamSupport.stream(bookRepository.findAll().spliterator(), false).map(BookDTO::from)
        .collect(Collectors.toList());
  }

  public BookDTO getBook(String a_snb) {
    BookDTO bookDTO = BookDTO.from(findBySbn(a_snb));
    if (bookDTO.getSbn() == null) {
      bookDTO = null;
    }
    return bookDTO;
  }

  public BookDTO editBook(BookDTO a_bookDTO) {
    Book book = findBySbn(a_bookDTO.getSbn());
    if (!book.getSbn().isEmpty()) {
      // setting new values on book variable
      book.setName(a_bookDTO.getName());
      book.setAuthor(a_bookDTO.getAuthor());
      book.setDescription(a_bookDTO.getDescription());
      book.setAmount(a_bookDTO.getAmount());
      // updating on database
      bookRepository.save(book);
      return BookDTO.from(book);
    }

    return null;
  }

  public BookDTO deleteBook(String a_sbn) {
    Book book = findBySbn(a_sbn);
    if (!book.getSbn().isEmpty()) {
      bookRepository.delete(book);
      return BookDTO.from(book);
    }
    return null;
  }

  private Book findBySbn(String a_sbn) {
    return bookRepository.findById(a_sbn).orElse(new Book());
  }

  private final BookRepository bookRepository;
}