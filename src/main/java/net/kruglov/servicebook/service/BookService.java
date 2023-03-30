package net.kruglov.servicebook.service;

import net.kruglov.servicebook.dto.request.AuthorRequest;
import net.kruglov.servicebook.dto.request.BookRequest;
import net.kruglov.servicebook.dto.request.ReviewsRequest;
import net.kruglov.servicebook.dto.response.BookResponse;
import net.kruglov.servicebook.dto.response.BooksResponse;
import net.kruglov.servicebook.dto.response.ReviewsResponse;
import net.kruglov.servicebook.exception.UserException;

public interface BookService {

    BookResponse createBook(BookRequest book);

    BooksResponse getBooks(int page, int size, String fieldForSearch, String value);

    ReviewsResponse createReviews(ReviewsRequest request) throws UserException;

    BookResponse addAuthor(Integer bookId, AuthorRequest request);

    BookResponse getInfoAboutBook(Integer bookId);

    ReviewsResponse updateReviews(Integer reviewId, ReviewsRequest request) throws UserException;

    BookResponse updateBook(Integer bookId, BookRequest request);

}

