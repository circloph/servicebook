package net.thumbtack.servicebook.service;

import net.thumbtack.servicebook.dto.request.AuthorRequest;
import net.thumbtack.servicebook.dto.request.BookRequest;
import net.thumbtack.servicebook.dto.request.ReviewsRequest;
import net.thumbtack.servicebook.dto.response.BookResponse;
import net.thumbtack.servicebook.dto.response.BooksResponse;
import net.thumbtack.servicebook.dto.response.ReviewsResponse;
import net.thumbtack.servicebook.exception.UserException;

public interface BookService {

    BookResponse createBook(BookRequest book);

    BooksResponse getBooks(int page, int size, String fieldForSearch, String value);

    ReviewsResponse createReviews(ReviewsRequest request) throws UserException;

    BookResponse addAuthor(Integer bookId, AuthorRequest request);

    BookResponse getInfoAboutBook(Integer bookId);

    ReviewsResponse updateReviews(Integer reviewId, ReviewsRequest request) throws UserException;

    BookResponse updateBook(Integer bookId, BookRequest request);

}

