package com.mcms.study.rest.service.book;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mcms.study.rest.common.exception.SystemException;
import com.mcms.study.rest.model.book.Book;
import com.mcms.study.rest.service.review.ReviewService;
import com.mcms.study.rest.service.review.ReviewServiceImpl;

@Service("bookService")
public class BookServiceImpl implements BookService {

    private Map<Long, Book> bookMap;

    public BookServiceImpl() {
        bookMap = new HashMap<Long, Book>();
        bookMap.put(1L, Book.builder().withBookId(1L).withName("Architectural Styles and the Design of Network-based Software Architectures")
                .withAuthor("Roy Thomas Fielding").withPrice(BigDecimal.valueOf(100.000d)).build());
        bookMap.put(2L, Book.builder().withBookId(2L).withName("Clean Code - A Handbook of Agile Software Craftsmanship").withAuthor("Robert C. Martin")
                .withPrice(BigDecimal.valueOf(100.000d)).build());
    }

    @Override
    public List<Book> list() {
        return bookMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Book read(long bookId) {
        if (!bookMap.containsKey(bookId)) {
            throw new SystemException("No entry found");
        }
        return bookMap.get(bookId);
    }

    @Override
    public void create(Book book) {
        if (bookMap.containsKey(book.getBookId())) {
            throw new SystemException("Duplicate entry");
        } else {
            bookMap.put(book.getBookId(), book);
        }
    }

    @Override
    public void update(Book book) {
        if (!bookMap.containsKey(book.getBookId())) {
            throw new SystemException("No entry found");
        }
        bookMap.put(book.getBookId(), book);
    }

    @Override
    public void delete(long bookId) {
        if (!bookMap.containsKey(bookId)) {
            throw new SystemException("No entry found");
        }
        bookMap.remove(bookId);
    }

    @Override
    public ReviewService getReviewService() {
        return new ReviewServiceImpl();
    }
}