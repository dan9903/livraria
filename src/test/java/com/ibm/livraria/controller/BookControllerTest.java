package com.ibm.livraria.controller;

import java.util.ArrayList;
import java.util.List;

import com.ibm.livraria.model.dto.BookDTO;
import com.ibm.livraria.service.BookService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest
public class BookControllerTest {

  @Autowired
  private BookController bookController;

  @MockBean
  private BookService bookService;

  @BeforeEach
  public void setup() {
    RestAssuredMockMvc.standaloneSetup(this.bookController);
  }

  @Test
  public void ShouldBeReturnOKandEmptyListWhenGetAllBooks() {
    List<BookDTO> books = new ArrayList<BookDTO>();

    Mockito.when(this.bookService.getAllBooks(0, 10)).thenReturn(books);

    RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/books/").then().statusCode(HttpStatus.OK.value());

  }

  @Test
  public void ShouldBeReturnOKWhenFindBook() {
    String a_sbn = "98-7812-562-9";
    Mockito.when(this.bookService.getBook(a_sbn)).thenReturn(new BookDTO("98-7812-562-9", "El Cielo Flautista",
        "um livro muito legal escrito ali encima", "Servando Martinez Delrio", 124));

    RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/books/{a_sbn}", a_sbn).then()
        .statusCode(HttpStatus.OK.value());
  }

  @Test
  public void ShouldBeReturnNotFoundWhenFindBook() {
    String sbn = "98-7812-562-5";

    Mockito.when(this.bookService.getBook(sbn)).thenReturn(null);

    RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/books/{a_sbn}", sbn).then()
        .statusCode(HttpStatus.NOT_FOUND.value());
  }

  @Test
  public void ShouldBeReturnSuccessWhenCreateBook() {
    BookDTO book = new BookDTO("98-7812-562-7", "El Abuelo de mi tia", "livro de memorias de infancia",
        "Everaldo Martinez", 150);

    Mockito.when(this.bookService.addBook(book)).thenReturn(book);

    RestAssuredMockMvc.given().contentType(ContentType.JSON).body(book).when().post("/books").then()
        .statusCode(HttpStatus.CREATED.value());
  }

  @Test
  public void ShouldBeReturnUnprocessableEntityWhenCreateBook() {
    BookDTO book = new BookDTO("98-7812-562-7", "El Abuelo de mi tia", "livro de memorias de infancia",
        "Everaldo Martinez", 150);

    Mockito.when(this.bookService.addBook(book)).thenReturn(null);

    RestAssuredMockMvc.given().contentType(ContentType.JSON).body(book).when().post("/books").then()
        .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
  }

  @Test
  public void ShouldBeReturnOkWhenUpdateBook() {
    BookDTO book = new BookDTO("98-7812-562-7", "El Abuelo de mi primo", "livro de memorias de infancia",
        "Everaldo Martinez", 150);

    Mockito.when(this.bookService.editBook(book)).thenReturn(book);

    RestAssuredMockMvc.given().contentType(ContentType.JSON).body(book).when().put("/books").then()
        .statusCode(HttpStatus.OK.value());
  }

  @Test
  public void ShouldBeReturnNotAcceptableWhenUpdateBook() {
    BookDTO book = new BookDTO("98-7812-562-6", "El Abuelo de mi tia", "livro de memorias de infancia",
        "Everaldo Martinez", 150);

    Mockito.when(this.bookService.editBook(book)).thenReturn(null);

    RestAssuredMockMvc.given().contentType(ContentType.JSON).body(book).when().post("/books").then()
        .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
  }

  @Test
  public void ShouldBeReturnOKWhenDeleteBook() {
    String isbn = "98-7812-572-6";

    Mockito.when(this.bookService.deleteBook(isbn)).thenReturn(new BookDTO());

    RestAssuredMockMvc.given().accept(ContentType.JSON).when().delete("/books/{a_isbn}", isbn).then()
        .statusCode(HttpStatus.OK.value());
  }

  @Test
  public void ShouldBeReturnNotAcceptableWhenDeleteBook() {
    String isbn = "98-7812-572-8";

    Mockito.when(this.bookService.deleteBook(isbn)).thenReturn(null);

    RestAssuredMockMvc.given().accept(ContentType.JSON).when().delete("/books/{a_isbn}", isbn).then()
        .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
  }
}