package core.sff.avalon.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
  private String isbn;
  private String title;
  private String author;
  private double price;

  public Book() {
  
  }
  
  public Book(String isbn, String title, String author, double price) {
    this.isbn = isbn;
    this.title = title;
    this.author = author;
    this.price = price;
  }

  @Override
  public String toString() {
    return this.title + " by " + this.author;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }
  
  public double getPrice() {
    return price;
  }
}
