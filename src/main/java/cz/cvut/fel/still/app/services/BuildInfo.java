package cz.cvut.fel.still.app.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:build.properties")
public class BuildInfo {
    @Value("${application-description}")
    @Getter
    private String applicationDescription;

    @Value("${application-version}")
    @Getter
    private String applicationVersion;

    @Value("${application-name}")
    @Getter
    private String applicationName;
}
