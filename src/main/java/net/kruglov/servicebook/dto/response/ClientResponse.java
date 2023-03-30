package net.kruglov.servicebook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {

    private int userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String numberPhone;
    private String role;
    
}
