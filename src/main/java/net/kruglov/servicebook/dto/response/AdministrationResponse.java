package net.kruglov.servicebook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministrationResponse {

    private int userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String position;
    private String role;
    
}
