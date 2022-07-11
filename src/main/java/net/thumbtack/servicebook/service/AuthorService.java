package net.thumbtack.servicebook.service;

import net.thumbtack.servicebook.dto.request.AuthorRequest;
import net.thumbtack.servicebook.dto.request.BookRequest;
import net.thumbtack.servicebook.dto.response.AuthorResponse;

public interface AuthorService {

    AuthorResponse createAuthor(AuthorRequest author);

    AuthorResponse addBook(Integer authorId, BookRequest request);

    AuthorResponse getInfoAboutAuthor(Integer authorId);

    AuthorResponse updateAuthor(Integer authorId, AuthorRequest request);

}
