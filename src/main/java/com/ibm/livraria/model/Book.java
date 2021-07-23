package com.ibm.livraria.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ibm.livraria.model.dto.BookDTO;
import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {
  public static Book from(BookDTO a_dto) {
    Book entity = new Book();
    entity.setSbn(a_dto.getSbn());
    entity.setName(a_dto.getName());
    entity.setDescription(a_dto.getDescription());
    entity.setAuthor(a_dto.getAuthor());
    entity.setAmount(a_dto.getAmount());
    return entity;
  }

  @Id
  private String sbn;
  private String name;
  private String description;
  private String author;
  private int amount;
}