package cz.cvut.fel.still.app.services;

import co.elastic.apm.api.CaptureSpan;
import co.elastic.apm.api.Traced;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class SlowService {

    private final NodeJsService nodeJsService;

    @Autowired
    private BuildInfo buildInfo;

    public SlowService(NodeJsService jsonConsumerService) {
        this.nodeJsService = jsonConsumerService;
    }

    @CaptureSpan("otherOperations")
    public void doWorkAndCaptureSpan() throws InterruptedException {
        Thread.sleep(1500L);
        log.info("Doing some work ...");

        log.info("I'm in the original span");

        Thread.sleep(1000L);
        log.info("I'm in the new span doing some cool work that needs its own span");

        //log.info("Message from REST service (1) = '{}'", jsonConsumerService.getMessageWebClient());
        log.info("Message from REST service (1) = '{}'", nodeJsService.getMessageRestTemplate());

        //log.info("I'm in the original span");
        doWork();
    }

    @Async
    public CompletableFuture<BuildInfo> doWorkAsync() throws InterruptedException {
        doWorkAndCaptureSpan();
        return CompletableFuture.completedFuture(buildInfo);
    }

    @CaptureSpan("another")
    public void doWork() throws InterruptedException {
        Thread.sleep(1500L);
        log.info("(1) Doing some work and captured span ...");

        log.info("(1) I'm in the original span");

        Thread.sleep(300L);
        log.info("(1) I'm in the new span doing some cool work that needs its own span");

        log.info("(1) I'm in the original span");
    }

    @Traced
    public void doTracedWork() throws InterruptedException {
        Thread.sleep(2500L);
        log.info("(2) Doing some work ...");

        log.info("(2) I'm in the original span");

        Thread.sleep(1000L);
        log.info("(2) I'm in the new span doing some cool work that needs its own span");

        log.info("(2) I'm in the original span");
    }
}