package cz.cvut.fel.still.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ConfigurationController {

    @Autowired
    private BuildInfo buildInfo;

    @Autowired
    private SlowService slowService;

    @GetMapping("/buildinfo")
    public BuildInfo getBuildInfo() throws InterruptedException {
        log.info("getting build info");
        slowService.doWork();
        return buildInfo;
        // https://cloud.spring.io/spring-cloud-sleuth/reference/html/
    }
}
