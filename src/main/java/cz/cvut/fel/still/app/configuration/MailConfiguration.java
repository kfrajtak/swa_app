package cz.cvut.fel.still.app.configuration;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Email;

@ConfigurationProperties(prefix = "mail")
@Validated
@ConstructorBinding
@AllArgsConstructor
@ToString
public class MailConfiguration {
    @NotBlank
    @Getter
    @Setter
    private String hostName;

    @Min(1025)
    @Max(65536)
    @Getter
    @Setter
    private int port;

    @Email
    @Getter
    @Setter
    @NotBlank
    private String from;
}
