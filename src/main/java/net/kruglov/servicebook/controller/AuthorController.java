package net.kruglov.servicebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import net.kruglov.servicebook.dto.request.AuthorRequest;
import net.kruglov.servicebook.dto.request.BookRequest;
import net.kruglov.servicebook.dto.response.AuthorResponse;
import net.kruglov.servicebook.service.AuthorService;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @MutationMapping
    public AuthorResponse createAuthor(@Argument AuthorRequest author) {
        return authorService.createAuthor(author);
    }
    
    @MutationMapping
    public AuthorResponse addBook(@Argument Integer authorId, @Argument BookRequest book) {
        return authorService.addBook(authorId, book);
    }
    
    @MutationMapping
    public AuthorResponse updateAuthor(@Argument Integer authorId,@Argument AuthorRequest author) {
        return authorService.updateAuthor(authorId, author);
    }

    @QueryMapping
    public AuthorResponse getInfoAboutAuthor(@Argument Integer authorId) {
        return authorService.getInfoAboutAuthor(authorId);
    }  

}
