package cz.cvut.fel.still.app;

//import/ co.elastic.apm.api.CaptureTransaction;
import co.elastic.apm.api.CaptureTransaction;
import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class ConfigurationController {

    @Autowired
    private BuildInfo buildInfo;

    @Autowired
    private SlowService slowService;

    //@CaptureTransaction
    @GetMapping("/whoami")
    public Map<String, String> getWhoAmI(@RequestParam String name) {
        // https://www.baeldung.com/spring-request-param
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        return map; // returned as JSON
    }

    @CaptureTransaction
    @GetMapping("/buildinfo")
    public BuildInfo getBuildInfo() throws InterruptedException {
        Transaction transaction = ElasticApm.currentTransaction();
        log.info("getting build info " + transaction.getId());
        slowService.doWorkAndCaptureSpan();
        return buildInfo;
    }

    //@CaptureTransaction
    @GetMapping("/traced")
    public Map<String, String> traced() throws InterruptedException {
        slowService.doTracedWork();
        HashMap<String, String> map = new HashMap<>();
        map.put("traced", "true");
        return map;
    }
}
