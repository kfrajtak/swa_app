package cz.cvut.fel.still.app.configuration;

import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ConfigurationProperties(prefix = "mail")
@Validated
@ConstructorBinding
@ToString
public class MailConfiguration {
    @NotBlank
    @Getter
    private String hostName;

    @Min(1025)
    @Max(65536)
    @Getter
    private int port;

    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
    @Getter
    @NotBlank
    private String from;

    public MailConfiguration(String hostName, int port, String from) {
        this.hostName = hostName;
        this.port = port;
        this.from = from;
    }
}
