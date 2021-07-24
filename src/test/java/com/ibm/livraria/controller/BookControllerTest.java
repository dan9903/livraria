package com.ibm.livraria.controller;

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
  public void ShouldBeReturnSucess_WhenFindABook() {
    String a_sbn = "98-7812-562-9";
    Mockito.when(this.bookService.getBook(a_sbn)).thenReturn(new BookDTO("98-7812-562-9", "El Cielo Flautista",
        "um livro muito legal escrito ali encima", "Servando Martinez Delrio", 124));

    RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/books/{a_sbn}", a_sbn).then()
        .statusCode(HttpStatus.OK.value());
  }

  @Test
  public void ShouldBeReturnNotFound_WhenFindABook() {
    String a_sbn = "98-7812-562-5";

    Mockito.when(this.bookService.getBook(a_sbn)).thenReturn(null);

    RestAssuredMockMvc.given().accept(ContentType.JSON).when().get("/books/{a_sbn}", a_sbn).then()
        .statusCode(HttpStatus.NOT_FOUND.value());
  }
}