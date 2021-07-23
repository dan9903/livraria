package com.ibm.livraria.model.dto;

import com.ibm.livraria.model.Book;
import lombok.Data;

@Data
public class BookDTO {
  public static BookDTO from(Book a_entity) {
    BookDTO dto = new BookDTO();
    dto.setSbn(a_entity.getSbn());
    dto.setName(a_entity.getName());
    dto.setDescription(a_entity.getDescription());
    dto.setAuthor(a_entity.getAuthor());
    dto.setAmount(a_entity.getAmount());
    return dto;
  }

  private String sbn;
  private String name;
  private String description;
  private String author;
  private int amount;
}