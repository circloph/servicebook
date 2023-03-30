package net.kruglov.servicebook.dto.request;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    
    private String title;
    private String genre;
    private String description;
    @Pattern(regexp = "^\\d{4}$", message = "year of production must contains 4 digits")
    private int yearOfProduction;
    
}