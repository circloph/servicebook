package net.kruglov.servicebook.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import net.kruglov.servicebook.dto.request.AuthorRequest;
import net.kruglov.servicebook.dto.request.BookRequest;
import net.kruglov.servicebook.dto.request.ReviewsRequest;
import net.kruglov.servicebook.dto.response.BookResponse;
import net.kruglov.servicebook.dto.response.BooksResponse;
import net.kruglov.servicebook.dto.response.ReviewsResponse;
import net.kruglov.servicebook.service.BookService;

@Controller()
public class BookController {

    @Autowired
    private BookService bookService;

    @MutationMapping
    public BookResponse createBook(@Argument BookRequest book) {
        return bookService.createBook(book);
    }
    
    @MutationMapping
    public ReviewsResponse createReviews(@Argument ReviewsRequest reviews) {
        return bookService.createReviews(reviews);
    }
    
    @MutationMapping
    public BookResponse addAuthor(@Argument() Integer bookId, @Argument AuthorRequest author) {
        return bookService.addAuthor(bookId, author);
    }
    
    @MutationMapping
    public ReviewsResponse updateReviews(@Argument Integer reviewId,@Argument ReviewsRequest review) {
        return bookService.updateReviews(reviewId, review);
    }
    
    @MutationMapping
    public BookResponse updateBook(@Argument Integer bookId,@Argument BookRequest book) {
        return bookService.updateBook(bookId, book);
    }

    @QueryMapping
    public BooksResponse getBooks(@Argument int page, @Argument int size, @Argument String fieldForSearch,@Argument String value) {
        BooksResponse b = bookService.getBooks(page, size, fieldForSearch, value);
        return b;
    }
    
    @QueryMapping
    public BookResponse getInfoAboutBook(@Argument Integer bookId) {
        return bookService.getInfoAboutBook(bookId);
    }
    
}
