package net.kruglov.servicebook.dto.request;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdministrationRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    @Size(min = 4, max = 10, message = "login must be between 4 and 10 characters")
    private String login;
    @Size(min = 6, message = "password must be more than 6 characters")
    private String password;
    private String position;
    
}
