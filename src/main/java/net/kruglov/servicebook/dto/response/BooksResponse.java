package net.kruglov.servicebook.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksResponse {

    private List<BookResponse> books;
    private int amountOfBooks;
    
}
