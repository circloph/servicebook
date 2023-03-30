package net.kruglov.servicebook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsResponse {

    private Integer id;
    private Integer userId;
    private Integer bookId;
    private String text;
    private Integer mark;
    
}
