package cz.cvut.fel.still.app;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

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

    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
    @Getter
    @Setter
    @NotBlank
    private String from;
}
