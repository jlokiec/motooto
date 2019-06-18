package pl.motooto.webapp.model.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    @NotEmpty
    @Size(max = 32)
    private String username;

    @Email
    @Size(max = 32)
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String passwordRepeat;

    @NotEmpty
    @Size(max = 32)
    private String firstName;

    @NotEmpty
    @Size(max = 32)
    private String lastName;
}
