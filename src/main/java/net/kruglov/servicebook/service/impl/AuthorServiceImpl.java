package net.kruglov.servicebook.service.impl;

import java.util.ArrayList;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import net.kruglov.servicebook.dto.mappers.AuthorDtoMapper;
import net.kruglov.servicebook.dto.mappers.BookDtoMapper;
import net.kruglov.servicebook.dto.request.AuthorRequest;
import net.kruglov.servicebook.dto.request.BookRequest;
import net.kruglov.servicebook.dto.response.AuthorResponse;
import net.kruglov.servicebook.model.Author;
import net.kruglov.servicebook.model.Book;
import net.kruglov.servicebook.repository.AuthorRepository;
import net.kruglov.servicebook.repository.BookRepository;
import net.kruglov.servicebook.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookDtoMapper bookDtoMapper = Mappers.getMapper(BookDtoMapper.class);

    @Autowired
    private AuthorDtoMapper authorDtoMapper = Mappers.getMapper(AuthorDtoMapper.class);

    @Secured("ROLE_ADMIN")
    @Override
    public AuthorResponse createAuthor(AuthorRequest request) {
        Author author = AuthorDtoMapper.INSTANCE.authorRequestToAuthor(request);
        return AuthorDtoMapper.INSTANCE.authorToAuthorResponse(repository.save(author));
    }

    @Secured("ROLE_ADMIN")
    @Override
    public AuthorResponse addBook(Integer authorId, BookRequest request) {
        Author author = repository.findById(authorId).get();
        if (author.getBooks() == null) {
            author.setBooks(new ArrayList<Book>());
        }
        Book book = bookRepository.save(bookDtoMapper.bookRequestToBook(request));
        author.getBooks().add(book);
        repository.save(author);
        return authorDtoMapper.authorToAuthorResponse(author);
    }

    @Secured({"ROLE_ADMIN", "ROLE_CLIENT"})
    @Override
    public AuthorResponse getInfoAboutAuthor(Integer authorId) {
        Author author = repository.findById(authorId).get();
        
        return authorDtoMapper.authorToAuthorResponse(author);
    }

    @Secured("ROLE_ADMIN")
    @Override
    public AuthorResponse updateAuthor(Integer authorId, AuthorRequest request) {
        Author author = repository.findById(authorId).get();
        authorDtoMapper.updateAuthor(author, request);
        repository.save(author);
        return authorDtoMapper.authorToAuthorResponse(author);
    }
}
