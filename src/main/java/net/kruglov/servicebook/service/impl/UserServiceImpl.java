package net.kruglov.servicebook.service.impl;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.kruglov.servicebook.dto.mappers.UserDtoMapper;
import net.kruglov.servicebook.dto.request.AdministrationRequest;
import net.kruglov.servicebook.dto.request.ClientRequest;
import net.kruglov.servicebook.dto.request.LoginRequest;
import net.kruglov.servicebook.dto.response.AdministrationResponse;
import net.kruglov.servicebook.dto.response.ClientResponse;
import net.kruglov.servicebook.dto.response.LoginResponse;
import net.kruglov.servicebook.model.Administration;
import net.kruglov.servicebook.model.Client;
import net.kruglov.servicebook.model.ERole;
import net.kruglov.servicebook.model.Role;
import net.kruglov.servicebook.model.User;
import net.kruglov.servicebook.repository.AdministrationRepository;
import net.kruglov.servicebook.repository.ClientRepository;
import net.kruglov.servicebook.repository.RoleRepository;
import net.kruglov.servicebook.repository.UserRepository;
import net.kruglov.servicebook.security.jwt.JwtTokenProvider;
import net.kruglov.servicebook.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private AuthenticationManager authenticationManager;
    private RoleRepository roleRepository;
    private ClientRepository clientRepository;
    private AdministrationRepository adminRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    private UserDtoMapper userDtoMapper = Mappers.getMapper(UserDtoMapper.class);

    public UserServiceImpl(AuthenticationManager authenticationManager, RoleRepository roleRepository,
            ClientRepository clientRepository, AdministrationRepository adminRepository, UserRepository userRepository,
            PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.clientRepository = clientRepository;
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public ClientResponse registerClient(@Valid ClientRequest request) {
        Client client = userDtoMapper.clientRequestToClient(request);

        Role role =  roleRepository.findByName(ERole.CLIENT);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setRole(role);
        client.setUserId(userRepository.save(client).getUserId());

        return userDtoMapper.clientToClientResponse(clientRepository.save(client));
    }

    @Override
    public AdministrationResponse registerAdmin(AdministrationRequest request) {
        Administration administration = userDtoMapper.adminRequestToAdministration(request);

        Role role = roleRepository.findByName(ERole.ADMIN);
        administration.setPassword(passwordEncoder.encode(administration.getPassword()));
        administration.setRole(role);
        administration.setUserId(userRepository.save(administration).getUserId());

        return userDtoMapper.administrationToAdminResponse(adminRepository.save(administration));
    }

    @Override
    public LoginResponse login(LoginRequest login) {
        LoginResponse response = null;
        String token;
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getLogin(), login.getPassword()));
        if (auth.isAuthenticated()) {
            token = jwtTokenProvider.createToken(auth);
            User user = userRepository.findByLogin(login.getLogin());
            response =  userDtoMapper.userToLoginResponse(user, token, user.getUserId());
        }
        return response;
    }

    @Override
    public ClientResponse getInfoAboutClient(String login) {
        Client client = (Client) userRepository.findByLogin(login);
        return userDtoMapper.clientToClientResponse(client);
    }
    
}
