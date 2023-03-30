package net.kruglov.servicebook.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import net.kruglov.servicebook.dto.request.AdministrationRequest;
import net.kruglov.servicebook.dto.request.ClientRequest;
import net.kruglov.servicebook.dto.response.AdministrationResponse;
import net.kruglov.servicebook.dto.response.ClientResponse;
import net.kruglov.servicebook.dto.response.LoginResponse;
import net.kruglov.servicebook.model.Administration;
import net.kruglov.servicebook.model.Client;
import net.kruglov.servicebook.model.User;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserDtoMapper {

    UserDtoMapper INSTANCE = Mappers.getMapper( UserDtoMapper.class );

    Administration adminRequestToAdministration(AdministrationRequest request);

    @Mapping(target = "role", expression = "java(administration.getRole().getERole().getName())")
    AdministrationResponse administrationToAdminResponse(Administration administration);

    Client clientRequestToClient(ClientRequest request);

    @Mapping(target = "role", expression = "java(client.getRole().getERole().getName())")
    ClientResponse clientToClientResponse(Client client);

    @Mapping(target = "token", expression = "java(token)")
    @Mapping(target = "role", expression = "java(user.getRole().getERole().getName())")
    @Mapping(target = "userId", expression = "java(userId)")
    LoginResponse userToLoginResponse(User user, String token, Integer userId);
    
}
