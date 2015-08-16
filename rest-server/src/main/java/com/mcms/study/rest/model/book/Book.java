package com.mcms.study.rest.model.book;

import java.math.BigDecimal;

public class Book {

    private long bookId;
    private String name;
    private String author;
    private BigDecimal price;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static BookBuilder builder() {
        return new BookBuilder();
    }

    public static class BookBuilder {

        private Book book;

        public BookBuilder() {
            book = new Book();
        }

        public BookBuilder withBookId(long bookId) {
            book.setBookId(bookId);
            return this;
        }

        public BookBuilder withName(String name) {
            book.setName(name);
            return this;
        }

        public BookBuilder withAuthor(String author) {
            book.setAuthor(author);
            return this;
        }

        public BookBuilder withPrice(BigDecimal price) {
            book.setPrice(price);
            return this;
        }

        public Book build() {
            return book;
        }

    }

}
