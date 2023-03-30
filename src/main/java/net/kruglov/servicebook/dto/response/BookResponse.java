package net.kruglov.servicebook.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private int id;
    private String title;
    private String genre;
    private String description;
    private int yearOfProduction;
    private double averageMark;
    private int amountOfReviews; 
    private List<AuthorResponse> authors;
    private List<ReviewsResponse> reviews;
    private int amountOfBooks;
    
}
