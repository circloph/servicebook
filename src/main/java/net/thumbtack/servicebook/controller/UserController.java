package net.thumbtack.servicebook.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import net.thumbtack.servicebook.dto.request.AdministrationRequest;
import net.thumbtack.servicebook.dto.request.ClientRequest;
import net.thumbtack.servicebook.dto.request.LoginRequest;
import net.thumbtack.servicebook.dto.response.AdministrationResponse;
import net.thumbtack.servicebook.dto.response.ClientResponse;
import net.thumbtack.servicebook.dto.response.LoginResponse;
import net.thumbtack.servicebook.service.UserService;

@Controller
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    @MutationMapping
    public LoginResponse login(@javax.validation.Valid @Argument LoginRequest login) {
        return userService.login(login);
    }
    
    @MutationMapping
    public ClientResponse registerClient( @Valid @Argument ClientRequest client) {
        return userService.registerClient(client);
    }
    
    @MutationMapping
    public AdministrationResponse registerAdministration(@Argument AdministrationRequest administration) {
        return userService.registerAdmin(administration);
    }

    @MutationMapping
    public String test(@Argument("value") String value) {
        return value;
    }

    @QueryMapping
    public ClientResponse getInfoAboutClient(@Argument String login) {
        return userService.getInfoAboutClient(login);
    }
    
}
