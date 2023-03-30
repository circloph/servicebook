package net.kruglov.servicebook.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.kruglov.servicebook.dto.mappers.AuthorDtoMapper;
import net.kruglov.servicebook.dto.mappers.BookDtoMapper;
import net.kruglov.servicebook.dto.mappers.ReviewsDtoMapper;
import net.kruglov.servicebook.dto.request.AuthorRequest;
import net.kruglov.servicebook.dto.request.BookRequest;
import net.kruglov.servicebook.dto.request.ReviewsRequest;
import net.kruglov.servicebook.dto.response.BookResponse;
import net.kruglov.servicebook.dto.response.BooksResponse;
import net.kruglov.servicebook.dto.response.ReviewsResponse;
import net.kruglov.servicebook.error.ErrorCode;
import net.kruglov.servicebook.exception.UserException;
import net.kruglov.servicebook.model.Author;
import net.kruglov.servicebook.model.Book;
import net.kruglov.servicebook.model.Reviews;
import net.kruglov.servicebook.model.User;
import net.kruglov.servicebook.repository.AuthorRepository;
import net.kruglov.servicebook.repository.BookRepository;
import net.kruglov.servicebook.repository.ReviewsRepository;
import net.kruglov.servicebook.repository.UserRepository;
import net.kruglov.servicebook.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private ReviewsRepository reviewsRepository;
    private UserRepository userRepository;
    @Autowired
    private BookDtoMapper bookDtoMapper = Mappers.getMapper(BookDtoMapper.class);
    @Autowired
    private ReviewsDtoMapper reviewsDtoMapper = Mappers.getMapper(ReviewsDtoMapper.class);
    @Autowired
    private AuthorDtoMapper authorDtoMapper = Mappers.getMapper(AuthorDtoMapper.class);

    @Secured("ROLE_ADMIN")
    @Override
    public BookResponse createBook(BookRequest request) {
        Book book = bookDtoMapper.bookRequestToBook(request);
        return bookDtoMapper.bookToBookResponse(bookRepository.save(book));
    }

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
            ReviewsRepository reviewsRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.reviewsRepository = reviewsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Secured({"ROLE_ADMIN", "ROLE_CLIENT"})
    @Override
    public BooksResponse getBooks(int page, int size, String fieldForSearch, String value) {
        List<Book> booksFromDB = null;
        if (page == 0 && size == 0 && fieldForSearch == null && value == null) {
            return new BooksResponse(bookDtoMapper.bookListToBooksResponseList(bookRepository.findAll()), 0);
        }
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        int amountOfBooks = 0;
        Pageable sortedByYearOfProduction = PageRequest.of(page, size, Sort.by("yearOfProduction"));
        if (fieldForSearch != null && fieldForSearch.equals("title")) {
            amountOfBooks = bookRepository.findByTitle(value, pageable).size();
            booksFromDB = bookRepository.findByTitle(value, sortedByYearOfProduction);
        } else if (fieldForSearch != null && fieldForSearch.equals("description")) {
            amountOfBooks = bookRepository.findByDescriptionContains(value, pageable).size();
            booksFromDB = bookRepository.findByDescriptionContains(value, sortedByYearOfProduction);
        } else if (fieldForSearch != null && fieldForSearch.equals("author")) {
            amountOfBooks = bookRepository.findByAuthorsLastName(value, pageable).size();
            booksFromDB = bookRepository.findByAuthorsLastName(value, sortedByYearOfProduction);
        } else {
            amountOfBooks = bookRepository.findAll(pageable).getContent().size();
            booksFromDB = bookRepository.findAll(sortedByYearOfProduction).getContent();
        }
        List<BookResponse> responseBooks = bookDtoMapper.bookListToBooksResponseList(booksFromDB);
        BooksResponse booksResp = new BooksResponse(responseBooks, amountOfBooks);
        return booksResp;
    }


    @Transactional
    @Secured({"ROLE_ADMIN", "ROLE_CLIENT"})
    @Override
    public ReviewsResponse createReviews(ReviewsRequest request) throws UserException {
        Reviews reviews = reviewsDtoMapper.reviewsRequestToReviews(request);
        Book book = bookRepository.findById(request.getBookId()).get();
        User user = userRepository.findById(request.getUserId()).get();
        reviews.setBook(book);
        reviews.setUser(user);
        boolean isExist = book.getReviews().stream().anyMatch(r -> r.getUser().equals(user));
        if (!isExist) {
            Reviews reviewFromDB = reviewsRepository.save(reviews);
            return reviewsDtoMapper.reviewsToReviewsResponse(reviewFromDB);
        }
        throw new UserException(ErrorCode.REVIEW_ALREADY_EXISTS);
    }

    @Secured("ROLE_ADMIN")
    @Override
    @Transactional
    public BookResponse addAuthor(Integer bookId, AuthorRequest request) {

        Book book = bookRepository.findById(bookId).get();

        if (book.getAuthors() == null) {
            book.setAuthors(new ArrayList<Author>());
        }
        Author author = authorRepository.save(authorDtoMapper.authorRequestToAuthor(request));
        book.getAuthors().add(author);
        bookRepository.save(book);
        return bookDtoMapper.bookToBookResponse(book);
    }

    @Secured({"ROLE_ADMIN", "ROLE_CLIENT"})
    @Transactional
    @Override
    public BookResponse getInfoAboutBook(Integer bookId) {
        Book book = bookRepository.findById(bookId).get();
        return bookDtoMapper.bookToBookResponse(book);
    }

    @Secured("ROLE_CLIENT")
    @Override
    public ReviewsResponse updateReviews(Integer reviewId, ReviewsRequest request) throws UserException {

        User user = userRepository.findById(request.getUserId()).get();
        Reviews reviews = reviewsRepository.findById(reviewId).get();

        User userFromReview = userRepository.findById(reviews.getUser().getUserId()).get();
        if (user.equals(userFromReview)) {
            reviewsDtoMapper.updateReview(reviews, request);
            reviewsRepository.save(reviews);
            return reviewsDtoMapper.reviewsToReviewsResponse(reviews);
        }
        throw new UserException(ErrorCode.INVALID_REVIEW_ID);
    }

    @Transactional
    @Secured("ROLE_ADMIN")
    @Override
    public BookResponse updateBook(Integer bookId, BookRequest request) {
        
        Book book = bookRepository.findById(bookId).get();
        bookDtoMapper.updateBook(book, request);
        bookRepository.save(book);

        return bookDtoMapper.bookToBookResponse(book);
    }

}
