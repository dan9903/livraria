package com.ibm.livraria.model.exception;

import java.text.MessageFormat;

public class BookNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public BookNotFoundException(String a_sbn) {
    super(MessageFormat.format("Could not find a book with sbn: {0}", a_sbn));
  }
}