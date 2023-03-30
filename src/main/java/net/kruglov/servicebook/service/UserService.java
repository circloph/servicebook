package net.kruglov.servicebook.service;

import org.springframework.stereotype.Component;

import net.kruglov.servicebook.dto.request.AdministrationRequest;
import net.kruglov.servicebook.dto.request.ClientRequest;
import net.kruglov.servicebook.dto.request.LoginRequest;
import net.kruglov.servicebook.dto.response.AdministrationResponse;
import net.kruglov.servicebook.dto.response.ClientResponse;
import net.kruglov.servicebook.dto.response.LoginResponse;

@Component
public interface UserService {

    LoginResponse login(LoginRequest login);

    ClientResponse registerClient(ClientRequest request); 

    AdministrationResponse registerAdmin(AdministrationRequest request);

    ClientResponse getInfoAboutClient(String login);
}
