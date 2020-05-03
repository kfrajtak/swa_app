package cz.cvut.fel.still.app.controllers;

import co.elastic.apm.api.CaptureTransaction;
import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Transaction;
import cz.cvut.fel.still.app.services.BuildInfo;
import cz.cvut.fel.still.app.services.SlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

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
        Thread.sleep(new Random().nextInt(5) * 100L);
        log.info("getting build info " + transaction.getId());
        slowService.doWorkAndCaptureSpan();
        Thread.sleep(new Random().nextInt(5) * 120L);
        return buildInfo;
    }

    @CaptureTransaction
    @GetMapping("/buildinfoasync")
    public CompletableFuture<BuildInfo> getBuildInfoAsync() throws InterruptedException {
        CompletableFuture<BuildInfo> future = slowService.doWorkAsync();
        // https://www.baeldung.com/java-completablefuture
        // https://howtodoinjava.com/spring-boot2/rest/spring-async-controller-responsebodyemitter/
        // https://spring.io/blog/2012/05/10/spring-mvc-3-2-preview-making-a-controller-method-asynchronous/
        // https://howtodoinjava.com/spring-boot2/rest/enableasync-async-controller/
        /*CompletableFuture<Integer> mult = futureNumber
                .thenApply(s -> s * 10)
                .handle((s, throwable) -> {
                    return throwable == null ? -1 : s;
                });
        log.info("Async result = {}", mult.get());*/
        return future;
    }

    @CaptureTransaction
    @GetMapping("/traced")
    public Map<String, String> traced() throws InterruptedException {
        slowService.doTracedWork();
        HashMap<String, String> map = new HashMap<>();
        map.put("traced", "true");
        return map;
    }
}
