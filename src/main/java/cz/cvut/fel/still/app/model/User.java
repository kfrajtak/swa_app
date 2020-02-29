package cz.cvut.fel.still.app.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

@EqualsAndHashCode
@Data
@Builder
public class User {

    private int id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @Size(min = 10, max = 200, message = "About Me must be between 10 and 200 characters")
    private String aboutMe;

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;

    @Email(message = "Email should be valid")
    @NotNull
    private String email;
}
