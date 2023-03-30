package net.kruglov.servicebook.dto.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import net.kruglov.servicebook.dto.request.AuthorRequest;
import net.kruglov.servicebook.dto.response.AuthorResponse;
import net.kruglov.servicebook.model.Author;

@Component
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AuthorDtoMapper {

    AuthorDtoMapper INSTANCE = Mappers.getMapper( AuthorDtoMapper.class );

    Author authorRequestToAuthor(AuthorRequest request);

    AuthorResponse authorToAuthorResponse(Author author);

    void updateAuthor(@MappingTarget Author author, AuthorRequest request);
    
}
