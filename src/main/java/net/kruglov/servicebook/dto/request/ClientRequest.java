package net.kruglov.servicebook.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

    @NotBlank
    private String firstName;
    private String middleName;
    private String lastName;
    @Size(min = 4, max = 10, message = "login must be between 4 and 10 characters")
    private String login;
    @Size(min = 6, message = "password must be more than 6 characters")
    private String password;
    @Valid
    @Pattern(message = "invalid number phone value", regexp = "^(\\+7|8)\\-?\\d{3}\\-?\\d{3}\\-?\\d{2}\\-?\\d{2}$")
    private String numberPhone;
    
}
