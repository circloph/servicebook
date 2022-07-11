package net.thumbtack.servicebook.service;

import org.springframework.stereotype.Component;

import net.thumbtack.servicebook.dto.request.AdministrationRequest;
import net.thumbtack.servicebook.dto.request.ClientRequest;
import net.thumbtack.servicebook.dto.request.LoginRequest;
import net.thumbtack.servicebook.dto.response.AdministrationResponse;
import net.thumbtack.servicebook.dto.response.ClientResponse;
import net.thumbtack.servicebook.dto.response.LoginResponse;

@Component
public interface UserService {

    LoginResponse login(LoginRequest login);

    ClientResponse registerClient(ClientRequest request); 

    AdministrationResponse registerAdmin(AdministrationRequest request);

    ClientResponse getInfoAboutClient(String login);
}
