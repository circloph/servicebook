package net.kruglov.servicebook.dto.mappers;

import java.util.List;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import net.kruglov.servicebook.dto.request.ReviewsRequest;
import net.kruglov.servicebook.dto.response.ReviewsResponse;
import net.kruglov.servicebook.model.Reviews;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ReviewsDtoMapper {

    ReviewsDtoMapper INSTANCE = Mappers.getMapper( ReviewsDtoMapper.class );
    
    // @Mapping(target = )
    Reviews reviewsRequestToReviews(ReviewsRequest request);

    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "bookId", expression = "java(reviews.getBook().getId())")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "mark", source = "mark")
    ReviewsResponse reviewsToReviewsResponse(Reviews reviews);

    List<ReviewsResponse> listToList(List<Reviews> reviews);

    void updateReview(@MappingTarget Reviews reviews, ReviewsRequest request);

}
