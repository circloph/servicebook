package net.kruglov.servicebook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {

    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String shortBiography;
    
}
