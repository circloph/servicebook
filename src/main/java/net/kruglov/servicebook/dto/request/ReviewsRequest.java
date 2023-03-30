package net.kruglov.servicebook.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsRequest {
    
    private String text;
    private Integer mark;
    private Integer bookId;
    private Integer userId;
}