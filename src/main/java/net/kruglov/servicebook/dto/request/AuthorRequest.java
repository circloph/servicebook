package net.kruglov.servicebook.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    private String shortBiography;
    
}
