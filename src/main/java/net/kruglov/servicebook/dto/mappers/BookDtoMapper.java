package net.kruglov.servicebook.dto.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import net.kruglov.servicebook.dto.request.BookRequest;
import net.kruglov.servicebook.dto.response.BookResponse;
import net.kruglov.servicebook.dto.response.ReviewsResponse;
import net.kruglov.servicebook.model.Book;
import net.kruglov.servicebook.model.Reviews;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface BookDtoMapper {

    Book bookRequestToBook(BookRequest request);

    @Mapping(target = "authors", source = "authors")
    @Mapping(target = "averageMark", source = "reviews" , qualifiedByName = "averageMarkFromReviews")
    @Mapping(target = "amountOfReviews", source = "reviews", qualifiedByName = "amountOfReviews")
    // @Mapping(target = "reviews", source = "reviews")
    // @Mapping(target = "reviews.bookId", source = "id")
    BookResponse bookToBookResponse(Book book);

    List<BookResponse> bookListToBooksResponseList(List<Book> books);

   @Named("averageMarkFromReviews")
   default Double averageMarkFromReviews(List<Reviews> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return (double) 0;
        }
        return reviews.stream().collect(Collectors.averagingInt(r -> r.getMark()));
   }

   @Named("amountOfReviews")
   default Integer amountOfReviews(List<Reviews> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return 0;
        }
        return reviews.size();
   }

   void updateBook(@MappingTarget Book book, BookRequest request);

   default ReviewsResponse reviewsToReviewsResponse(Reviews reviews) {
    if ( reviews == null ) {
        return null;
    }
    ReviewsResponse reviewsResponse = new ReviewsResponse();

    reviewsResponse.setId( reviews.getId() );
    reviewsResponse.setText( reviews.getText() );
    reviewsResponse.setMark( reviews.getMark() );
    reviewsResponse.setBookId( reviews.getBook().getId() );
    reviewsResponse.setUserId( reviews.getUser().getUserId() );

    return reviewsResponse;
}
  
}
