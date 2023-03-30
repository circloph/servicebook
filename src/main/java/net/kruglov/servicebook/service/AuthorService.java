package net.kruglov.servicebook.service;

import net.kruglov.servicebook.dto.request.AuthorRequest;
import net.kruglov.servicebook.dto.request.BookRequest;
import net.kruglov.servicebook.dto.response.AuthorResponse;

public interface AuthorService {

    AuthorResponse createAuthor(AuthorRequest author);

    AuthorResponse addBook(Integer authorId, BookRequest request);

    AuthorResponse getInfoAboutAuthor(Integer authorId);

    AuthorResponse updateAuthor(Integer authorId, AuthorRequest request);

}
